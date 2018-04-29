package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModel;
import com.jsen.test.entity.HcModelShare;
import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.mapper.HcModelGreatMapper;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.mapper.HcModelShareMapper;
import com.jsen.test.service.HcModelShareService;
import com.jsen.test.service.ScsWorkService;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.ResponseBase;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.help.Finder;
import com.jsen.test.utils.modelcore.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 */
@Service
public class HcModelShareServiceImpl implements HcModelShareService {
    private static final Logger logger = LoggerFactory.getLogger(HcModelShareServiceImpl.class);

    @Autowired
    HcModelShareMapper hcModelShareMapper;
    @Autowired
    HcModelMapper hcModelMapper;
    @Autowired
    SqlCreatorService sqlCreatorService;
    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;
    @Autowired
    Finder finder;
    @Autowired
    HcModelGreatMapper hcModelGreatMapper;
    @Autowired
    ScsWorkService scsWorkService;

    @Override
    public ResponseBase listAll(Integer id) {
        return ResponseBase.create().code(0).data(hcModelShareMapper.listAllSimple(id)).add("greats", hcModelGreatMapper.findGreatByUid(id));
    }

    @Override
    public ResponseBase save(byte[] modelData, int creator, String name, String intro, Integer type) {
        int has = hcModelShareMapper.countByName(name);
        if (has > 0) {
            return ConstantResponse.FAIL.msg("分享模型存在");
        }
        HcModelShare hcModelShare = new HcModelShare().setModelData(modelData).setCollect(0).setType(type)
                .setCreateTime(new Date()).setLook(0).setCreator(creator).setName(name).setIntro(intro);
        if (hcModelShareMapper.insertOne(hcModelShare) > 0) {
            return ConstantResponse.SUCCESS;
        } else {
            return ConstantResponse.FAIL.data("保存共享模型失败");
        }
    }

    @Override
    public ResponseBase save(int modelId, int creator, String name, String intro, Integer type) {
        HcModel hcModel = hcModelMapper.findModelByModelIdAndCreatorId(modelId, creator);
        if (hcModel != null) {
            return save(hcModel.getModelData(), creator, name, intro, type);
        } else {
            return ConstantResponse.FAIL.msg("模型不存在");
        }
    }

    @Override
    public ResponseBase updateModelBy(String shareModelName, Integer creatorId, Integer modelId) {
        // 查找share model是否存在
        HcModelShare hcModelShare = hcModelShareMapper.findByShareModelNameAndCreatorId(shareModelName, creatorId);
        if (hcModelShare == null) {
            return ConstantResponse.FAIL.msg("分享模型不存在");
        }
        // 查找model是否存在
        HcModel hcModel = hcModelMapper.findModelByModelIdAndCreatorId(modelId, creatorId);
        if(hcModel == null) {
            return ConstantResponse.FAIL.msg("模型不存在");
        }
        // 更新
        hcModelShareMapper.updateById(hcModelShare.setModelData(hcModel.getModelData()));
        return ConstantResponse.SUCCESS;
    }

    @Override
    public ResponseBase deleteShareModelById(Integer id) {
        // 删除所有赞列表的赞 这里不要依靠外键作用
        int greatEff = hcModelGreatMapper.deleteGreatByMId(id);
        int eff = hcModelShareMapper.deleteShareModelById(id);
        if (eff > 0) {
            return ResponseBase.create().code(0).add("eff", eff).add("greatEff", greatEff);
        } else {
            return ConstantResponse.FAIL.msg("删除失败");
        }
    }

    /**
     * id是share model数据表的ID
     *
     * @param id
     * @return
     */
    @Override
    public ResponseBase execModel(Integer id, JSONObject dynamicValues) {
        HcModelShare hcModelShare = hcModelShareMapper.findShareModelById(id);
        if (hcModelShare == null) {
            return ConstantResponse.FAIL.msg("无法获取共享模型数据");
        }
        hcModelShareMapper.updateShareModelCollectLookById(id, hcModelShare.getCollect(), hcModelShare.getLook() + 1);
        String data = new String(hcModelShare.getModelData());
        JSONObject jData;
        try{
            jData = JSON.parseObject(data);
        } catch (Exception e) {
            logger.error("数据解析出错");
            return ConstantResponse.FAIL.msg("数据解析出错");
        }

        List<Node> workFlow = sqlCreatorService.genWorkFlow(jData, dynamicValues);
        JSONObject dynamicAttrs = new JSONObject();
        JSONObject tableFilters = finder.findTableFilters(jData);
        if (tableFilters != null) {
            for (String key: tableFilters.keySet()) {
                JSONArray array = new JSONArray();
                parseDynamicItem(tableFilters.getJSONArray(key), array);
                if (array.size() > 0) {
                    dynamicAttrs.put(key, array);
                }
            }
        }

        if (workFlow == null) {
            return ConstantResponse.FAIL.msg("创建任务树失败");
        }
        if (workFlow.size() == 0) {
            return ConstantResponse.FAIL.msg("没有任务");
        }
        int size = workFlow.size();
        List<String> sqls = Lists.newArrayList();
        for (Node aWorkFlow : workFlow) {
            sqls.add(aWorkFlow.genExecSql());
        }

        return ResponseBase.create().code(0).data(scsWorkService.exec(sqls, workFlow.get(size-1).genListViewTableSql()))
                .add("dynamicParams", dynamicAttrs);
    }

    // 查找所有动态属性
    private void parseDynamicItem(JSONArray tableFilter, JSONArray resultSet) {
        if(tableFilter == null) {
            return;
        }
        for (int i = 0; i < tableFilter.size(); i++) {
            JSONObject obj = tableFilter.getJSONObject(i);
            if (obj.getBoolean(ConfKeys.isGroup)) {
                parseDynamicItem(obj.getJSONArray(ConfKeys._left), resultSet);
                parseDynamicItem(obj.getJSONArray(ConfKeys._right), resultSet);
            } else {
                if (obj.getBoolean(ConfKeys.isDynamicValue)) {

                    JSONObject item = new JSONObject();
                    item.put(ConfKeys.dynamicName, obj.getString(ConfKeys.dynamicName));
                    item.put(ConfKeys.func, obj.getString(ConfKeys.func));
                    item.put(ConfKeys.value, obj.getString(ConfKeys.right));
                    resultSet.add(item);

                }
            }
        }
    }
}

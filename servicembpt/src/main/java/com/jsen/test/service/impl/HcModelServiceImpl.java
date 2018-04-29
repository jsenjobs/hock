package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModel;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.mapper.HcModelShareMapper;
import com.jsen.test.service.HcModelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jsen
 * @since 2018-04-16
 */
@Service
public class HcModelServiceImpl extends ServiceImpl<HcModelMapper, HcModel> implements HcModelService {


    @Autowired
    HcModelShareMapper hcModelShareMapper;

    @Override
    public ResponseBase create(String name, String intro, String modelData, int creator) {
        if (baseMapper.countByName(name) > 0) {
            return ResponseBase.create().code(0).msg("模型存在， 请重命名文件");
        }
        HcModel hcModel = new HcModel().setCreator(creator).setIntro(intro).setName(name).setModelData(modelData.getBytes());
        int eff = baseMapper.saveModel(hcModel);
        hcModel = baseMapper.findModelByName(name);
        if (eff > 0 && hcModel != null) {
            return ResponseBase.create().code(0).add("eff", eff).data(hcModel);
        } else {
            return ConstantResponse.FAIL.msg("保存模型失败");
        }
    }

    @Override
    public ResponseBase updateById(int modelId, String name, String intro, String modelData) {
        HcModel hcModel = new HcModel();
        if(!StringUtils.isEmpty(name)) {
            hcModel.setName(name);
        }
        if(!StringUtils.isEmpty(intro)) {
            hcModel.setIntro(intro);
        }
        if(!StringUtils.isEmpty(modelData)) {
            hcModel.setModelData(modelData.getBytes());
        }
        hcModel.setId(modelId);
        return ResponseBase.create().code(0).add("eff", baseMapper.updateModel(hcModel));
    }

    @Override
    public ResponseBase clearAllDataByUserId(int uId) {
        int eff = baseMapper.clearAllDataByUserId(uId);
        return ResponseBase.create().code(0).add("eff", eff);
    }

    @Override
    public ResponseBase findByUserId(int userId) {
        List<JSONObject> list = baseMapper.findModelByUserId(userId).stream().map((item) -> {
            JSONObject obj = new JSONObject();
            obj.put("id", item.getId());
            obj.put("name", item.getName());
            obj.put("intro", item.getIntro());
            obj.put("modelData", new String(item.getModelData()));
            obj.put("creator", item.getCreator());
            obj.put("type", item.getType());
            return obj;
        }).collect(Collectors.toList());
        return ResponseBase.create().code(0).data(list);
    }

    @Override
    public ResponseBase updateModelNameByName(String oldModelName, String newModelName) {
        int eff1 = baseMapper.updateModelNameByName(oldModelName, newModelName);
        int eff2 = hcModelShareMapper.updateShareModelNameByName(oldModelName, newModelName);
        return ResponseBase.create().code(0).add("effModel", eff1).add("effShareModel", eff2);
    }
}

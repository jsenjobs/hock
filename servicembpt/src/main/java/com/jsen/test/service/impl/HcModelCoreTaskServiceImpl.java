package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.HcModel;
import com.jsen.test.mapper.DynamicCoreMapper;
import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.service.HcModelCoreTaskService;
import com.jsen.test.service.ScsWorkService;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.ResponseBase;
import com.jsen.test.utils.modelcore.help.Finder;
import com.jsen.test.utils.modelcore.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/20
 */
@Service
public class HcModelCoreTaskServiceImpl implements HcModelCoreTaskService {

    public enum MODEL_TYPE {
        DataSource, Calc, Union
    }

    private static final Logger logger = LoggerFactory.getLogger(HcModelCoreTaskServiceImpl.class);

    @Autowired
    HcModelMapper hcModelMapper;

    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;

    @Autowired
    DynamicCoreMapper dynamicCoreMapper;

    @Autowired
    Finder finder;

    @Autowired
    SqlCreatorService sqlCreatorService;

    @Autowired
    ScsWorkService scsWorkService;


    @Override
    public ResponseBase execPart(Integer id, String uuid) {
        List<Node> workFlow = sqlCreatorService.getWorkFlow(id, uuid);
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
        return ConstantResponse.SUCCESS.data(scsWorkService.exec(sqls, workFlow.get(size-1).genListViewTableSql()));
    }



    /**
     * modelId是hc_model数据表的ID
     * 执行整个模型
     *
     * @param modelId
     * @return
     */
    @Override
    public ResponseBase execModel(Integer modelId) {
        HcModel hcModel = hcModelMapper.findModelDataById(modelId);
        if (hcModel == null) {
            return ConstantResponse.FAIL.msg("无法获取模型数据");
        }
        String data = new String(hcModel.getModelData());
        JSONObject jData;
        try{
            jData = JSON.parseObject(data);
        } catch (Exception e) {
            logger.error("数据解析出错");
            return ConstantResponse.FAIL.msg("数据解析出错");
        }

        List<Node> workFlow = sqlCreatorService.genWorkFlow(jData, new JSONObject());


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

        return ConstantResponse.SUCCESS.data(scsWorkService.exec(sqls, workFlow.get(size-1).genListViewTableSql()));
    }
}

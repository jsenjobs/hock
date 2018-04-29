package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsen.test.entity.HcModel;
import com.jsen.test.mapper.HcModelMapper;
import com.jsen.test.service.SqlCreatorService;
import com.jsen.test.utils.modelcore.TaskBuild;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.help.Finder;
import com.jsen.test.utils.modelcore.model.Node;
import lombok.Data;
import lombok.experimental.Accessors;
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
 * @since 2018/4/23
 */
@Service
public class SqlCreatorServiceImpl implements SqlCreatorService {
    @Data
    @Accessors(chain = true)
    public static class SimpleResult {
        private int code;
        private String data;
    }

    private static final SimpleResult faild;
    private static final SimpleResult succeed;
    static {
        faild = new SimpleResult().setCode(1);
        succeed = new SimpleResult().setCode(0);
    }

    private static final Logger logger = LoggerFactory.getLogger(SqlCreatorServiceImpl.class);
    @Autowired
    HcModelMapper hcModelMapper;
    @Autowired
    Finder finder;
    @Autowired
    TaskBuild taskBuild;
    @Override
    public SimpleResult genSQL(Integer currentModelId, String uuid) {
        HcModel hcModel = hcModelMapper.findModelDataById(currentModelId);
        // Preconditions.checkNotNull()
        if (hcModel == null) {
            logger.error("该模型不存在");
            return faild.setData("该模型不存在");
        }
        String data = new String(hcModel.getModelData());
        JSONObject jData;
        try{
            jData = JSON.parseObject(data);
        } catch (Exception e) {
            logger.error("数据解析出错");
            return faild.setData("数据解析出错");
        }
        Node node = taskBuild.buildTree(jData, uuid, new JSONObject());

        if (node == null) {
            logger.error("创建任务树出错");
            return faild.setData("创建任务树出错");
        }
        node.shrink();
        node.genDynamicAttrs();
        System.out.println(node);
        return succeed.setData(node.toString());
    }
    @Override
    public List<Node> getWorkFlow(Integer currentModelId, String uuid) {
        HcModel hcModel = hcModelMapper.findModelDataById(currentModelId);
        // Preconditions.checkNotNull()
        if (hcModel == null) {
            logger.error("该模型不存在");
            return null;
        }
        String data = new String(hcModel.getModelData());
        JSONObject jData;
        try{
            jData = JSON.parseObject(data);
        } catch (Exception e) {
            logger.error("数据解析出错");
            return null;
        }
        Node node = taskBuild.buildTree(jData, uuid, new JSONObject());

        if (node == null) {
            logger.error("创建任务树出错");
            return null;
        }
        node.shrink();
        node.genDynamicAttrs();
        List<Node> workFlow = Lists.newArrayList();
        node.dumpSqlWorkFlow(workFlow);
        return workFlow;
    }

    /**
     * 执行整个模型，搜索唯一的输出节点，建立任务树
     *
     * @param allConf
     * @return
     */
    @Override
    public List<Node> genWorkFlow(JSONObject allConf, JSONObject dynamicValues) {
        JSONArray datas = finder.findDatas(allConf);
        JSONArray unionDatas = finder.findUnionDatas(allConf);
        JSONArray links = finder.findLinks(allConf);
        if (datas == null || datas.size() == 0) {
            return null;
        }
        JSONObject model = datas.getJSONObject(0);
        JSONObject current = model;
        while (true) {
            current = finder.findChild(datas, unionDatas, links, current.getString(ConfKeys.id));
            if(current != null) {
                model = current;
                continue;
            }
            break;
        }


        Node node = taskBuild.buildTree(allConf, model.getString(ConfKeys.id), dynamicValues);

        if (node == null) {
            logger.error("创建任务树出错");
            return null;
        }
        node.shrink();
        node.genDynamicAttrs();
        List<Node> workFlow = Lists.newArrayList();
        node.dumpSqlWorkFlow(workFlow);
        return workFlow;
    }
}

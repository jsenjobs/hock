package com.jsen.test.utils.modelcore;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jsen.test.utils.modelcore.common.ConfKeys;
import com.jsen.test.utils.modelcore.common.ModelType;
import com.jsen.test.utils.modelcore.help.Finder;
import com.jsen.test.utils.modelcore.model.Node;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/23
 *
 * 根据模型信息和要执行的模型ID，创建Task Tree
 */
@Service
public class TaskBuild {


    private static final Logger logger = LoggerFactory.getLogger(TaskBuild.class);
    @Autowired
    Finder finder;

    public Node buildTree(JSONObject modelData, String uuid, JSONObject dynamicValues) {
        JSONArray datas = finder.findDatas(modelData);
        JSONArray unionDatas = finder.findUnionDatas(modelData);
        JSONArray links = finder.findLinks(modelData);
        JSONObject tableFilters = finder.findTableFilters(modelData);

        JSONObject modelConf = finder.findModel(datas, unionDatas, uuid);
        if (modelConf == null) {
            return null;
        }
        if (ModelType.Union.toString().equals(modelConf.getString(ConfKeys.type))) {
            // find child
            modelConf = finder.findChild(datas, unionDatas, links, modelConf.getString(ConfKeys.id));
        }
        if (modelConf == null) {
            return null;
        }
        Node node = new Node();
        node.parseSelf(finder, tableFilters, datas, unionDatas, links, modelConf, dynamicValues);
        if(node.isBad()) {
            return null;
        }
        return node;
    }

}

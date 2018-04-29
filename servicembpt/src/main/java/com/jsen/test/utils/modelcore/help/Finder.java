package com.jsen.test.utils.modelcore.help;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.jsen.test.utils.modelcore.common.ConfKeys;
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
public class Finder {
    public JSONArray findDatas(JSONObject modelData) {
        if(modelData.containsKey(ConfKeys.datas)) {
            return modelData.getJSONArray(ConfKeys.datas);
        }
        return null;
    }
    public JSONArray findUnionDatas(JSONObject modelData) {
        if(modelData.containsKey(ConfKeys.unionDatas)) {
            return modelData.getJSONArray(ConfKeys.unionDatas);
        }
        return null;
    }
    public JSONArray findLinks(JSONObject modelData) {
        if(modelData.containsKey(ConfKeys.links)) {
            return modelData.getJSONArray(ConfKeys.links);
        }
        return null;
    }
    public JSONObject findTableFilters(JSONObject modelData) {
        if(modelData.containsKey(ConfKeys.TABLE_FILTERS)) {
            return modelData.getJSONObject(ConfKeys.TABLE_FILTERS);
        }
        return null;
    }
    //@ end




    public JSONObject findModelByID(JSONArray datas, String uuid) {
        for (int i = 0; i < datas.size(); i++) {
            JSONObject o = datas.getJSONObject(i);
            if (uuid.equals(o.getString(ConfKeys.id))) {
                return o;
            }
        }
        return null;
    }

    public JSONObject findModel(JSONArray datas, JSONArray unionDatas, String uuid) {
        JSONObject target = null;
        if(datas != null) {
            target = findModelByID(datas, uuid);
        }
        if(target == null && unionDatas != null) {
            target = findModelByID(unionDatas, uuid);
        }
        return target;
    }
    // @end

    /**
     * 根据源节点ID查找连接（查找target ID，即子节点ID）
     * @param links
     * @param uuid
     * @return
     */
    public JSONObject findLinkBySourceId(JSONArray links, String uuid) {
        for (int i = 0; i < links.size(); i++) {
            JSONObject o = links.getJSONObject(i);
            if (uuid.equals(o.getString(ConfKeys.source))) {
                return o;
            }
        }
        return null;
    }
    /**
     * 根据目标节点ID查找连接（查找source ID，即父节点ID）
     * @param links
     * @param uuid
     * @return
     */
    public List<JSONObject> findLinkByTargetId(JSONArray links, String uuid) {
        List<JSONObject> lks = Lists.newArrayList();
        for (int i = 0; i < links.size(); i++) {
            JSONObject o = links.getJSONObject(i);
            if (uuid.equals(o.getString(ConfKeys.target))) {
                lks.add(o);
            }
        }
        if (lks.size() > 0) {
            return lks;
        }
        return null;
    }
    // @end

    public JSONObject findWorkCOnf(JSONObject model) {
        if (model.containsKey(ConfKeys.workConf)) {
            return model.getJSONObject(ConfKeys.workConf);
        }
        return null;
    }

    /**
     * 根据父节点ID 查找子节点数据
     * @param datas
     * @param unionDatas
     * @param links
     * @param uuid
     * @return
     */
    public JSONObject findChild(JSONArray datas, JSONArray unionDatas, JSONArray links, String uuid) {
        JSONObject toChildLink = findLinkBySourceId(links, uuid);
        if (toChildLink == null) {
            return null;
        }
        String targetUUID = toChildLink.getString(ConfKeys.target);
        return findModel(datas, unionDatas, targetUUID);
    }
    /**
     * 根据子节点的ID查找他的直接父节点们
     * @param datas
     * @param unionDatas
     * @param links
     * @param uuid
     * @return
     */
    public List<JSONObject> findParents(JSONArray datas, JSONArray unionDatas, JSONArray links, String uuid) {
        List<JSONObject> toParentLinks = findLinkByTargetId(links, uuid);
        if (toParentLinks == null) {
            return null;
        }
        List<JSONObject> m = Lists.newArrayList();
        String sourceUUID;
        JSONObject parent;
        for (JSONObject parentLink: toParentLinks) {
            sourceUUID = parentLink.getString(ConfKeys.source);
            parent = findModel(datas, unionDatas, sourceUUID);
            if (parent != null) {
                m.add(parent);
            }
        }
        if (m.size() > 0) {
            return m;
        }
        return null;
    }
    // @end

}

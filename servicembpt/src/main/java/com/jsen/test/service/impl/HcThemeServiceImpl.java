package com.jsen.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.jsen.test.entity.HcTheme;
import com.jsen.test.mapper.HcThemeMapper;
import com.jsen.test.service.HcTableService;
import com.jsen.test.service.HcThemeService;
import com.jsen.test.utils.ResponseBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/11
 */
@Service
public class HcThemeServiceImpl extends ServiceImpl<HcThemeMapper, HcTheme> implements HcThemeService {
    @Autowired
    HcTableService hcTableService;

    @Override
    public ResponseBase listAllAsTree(int id) {
        List<HcTheme> themeList = baseMapper.listAll(id);

        JSONArray tree = new JSONArray();
        append(id, tree, -1, themeList);
        return ResponseBase.create().code(0).data(tree);
    }

    @Override
    public ResponseBase listAll(int id) {
        return ResponseBase.create().code(0).data(baseMapper.listAll(id));
    }

    @Override
    public ResponseBase addTopic(String name, String comment) {
        return ResponseBase.create().code(0).add("eff", baseMapper.insertTopic(name, comment));
    }

    @Override
    public ResponseBase addSubTopic(String name, String comment, int parent) {
        if(baseMapper.countById(parent) > 0) {
            return ResponseBase.create().code(0).add("eff", baseMapper.insertSubTopic(name, comment, parent));
        }
        return ResponseBase.create().code(1).msg("parent is noe exist");
    }

    @Override
    public ResponseBase delTopic(Integer parentId) {
        return ResponseBase.create().code(0)
                .add("TBeff", baseMapper.deleteThemeTableByThemeParentId(parentId)) // 删除该主题的所有子主题下的所有表关联
                .add("Teff", baseMapper.deleteTopicByParentId(parentId)) //删除该主题的所有子主题
                .add("eff", baseMapper.deleteTopicById(parentId)); //删除该主题
    }

    @Override
    public ResponseBase delSubTopic(Integer id) {
        return ResponseBase.create().code(0)
                .add("TBeff", baseMapper.deleteThemeTableByThemeId(id)) // 删除该子主题的所有表关联
                .add("Teff", baseMapper.deleteTopicById(id)); // 删除该子主题
    }

    private void append(int userId, JSONArray tree, Integer treeId, List<HcTheme> themes) {
        if (treeId >= 0) {
            for (HcTheme hcTheme:themes) {
                if (treeId.equals(hcTheme.getParent())) {
                    add(userId, tree, hcTheme, themes);
                }
            }
        } else {
            for (HcTheme hcTheme:themes) {
                if (hcTheme.getParent() == null) {
                    add(userId, tree, hcTheme, themes);
                }
            }
        }
    }

    private void add(int userId, JSONArray tree, HcTheme hcTheme, List<HcTheme> themes) {
        JSONObject node = (JSONObject)JSON.toJSON(hcTheme);
        tree.add(node);
        if (hcTheme.getParent() == null) {
            JSONArray cTree = new JSONArray();
            append(userId, cTree, hcTheme.getId(), themes);
            if (!cTree.isEmpty()) {
                node.put("_children", cTree);
            }
        } else {
            int id = hcTheme.getId();
            System.out.println(id);
            System.out.println(userId);
            node.put("_tables", hcTableService.listTablesByTopic(id, userId));
        }

    }
}

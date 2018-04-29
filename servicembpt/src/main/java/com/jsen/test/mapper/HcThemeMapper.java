package com.jsen.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.HcTheme;
import com.jsen.test.entity.Role;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jsen
 * @since 2018-03-21
 */
@Service
public interface HcThemeMapper extends BaseMapper<HcTheme> {

    List<HcTheme> listAll(int id);

    int insertTopic(String name, String comment);
    int insertSubTopic(String name, String comment, int parentId);

    int countById(int id);

    // 根据parent topic的ID删除所有子topic下关联的数据表
    int deleteThemeTableByThemeParentId(Integer id);
    // 根据parentID删除所有相关主题
    int deleteTopicByParentId(Integer id);

    // 根据 sub topic的ID删除所有子topic下关联的数据表
    int deleteThemeTableByThemeId(Integer id);
    // 根据sub topic ID删除相关主题
    int deleteTopicById(Integer id);
}

package com.jsen.test.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jsen.test.entity.HcTable;
import com.jsen.test.entity.HcTheme;
import com.jsen.test.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
public interface HcTableMapper extends BaseMapper<HcTable> {

    List<HcTable> getTableByTopicIdAndUserId(int topicId, int userId);
    HcTable getTableById(int id);

    List<HcTable> listTables();

    int updateTable(HcTable hcTable);

    int deleteById(Integer id);
    int deleteTableRoleByTableId(Integer id);
    int deleteTableRoleByRoleId(Integer id);
    int deleteTableTopicByTableId(Integer id);

    int insertHcTable(HcTable hcTable);

    int insertRoleTable(Integer tableId, Integer roleId);
    int insertTopicTables(@Param("tableIds") List tableIds, @Param("topicId") Integer topicId);

    int findExistRoleTable(Integer tableId, Integer roleId);
    int findExistTopicTable(Integer tableId, Integer topicId);

    List<HcTable> listHcTableByRId(int id);

    int deleteRoleTableByRoleIdAndTableId(int roleId, int tableId);

}

package com.jsen.test.service.impl.scs;

import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.jsen.test.config.dbs.help.DS;
import com.jsen.test.config.dbs.help.DbTypes;
import com.jsen.test.constants.ConstantResponse;
import com.jsen.test.entity.DBMetaTable;
import com.jsen.test.mapper.DBMetaMapper;
import com.jsen.test.mapper.DynamicCoreMapper;
import com.jsen.test.mapper.DynamicPreCoreMapper;
import com.jsen.test.service.ScsWorkService;
import com.jsen.test.utils.ResponseBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/26
 */
@Service
public class ScsWorkServiceImpl implements ScsWorkService {
    private static final Logger logger = LoggerFactory.getLogger(ScsWorkServiceImpl.class);

    @Autowired
    DynamicPreCoreMapper dynamicPreCoreMapper;
    @Autowired
    DynamicCoreMapper dynamicCoreMapper;
    @Autowired
    DBMetaMapper dbMetaMapper;

    @DS(DbTypes.SCS1)
    @Override
    public List exec(List<String> sqls, String sql) {

        for (String s : sqls) {
            System.out.println(s);
            dynamicPreCoreMapper.execSqlReturnView(s);
        }
        return dynamicPreCoreMapper.list(sql);
    }

    @DS(DbTypes.SCS1)
    @Override
    public ResponseBase listColumns(String tableName) {
        return ResponseBase.create().code(0).data(dbMetaMapper.listColumnForTable(tableName));
    }

    @DS(DbTypes.SCS1)
    @Override
    public ResponseBase listTables(String dbName) {
        List<DBMetaTable> dbMetaTables = Lists.newArrayList();
        List l = dbMetaMapper.listTablesForDB(dbName);
        l.forEach(item -> {
            Map m = (Map)item;
            m.forEach((mk, mv) -> {
                dbMetaTables.add(new DBMetaTable((String) mv));
            });
        });
        return ResponseBase.create().code(0).data(dbMetaTables);
    }

    @DS(DbTypes.SCS1)
    @Override
    public ResponseBase listTableData(String tableName, Integer page, Integer limit, String query) {
        if (query != null && query.trim().length() > 0) {
            return ResponseBase.create().code(0).data(dynamicPreCoreMapper.list("select * from " + tableName + " WHERE " + query + " LIMIT " + page + "," + limit));
        } else  {
            return ResponseBase.create().code(0).data(dynamicPreCoreMapper.list("select * from " + tableName + " LIMIT " + page + "," + limit));
        }
    }

    @DS(DbTypes.SCS1)
    @Override
    public ResponseBase delViewTableByName(String name) {
        try {
            dynamicCoreMapper.delViewTableByName(name.trim());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ConstantResponse.FAIL.msg("删除视图操作失败：" + e.getMessage());
        }
        return ConstantResponse.SUCCESS.data(null);
    }

    @DS(DbTypes.SCS1)
    @Override
    public ResponseBase delViewTablesByName(JSONArray nameList) {
        try {
            for (Object viewTableName : nameList) {
                dynamicCoreMapper.delViewTableByName(viewTableName.toString().trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return ConstantResponse.FAIL.msg("删除视图操作失败：" + e.getMessage());
        }
        return ConstantResponse.SUCCESS.data(null);
    }
}

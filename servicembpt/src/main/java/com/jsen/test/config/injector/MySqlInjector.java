package com.jsen.test.config.injector;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;


/**
 * mybatisplus
 */
public class MySqlInjector extends LogicSqlInjector {

    @Override
    public void inject(Configuration configuration, MapperBuilderAssistant builderAssistant, Class<?> mapperClass, Class<?> modelClass, TableInfo table) {
        listAll(mapperClass, modelClass, table);
    }

    private void listAll(Class<?> mapperClass, Class<?> modeClass, TableInfo tableInfo) {
        String sql = "select * from " + tableInfo.getTableName();
        String method = "listAll";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modeClass);
        // Class<?> mapperClass, String id, SqlSource sqlSource, Class<?> resultType,
        //                                                    TableInfo table
        this.addSelectMappedStatement(mapperClass, method, sqlSource, modeClass, tableInfo);
    }
}

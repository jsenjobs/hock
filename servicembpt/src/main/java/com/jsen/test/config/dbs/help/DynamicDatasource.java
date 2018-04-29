package com.jsen.test.config.dbs.help;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
public class DynamicDatasource extends AbstractRoutingDataSource {
    private static final Logger log = LoggerFactory.getLogger(DynamicDatasource.class);

    @Override
    protected Object determineCurrentLookupKey() {
        log.info("数据源为{}", DataSourceContextHolder.getDB());
        return DataSourceContextHolder.getDB();
    }
}

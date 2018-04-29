package com.jsen.test.config.dbs.help;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * </p>
 *
 * @author ${User}
 * @since 2018/4/2
 */
public class DataSourceContextHolder {
    private static final Logger log = LoggerFactory.getLogger(DataSourceContextHolder.class);

    /**
     * 默认数据源
     */
    private static final ThreadLocal<DbTypes> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源名
     */
    public static void setDB(DbTypes dbType) {
        log.info("切换到{}数据源", dbType);
        contextHolder.set(dbType);
    }

    /**
     * 获取数据源名
     */
    public static DbTypes getDB() {
        return (contextHolder.get());
    }

    /**
     * 清除数据源名
     */
    public static void clearDB() {
        contextHolder.remove();
    }
}

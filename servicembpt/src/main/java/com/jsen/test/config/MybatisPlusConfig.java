package com.jsen.test.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.plugins.parser.ISqlParser;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantHandler;
import com.baomidou.mybatisplus.plugins.parser.tenant.TenantSqlParser;
import com.jsen.test.config.injector.MySqlInjector;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@Configuration
// @MapperScan({"com.jsen.test.mapper"})
public class MybatisPlusConfig {
    /*

    @Bean
    @ConfigurationProperties(prefix ="mybatis-plus")
    public GlobalConfiguration globalConfig() {
        return new GlobalConfiguration();
    }

    @ConfigurationProperties(prefix ="datasource1")
    @Bean(name ="datasource1")
    public DruidDataSource dataSource1() {
        return new DruidDataSource();
    }


    @ConfigurationProperties(prefix ="datasource2")
    @Bean(name ="datasource2")
    public DruidDataSource dataSource2() {
        return new DruidDataSource();
    }

    @Bean(name ="datasource3")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier(value ="datasource1")DataSource dataSource1,@Qualifier(value ="datasource2")DataSource dataSource2) {
        DynamicDataSource bean =new DynamicDataSource();
        Map<Object,Object> targetDataSources = Maps.newHashMap();
        targetDataSources.put("datasource1",dataSource1);
        targetDataSources.put("datasource2", dataSource2);
        bean.setTargetDataSources(targetDataSources);
        bean.setDefaultTargetDataSource(dataSource1);
        return bean;
    }



    @Bean("sqlSessionFactory")
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setGlobalConfig(globalConfig());
        return bean;
    }

    @Bean("sqlSessionFactory1")
    @ConfigurationProperties(prefix ="mybatis-plus1")
    public MybatisSqlSessionFactoryBean sqlSessionFactory1(DataSource dataSource, GlobalConfiguration globalConfig) throws IOException {
        MybatisSqlSessionFactoryBean bean = new TestFB();
        bean.setDataSource(dataSource);
        bean.setGlobalConfig(globalConfig);
        return bean;
    }
    */





    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        List<ISqlParser> iSqlParserList = new ArrayList<>();
        TenantSqlParser tenantSqlParser = new TenantSqlParser();
        tenantSqlParser.setTenantHandler(new TenantHandler() {
            @Override
            public Expression getTenantId() {
                return new LongValue(1L);
            }

            @Override
            public String getTenantIdColumn() {
                return "TenantID";
            }

            @Override
            public boolean doTableFilter(String tableName) {
                if (tableName.equals("testtable")) {
                    return false;
                }
                return true;
            }
        });
        iSqlParserList.add(tenantSqlParser);
        paginationInterceptor.setSqlParserList(iSqlParserList);
        return paginationInterceptor;
    }


    @Bean
    // @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    @Bean
    public SqlExplainInterceptor sqlExplainInterceptor() {
        return new SqlExplainInterceptor();
    }

    @Bean
    public MySqlInjector sqlInjector() {
        return new MySqlInjector();
    }
}

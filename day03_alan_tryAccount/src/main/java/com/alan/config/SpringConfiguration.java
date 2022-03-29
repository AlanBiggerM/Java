package com.alan.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 配置类
 */
@Configuration//配置类
@ComponentScan(basePackages = "com.alan")//要扫描的包
public class SpringConfiguration {
    /**
     * 创建一个runner对象
     * @param dataSource
     * @return
     */
    @Bean(name = "runner")//创建runner并加入IOC容器
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }
    /**
     * 创建数据源
     * @return
     */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/db03");
            ds.setUser("root");
            ds.setPassword("xiaoyahuan1997");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}

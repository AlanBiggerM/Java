package com.alan.utils;

import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 连接的工具类，用于从数据源中获取一个连接，并且实现和线程绑定
 */
//@Component("connectionUtils")
public class ConnectionUtils {
    private ThreadLocal<Connection> t1 = new ThreadLocal<Connection>();
    //@Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    //@Bean("connection")
    public Connection getThreadConnection() {
        //1.先从ThreadLocal上获取
        Connection conn = t1.get();
        //2.判断当前线程上是否有连接
        try {
            if(conn == null) {
                //3.从数据源中获取一个连接，并存入ThreadLocal中
                conn = dataSource.getConnection();
                t1.set(conn);
            }
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 把连接和线程解绑
     */
    public void removeConnection() {
        t1.remove();
        //将线程还回线程池
    }

}

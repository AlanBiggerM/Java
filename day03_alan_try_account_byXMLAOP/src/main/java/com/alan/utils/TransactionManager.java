package com.alan.utils;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * 和事务管理相关的工具类，包含了开启事务，提交事务，回滚事务和释放连接
 */
//@Component("transactionManager")
public class TransactionManager {
    //@Autowired
    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /**
     * 开始事务
     */
    public void beginTransaction() {
        try {
            connectionUtils.getThreadConnection().setAutoCommit(false);
            //设置为不自动提交
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 提交事务
     */
    public void commit() {
        try {
            connectionUtils.getThreadConnection().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 回滚事务
     */
    public void rollback() {
        try {
            connectionUtils.getThreadConnection().rollback();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 释放连接
     */
    public void release() {
        try {
            connectionUtils.getThreadConnection().close();
            //把连接还回连接池中
            connectionUtils.removeConnection();
            //将线程还回线程池
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

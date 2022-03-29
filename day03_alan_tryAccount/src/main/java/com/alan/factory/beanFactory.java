package com.alan.factory;

import com.alan.service.IAccountService;
import com.alan.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
@Component("proxyService")
@Aspect//当前类是一个切面类
public class beanFactory {
    @Pointcut("execution(* com.alan.service.impl.AccountServiceImpl.*(..))")
    private void pt1(){};
    @Autowired
    private TransactionManager txManager;
    /**
     * 获取Service的代理对象
     */
    @Around("pt1()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {
            Object[] args = pjp.getArgs();
            txManager.beginTransaction();
            rtValue = pjp.proceed(args);//明确调用切入点方法
            txManager.commit();
            return rtValue;
            //返回代理对象
        } catch (Throwable t) {
            txManager.rollback();
            throw new RuntimeException(t);
        } finally {
            txManager.release();
        }
    }
}

package com.alan.test;

import com.alan.config.SpringConfiguration;
import com.alan.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用junit单元测试
 * 1.导入spring整合junit的jar
 * 2.使用junit提供的注解把原有的main方法替换成spring提供的@Runwith
 * 3.告知spring的运行器，spring和ioc创建是基于xml还是注解的，并说明位置
 *  @ContextConfiguration
 *      locations:指定xml文件的位置，加上classpath关键字，表示在类路径下
 *      classes:指定注解类所在的位置
 */
public class AccountServiceTest {
    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //无bean.xml需要创建AnnotationConfigApplicationContext()对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        IAccountService as = (IAccountService) ac.getBean("accountService");
        as.transfer("aaa", "bbb", 100f);
    }
}

package com.springframework.test2;

import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.springframework.test1.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory 接口
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册UserDao


        // 2. 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        //UserService userService = (UserService) beanFactory.getBean("userService", "shaxiaoman", "girl", 16);
        //UserService userService = (UserService) beanFactory.getBean("userService",16,  "shaxiaoman", "girl");
        userService.queryUserInfo();

        // 4. 再次获取
        userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }




}

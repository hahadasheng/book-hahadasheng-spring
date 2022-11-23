package com.springframework;

import com.springframework.bean.UserService;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.BeanFactory;
import com.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory 接口
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        // 4. 再次获取
        userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}

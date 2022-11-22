package com.springframework;

import com.springframework.bean.UserService;
import org.junit.Test;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory 接口
        BeanFactory beanFactory = new BeanFactory();

        // 2. 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}

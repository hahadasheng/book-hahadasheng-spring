package com.springframework.test2;

import com.springframework.beans.PropertyValue;
import com.springframework.beans.PropertyValues;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.config.BeanReference;
import com.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.springframework.test2.bean.UserService;
import com.springframework.test2.bean.UserDao;

import org.junit.Test;


public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory 接口
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册UserDao bean对象
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        // 3. 定义UserService的填充属性(uId, userDao)
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValues(new PropertyValue("uId", "10001"));
        propertyValues.addPropertyValues(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. 注册UserService Bean对象
        beanFactory.registerBeanDefinition("userService",
                new BeanDefinition(UserService.class, propertyValues));

        // 5. 使用UserService获取Bean对象
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }




}

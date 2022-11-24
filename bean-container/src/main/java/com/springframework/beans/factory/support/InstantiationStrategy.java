package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    /**
     * 实例化方法
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor,
                       Object[] args) throws BeansException;
}

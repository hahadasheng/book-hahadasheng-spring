package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * 使用JDK反射方式进行实例化
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName,
                              Constructor ctor, Object[] args) throws BeansException {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (Objects.isNull(args)) {
                return clazz.getDeclaredConstructor().newInstance();
            }
            return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException
        | NoSuchMethodException e) {
            throw new BeansException(String.format("Failed to instantiate [%s]", clazz.getName()), e);
        }
    }
}

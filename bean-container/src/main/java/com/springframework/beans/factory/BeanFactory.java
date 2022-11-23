package com.springframework.beans.factory;

import com.springframework.beans.BeansException;

/**
 * 普通Bean工厂
 */
public interface BeanFactory {

    /**
     * 返回Bean的实例对象
     * @param beanName bean名称
     * @return bean对象
     * @throws BeansException 不能捕获Bean对象，抛出异常
     */
    Object getBean(String beanName) throws BeansException;
}

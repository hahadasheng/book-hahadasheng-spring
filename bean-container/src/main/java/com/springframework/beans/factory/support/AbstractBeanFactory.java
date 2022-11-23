package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.BeanFactory;
import com.springframework.beans.factory.config.BeanDefinition;

/**
 * 简单bean工厂类，可以获取单例bean
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingleton(beanName);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    /**
     * 获取定义类
     * @param beanName beanName
     * @return BeanDefinition
     * @throws BeansException BeanException
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 创建bean
     * @param beanName beanName
     * @param beanDefinition beanDefinition
     * @return Object
     * @throws BeansException BeanException
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}

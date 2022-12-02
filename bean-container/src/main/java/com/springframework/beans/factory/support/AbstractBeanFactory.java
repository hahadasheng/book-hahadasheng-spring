package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.BeanFactory;
import com.springframework.beans.factory.config.BeanDefinition;

import java.util.Objects;

/**
 * 简单bean工厂类，可以获取单例bean
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanHelper(beanName, null);
    }

    /**
     * 注意：在调用此方法时如果传入类似`getBean(beanName, (Object) null)`形式，
     * args并不是args==null，而是args==Object[]{null}
     * 这个是可变参数最容易出错的地方，因为与常规逻辑不一致！
     * 可变参数接受任何值都会转换为数组，包括只传递一个null
     */
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanHelper(beanName, args);
    }

    /**
     * 根据Class的类型强转类型
     */
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T)getBean(name);
    }

    private Object getBeanHelper(String beanName, Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null){
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        // 同步控制，避免创建多个Bean
        synchronized (beanDefinition.getBeanClass()) {
            bean = getSingleton(beanName);
            if (bean != null){
                return bean;
            }
            if (Objects.isNull(args) || args.length == 0) {
                return createBean(beanName, beanDefinition);
            }
            return createBean(beanName, beanDefinition, args);
        }
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

    /**
     * 创建含有参数的bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}

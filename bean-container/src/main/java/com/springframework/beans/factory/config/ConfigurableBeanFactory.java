package com.springframework.beans.factory.config;

import com.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * 获取BeanPostProcessor、BeanClassLoader等方法的配置接口
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}

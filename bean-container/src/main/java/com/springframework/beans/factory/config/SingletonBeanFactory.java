package com.springframework.beans.factory.config;

/**
 * 单例工厂接口
 */
public interface SingletonBeanFactory {

    /**
     * 获取单例对象
     * @param beanName 对象名称
     * @return 单例对象
     */
    Object getSingleton(String beanName);


    /**
     * 注册单例对象
     * @param beanName 对象名称
     * @param singletonObject 单例对象
     */
    void registerSingleton(String beanName, Object singletonObject);
}

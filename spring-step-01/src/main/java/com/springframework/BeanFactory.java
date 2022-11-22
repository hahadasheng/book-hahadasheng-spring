package com.springframework;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    final private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public Object getBean(String name) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(name);
        if (Objects.isNull(beanDefinition)) {
            return null;
        }
        return beanDefinition.getBean();
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name, beanDefinition);
    }
}

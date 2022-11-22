package com.springframework;

public class BeanDefinition {

    final private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return this.bean;
    }
}
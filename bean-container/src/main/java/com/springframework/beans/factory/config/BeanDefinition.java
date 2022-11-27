package com.springframework.beans.factory.config;

import com.springframework.beans.PropertyValues;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean定义
 */
@Getter
@Setter
public class BeanDefinition {

    /**
     * 对象元数据
     */
    private Class beanClass;

    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

}
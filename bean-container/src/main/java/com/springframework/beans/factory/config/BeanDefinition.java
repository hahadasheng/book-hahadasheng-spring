package com.springframework.beans.factory.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Bean定义
 */
@Getter
@Setter
@AllArgsConstructor
public class BeanDefinition {

    /**
     * 对象元数据
     */
    private Class beanClass;

}
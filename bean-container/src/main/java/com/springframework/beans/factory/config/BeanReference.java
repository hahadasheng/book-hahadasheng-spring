package com.springframework.beans.factory.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}

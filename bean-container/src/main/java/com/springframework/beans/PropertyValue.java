package com.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PropertyValue {

    // name 为bean名称
    private final String name;
    // value可能为基本类型，可能为一个引用对象，如果为引用对象使用BeanReference转换成Bean名称统一处理
    private final Object value;
}

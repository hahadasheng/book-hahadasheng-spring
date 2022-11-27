package com.springframework.beans;

import java.util.ArrayList;
import java.util.List;

public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValues(PropertyValue pv) {
        this.propertyValueList.add(pv);
    }

    public PropertyValue[] getPropertyValues() {
        /* new Object[0] 告诉要拷贝的类型，这里的逻辑会重新创建一个此类型并且长度与
        * 元数组一样的长度的基本数组（并不是可以往长度为0的列表中拷贝数组，这样是非法的）
        * new PropertyValue[0] = new PropertyValue[]{}*/
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName) {
        for (PropertyValue pv : this.propertyValueList) {
            if (pv.getName().equals(propertyName)) {
                return pv;
            }
        }
        return null;
    }
}

package com.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.springframework.beans.BeansException;
import com.springframework.beans.PropertyValue;
import com.springframework.beans.PropertyValues;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.config.BeanReference;

import java.lang.reflect.Constructor;
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanRegistry extends AbstractBeanRegistry {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    final private InstantiationStrategy simpleInstantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return createBean(beanName, beanDefinition, null);
    }

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给bean对象填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException(String.format("Instantiation of bean '%s' failed", beanName), e);
        }
        registerSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName,
                                        Object[] args) {
        Constructor<?> constructorToUse = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        outer:
        if (!Objects.isNull(args) && args.length > 0) {
            for (Constructor<?> ctor : beanClass.getDeclaredConstructors()) {
                if (ctor.getParameterTypes().length == args.length
                        && matchParameterTypes(ctor.getParameterTypes(), args)) {
                    constructorToUse = ctor;
                    break outer;
                }
            }
            // 没有找到匹配的构造参数，需要抛出异常，参数类型详细就不打印了
            throw new BeansException(String.format("Can`t found match Parameter for class [%s]", beanClass.getName()));
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    /**
     * 判断元类参数与传递参数索引对应的类型是否一一对应
     */
    private boolean matchParameterTypes(Class<?>[] parameterTypes, Object[] args) {
        assert parameterTypes.length == args.length;
        for (int i = 0; i < args.length; i++) {
            if (parameterTypes[i] != args[i].getClass()) {
                return false;
            }
        }
        return true;
    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    /**
     * todo bean对象属性填充
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReference) {
                    // 例如，A 依赖B，获取B的数理化对象
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
                // 属性填充，使用 hutool-all 工具类（小而全）
                BeanUtil.setFieldValue(bean, name, value);
            }

        } catch (Exception e) {
            throw new BeansException("Error setting property values: " + beanName);
        }
    }
}

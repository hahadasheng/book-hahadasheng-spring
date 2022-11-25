package com.springframework.beans.factory.support;

import com.springframework.beans.BeansException;
import com.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.util.Objects;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    final private InstantiationStrategy cglibInstantiationStrategy = new CglibSubclassingInstantiationStrategy();

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
        return cglibInstantiationStrategy.instantiate(beanDefinition, beanName, constructorToUse, args);
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

}

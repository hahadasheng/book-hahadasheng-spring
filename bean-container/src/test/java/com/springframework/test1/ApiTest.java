package com.springframework.test1;

import com.springframework.test1.bean.UserService;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.support.DefaultListableBeanFactory;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1. 初始化 BeanFactory 接口
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 2. 注册Bean对象
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registerBeanDefinition("userService", beanDefinition);

        // 3. 获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        //UserService userService = (UserService) beanFactory.getBean("userService", "shaxiaoman", "girl", 16);
        //UserService userService = (UserService) beanFactory.getBean("userService",16,  "shaxiaoman", "girl");
        userService.queryUserInfo();

        // 4. 再次获取
        userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

    @Test
    public void test_newInstance() throws Exception {
        UserService userService = UserService.class.newInstance();
        System.out.println(userService);
    }

    @Test
    public void test_constructor() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(
                String.class, String.class, Integer.class);
        UserService userService = declaredConstructor.newInstance("shaxiaoman", "girl", 16);
        userService.queryUserInfo();
    }

    @Test
    public void test_parameterTypes() throws Exception {
        Class<UserService> userServiceClass = UserService.class;
        Constructor<?>[] declaredConstructors = userServiceClass.getDeclaredConstructors();
        Constructor<UserService> declaredConstructor = userServiceClass.getDeclaredConstructor(declaredConstructors[1].getParameterTypes());
        UserService userService = declaredConstructor.newInstance(16, "shaxiaoman", "girl");
        userService.queryUserInfo();
    }

    @Test
    public void test_cglib() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserService.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        Object o = enhancer.create(new Class[]{Integer.class, String.class, String.class}, new Object[]{16, "shaxiaoman", "girl"});
        ((UserService) o).queryUserInfo();
    }

}

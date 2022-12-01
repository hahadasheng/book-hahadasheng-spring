package com.springframework.beans.factory.xm;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.springframework.beans.BeansException;
import com.springframework.beans.PropertyValue;
import com.springframework.beans.factory.config.BeanDefinition;
import com.springframework.beans.factory.config.BeanReference;
import com.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.springframework.core.io.Resource;
import com.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            try(InputStream inputStream = resource.getInputStream()) {
                doLoadBeanDefinitions(inputStream);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BeansException("IOException parsing XML document from " + resource, e);
        }
    }

    @Override
    public void loadBeanDefinitions(Resource... resources) throws BeansException {
        for (Resource resource : resources) {
            this.loadBeanDefinitions(resources);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        loadBeanDefinitions(getResourceLoader().getResource(location));
    }

    /**
     * 解析XML核心实现方法
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document doc = XmlUtil.readXML(inputStream);
        Element root = doc.getDocumentElement();
        NodeList childNodes = root.getChildNodes();

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            // 判断元素
            if (!(item instanceof Element)) {
                continue;
            }

            // 判断对象
            if (!"bean".equals(item.getNodeName())) {
                continue;
            }

            // 解析标签
            Element bean = (Element) item;
            String id = bean.getAttribute("id");
            String name = bean.getAttribute("name");
            String className = bean.getAttribute("class");
            // 获取class,方便获取类中的名称
            Class<?> clazz = Class.forName(className);
            // 优先级 id>name
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)) {
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            // 定义bean对象
            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            // 读取属性并填充
            NodeList propertiesLike = bean.getChildNodes();
            for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                if (!(propertiesLike.item(j) instanceof Element)) {
                    continue;
                }
                if (!"property".equals(propertiesLike.item(j).getNodeName())) {
                    continue;
                }
                // 解析标签property
                Element property = (Element) propertiesLike.item(j);
                String attrName = property.getAttribute("name");
                String attrValue = property.getAttribute("value");
                String attrRef = property.getAttribute("ref");

                // 获取属性值；引入对象、值对象
                Object value = StrUtil.isNotEmpty(attrRef) ? new BeanReference(attrRef) : attrValue;

                // 创建属性信息
                PropertyValue propertyValue = new PropertyValue(attrName, value);
                beanDefinition.getPropertyValues().addPropertyValues(propertyValue);

                if (getRegistry().containsBeanDefinition(beanName)) {
                    throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                }

                // 注册 BeanDefinition
                getRegistry().registerBeanDefinition(beanName, beanDefinition);
            }

        }

    }
}

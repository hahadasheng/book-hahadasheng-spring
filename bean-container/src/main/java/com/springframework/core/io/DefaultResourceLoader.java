package com.springframework.core.io;

import cn.hutool.core.lang.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader {
    @Override
    public Resource getResource(String location) {
        Assert.notNull(location, "Location must not be null");
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            // 尝试Classpath方式
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 再尝试URL方式
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 最后使用文件系统方式
                return new FileSystemResource(location);
            }
        }
    }
}

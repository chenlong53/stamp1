package com.detection.demo.util;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertyResolver;
import org.springframework.core.env.PropertySourcesPropertyResolver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

/**
 * 类注释
 * User: 9527
 * Time: 2015-05-07 13:59
 */
public class PropertiesFileReader {

    private static final PropertyResolver resolver;

    static {
        try {
            MutablePropertySources sources = new MutablePropertySources();
//            sources.addFirst(new ResourcePropertySource("docker", new EncodedResource(new ClassPathResource("docker-java.properties"), "utf-8")));

            sources.addFirst(new ResourcePropertySource("console", new EncodedResource(new ClassPathResource("console.properties"), "utf-8")));


            //            sources.addFirst(new ResourcePropertySource("ospart", new EncodedResource(new ClassPathResource("ospart.properties"), "utf-8")));

            resolver = new PropertySourcesPropertyResolver(sources);
        } catch (IOException ex) {
            throw new IllegalStateException("properties file initialize failure", ex);
        }
    }

    /**
     * 根据key获取配置
     *
     * @param key 配置的key
     * @return
     */
    public static String getProperty(String key) {
        return resolver.getProperty(key);
    }

    /**
     * 根据key获取配置
     *
     * @param key          配置的key
     * @param defaultValue 当获取到的配置值为null时的默认返回值
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        return resolver.getProperty(key, defaultValue);
    }

    /**
     * 根据key获取配置
     * 当不存在该配置时会抛出异常
     *
     * @param key 配置key
     * @return
     */
    public static String getRequiredProperty(String key) {
        return resolver.getRequiredProperty(key);
    }
}


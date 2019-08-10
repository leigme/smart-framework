package me.leig.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载属性文件
 *
 */
public final class PropsUtil {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream inputStream = null;
        try {
            inputStream = ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if (null == inputStream) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            properties = new Properties();
            properties.load(inputStream);
        } catch (Exception e) {
            logger.error("load properties file failure", e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("close input stream failure", e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取 String 类型的属性值(默认值为空字符串)
     *
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 获取 String 类型的属性值(可指定默认值)
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取 int 类型的属性值(默认值为 0)
     *
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    public static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if (properties.containsKey(key)) {
            value = CastUtil.castInt(properties.getProperty(key));
        }
        return value;
    }
}

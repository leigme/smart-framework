package me.leig.smart4j.helper;

import me.leig.smart4j.util.ArrayUtil;
import me.leig.smart4j.util.CollectionUtil;
import me.leig.smart4j.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 依赖注入助手类
 */
public final class IocHelper {

    static {
        // 获取所有的Bean类与Bean实例之间的映射关系(简称BeanMap)
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (CollectionUtil.isNotEmpty(beanMap)) {
            // 遍历BeanMap
            for (Map.Entry<Class<?>, Object> beanEntry: beanMap.entrySet()) {
                // 从BeanMap中获取Bean类与Bean实例
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();
                // 获取Bean类定义的所有成员变量(简称Bean Field)
                Field[] beanFields = beanClass.getDeclaredFields();
                if (ArrayUtil.isNotEmpty(beanFields)) {
                    // 遍历Bean Field
                    for (Field beanField: beanFields) {
                        // 判断当前Bean Field是否带有Inject注解
                        Class<?> beanFieldClass = beanField.getType();
                        Object beanFieldInstance = beanMap.get(beanFieldClass);
                        if (null != beanFieldInstance) {
                            // 通过反射初始化BeanField的值
                            ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                        }
                    }
                }
            }
        }
    }

}

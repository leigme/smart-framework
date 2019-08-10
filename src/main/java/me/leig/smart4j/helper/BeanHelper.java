package me.leig.smart4j.helper;

import me.leig.smart4j.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Bean 助手类
 */
public final class BeanHelper {

    /**
     * 定义Bean映射(用于存放Bean类与Bean实例的映射关系)
     *
     */
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> cls: beanClassSet) {
            Object obj = ReflectionUtil.newInstance(cls);
            BEAN_MAP.put(cls, obj);
        }
    }

    /**
     * 获取Bean映射
     *
     * @return
     */
    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    /**
     * 获取Bean实例
     *
     * @param cls
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<?> cls) {
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can not get bean by class: " + cls);
        }
        return (T) BEAN_MAP.get(cls);
    }
}

package me.leig.smart4j;

import me.leig.smart4j.helper.BeanHelper;
import me.leig.smart4j.helper.ClassHelper;
import me.leig.smart4j.helper.ControllerHelper;
import me.leig.smart4j.helper.IocHelper;
import me.leig.smart4j.util.ClassUtil;

/**
 * 加载相应的Helper类
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
                ClassHelper.class,
                BeanHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> cls: classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}

package me.leig.smart4j.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON 工具类
 */
public final class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 将 POJO 转 JSON
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toJson(T t) {
        String json;
        try {
            json = JSON.toJSONString(t);
        } catch(Exception e) {
            logger.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 转 POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = JSON.parseObject(json, type);
        } catch(Exception e) {
            logger.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}

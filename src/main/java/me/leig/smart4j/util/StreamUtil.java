package me.leig.smart4j.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具
 */
public final class StreamUtil {

    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    /**
     * 从输入流中获取字符串
     *
     * @param inputStream
     * @return
     */
    public static String getString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (null != (line = reader.readLine())) {
                sb.append(line);
            }
        } catch(Exception e) {
            logger.error("get string failure", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

}

package com.springboot.shiro.util;

import java.util.UUID;

/**
 * @author xutianhong
 * @Date 2021/4/5 5:15 下午
 */
public class UUidUtil {
    public static final int UUID_LENGTH_16 = 16;

    /**
     * 生成随机字符串UUID（长度32）
     *
     * @return
     */
    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    /**
     * 生成16位随机字符串UUID
     *
     * @return
     */
    public static String get16UUID() {
        return getUUID().substring(0, UUID_LENGTH_16);
    }
}

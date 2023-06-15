package com.elvesyuki.javautils.normal.utils;

/**
 * @author ：luohuan
 * @date ：Created in 2022/3/24/024 11:31
 * @description： 认证工具
 * @modified By：
 */
public class AuthUtils {
    private AuthUtils() {
    }

    /**
     * 密码salt长度 固定为 6 不可更改
     */
    public static final Integer AUTH_SALT_LENGTH = 6;

    public static String generateSalt() {

        // 为解耦，不使用工具类方法
        long currentTimeMillis = System.currentTimeMillis();

        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < AUTH_SALT_LENGTH; i++) {
            salt.append((char) (currentTimeMillis % (1L << (7 * (i + 1)) + 1) % 26 + 97));
        }

        return salt.toString();
    }


}

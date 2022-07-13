package com.elvesyuki.javautils.normal.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author ：luohuan
 * @date ：Created in 2022/3/25/025 10:51
 * @description：
 * @modified By：
 */
public class Base64Utils {

    private Base64Utils() {
    }

    /**
     * Base64加密
     * @param src
     * @return
     */
    public static String encode(byte[] src) {
        byte[] encodeBytes = Base64.getEncoder().encode(src);
        return new String(encodeBytes);
    }

    /**
     * Base64加密
     * @param src
     * @return
     */
    public static String encode(String src) {
        byte[] encodeBytes = Base64.getEncoder().encode(src.getBytes(StandardCharsets.UTF_8));
        return new String(encodeBytes);
    }

    /**
     * Base64 解密
     * @param src
     * @return
     */
    public static String decode(String src) {
        byte[] decodeBytes = Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8));
        return new String(decodeBytes);
    }

}

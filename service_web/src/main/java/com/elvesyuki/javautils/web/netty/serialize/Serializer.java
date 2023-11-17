package com.elvesyuki.javautils.web.netty.serialize;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:39
 * @description：
 * @modified By：
 */
public interface Serializer {

    /**
     * JSON序列化
     */
    byte JSON_SERIALIZER = 1;

    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     * @return
     */
    byte getSerializerAlgorithm();

    /**
     * Java对象转换成二进制数据
     * @param o
     * @return
     */
    byte[] serialize(Object o);

    /**
     * 二进制数据转换成Java对象
     * @param bytes
     * @param clazz
     * @return
     * @param <T>
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);


}

package com.elvesyuki.javautils.web.netty.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:43
 * @description：
 * @modified By：
 */
public class JSONSerializer implements Serializer{
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}

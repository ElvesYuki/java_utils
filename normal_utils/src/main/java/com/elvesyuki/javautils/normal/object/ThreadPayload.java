package com.elvesyuki.javautils.normal.object;

/**
 * @author ：luohuan
 * @date ：Created in 2022/5/7/007 16:13
 * @description：
 * @modified By：
 */
public class ThreadPayload<T> {

    /**
     * 时间戳
     */
    private Long ts;

    /**
     * 有效载荷
     */
    private T payload;

    public ThreadPayload() {
        this.setTs(System.currentTimeMillis());
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ThreadPayload{" +
                "ts=" + ts +
                ", payload=" + payload +
                '}';
    }
}

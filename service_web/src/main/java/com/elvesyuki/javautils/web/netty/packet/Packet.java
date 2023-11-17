package com.elvesyuki.javautils.web.netty.packet;

/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:35
 * @description：
 * @modified By：
 */
public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令
     * @return
     */
    public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}

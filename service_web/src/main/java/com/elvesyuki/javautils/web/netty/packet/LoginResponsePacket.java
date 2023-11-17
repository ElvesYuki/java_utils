package com.elvesyuki.javautils.web.netty.packet;


/**
 * @author ：luohuan
 * @date ：Created in 2023/11/16 16:37
 * @description：
 * @modified By：
 */
public class LoginResponsePacket extends Packet{

    private Boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

package com.elvesyuki.javautils.normal.enums;

/**
 * @author ：luohuan
 * @date ：Created in 2021/8/27 上午11:58
 * @description：定义通知类型枚举
 * @modified By：
 */
public enum NoticeTypeEnum {
    EMAIL_NOTICE(1, "邮件通知"),
    SMS_NOTICE(2, "短信通知"),
    SYS_NOTICE(3, "系统通知"),
    PUSH_NOTICE(4, "推送通知"),
    ;

    /**
     * 通知类型标志
     */
    private int sign;

    /**
     * 通知名称
     */
    private String name;

    NoticeTypeEnum() {
    }

    NoticeTypeEnum(int sign, String name) {
        this.sign = sign;
        this.name = name;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

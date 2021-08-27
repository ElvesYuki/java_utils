package com.elvesyuki.javautils.normal.enums;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：luohuan
 * @date ：Created in 2021/8/27 上午11:58
 * @description：通知的信息枚举
 * @modified By：
 */
public enum NoticeInfoEnum {
    // 默认通知信息
    TEST("test",0, "测试发送，全平台发送通知", NoticeTypeEnum.EMAIL_NOTICE, NoticeTypeEnum.SMS_NOTICE, NoticeTypeEnum.SYS_NOTICE, NoticeTypeEnum.PUSH_NOTICE),
    DEFAULT_EMAIL("default_email",0, "默认邮件通知", NoticeTypeEnum.EMAIL_NOTICE),
    DEFAULT_SMS("default_sms",0, "默认短信通知", NoticeTypeEnum.SMS_NOTICE),
    DEFAULT_SYS("default_sys",0, "默认系统通知", NoticeTypeEnum.SYS_NOTICE),

    // 可以自定义通知信息
    S0003("S0003",0, "管理员排期", NoticeTypeEnum.SYS_NOTICE),


    ;

    @NotNull(message = "当前推送码，必须填写")
    private String code;

    /**
     * 是否延时推送（ms），0为立即发送
     */
    private long delayTime;

    /**
     * 描述
     */
    private String desc;

    @NotNull(message = "推送类型，必须填写")
    private List<NoticeTypeEnum> noticeType = new ArrayList<>();

    NoticeInfoEnum(String code, long delayTime, String desc, NoticeTypeEnum... noticeType) {
        this.code = code;
        this.delayTime = delayTime;
        this.desc = desc;
        this.noticeType.addAll(Arrays.asList(noticeType));
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<NoticeTypeEnum> getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(List<NoticeTypeEnum> noticeType) {
        this.noticeType = noticeType;
    }

    public long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(long delayTime) {
        this.delayTime = delayTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



    @Override
    public String toString() {
        return "NoticeInfoEnum{" +
                "code='" + code + '\'' +
                ", noticeType=" + noticeType +
                ", delayTime=" + delayTime +
                ", desc='" + desc + '\'' +
                '}';
    }
}

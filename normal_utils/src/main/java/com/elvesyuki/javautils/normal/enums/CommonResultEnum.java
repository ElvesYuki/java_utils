package com.elvesyuki.javautils.normal.enums;

import java.util.EnumSet;

/**
 * @author ：luohuan
 * @date ：Created in 2021/7/2 上午9:40
 * @description：
 * @modified By：
 */
public enum CommonResultEnum {
    SUCCESS("200", "success"),
    FAIL("500", "fail"),
    INTERNAL_FAIL("500", "发生内部错误，请稍后重试"),
    BUSINESS_ERROR("501", "业务类型错误"),
    UN_AUTH("301", "未授权，请先登录"),
    MULTI_AUTH("303","已在其他设备登录"),
    TOKEN_EXPIRE("422", "token过期，请重新获取");

    /**
     * 状态码
     */
    private String code;

    /**
     * 消息
     */
    private String msg;

    CommonResultEnum(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    /**
     * 判断枚举是否合法
     * @param code
     * @return
     */
    public static CommonResultEnum findByCode(String code) {
        for (CommonResultEnum each : EnumSet.allOf(CommonResultEnum.class)) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

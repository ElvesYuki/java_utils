package com.elvesyuki.javautils.normal.dto;

import com.elvesyuki.javautils.normal.enums.CommonResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：luohuan
 * @date ：Created in 2021/7/2 上午11:55
 * @description：
 * @modified By：
 */
public class XmoException extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(XmoException.class);

    /**
     * 状态信息
     */
    protected String httpCode;

    /**
     * 错误消息
     */
    protected String errMsg;

    /**
     * 业务提示码
     */
    protected String bizCode;

    /**
     * 原始异常
     */
    protected Exception exception;

    public XmoException() {
        super();
    }

    public XmoException(String errMsg){
        logger.error(errMsg);
    }

    public XmoException(Exception exception) {
        this.exception = exception;
        this.httpCode = CommonResultEnum.FAIL.getCode();
        this.errMsg = exception.getMessage();
        this.bizCode = CommonResultEnum.FAIL.getCode();
    }

    public String getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(String httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }
}

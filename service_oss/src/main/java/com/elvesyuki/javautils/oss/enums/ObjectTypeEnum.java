package com.elvesyuki.javautils.oss.enums;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/6 下午4:13
 */
public enum ObjectTypeEnum {

    DEFAULT("default", "default", "default", "默认类型，需要自行判断"),

    IMAGE("image", "default", "default", "图像类型"),
    VIDEO("video", "default", "default", "视频类型"),
    AUDIO("audio", "default", "default", "音频类型"),

    JSON("json", "json", "application/json", "上传类型为json字符串，做文本存储用"),
    HTML("html", "html", "text/html","富文本片段"),


    ;

    /**
     * 上传的文件类型， 默认为default， 自行判断， 可手动填入， 如json、html
     */
    private String objectType;

    /**
     * 后缀
     */
    private String objectSuffix;

    /**
     * 网络媒体类型
     */
    private String contentType;

    /**
     * 描述
     */
    private String desc;

    ObjectTypeEnum(String objectType, String objectSuffix, String contentType, String desc) {
        this.objectType = objectType;
        this.objectSuffix = objectSuffix;
        this.contentType = contentType;
        this.desc = desc;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getObjectSuffix() {
        return objectSuffix;
    }

    public void setObjectSuffix(String objectSuffix) {
        this.objectSuffix = objectSuffix;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "OssTypeEnum{" +
                "objectType='" + objectType + '\'' +
                ", objectSuffix='" + objectSuffix + '\'' +
                ", contentType='" + contentType + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

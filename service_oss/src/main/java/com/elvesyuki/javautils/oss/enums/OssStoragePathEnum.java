package com.elvesyuki.javautils.oss.enums;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/6 下午12:00
 */
public enum OssStoragePathEnum {

    DEFAULT("default", "default", "temp", "default/", "默认存储的路径"),
    TEMP("temp", "default", "temp", "temp/", "默认存储的临时路径"),

    DEFAULT_JSON("default_json", "json", "temp", "default/", "默认json存储的路径"),
    DEFAULT_HTML("default_html", "html", "temp", "default/", "默认html存储的路径"),

    ;

    /**
     * 存储的路径 码 全局唯一
     */
    private String pathCode;

    /**
     * 上传的文件类型， 默认为default， 自行判断， 可手动填入， 如json、html
     */
    private String objectType;

    /**
     * bucket name
     */
    private String bucketName;

    /**
     * 存储的对象路径，不包含文件名
     */
    private String objectPath;

    /**
     * 描述
     */
    private String desc;

    OssStoragePathEnum(String pathCode, String objectType, String bucketName, String objectPath, String desc) {
        this.pathCode = pathCode;
        this.objectType = objectType;
        this.bucketName = bucketName;
        this.objectPath = objectPath;
        this.desc = desc;
    }

    public String getPathCode() {
        return pathCode;
    }

    public void setPathCode(String pathCode) {
        this.pathCode = pathCode;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.elvesyuki.javautils.oss.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/6 下午12:00
 */
public enum OssStoragePathEnum {

    DEFAULT("default", ObjectTypeEnum.DEFAULT.getObjectType(), "java-utils", "default/", "默认存储的路径", -1L),
    TEMP("temp", ObjectTypeEnum.DEFAULT.getObjectType(), "java-utils", "temp/", "默认存储的临时路径", -1L),

    DEFAULT_JSON("default_json", ObjectTypeEnum.JSON.getObjectType(), "java-utils", "default/", "默认json存储的路径", -1L, "json"),
    DEFAULT_HTML("default_html", ObjectTypeEnum.HTML.getObjectType(), "java-utils", "default/", "默认html存储的路径", -1L,"html"),

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

    /**
     * 上传的最大大小  字节数 -1L代表不限制
     */
    private Long maxSize = -1L;

    /**
     * 允许的文件名后缀, 空代表允许所有
     */
    private List<String> objectSuffix = new ArrayList<>();


    OssStoragePathEnum(String pathCode, String objectType, String bucketName, String objectPath, String desc) {
        this.pathCode = pathCode;
        this.objectType = objectType;
        this.bucketName = bucketName;
        this.objectPath = objectPath;
        this.desc = desc;
    }


    OssStoragePathEnum(String pathCode, String objectType, String bucketName, String objectPath, String desc, Long maxSize) {
        this.pathCode = pathCode;
        this.objectType = objectType;
        this.bucketName = bucketName;
        this.objectPath = objectPath;
        this.desc = desc;
        this.maxSize = maxSize;
    }

    OssStoragePathEnum(String pathCode, String objectType, String bucketName, String objectPath, String desc, Long maxSize, String ... suffix) {
        this.pathCode = pathCode;
        this.objectType = objectType;
        this.bucketName = bucketName;
        this.objectPath = objectPath;
        this.desc = desc;
        this.maxSize = maxSize;
        this.objectSuffix.addAll(Arrays.asList(suffix));
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

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public List<String> getObjectSuffix() {
        return objectSuffix;
    }

    public void setObjectSuffix(List<String> objectSuffix) {
        this.objectSuffix = objectSuffix;
    }
}

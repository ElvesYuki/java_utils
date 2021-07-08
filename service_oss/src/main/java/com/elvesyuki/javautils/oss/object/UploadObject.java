package com.elvesyuki.javautils.oss.object;

import io.swagger.annotations.ApiModelProperty;

import java.io.InputStream;
import java.io.Serializable;

/**
 * @ClassName AdminOrgController
 * @Description 上传封装对象
 * @Author luohuan
 * @Date 2021/7/6 下午2:38
 */
public class UploadObject<T> implements Serializable {

    private static final long serialVersionUID = -3896061387843802537L;

    @ApiModelProperty(value = "是否覆盖上传，默认为false，表示新增")
    protected Boolean cover;

    @ApiModelProperty(value = "对象格式")
    protected String ossType;

    @ApiModelProperty(value = "对象本体")
    protected T ossContent;

    @ApiModelProperty(value = "对象本体流")
    protected InputStream ossInputStream;

    @ApiModelProperty(value = "对象源文件name")
    protected String ossName;

    @ApiModelProperty(value = "对象格式，文件名后缀")
    protected String format;

    @ApiModelProperty(value = "对象contentType")
    protected String contentType;

    /**
     * 对象大小，单位Byte
     */
    @ApiModelProperty(value = "对象大小，单位Byte")
    protected Long Size;

    @ApiModelProperty(value = "对象bucketName")
    protected String bucketName;

    @ApiModelProperty(value = "对象objectName")
    protected String objectName;

    @ApiModelProperty(value = "对象versionId")
    protected String versionId;

    public UploadObject() {
        cover = false;
    }

    public Boolean getCover() {
        return cover;
    }

    public void setCover(Boolean cover) {
        this.cover = cover;
    }

    public String getOssType() {
        return ossType;
    }

    public void setOssType(String ossType) {
        this.ossType = ossType;
    }

    public T getOssContent() {
        return ossContent;
    }

    public void setOssContent(T ossContent) {
        this.ossContent = ossContent;
    }

    public InputStream getOssInputStream() {
        return ossInputStream;
    }

    public void setOssInputStream(InputStream ossInputStream) {
        this.ossInputStream = ossInputStream;
    }

    public String getOssName() {
        return ossName;
    }

    public void setOssName(String ossName) {
        this.ossName = ossName;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return Size;
    }

    public void setSize(Long size) {
        Size = size;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    @Override
    public String toString() {
        return "UploadObject{" +
                "cover=" + cover +
                ", ossType='" + ossType + '\'' +
                ", ossContent=" + ossContent +
                ", ossInputStream=" + ossInputStream +
                ", ossName='" + ossName + '\'' +
                ", format='" + format + '\'' +
                ", contentType='" + contentType + '\'' +
                ", Size=" + Size +
                ", bucketName='" + bucketName + '\'' +
                ", objectName='" + objectName + '\'' +
                ", versionId='" + versionId + '\'' +
                '}';
    }

}

package oss.object;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 对象存储公共对象
 */
@Component
public class OssObject implements Serializable {

    private static final long serialVersionUID = 4031881687481584025L;

    @ApiModelProperty(value = "上传状态")
    protected Integer uploadStatus;

    /**
     * 真实对象地址
     * = bucket + 对象路径
     */
    @ApiModelProperty(value = "真实对象地址")
    protected String url;

    @ApiModelProperty(value = "对象存储bucket")
    protected String bucket;

    @ApiModelProperty(value = "对象路径")
    protected String object;

    @ApiModelProperty(value = "对象源文件name")
    protected String fileName;

    @ApiModelProperty(value = "对象格式，文件名后缀")
    protected String format;

    @ApiModelProperty(value = "对象contentType")
    protected String contentType;

    /**
     * 对象大小，单位Byte
     */
    @ApiModelProperty(value = "对象大小，单位Byte")
    protected Long Size;

    public Integer getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(Integer uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    @Override
    public String toString() {
        return "OssObject{" +
                "uploadStatus=" + uploadStatus +
                ", url='" + url + '\'' +
                ", bucket='" + bucket + '\'' +
                ", object='" + object + '\'' +
                ", fileName='" + fileName + '\'' +
                ", format='" + format + '\'' +
                ", contentType='" + contentType + '\'' +
                ", Size=" + Size +
                '}';
    }
}

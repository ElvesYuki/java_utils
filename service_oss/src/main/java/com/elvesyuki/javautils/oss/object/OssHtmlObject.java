package com.elvesyuki.javautils.oss.object;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/6 下午4:48
 */
public class OssHtmlObject extends OssObject implements Serializable {

    private static final long serialVersionUID = -1606249152125554467L;

    @ApiModelProperty(value = "文本节选")
    private String contentExcerpt;

    @ApiModelProperty(value = "图片url节选")
    private List<String> pictureList;

    @ApiModelProperty(value = "图片数量")
    private Integer pictureCount;

    public String getContentExcerpt() {
        return contentExcerpt;
    }

    public void setContentExcerpt(String contentExcerpt) {
        this.contentExcerpt = contentExcerpt;
    }

    public List<String> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<String> pictureList) {
        this.pictureList = pictureList;
    }

    public Integer getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(Integer pictureCount) {
        this.pictureCount = pictureCount;
    }

    @Override
    public String toString() {
        return "OssHtmlObject{" +
                "contentExcerpt='" + contentExcerpt + '\'' +
                ", pictureList=" + pictureList +
                ", pictureCount=" + pictureCount +
                '}';
    }
}

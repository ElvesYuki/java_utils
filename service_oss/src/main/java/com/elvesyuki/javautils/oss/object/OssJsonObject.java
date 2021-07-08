package com.elvesyuki.javautils.oss.object;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 图片对象返回
 */
@Component
public class OssJsonObject extends OssObject implements Serializable {

    private static final long serialVersionUID = 6050863319039200413L;

    @ApiModelProperty(value = "文本节选")
    private String contentExcerpt;

    public String getContentExcerpt() {
        return contentExcerpt;
    }

    public void setContentExcerpt(String contentExcerpt) {
        this.contentExcerpt = contentExcerpt;
    }

    @Override
    public String toString() {
        return "OssJsonObject{" +
                "contentExcerpt='" + contentExcerpt + '\'' +
                '}';
    }
}

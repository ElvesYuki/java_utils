package oss.object;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 图片对象返回
 */
@Component
public class ImgOssObject extends OssObject implements Serializable {

    private static final long serialVersionUID = 6575058908030437721L;

    @ApiModelProperty(value = "图片宽度")
    private Integer width;

    @ApiModelProperty(value = "图片高度")
    private Integer height;

    @ApiModelProperty(value = "缩略图")
    private ImgOssObject thumbnail;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public ImgOssObject getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(ImgOssObject thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "ImgOssObject{" +
                "width=" + width +
                ", height=" + height +
                ", thumbnail=" + thumbnail +
                '}';
    }
}

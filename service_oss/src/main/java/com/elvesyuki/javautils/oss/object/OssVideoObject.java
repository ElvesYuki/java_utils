package com.elvesyuki.javautils.oss.object;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 视频对象返回
 */
@Component
public class OssVideoObject extends OssObject implements Serializable {

    private static final long serialVersionUID = -5551698247240745104L;

    /**
     * 视频（帧）宽度 ，单位为px
     */
    @ApiModelProperty(value = "视频宽度,px")
    private Integer width;

    /**
     * 视频（帧）高度 ，单位为px
     */
    @ApiModelProperty(value = "视频高度,xp")
    private Integer height;

    /**
     * 视频时长 ,单位：毫秒
     */
    @ApiModelProperty(value = "视频时长")
    private Long duration;

    /**
     * 比特率，单位：Kb/s
     * 指视频每秒传送（包含）的比特数
     */
    @ApiModelProperty(value = "视频比特率")
    private Integer bitRate;

    /**
     * 编码器
     */
    @ApiModelProperty(value = "编码器")
    private String encoder;

    /**
     * 帧率，单位：FPS（Frame Per Second）
     * 指视频每秒包含的帧数
     */
    @ApiModelProperty(value = "帧率")
    private Float frameRate;

    @ApiModelProperty(value = "视频封面")
    private OssImgObject thumbnail;

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

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Integer getBitRate() {
        return bitRate;
    }

    public void setBitRate(Integer bitRate) {
        this.bitRate = bitRate;
    }

    public String getEncoder() {
        return encoder;
    }

    public void setEncoder(String encoder) {
        this.encoder = encoder;
    }

    public Float getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(Float frameRate) {
        this.frameRate = frameRate;
    }

    public OssImgObject getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(OssImgObject thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "VideoOssObject{" +
                "width=" + width +
                ", height=" + height +
                ", duration=" + duration +
                ", bitRate=" + bitRate +
                ", encoder='" + encoder + '\'' +
                ", frameRate=" + frameRate +
                ", thumbnail=" + thumbnail +
                '}';
    }
}

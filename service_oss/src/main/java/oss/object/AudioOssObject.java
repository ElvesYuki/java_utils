package oss.object;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 音频对象返回
 */
@Component
public class AudioOssObject extends OssObject implements Serializable {

    private static final long serialVersionUID = 1550466356590121826L;

    /**
     * 视频时长 ,单位：毫秒
     */
    @ApiModelProperty(value = "音频时长")
    private Long duration;

    /**
     * 比特率，单位：Kb/s
     * 指视频每秒传送（包含）的比特数
     */
    @ApiModelProperty(value = "音频比特率")
    private Integer bitRate;

    /**
     * 编码器
     */
    @ApiModelProperty(value = "编码器")
    private String encoder;

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

    @Override
    public String toString() {
        return "AudioOssObject{" +
                "duration=" + duration +
                ", bitRate=" + bitRate +
                ", encoder='" + encoder + '\'' +
                '}';
    }
}

package com.elvesyuki.javautils.media.convert.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LuoHuan
 * @date 2021/7/22
 */
@Component
public class OptionVideoUtils {

    private static final Logger log = LoggerFactory.getLogger(OptionVideoUtils.class);

    /**
     * 获取视频封面帧，取第一秒第一帧
     * @param fileInputUrl
     * @param fileOutPut
     */
    public static void getVideoCoverFrame(String fileInputUrl, File fileOutPut) {

        //TODO 检测minio中文件是否存在
        if (null == fileOutPut) {
            throw new RuntimeException("转换后的视频路径为空，请检查转换后的视频存放路径是否正确");
        }

        String format = FFmpegCommandUtils.getFormatByUrl(fileInputUrl);
        if (!FFmpegCommandUtils.isLegalFormat(format, FFmpegCommandUtils.VIDEO_TYPE)) {
            throw new RuntimeException("无法解析的视频格式：" + format);
        }

        List<String> commands = new ArrayList<String>();
        commands.add("-i");
        commands.add(fileInputUrl);
        // 获取视频第几秒的帧数
        commands.add("-ss");
        String resolution = "1";
        commands.add(resolution);

        // 获取几帧数
        commands.add("-vframes");
        commands.add("1");
        // 当已存在输出文件时，不提示是否覆盖
        commands.add("-y");
        commands.add(fileOutPut.getAbsolutePath());
        //executeCommand(commands);
        FFmpegCommandUtils.executeCommand(commands);
    }

    /**
     * 获取视频封面帧，取第一秒第一帧
     * @param fileInputUrl
     * @param fileOutPut
     * @param duration
     */
    public static void getVideoCoverFrame(String fileInputUrl, File fileOutPut, Long duration) {

        //TODO 检测文件是否存在
        if (null == fileOutPut) {
            throw new RuntimeException("转换后的视频路径为空，请检查转换后的视频存放路径是否正确");
        }

        long interval = duration/1000/100;

        String format = FFmpegCommandUtils.getFormatByUrl(fileInputUrl);
        if (!FFmpegCommandUtils.isLegalFormat(format, FFmpegCommandUtils.VIDEO_TYPE)) {
            throw new RuntimeException("无法解析的视频格式：" + format);
        }

        List<String> commands = new ArrayList<String>();
        commands.add("-i");
        commands.add(fileInputUrl);
        // 获取视频第几秒的帧数
        commands.add("-ss");
//        String resolution = "1";
        commands.add(Long.toString(interval));

        // 获取几帧数
        commands.add("-vframes");
        commands.add("1");
        // 当已存在输出文件时，不提示是否覆盖
        commands.add("-y");
        commands.add(fileOutPut.getAbsolutePath());
        //executeCommand(commands);
        FFmpegCommandUtils.executeCommand(commands);
    }


}

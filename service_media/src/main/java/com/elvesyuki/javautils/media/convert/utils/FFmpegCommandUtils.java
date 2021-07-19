package com.elvesyuki.javautils.media.convert.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.sql.Time;
import java.util.*;
import java.util.regex.Pattern;


/**
 * @author LuoHuan
 * @date 2021/7/19
 */
@Component
public class FFmpegCommandUtils {

    private static final Logger log = LoggerFactory.getLogger(FFmpegCommandUtils.class);

    /**
     * 可以处理的视频格式
     */
    public final static String[] VIDEO_TYPE = {"MP4", "WMV"};
    /**
     * 可以处理的图片格式
     */
    public final static String[] IMAGE_TYPE = {"JPG", "JPEG", "PNG", "GIF"};
    /**
     * 可以处理的音频格式
     */
    public final static String[] AUDIO_TYPE = {"AAC"};
    /**
     * 视频帧抽取时的默认时间点，第10s（秒）
     * （Time类构造参数的单位:ms）
     */
    private static final Time DEFAULT_TIME = new Time(0, 0, 10);
    /**
     * 视频帧抽取的默认宽度值，单位：px
     */
    private static int DEFAULT_WIDTH = 320;
    /**
     * 视频帧抽取的默认时长，单位：s（秒）
     */
    private static int DEFAULT_TIME_LENGTH = 10;
    /**
     * 抽取多张视频帧以合成gif动图时，gif的播放速度
     */
    private static int DEFAULT_GIF_PLAYTIME = 110;
    /**
     * FFmpeg程序执行路径
     * 当前系统安装好ffmpeg程序并配置好相应的环境变量后，值为ffmpeg可执行程序文件在实际系统中的绝对路径
     */
    // /usr/bin/ffmpeg
//     private static String FFMPEG_PATH = "/opt/ffmpeg/ffmpeg_bin/ffmpeg";
//    private static String FFMPEG_PATH = "/data/ffmpeg/ffmpeg-4.3/ffmpeg";
    private static String FFMPEG_PATH = "ffmpeg";

    /**
     * 视频时长正则匹配式
     * 用于解析视频及音频的时长等信息时使用；
     * <p>
     * (.*?)表示：匹配任何除\r\n之外的任何0或多个字符，非贪婪模式
     */
    private static String durationRegex = "Duration: (\\d*?):(\\d*?):(\\d*?)\\.(\\d*?), start: (.*?), bitrate: (\\d*) kb\\/s.*";
    private static Pattern durationPattern;
    /**
     * 视频流信息正则匹配式
     * 用于解析视频详细信息时使用；
     */
    private static String videoStreamRegex = "Stream #\\d:\\d[\\(]??\\S*[\\)]??: Video: (\\S*\\S$?)[^\\,]*, (.*?), (\\d*)x(\\d*)[^\\,]*, (\\d*) kb\\/s, (\\d*[\\.]??\\d*) fps";
    private static Pattern videoStreamPattern;
    /**
     * 音频流信息正则匹配式
     * 用于解析音频详细信息时使用；
     */
    private static String musicStreamRegex = "Stream #\\d:\\d[\\(]??\\S*[\\)]??: Audio: (\\S*\\S$?)(.*), (.*?) Hz, (.*?), (.*?), (\\d*) kb\\/s";
    private static Pattern musicStreamPattern;

    /**
     * 静态初始化时先加载好用于音视频解析的正则匹配式
     */
    static {
        durationPattern = Pattern.compile(durationRegex);
        videoStreamPattern = Pattern.compile(videoStreamRegex);
        musicStreamPattern = Pattern.compile(musicStreamRegex);
    }

    /**
     * 获取当前多媒体处理工具内的ffmpeg的执行路径
     *
     * @return
     */
    public static String getFFmpegPath() {
        return FFMPEG_PATH;
    }

    /**
     * 设置当前多媒体工具内的ffmpeg的执行路径
     *
     * @param ffmpeg_path ffmpeg可执行程序在实际系统中的绝对路径
     * @return
     */
    public static boolean setFFmpegPath(String ffmpeg_path) {
        if (StringUtils.isBlank(ffmpeg_path)) {
            log.error("--- 设置ffmpeg执行路径失败，因为传入的ffmpeg可执行程序路径为空！ ---");
            return false;
        }
        File ffmpegFile = new File(ffmpeg_path);
        if (!ffmpegFile.exists()) {
            log.error("--- 设置ffmpeg执行路径失败，因为传入的ffmpeg可执行程序路径下的ffmpeg文件不存在！ ---");
            return false;
        }
        FFMPEG_PATH = ffmpeg_path;
        log.info("--- 设置ffmpeg执行路径成功 --- 当前ffmpeg可执行程序路径为： " + ffmpeg_path);
        return true;
    }

    /**
     * 测试当前多媒体工具是否可以正常工作
     * @return
     */
    public static boolean isExecutable() {
        File ffmpegFile = new File(FFMPEG_PATH);
        if (!ffmpegFile.exists()) {
            log.error("--- 工作状态异常，因为传入的ffmpeg可执行程序路径下的ffmpeg文件不存在！ ---");
            return false;
        }
        List<String> cmds = new ArrayList<>(1);
        cmds.add("-version");
        String ffmpegVersionStr = executeCommand(cmds);
        if (StringUtils.isBlank(ffmpegVersionStr)) {
            log.error("--- 工作状态异常，因为ffmpeg命令执行失败！ ---");
            return false;
        }
        log.info("--- 工作状态正常 ---");
        return true;
    }

    /**
     * 执行FFmpeg命令
     * @param commonds 要执行的FFmpeg命令
     * @return FFmpeg程序在执行命令过程中产生的各信息，执行出错时返回null
     */
    public static String executeCommand(List<String> commonds) {
        if (CollectionUtils.isEmpty(commonds)) {
            log.error("--- 指令执行失败，因为要执行的FFmpeg指令为空！ ---");
            return null;
        }
        LinkedList<String> ffmpegCmds = new LinkedList<>(commonds);
        // 设置ffmpeg程序所在路径
        ffmpegCmds.addFirst(FFMPEG_PATH);
        log.info("--- 待执行的FFmpeg指令为：---" + ffmpegCmds);
        String cmdStrte = Arrays.toString(ffmpegCmds.toArray()).replace(",", "");
        log.info("--- 待执行的FFmpeg指令为：---" + cmdStrte);

        Runtime runtime = Runtime.getRuntime();
        Process ffmpeg = null;
        try {
            // 执行ffmpeg指令
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(ffmpegCmds);
            ffmpeg = builder.start();
            log.info("--- 开始执行FFmpeg指令：--- 执行线程名：" + builder.toString());

            // 取出输出流和错误流的信息
            // 注意：必须要取出ffmpeg在执行命令过程中产生的输出信息，如果不取的话当输出流信息填满jvm存储输出留信息的缓冲区时，线程就回阻塞住
            PrintStream errorStream = new PrintStream(ffmpeg.getErrorStream());
            PrintStream inputStream = new PrintStream(ffmpeg.getInputStream());
            errorStream.start();
            inputStream.start();
            // 等待ffmpeg命令执行完
            ffmpeg.waitFor();

            // 获取执行结果字符串
            String result = errorStream.stringBuffer.append(inputStream.stringBuffer).toString();

            // 输出执行的命令信息
            String cmdStr = Arrays.toString(ffmpegCmds.toArray()).replace(",", "");
            String resultStr = StringUtils.isBlank(result) ? "【异常】" : "正常";
            log.info("--- 已执行的FFmepg命令： ---" + cmdStr + " 已执行完毕,执行结果： " + resultStr);
            return result;

        } catch (Exception e) {
            log.error("--- FFmpeg命令执行出错！ --- 出错信息： " + e.getMessage());
            return null;

        } finally {
            if (null != ffmpeg) {
                ProcessKiller ffmpegKiller = new ProcessKiller(ffmpeg);
                // JVM退出时，先通过钩子关闭FFmepg进程
                runtime.addShutdownHook(ffmpegKiller);
            }
        }
    }


    /**
     * 获取指定文件的后缀名
     * @param file
     * @return
     */
    public static String getFormat(File file) {
        String fileName = file.getName();
        String format = fileName.substring(fileName.indexOf(".") + 1);
        return format;
    }

    /**
     * 根据URL获取文件名
     * @param fileUrl
     * @return
     */
    public static String getFormatByUrl(String fileUrl) {
        if ("".equals(fileUrl)) {
        }

        String formatTemp = fileUrl.substring(fileUrl.lastIndexOf(".") + 1);
        return formatTemp.split("\\?")[0];
    }

    /**
     * 检测视频格式是否合法
     * @param format
     * @param formats
     * @return
     */
    public static boolean isLegalFormat(String format, String[] formats) {
        for (String item : formats) {
//            if (item.equals(StringUtils.upperCase(format))) {
//                return true;
//            }
        }
        return false;
    }

    /**
     * 在程序退出前结束已有的FFmpeg进程
     */
    private static class ProcessKiller extends Thread {
        private Process process;

        public ProcessKiller(Process process) {
            this.process = process;
        }

        @Override
        public void run() {
            this.process.destroy();
            log.info("--- 已销毁FFmpeg进程 --- 进程名： " + process.toString());
        }
    }

    /**
     * 用于取出ffmpeg线程执行过程中产生的各种输出和错误流的信息
     */
    private static class PrintStream extends Thread {
        InputStream inputStream = null;
        BufferedReader bufferedReader = null;
        StringBuffer stringBuffer = new StringBuffer();

        public PrintStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override
        public void run() {
            try {
                if (null == inputStream) {
                    log.error("--- 读取输出流出错！因为当前输出流为空！---");
                }
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    log.info(line);
                    stringBuffer.append(line);
                }
            } catch (Exception e) {
                log.error("--- 读取输入流出错了！--- 错误信息：" + e.getMessage());
            } finally {
                try {
                    if (null != bufferedReader) {
                        bufferedReader.close();
                    }
                    if (null != inputStream) {
                        inputStream.close();
                    }
                } catch (IOException e) {
                    log.error("--- 调用PrintStream读取输出流后，关闭流时出错！---");
                }
            }
        }
    }

    /**
     *
     * @return 生成的UUID（不包含后缀）
     */
    public static String generateUUID() {
        char[] toUuid = new char[32];
        char[] uuid = UUID.randomUUID().toString().toCharArray();
        System.arraycopy(uuid, 0, toUuid, 0, 8);
        System.arraycopy(uuid, 9, toUuid, 8, 4);
        System.arraycopy(uuid, 14, toUuid, 12, 4);
        System.arraycopy(uuid, 19, toUuid, 16, 4);
        System.arraycopy(uuid, 24, toUuid, 20, 12);
        return new String(toUuid);
    }


}

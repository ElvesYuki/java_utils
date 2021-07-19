package com.elvesyuki.javautils.media.convert.utils;

import com.elvesyuki.javautils.normal.config.XmoApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/**
 * @author LuoHuan
 * @date 2021/7/19
 */
@Component
public class DiskFileItemUtils {

    private static final Logger logger = LoggerFactory.getLogger(DiskFileItemUtils.class);

    private static ApplicationContext applicationContext;

    private static File tempFileDir;

    @Resource
    private XmoApplicationContext xmoApplicationContext;

    /**
     * 实例化
     *
     * @return
     */
    @PostConstruct
    public void init() {
        DiskFileItemUtils.applicationContext = xmoApplicationContext.getApplicationContext();
        DiskFileItemUtils.tempFileDir = getDiskFileDir();
    }

    /**
     * 获取临时文件夹
     * @return
     */
    public static File getDiskFileDir() {


        ApplicationContext ctx = DiskFileItemUtils.applicationContext;
        WebApplicationContext webApplicationContext = null;
        if (ctx instanceof WebApplicationContext) {
            webApplicationContext =  (WebApplicationContext) ctx;
        }
        ServletContext servletContext = null;
        if (webApplicationContext != null) {
            servletContext = webApplicationContext.getServletContext();
        }
        Assert.notNull(servletContext, "ServletContext must not be null");
        File tempFileDir = WebUtils.getTempDir(servletContext);

        return tempFileDir;

        /*File file = new File("/data/temp");
        if (!file.exists()){
            file.mkdir();
        }
        return file;*/
    }

    /**
     * 获取唯一文件名
     *
     * @return 生成的文件名（不包含后缀）
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


    /**
     * 生成mp4临时文件
     * @return
     */
    public static File createMp4TempFile() {

        File mp4TempFile = null;
        try {
            mp4TempFile = File.createTempFile(DiskFileItemUtils.generateUUID(), ".mp4", DiskFileItemUtils.tempFileDir);
            logger.info("创建临时文件:"+mp4TempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mp4TempFile;

    }

    /**
     * 生成ts临时文件
     * @return
     */
    public static File createTSTempFile() {

        File tsTempFile = null;
        try {
            tsTempFile = File.createTempFile(DiskFileItemUtils.generateUUID(), ".ts", DiskFileItemUtils.tempFileDir);
            logger.info("创建临时文件:"+tsTempFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tsTempFile;

    }

    /**
     * 生成临时文件
     * @return
     */
    public static File createTempFile(String suffix) {

        File tempFile = null;
        try {
            tempFile = File.createTempFile(DiskFileItemUtils.generateUUID(), suffix, DiskFileItemUtils.tempFileDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;

    }

    /**
     * 清除临时文件
     * @return
     */
    public static Boolean deleteTempFile(File deleteTempFile) {
        deleteTempFile.delete();
        return true;
    }

}

package com.elvesyuki.javautils.oss.util;

import com.elvesyuki.javautils.oss.handler.IOssCoverHandler;
import com.elvesyuki.javautils.oss.handler.IOssUploadHandler;
import com.elvesyuki.javautils.oss.object.OssObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @ClassName OssUtils
 * @Description Oss工具类
 * @Author luohuan
 * @Date 2021/3/9 下午12:06
 */
@Component
public class OssUtils {

    private static final Logger logger = LoggerFactory.getLogger(OssUtils.class);

    @Resource
    private IOssUploadHandler minioUploadHandler;

    @Resource
    private IOssCoverHandler minioCoverHandler;

    public static IOssUploadHandler uploadHandler;

    public static IOssCoverHandler coverHandler;

    public final static String DEFAULT = "default";

    public final static String TEMP = "temp";

    @PostConstruct
    public void init() {

        OssUtils.uploadHandler = minioUploadHandler;
        OssUtils.coverHandler = minioCoverHandler;

    }

    /**
     * 上传文件
     * @param file
     * @param code
     * @return
     */
    public static OssObject putObject(MultipartFile file, String code) {
        OssObject ossObject = uploadHandler.uploadObject(file, code);
        return ossObject;
    }

    /**
     * 覆盖上传
     * @param file
     * @param ossUrl
     * @return
     */
    public static OssObject coverObject(MultipartFile file, String ossUrl) {
        OssObject ossObject = coverHandler.coverObject(file, ossUrl);
        return ossObject;
    }

}

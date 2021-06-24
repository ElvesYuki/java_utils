package com.elvesyuki.javautils.oss.dispatch;

import com.elvesyuki.javautils.oss.factory.IOssFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @ClassName OssUploadDispatch
 * @Description 适配器接口
 * @Author luohuan
 * @Date 2021/3/9 上午10:13
 */
@Component
public class OssUploadDispatch implements IOssDispatch {

    private static final Logger logger = LoggerFactory.getLogger(OssUploadDispatch.class);

    @Resource
    private IOssFactory xmoUploadFactory;

    @Override
    public IOssFactory dispatch(MultipartFile file, String code) {

        if (!StringUtils.hasText(code)) {
            logger.error("对象存储参数异常");
        }

        if ("default".equals(code) || "temp".equals(code)) {
            return xmoUploadFactory;
        }

        return xmoUploadFactory;

    }

}

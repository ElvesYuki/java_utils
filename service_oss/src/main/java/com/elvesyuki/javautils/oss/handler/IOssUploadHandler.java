package com.elvesyuki.javautils.oss.handler;

import com.elvesyuki.javautils.oss.object.OssObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssUploadHandler
 * @Description 上传处理器接口
 * @Author luohuan
 * @Date 2021/3/9 下午12:26
 */
public interface IOssUploadHandler {

    OssObject uploadObject(MultipartFile file, String code);

}

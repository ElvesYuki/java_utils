package com.elvesyuki.javautils.oss.handler;

import com.elvesyuki.javautils.oss.factory.IOssFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.elvesyuki.javautils.oss.dispatch.IOssDispatch;
import com.elvesyuki.javautils.oss.object.OssObject;

import javax.annotation.Resource;

/**
 * @ClassName MinioUploadHandler
 * @Description 上传处理器
 * @Author luohuan
 * @Date 2021/3/9 下午12:27
 */
@Component
public class MinioUploadHandler implements IOssUploadHandler {

    @Resource
    private IOssDispatch ossUploadDispatch;

    @Override
    public OssObject uploadObject(MultipartFile file, String code) {
        IOssFactory IOssFactory = ossUploadDispatch.dispatch(file, code);
        OssObject ossObject = IOssFactory.getOssObject(file, code);
        return ossObject;
    }
}

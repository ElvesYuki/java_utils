package com.elvesyuki.javautils.oss.factory;

import com.elvesyuki.javautils.oss.object.OssImgObject;
import com.elvesyuki.javautils.oss.object.OssObject;
import com.elvesyuki.javautils.oss.operator.IOssUploadOperator;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 用户对象存储工厂类
 */
@Component
public class XmoUserFactory extends XmoUploadFactory {

    @Resource
    private IOssUploadOperator ossUploadOperator;


    @Override
    public OssObject getOssObject(MultipartFile file, String code) {
        return super.getOssObject(file, code);
    }

    private OssImgObject getUserAvatarObject(MultipartFile file) {
        return null;
    }

}

package oss.factory;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import oss.object.ImgOssObject;
import oss.object.OssObject;
import oss.operator.IOssUploadOperator;

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

    private ImgOssObject getUserAvatarObject(MultipartFile file) {
        return null;
    }

}

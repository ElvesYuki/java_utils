package com.elvesyuki.javautils.oss.factory;

import com.elvesyuki.javautils.oss.object.OssObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName VianCoverFactory
 * @Description 对象工厂--覆盖上传
 * @Author luohuan
 * @Date 2021/3/9 下午4:45
 */
@Component
public class XmoCoverFactory implements IOssFactory {

    @Override
    public OssObject getOssObject(MultipartFile file, String code) {

        return null;
    }
}

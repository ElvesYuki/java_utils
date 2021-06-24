package com.elvesyuki.javautils.oss.factory;

import org.springframework.web.multipart.MultipartFile;
import com.elvesyuki.javautils.oss.object.OssObject;

/**
 * @ClassName OssFactory
 * @Description 对象工厂类策略接口
 * @Author luohuan
 * @Date 2021/3/8 下午4:25
 */
public interface IOssFactory {

    OssObject getOssObject(MultipartFile file, String code);

}

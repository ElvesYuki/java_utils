package com.elvesyuki.javautils.oss.operator;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssOperator
 * @Description 上传操作者接口
 * @Author luohuan
 * @Date 2021/3/8 下午4:27
 */
public interface IOssUploadOperator {

    void putObject(MultipartFile file, String bucketName, String fileUrl);

}

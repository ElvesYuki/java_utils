package com.elvesyuki.javautils.oss.operator;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssCoverOperator
 * @Description 覆盖操作者接口
 * @Author luohuan
 * @Date 2021/3/9 下午4:46
 */
public interface IOssCoverOperator {

    void coverObject(MultipartFile file, String bucketName, String fileUrl);

}

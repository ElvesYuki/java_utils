package com.elvesyuki.javautils.oss.handler;

import com.elvesyuki.javautils.oss.object.OssObject;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName MinioCoverHandler
 * @Description 覆盖上传处理器
 * @Author luohuan
 * @Date 2021/3/9 下午3:55
 */
@Component
public class MinioCoverHandler implements IOssCoverHandler {
    @Override
    public OssObject coverObject(MultipartFile file, String OssUrl) {
        return null;
    }
}

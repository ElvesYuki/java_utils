package oss.handler;

import org.springframework.web.multipart.MultipartFile;
import oss.object.OssObject;

/**
 * @ClassName OssCoverHandler
 * @Description 覆盖上传处理器接口
 * @Author luohuan
 * @Date 2021/3/9 下午3:54
 */
public interface IOssCoverHandler {

    OssObject coverObject(MultipartFile file, String OssUrl);

}

package oss.factory;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import oss.object.OssObject;
import oss.operator.IOssUploadOperator;
import oss.util.OssFunctionUtils;

import javax.annotation.Resource;

/**
 * @ClassName VianMinioFactory
 * @Description 对象工厂--上传
 * @Author luohuan
 * @Date 2021/3/8 下午4:48
 */
@Component
public class XmoUploadFactory implements IOssFactory {

    private final String DEFAULT = "default/";

    private final String TEMP = "temp/";

    private final String BUCKET_NAME = "java-utils";

    @Resource
    private IOssUploadOperator ossUploadOperator;

    @Override
    public OssObject getOssObject(MultipartFile file, String code) {

        String fileUrl = null;

        if ("default".equals(code)) {
            fileUrl = DEFAULT;
        } else if ("temp".equals(code)) {
            fileUrl = TEMP;
        }

        String generateFileUrl = OssFunctionUtils.generateFileUrl(file, fileUrl);

        String bucketName = BUCKET_NAME;

        ossUploadOperator.putObject(file, bucketName, generateFileUrl);

        OssObject ossObject = new OssObject();
        ossObject.setFileName(file.getOriginalFilename());
        ossObject.setContentType(file.getContentType());
        ossObject.setUrl("/"+ bucketName + "/" +generateFileUrl);
        ossObject.setSize(file.getSize());

        return ossObject;
    }

}

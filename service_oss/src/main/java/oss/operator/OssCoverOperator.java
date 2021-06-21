package oss.operator;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import oss.client.IXmoOssClient;
import oss.client.XmoMinioClient;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName OssCoverOperation
 * @Description 覆盖操作者
 * @Author luohuan
 * @Date 2021/3/9 下午4:50
 */
public class OssCoverOperator implements IOssCoverOperator {
    private static final Logger logger = LoggerFactory.getLogger(OssUploadOperator.class);

//    @Resource
//    private IExceptionHandler IExceptionHandler;

    @Resource
    private IXmoOssClient ossClientBean;

    @Resource
    private IXmoOssClient ossClientWrite;

    @Resource
    private IXmoOssClient ossClientRead;

    private MinioClient minioClient;

    @PostConstruct
    public void init() {

        IXmoOssClient iXmoOssClient = ossClientBean.getOssClient();
        if (iXmoOssClient instanceof XmoMinioClient) {
            this.minioClient = ((XmoMinioClient) iXmoOssClient).getMinioClient();
        }

    }

    @Override
    public void coverObject(MultipartFile file, String bucketName, String fileUrl) {
        //根据文件获取输入流
        InputStream inputStream = null;
        try {

            //根据文件获取输入流
            inputStream = file.getInputStream();

            //根据文件获取文件类型
            String contentType = file.getContentType();

            this.minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileUrl)
                            .stream(inputStream, -1, 10485760)
                            .contentType(contentType)
                            .build()
            );

            logger.info("储存路径----" + "/" + bucketName + "/" + fileUrl);
//            throw new VianException("发生异常");

        } catch (Exception e) {
//            VianException vianException = exceptionHandler.catchException(e);
//            throw new VianException("发生未知错误");
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error("Minio方法InputStream资源释放错误" + e.getMessage());
            }
        }
    }
}
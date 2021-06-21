package oss.config;

import io.minio.MinioClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oss.client.IXmoOssClient;
import oss.client.XmoMinioClient;
import oss.client.XmoMinioReadClient;
import oss.client.XmoMinioWriteClient;

/**
 * 对象存储配置-minio
 */
@Configuration
public class OssConfig {

    private static final Logger logger = LoggerFactory.getLogger(OssConfig.class);

    @Value("${Minio.endPoint}")
    private String endPoint;

    @Value("${Minio.port}")
    private Integer port;

    @Value("${Minio.url}")
    private String url;

    @Value("${Minio.accessKey}")
    private String accessKey;

    @Value("${Minio.secretKey}")
    private String secretKey;

    @Value("${Minio.write.accessKey}")
    private String writeAccessKey;

    @Value("${Minio.write.secretKey}")
    private String writeSecretKey;

    @Value("${Minio.read.accessKey}")
    private String readAccessKey;

    @Value("${Minio.read.secretKey}")
    private String readSecretKey;

    /**
     * minio客户端
     * @return
     */
    @Bean(name = "ossClientBean")
    public IXmoOssClient minioClient() {

        MinioClient minioClient = MinioClient.builder()
                .endpoint(url)
                .credentials(accessKey, secretKey)
                .build();
        logger.info("minioClientBean创建成功");
        XmoMinioClient xmoMinioClient = new XmoMinioClient();
        xmoMinioClient.setMinioClient(minioClient);

        return xmoMinioClient;
    }

    /**
     * minio写客户端
     * @return
     */
    @Bean(name = "ossClientWrite")
    public IXmoOssClient minioClientWrite() {

        MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint, port, false)
                .credentials(accessKey, secretKey)
                .build();
        logger.info("minioClientWrite创建成功");
        XmoMinioWriteClient xmoMinioWriteClient = new XmoMinioWriteClient();
        xmoMinioWriteClient.setMinioClient(minioClient);

        return xmoMinioWriteClient;
    }

    /**
     * minio读客户端
     * @return
     */
    @Bean(name = "ossClientRead")
    public IXmoOssClient minioClientRead() {

        MinioClient minioClient = MinioClient.builder()
                .endpoint(endPoint, port, false)
                .credentials(accessKey, secretKey)
                .build();
        logger.info("minioClientRead创建成功");
        XmoMinioReadClient xmoMinioReadClient = new XmoMinioReadClient();
        xmoMinioReadClient.setMinioClient(minioClient);

        return xmoMinioReadClient;
    }

}

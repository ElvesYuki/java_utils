package oss.client;

import io.minio.MinioClient;

/**
 * @ClassName MinioClient
 * @Description 对象存储操作客户端接口
 * @Author luohuan
 * @Date 2021/3/5 下午3:33
 */
public class XmoMinioWriteClient implements IXmoOssClient {

    private MinioClient minioClient;

    @Override
    public IXmoOssClient getOssClient() {
        return this;
    }

    public MinioClient getMinioClient() {
        return minioClient;
    }

    public void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }
}

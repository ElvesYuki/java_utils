package com.elvesyuki.javautils.oss.util;

import com.elvesyuki.javautils.normal.dto.XmoException;
import com.elvesyuki.javautils.oss.client.IXmoOssClient;
import com.elvesyuki.javautils.oss.client.XmoMinioClient;
import com.elvesyuki.javautils.oss.handler.IExceptionHandler;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/7 上午11:02
 */
@Component
public class OssMinioUtils {

    private static final Logger logger = LoggerFactory.getLogger(OssMinioUtils.class);

    private static IExceptionHandler STATIC_I_EXCEPTION_HANDLER = null;

    private static MinioClient MINIO_CLIENT = null;

    @Resource
    private IExceptionHandler iExceptionHandler;

    @Resource
    private IXmoOssClient ossClientBean;

    @Resource
    private IXmoOssClient ossClientWrite;

    @Resource
    private IXmoOssClient ossClientRead;

    @PostConstruct
    public void init() {
        OssMinioUtils.STATIC_I_EXCEPTION_HANDLER = iExceptionHandler;
        IXmoOssClient ossClient = ossClientBean.getOssClient();
        if (ossClient instanceof XmoMinioClient) {
            OssMinioUtils.MINIO_CLIENT = ((XmoMinioClient) ossClient).getMinioClient();
        }
    }

    /**
     * 查看bucket是否存在
     * @param bucketName
     * @return
     */
    public static Boolean bucketExist(String bucketName) {

        boolean found = false;

        try {
            found = MINIO_CLIENT.bucketExists(BucketExistsArgs.builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
//            throw  STATIC_I_EXCEPTION_HANDLER.catchException(e);
//            throw new VianException("发生未知错误");
        }

        logger.info(bucketName + "--Bucket存在");

        return found;

    }

    /**
     * 查看object是否存在
     * @param bucketName
     * @param object
     * @param versionId 未开启版本控制则填null
     * @return
     */
    public static StatObjectResponse statObject(String bucketName, String object, String versionId) {

        Boolean bucketExist = bucketExist(bucketName);

        if (!bucketExist) {
            throw new XmoException();
        }

        StatObjectArgs.Builder builder = StatObjectArgs.builder()
                .bucket(bucketName)
                .object(object);

        if (null != versionId) {
            builder.versionId(versionId);
        }

        StatObjectResponse statObjectResponse = null;

        try {
            statObjectResponse = MINIO_CLIENT.statObject(builder.build());
        } catch (Exception e) {
//            throw  STATIC_I_EXCEPTION_HANDLER.catchException(e);
//            throw new VianException("发生未知错误");
        }

        return statObjectResponse;
    }

    /**
     * 查看object是否存在
     * @param ossUrl 文件访问路径
     * @param versionId 未开启版本控制则填null
     * @return
     */
    public static StatObjectResponse statObject(String ossUrl, String versionId) {

        Map<String, String> bucketNameAndObjectName = OssFunctionUtils.getBucketNameAndObjectName(ossUrl);

        String bucketName = bucketNameAndObjectName.get(OssFunctionUtils.MAP_KEY_BUCKET_NAME);

        Boolean bucketExist = bucketExist(bucketName);

        if (!bucketExist) {
            throw new XmoException();
        }

        String objectName = bucketNameAndObjectName.get(OssFunctionUtils.MAP_KEY_OBJECT_NAME);

        StatObjectArgs.Builder builder = StatObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName);

        if (null != versionId) {
            builder.versionId(versionId);
        }

        StatObjectResponse statObjectResponse = null;

        try {
            statObjectResponse = MINIO_CLIENT.statObject(builder.build());
        } catch (Exception e) {
//            throw  STATIC_I_EXCEPTION_HANDLER.catchException(e);
//            throw new VianException("发生未知错误");
        }

        return statObjectResponse;
    }


}

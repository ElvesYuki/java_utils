package com.elvesyuki.javautils.oss.object;

import com.elvesyuki.javautils.oss.enums.ObjectTypeEnum;
import com.elvesyuki.javautils.oss.enums.OssStoragePathEnum;
import com.elvesyuki.javautils.oss.util.OssMinioUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/7/6 下午2:55
 */
@Component
public class OssStorageMap {

    private static final Logger logger = LoggerFactory.getLogger(OssStorageMap.class);

    /**
     * Oss 路径-枚举类MAP
     */
    public static final Map<String, OssStoragePathEnum> OSS_PATH_MAP = new HashMap<>();

    /**
     * Oss 对象类型类MAP
     */
    public static final Map<String, ObjectTypeEnum> OBJECT_TYPE_MAP = new HashMap<>();

    /**
     * 非文件的默认对象名字 如default.json  default.html
     */
    public static final Map<String, String> DEFAULT_OSS_NAME_MAP = new HashMap<>();

    @PostConstruct
    public void init() {

        List<OssStoragePathEnum> ossStoragePathEnums = Arrays.asList(OssStoragePathEnum.values());

        ossStoragePathEnums.forEach(ossStoragePathEnum -> {
            OSS_PATH_MAP.put(ossStoragePathEnum.getPathCode(), ossStoragePathEnum);

            // 检查所有的bucketName 是否正常
            Boolean bucketExist = OssMinioUtils.bucketExist(ossStoragePathEnum.getBucketName());

            if (!bucketExist) {
                logger.error("Minio存储异常，bucketName 配置错误");
            }
            logger.info("Minio存储检查正常");

        });

        logger.info("OSS_PATH_MAP--size" + OSS_PATH_MAP.size());

        List<ObjectTypeEnum> objectTypeEnums = Arrays.asList(ObjectTypeEnum.values());

        objectTypeEnums.forEach(objectTypeEnum -> {
            OBJECT_TYPE_MAP.put(objectTypeEnum.getObjectType(), objectTypeEnum);

            if (objectTypeEnum.getObjectType().equals(ObjectTypeEnum.JSON.getObjectType())) {
                DEFAULT_OSS_NAME_MAP.put(objectTypeEnum.getObjectType(), "default.json");
            } else if (objectTypeEnum.getObjectType().equals(ObjectTypeEnum.HTML.getObjectType())) {
                DEFAULT_OSS_NAME_MAP.put(objectTypeEnum.getObjectType(), "default.html");
            } else {
                logger.info("正常");
            }

        });

        logger.info("OBJECT_TYPE_MAP--size" + OBJECT_TYPE_MAP.size());

    }

}

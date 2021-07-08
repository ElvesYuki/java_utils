package com.elvesyuki.javautils.oss.util;

import com.elvesyuki.javautils.normal.dto.XmoException;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * @ClassName OssUtils
 * @Description Oss基础操作工具类
 * @Author luohuan
 * @Date 2021/3/9 上午10:03
 */
public class OssFunctionUtils {

    public static final String MAP_KEY_BUCKET_NAME = "bucketName";

    public static final String MAP_KEY_FILE_URL = "fileUrl";

    public static final String MAP_KEY_FILE_NAME = "fileName";

    public static final String MAP_KEY_OBJECT_NAME = "objectName";

    /**
     * 获取唯一文件名
     *
     * @return 生成的文件名（不包含后缀）
     */
    public static String generateUUID() {
        char[] toUuid = new char[32];
        char[] uuid = UUID.randomUUID().toString().toCharArray();
        System.arraycopy(uuid, 0, toUuid, 0, 8);
        System.arraycopy(uuid, 9, toUuid, 8, 4);
        System.arraycopy(uuid, 14, toUuid, 12, 4);
        System.arraycopy(uuid, 19, toUuid, 16, 4);
        System.arraycopy(uuid, 24, toUuid, 20, 12);
        return new String(toUuid);
    }

    /**
     * 获取文件后缀
     *
     * @return 后缀
     */
    public static String getFileSuffix(String originalFilename) {

        if ("".equals(originalFilename)) {
            throw new XmoException("文件名不能为空");
        }

        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

    }

    /**
     * 生成文件存储路径(不包含bucketName)
     * 主要做：fileUrl+UUID+后缀
     * file = 00/00/
     * uuid = uuid
     * 后缀 = .mp4
     * <p>
     * 返回  00/00/uuid.mp4
     *
     * @return 生成的文件存储路径(不包含bucketName)
     */
    public static String generateFileUrl(MultipartFile multipartFile, String fileUrl) {

        StringBuilder stringBuilder = new StringBuilder();

        //根据文件获取文件名
        String originalFilename = multipartFile.getOriginalFilename();
        if (null == originalFilename) {
            throw new XmoException("获取文件名出错");
        }
        //拼接文件路径
        stringBuilder.append(fileUrl);
        //拼接文件名
        stringBuilder.append(generateUUID());
        //拼接文件后缀
        stringBuilder.append(".");
        stringBuilder.append(getFileSuffix(originalFilename));

        return stringBuilder.toString();
    }

    /**
     * 生成文件存储路径(不包含bucketName)
     * 主要做：fileUrl+UUID+后缀
     * file = 00/00/
     * uuid = uuid
     * 后缀 = .mp4
     * <p>
     * 返回  00/00/uuid.mp4
     *
     * @return 生成的文件存储路径(不包含bucketName)
     */
    public static String generateFileUrl(String fileName, String fileUrl) {

        StringBuilder stringBuilder = new StringBuilder();

        //根据文件获取文件名
        if (null == fileName) {
            throw new XmoException("获取文件名出错");
        }
        //拼接文件路径
        stringBuilder.append(fileUrl);
        //拼接文件名
        stringBuilder.append(generateUUID());
        //拼接文件后缀
        stringBuilder.append(".");
        stringBuilder.append(getFileSuffix(fileName));

        return stringBuilder.toString();
    }

    /**
     * 生成文件存储路径(不包含bucketName)
     * 主要做：fileUrl+UUID+后缀
     * file = 00/00/
     * uuid = uuid
     * 后缀 = .mp4
     * <p>
     * 返回  00/00/uuid.mp4
     *
     * @return 生成的文件存储路径(不包含bucketName)
     */
//    public static String generateFileUrl(String fileUrl, String fileSuffix) {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        //拼接文件路径
//        stringBuilder.append(fileUrl);
//        //拼接文件名
//        stringBuilder.append(generateUUID());
//        //拼接文件后缀
//        stringBuilder.append(fileSuffix);
//
//        return stringBuilder.toString();
//    }

    /**
     * 根据文件的真实路径分割路径参数
     *
     * @param fileUrl
     * @return
     *
     * HashMap<String, String>
     * bucketName
     * fileUrl
     * fileName
     */
    public static Map<String, String> getFileUrlSuffixBucketName(String fileUrl) {

        String[] fileUrlSplit = fileUrl.split(Pattern.quote("/"));

        HashMap<String, String> stringStringHashMap = new HashMap<>(4);

        //bucketName: bucketName
        stringStringHashMap.put(MAP_KEY_BUCKET_NAME, fileUrlSplit[1]);

        //拼接中间的路径名：路径/路径/
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("/");
        for (int i = 2; i < fileUrlSplit.length - 1; i++) {
            stringBuilder
                    .append(fileUrlSplit[i])
                    .append("/");
        }
        stringStringHashMap.put(MAP_KEY_FILE_URL, stringBuilder.toString());

        //fileName：名字.后缀
        stringStringHashMap.put(MAP_KEY_FILE_NAME, fileUrlSplit[fileUrlSplit.length - 1]);

        return stringStringHashMap;
    }

    /**
     * 根据文件的真实路径分割路径参数
     *
     * @param fileUrl
     * @return
     *
     * HashMap<String, String>
     * bucketName
     * fileUrl
     * objectName
     */
    public static Map<String, String> getBucketNameAndObjectName(String fileUrl) {

        String[] fileUrlSplit = fileUrl.split(Pattern.quote("/"));

        HashMap<String, String> stringStringHashMap = new HashMap<>(4);

        //bucketName: bucketName
        stringStringHashMap.put(MAP_KEY_BUCKET_NAME, fileUrlSplit[1]);

        //拼接中间的路径名：路径/路径/
        StringBuilder stringBuilder = new StringBuilder();
        //stringBuilder.append("/");
        for (int i = 2; i < fileUrlSplit.length - 1; i++) {
            stringBuilder
                    .append(fileUrlSplit[i])
                    .append("/");
        }
        stringStringHashMap.put(MAP_KEY_FILE_URL, stringBuilder.toString());

        //objectName
        stringBuilder.append(fileUrlSplit[fileUrlSplit.length - 1]);

        //fileName：名字.后缀
        stringStringHashMap.put(MAP_KEY_OBJECT_NAME, stringBuilder.toString());

        return stringStringHashMap;
    }

    /**
     * 生成文件存储路径(包含bucketName)
     * bucketName 111
     * fileUrl 00/00/uuid.mp4
     *
     * @return 生成的文件存储路径(不包含bucketName)  /111/00/00/uuid.mp4
     */
    public static String getBucketNameFileUrl(String bucketName, String fileUrl) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
                .append("/")
                .append(bucketName)
                .append("/")
                .append(fileUrl);

        return stringBuilder.toString();
    }

    /**
     * 获取内容字符串转输入流
     *
     * @param content 内容字符串
     * @return
     */
    public static InputStream strToInputStream(String content) {

        if (null == content || "".equals(content.trim())) {
            throw new XmoException("不合法的输入内容");
        }

        ByteArrayInputStream contentInputStream = new ByteArrayInputStream(content.getBytes());

        return contentInputStream;

    }

}

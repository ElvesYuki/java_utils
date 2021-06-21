package oss.handler;

import io.minio.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * @ClassName MinioExceptionHandler
 * @Description 异常捕捉处理器
 * @Author luohuan
 * @Date 2021/3/9 上午10:56
 */
@Component
public class MinioExceptionHandler implements IExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MinioExceptionHandler.class);

    private HashMap<String, String> exceptionMap;

    @PostConstruct
    public void init() {

        HashMap<String, String> vianExceptionHashMap = new HashMap<>();

        vianExceptionHashMap.put(ErrorResponseException.class.getName(), "S3服务返回了错误响应");
        vianExceptionHashMap.put(IllegalArgumentException.class.getName(), "传递了无效的参数");
        vianExceptionHashMap.put(InsufficientDataException.class.getName(), "InputStream中没有足够的可用数据");
        vianExceptionHashMap.put(InternalException.class.getName(), "Oss内部库错误");
        vianExceptionHashMap.put(InvalidKeyException.class.getName(), "缺少HMAC SHA-256库");
        vianExceptionHashMap.put(InvalidResponseException.class.getName(), "S3服务返回无效或没有错误响应");
        vianExceptionHashMap.put(IOException.class.getName(), "S3操作上的I/O错误");
        vianExceptionHashMap.put(NoSuchAlgorithmException.class.getName(), "缺少MD5或SHA-256摘要库");
        vianExceptionHashMap.put(ServerException.class.getName(), "HTTP服务器错误");
        vianExceptionHashMap.put(XmlParserException.class.getName(), "XML解析错误");

        this.exceptionMap = vianExceptionHashMap;
        logger.info("Minio异常捕捉器加载完成");

    }

//    @Override
//    public VianException catchException(Exception e) {
//
//        VianException vianException = null;
//
//        if (exceptionMap.containsKey(e.getClass().getName())) {
//            vianException = new VianException(this.exceptionMap.get(e.getClass().getName()), e);
//        } else {
//            vianException = new VianException(CommonBizCodeEnum.DEFAULT);
//        }
//        logger.error(e.getMessage());
//
//        return vianException;
//    }
}

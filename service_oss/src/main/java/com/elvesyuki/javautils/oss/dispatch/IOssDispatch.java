package com.elvesyuki.javautils.oss.dispatch;


import com.elvesyuki.javautils.oss.factory.IOssFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName OssDispatch
 * @Description 适配器接口
 * @Author luohuan
 * @Date 2021/3/9 上午10:12
 */
public interface IOssDispatch {

    IOssFactory dispatch(MultipartFile file, String code);

}

package oss.dispatch;


import org.springframework.web.multipart.MultipartFile;
import oss.factory.IOssFactory;

/**
 * @ClassName OssDispatch
 * @Description 适配器接口
 * @Author luohuan
 * @Date 2021/3/9 上午10:12
 */
public interface IOssDispatch {

    IOssFactory dispatch(MultipartFile file, String code);

}

package com.elvesyuki.javautils.normal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/6/21 下午3:56
 */
@Configuration
public class SpringMvcConfig {

    /**
     * 显示声明CommonsMultipartResolver为multipartResolver
     * @return
     */
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        //resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        resolver.setResolveLazily(true);
        resolver.setMaxInMemorySize(50*1024*1024);
        //上传文件大小 50M 50*1024*1024
        resolver.setMaxUploadSize(1024*1024*1024);
        return resolver;
    }

}

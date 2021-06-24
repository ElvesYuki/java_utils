package com.elvesyuki.javautils.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/6/21 下午2:32
 */
@EnableSwagger2WebMvc
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class} , scanBasePackages = {"com.elvesyuki.javautils.**"})
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class);
    }
}

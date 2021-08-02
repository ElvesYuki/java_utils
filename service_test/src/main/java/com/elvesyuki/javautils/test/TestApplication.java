package com.elvesyuki.javautils.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @ClassName AdminOrgController
 * @Description
 * @Author luohuan
 * @Date 2021/8/2 下午12:40
 */
@EnableSwagger2WebMvc
@SpringBootApplication(exclude = {MultipartAutoConfiguration.class} , scanBasePackages = {"com.elvesyuki.javautils.**"})
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class);
    }
}

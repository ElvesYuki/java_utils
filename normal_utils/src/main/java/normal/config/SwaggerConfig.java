package normal.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ：luohuan
 * @date ：Created in 2021/4/22 下午12:26
 * @description：
 * @modified By：
 */
@EnableSwagger2WebMvc
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组名称
//                .groupName("2.X版本")
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档的标题
                .title("xmoOnlineAPP")
                .contact( new Contact("LUOHUAN","https://www.evlesyuki.com/","1026770043@qq.com"))
                // 设置文档的描述
                .description("xmoOnlineAPP 接口文档")
                // 设置文档的版本信息-> 1.0.0 Version information
                .version("1.0.0")
                // 设置文档的License信息->1.3 License information
                .termsOfServiceUrl("https://www.evlesyuki.com/")
                .build();
    }

}

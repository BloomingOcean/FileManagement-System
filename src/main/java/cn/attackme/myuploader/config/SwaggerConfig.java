package cn.attackme.myuploader.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 启用 Swagger2
public class SwaggerConfig {

    @Value("${sys.version}")
    private String systemPublish;

    @Bean
    public Docket uploaderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("UploaderAPI")
                .pathMapping("/")
                .select()
                    .apis(RequestHandlerSelectors.basePackage("cn.attackme.myuploader.controller"))
                    .paths(Predicates.not(PathSelectors.regex("/Ex.*")))//不监控 测试api
                    .paths(PathSelectors.regex("/.*"))//过滤的接口
                .build()
                .apiInfo(new ApiInfoBuilder()
                        .title("文件管理系统API")
                        .version(systemPublish)
                        .contact(new Contact("Liyang", "1519365158", "@qq.com"))
                        .build());
    }
}

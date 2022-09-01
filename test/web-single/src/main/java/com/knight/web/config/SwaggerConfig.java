package com.knight.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger配置类
 * <p>
 * <a href="http://localhost:8081/swagger-ui/">访问地址</a>
 *
 * @author TortoiseKnightB
 * @date 2022/09/01
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                .groupName("web-single")
                .select()
                // RequestHandlerSelectors, 配置要扫描接口的方式
                // basePackage: 指定要扫描的包
                // any(): 扫描全部
                // none(): 不扫描
                // withClassAnnotation: 扫描类上的注解, 参数是一个注解的反射对象 withClassAnnotation(RestController.class)
                // withMethodAnnotation: 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.knight.web.controller"))
                // paths 过滤什么路径 不显示到swagger文档中
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Horn of Plenty")
                .description("web-single 单体项目框架")
                .termsOfServiceUrl("https://github.com/TortoiseKnightB/Horn_of_Plenty/tree/test/test/web-single")
                .version("1.0.0")
                .build();
    }
}

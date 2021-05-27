package com.system.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * Swagger configuration.
 *
 * @author Sangeeth Perera
 * @version  2021.05.26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.system.shoppingcart"))
                .paths(regex("/.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Shopping Cart", "Api Documentation", "v1-docs", "", "", "", ""
        );
        return apiInfo;
    }
}
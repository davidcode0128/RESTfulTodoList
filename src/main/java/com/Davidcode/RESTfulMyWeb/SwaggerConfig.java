package com.Davidcode.RESTfulMyWeb;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()                
                .apis(RequestHandlerSelectors.basePackage("com.Davidcode.RESTfulMyWeb.controller"))
                .paths(PathSelectors.any())
                //.paths(Predicates.negate(PathSelectors.regex("/error.*")))
                //.paths(Predicate.not(PathSelectors.regex("/error.*")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "RESTful To Do List",
                "RESTful To Do List API",
                "v1.0",
                "",
                new Contact("Davidcode", "",
                        "ckorsock1@gmail.com"),
                "", "", Collections.emptyList());
    }
    
}
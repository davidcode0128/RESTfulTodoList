package com.Davidcode.RESTfulMyWeb;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebMvc
@Configuration
public class SwaggerConfig implements WebMvcConfigurer{

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
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
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.
          addResourceHandler("/swagger-ui/**")
          .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
          .resourceChain(false);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/swagger-ui/")
          .setViewName("forward:" + "/swagger-ui/index.html");
    }
}
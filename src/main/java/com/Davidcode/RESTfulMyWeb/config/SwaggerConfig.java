package com.Davidcode.RESTfulMyWeb.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
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
        		.apiInfo(apiInfo())
                .select()               
                // 搜尋有@Tag註解的類，並生成一個對應的分組；類下面的所有http請求方法，都會生成對應的API接口
            	// 通過這個配置，就可以將那些沒有添加@Tag註解的控制器類排除掉
                //.apis(RequestHandlerSelectors.withClassAnnotation(Tag.class))
                .apis(RequestHandlerSelectors.basePackage("com.Davidcode.RESTfulMyWeb.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("RESTful To Do List")
    			.description("RESTful To Do List API")
    			.version("v1.0")
    			.termsOfServiceUrl("https://davidcode.netlify.app/")
    			.contact(new Contact("Davidcode", "https://davidcode.netlify.app/",
                        "ckorsock1@gmail.com"))
    			.build();
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
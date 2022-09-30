package com.Davidcode.RESTfulMyWeb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
        .addResourceLocations("classpath:/statics/");        
        registry.addResourceHandler("/swagger-ui.html") // 解決 swagger ui 404 錯誤
        .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("resources/**")
        .addResourceLocations("/resources/");
    }
	
}

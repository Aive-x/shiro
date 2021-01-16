package com.springboot.shiro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author xutianhong
 * @Date 2021/1/9 3:54 下午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 通过此处的配置，使得全局支持跨域访问（目前主要用于前端使用axios访问后端接口）*/
    @Override
    public void addCorsMappings(CorsRegistry registry) {
/*        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(1800)
                .allowedOrigins("*")
                .allowCredentials(true);*/
    }

}

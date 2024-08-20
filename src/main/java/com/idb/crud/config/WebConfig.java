package com.idb.crud.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("Access-Control-Allow-Origin", "*")
                // .allowCredentials(true)
                // .allowedOriginPatterns("*")
                .maxAge(3600)
                .exposedHeaders("Access-Control-Allow-Origin");

    }
}

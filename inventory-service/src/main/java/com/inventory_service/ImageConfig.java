package com.inventory_service;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImageConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        Path uploadPath = Paths.get("uploads/products");

        registry.addResourceHandler("/images/**")
                .addResourceLocations(uploadPath.toUri().toString());
    }
}


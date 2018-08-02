package com.cheb.inventorybook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig {

//    @Bean
//    public WebMvcConfigurer configurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addResourceHandlers(ResourceHandlerRegistry registry) {
//                if (!registry.hasMappingForPattern("/webjars/**")) {
//                    registry.addResourceHandler("/webjars/**").addResourceLocations(
//                            "classpath:/META-INF/resources/webjars/");
//                }
//                if (!registry.hasMappingForPattern("/static/**")) {
//                    registry.addResourceHandler("/static/**").addResourceLocations(
//                            "/resources/static/**");
//                }
//            }
//        };
//    }
}

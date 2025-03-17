package dev.joaopdias.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${ALLOWED_ORIGIN}")
    private String allowedOrigin;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                if (allowedOrigin != null)
                    registry.addMapping("/**")
                            .allowedOrigins(allowedOrigin)
                            .allowedMethods("*")
                            .allowedHeaders("*");
                else
                    registry.addMapping("/**")
                            .allowedOrigins("http://localhost:4000", "http://localhost:4200")
                            .allowedMethods("*")
                            .allowedHeaders("*");
            }
        };
    }
}

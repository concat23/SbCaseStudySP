package com.dev.sbauth.doc;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class DocConfig {

    @Bean
    RouterFunction<ServerResponse> staticResourceRouter(){
        return RouterFunctions.resources("/swagger-ui/**", new ClassPathResource("static/"));
    }
}

package com.getair.bookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Configuration
@EnableConfigurationProperties
@EnableSwagger2
public class SpringbootApplication {
    public static void main(String args[]){
        SpringApplication.run(SpringbootApplication.class,args);
    }
}

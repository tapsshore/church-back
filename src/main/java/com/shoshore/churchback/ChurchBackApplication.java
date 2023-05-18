package com.shoshore.churchback;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ChurchBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChurchBackApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title(" Church Management Service API documentation")
                .version("0.1")
                .description("This is the API documentation for the Church Management service for the  application - " +
                        " ")).addServersItem(new Server().url("http://localhost:8086")).addServersItem(new Server().url("https://test.chrch.net/api"));
    }
}

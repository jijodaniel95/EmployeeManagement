package com.company.employeemanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl("http://localhost:" + serverPort);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("support@employeemanagement.com");
        contact.setName("Employee Management API Support");
        contact.setUrl("https://www.employeemanagement.com");


        Info info = new Info()
                .title("Employee Management API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage employees.")
                .termsOfService("https://www.employeemanagement.com/terms");

        return new OpenAPI()
                .info(info)
                .servers(List.of(devServer));
    }
} 
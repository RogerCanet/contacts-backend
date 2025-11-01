package dev.rcanet.contacts.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
    
    @Bean
    public OpenAPI contactApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Contacts Api")
                .description("A simple CRUD API to manage contacts — built with Spring Boot 3 and documented using OpenAPI (Swagger).")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Rcanet Dev")
                    .email("roger.canet979@gmail.com")
                    .url("https://github.com/RogerCanet"))
                .license(new License()
                    .name("MIT License")
                    .url("https://opensource.org/licenses/MIT"))
                );
    }
}

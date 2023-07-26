package com.epam.project;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Cloth Shop API", version = "2.0", description = "This is a sample API for Spring Boot application for EPAM's Internal Lab program."))
@SecurityScheme(name = "clothShopApi", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class ClothShopAppJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClothShopAppJwtApplication.class, args);
    }


}

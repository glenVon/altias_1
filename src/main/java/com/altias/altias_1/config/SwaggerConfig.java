package com.altias.altias_1.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        String bd_altias = "Base de Datos Altias";
        return new OpenAPI()
                .info(new Info()
                        .title("API del Demonio")
                        .version("1.0")
                        .description("API para la gestión de usuarios del sistema Altias" + bd_altias)
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("soporte@altias.com")));
    }

    
}

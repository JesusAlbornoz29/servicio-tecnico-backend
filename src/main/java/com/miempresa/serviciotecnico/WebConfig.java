package com.miempresa.serviciotecnico;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Esto es para que permita todas las rutas
                .allowedOrigins("http://localhost:3000") // Aqui le marcamos el origen permitido
                .allowedMethods("GET","POST","PUT","DELETE", "OPTIONS")
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permitir credenciales
    }
}

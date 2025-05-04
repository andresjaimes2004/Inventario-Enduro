package com.enduro.inventario.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configuraciones adicionales si son necesarias
        return modelMapper;
    }

    // Otros beans de configuración pueden ir aquí
}

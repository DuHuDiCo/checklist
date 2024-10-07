package com.checklist.checklist.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Define los orígenes permitidos
        configuration.setAllowedOrigins(
                Arrays.asList("http://localhost:4200/", "http://192.168.1.171:8600", "http://201.184.154.82:8500",
                        "https://checklistgmjlocal.duckdns.org/", "http://checklistgmjexterno.duckdns.org/"));

        // Métodos permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Cabeceras permitidas
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

        // Permitir credenciales (cookies, tokens, etc.)
        configuration.setAllowCredentials(true);

        // Asocia la configuración a todas las rutas (/**)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

}

package com.altias.altias_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements WebMvcConfigurer { // Implementa la interfaz

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // Habilita la configuración CORS
            .csrf().disable()
            .authorizeHttpRequests()
                .requestMatchers("/api/v1/users/**").permitAll()
                .anyRequest().authenticated()
            .and()
            .httpBasic();
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("1234"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Override // Añade la anotación @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:8080")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
}

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;

// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     @Bean
//     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//         http
//             .csrf().disable()
//             .authorizeHttpRequests()
//                 .requestMatchers("/api/v1/users/**").permitAll() // Permite acceso sin autenticación
//                 .anyRequest().authenticated()
//             .and()
//             .httpBasic();
        
//         return http.build();
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     // Usuario de prueba en memoria
//     @Bean
//     public InMemoryUserDetailsManager userDetailsService() {
//         UserDetails user = User.builder()
//             .username("admin") // Nombre de usuario
//             .password(passwordEncoder().encode("1234")) // Contraseña codificada
//             .roles("USER")
//             .build();
//         return new InMemoryUserDetailsManager(user);
//     }

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/api/**")
//             .allowedOrigins("http://localhost:3000") // Tu frontend
//             .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//             .allowedHeaders("*")
//             .allowCredentials(true)
//             .maxAge(3600);
//     }
// }
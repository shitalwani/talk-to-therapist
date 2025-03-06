package com.therapist.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter){
        this.jwtRequestFilter = jwtRequestFilter;
    }


    @Bean
    public void configure(HttpSecurity http) throws Exception {
        // Disable CSRF for stateless REST API
        http
                .csrf().disable()  // Disable CSRF protection (deprecated, but still works for now)
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()  // Open access to authentication endpoints
                .antMatchers("/admin/**").hasRole("ADMIN")  // Admin access only
                .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // User & Admin access
                .anyRequest().authenticated()  // All other requests require authentication
                .and()
                .addFilterBefore(jwtRequestFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);  // Add JWTmePasswordAuthenticationFilter
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // To encode user passwords
    }
}

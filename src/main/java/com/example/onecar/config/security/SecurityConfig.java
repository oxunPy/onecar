package com.example.onecar.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtUtil jwtUtil;
    private final UserDetailsService myUserDetailsService;

    public SecurityConfig(JwtUtil jwtUtil,
                          UserDetailsService myUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.myUserDetailsService = myUserDetailsService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> {
                    try {
                        csrf.disable()
                                .authorizeHttpRequests(auth -> {
                                    auth
                                            .requestMatchers("/api/v1/auth/**")
                                            .permitAll()
                                            .anyRequest()
                                            .authenticated();
                                });
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).sessionManagement(sessionManage -> {
                    sessionManage.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                }).addFilterBefore(new JwtAuthenticationFilter(jwtUtil, myUserDetailsService), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

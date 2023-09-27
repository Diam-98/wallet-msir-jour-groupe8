package com.wallet_app.walette_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {
    private UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf(csrf->csrf.disable())
//                .authorizeHttpRequests((authorize)->
//                        authorize.requestMatchers("/api/**").permitAll()
//                                .requestMatchers("/api/auth/register").permitAll()
//                                .requestMatchers("/api/auth/login").permitAll()
//                                .requestMatchers("/").permitAll()
//                                .anyRequest().authenticated()
//                );
//
//        return http.build();

        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest()
                .permitAll())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();

    }

//    csrf(csrf->csrf.disable())


}

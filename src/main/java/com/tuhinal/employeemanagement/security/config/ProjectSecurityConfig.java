package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.security.filter.JwtTokenGeneratorFilter;
import com.tuhinal.employeemanagement.security.filter.JwtTokenValidatorFilter;
import com.tuhinal.employeemanagement.security.filter.SecurityFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler hasandler = new CsrfTokenRequestAttributeHandler();
//        handler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(req -> req.disable())
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/register", "/notice", "/login").permitAll()
//                                .requestMatchers("/employee-info").hasAuthority("EMPLOYEE")
//                                .requestMatchers("/punch-in", "/attendance").hasAnyAuthority("EMPLOYEE", "SUPER_ADMIN")
                                .anyRequest().authenticated())
                //sudhu login thaklei jei api gula access korte parbe kono authorityr dorkar nei seigula ekhane dite hbe.)

                .addFilterBefore(new JwtTokenValidatorFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider());

        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}

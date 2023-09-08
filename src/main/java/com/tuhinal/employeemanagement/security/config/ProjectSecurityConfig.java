package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.security.filter.SecurityFilter;
import com.tuhinal.employeemanagement.security.filter.UnAuthorizedUserAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {
    
    @Autowired private UserDetailsService userDetailsService;
    @Autowired private UnAuthorizedUserAuthenticationEntryPoint unAuthorizedUserAuthenticationEntryPoint;
    @Autowired private SecurityFilter securityFilter;
    
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration
                                                                   authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(co -> co.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/api/register").permitAll()
                                .anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(unAuthorizedUserAuthenticationEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}

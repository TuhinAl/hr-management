package com.tuhinal.employeemanagement.security.config;

import com.tuhinal.employeemanagement.security.filter.JwtTokenValidatorFilter;
import com.tuhinal.employeemanagement.security.filter.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
@RequiredArgsConstructor
public class ProjectSecurityConfig {


    private final UserDetailsService userDetailsService;
    private final AuthenticationConfiguration authConfiguration;
    private final JwtUtil jwtUtil;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler hasandler = new CsrfTokenRequestAttributeHandler();
//        handler.setCsrfRequestAttributeName("_csrf");
        http.sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(req -> req.disable())
                .authorizeHttpRequests((requests) ->
                        requests.requestMatchers("/register", "/login").permitAll()
//                                .requestMatchers("/employee-info").hasAuthority("EMPLOYEE")
//                                .requestMatchers("/punch-in", "/attendance").hasAnyAuthority("EMPLOYEE", "SUPER_ADMIN")
                                .anyRequest().authenticated())
                //sudhu login thaklei jei api gula access korte parbe kono authorityr dorkar nei seigula ekhane dite hbe.)

                .addFilterBefore(new JwtTokenValidatorFilter(userDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class)
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

package com.tuhinal.employeemanagement.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
@Deprecated
/**
 * Please dont use this class, not uses anywhere, used for test purpose
 */
public class CsrfTokenFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        CsrfToken attribute = (CsrfToken) request.getAttribute(CsrfToken.class.getName());

        if (Objects.nonNull(attribute.getHeaderName())) {
            response.setHeader(attribute.getHeaderName(), attribute.getToken());
        }
        filterChain.doFilter(request, response);

    }
}

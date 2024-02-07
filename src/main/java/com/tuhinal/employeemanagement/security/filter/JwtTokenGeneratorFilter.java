package com.tuhinal.employeemanagement.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    public static final String JWT_HEADER = "Authorization";
    public static final String  KEY = "Thesearehugenumbersforanewappevenwhentakingintoaccountthatthemorethantwobillionmonthlyactive" +
            "Instagramuserswerepromotedtoinstallthisnewapp";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String username = request.getParameter("username");
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = new User(username, "", new ArrayList<>());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

            String token = Jwts
                    .builder()
                    .setIssuer("Tuhin")
                    .setSubject("JWT Token")
//                    .setSubject(username)
                    .claim("username", authenticationToken.getName())
                    .claim("authorities", populateAuthorities(authenticationToken.getAuthorities()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date((System.currentTimeMillis() + 60000)))
                    .signWith(secretKey)
                    .compact();
            response.addHeader(JWT_HEADER, "Bearer " + token);
        }
        chain.doFilter(request, response);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritySet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : collection) {
            authoritySet.add(grantedAuthority.getAuthority());
        }
        return String.join(",", authoritySet);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/api/auth/login");
    }
}

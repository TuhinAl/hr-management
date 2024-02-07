package com.tuhinal.employeemanagement.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtTokenValidatorFilter extends OncePerRequestFilter {

    @Autowired private UserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtUtil;

    public static final String JWT_HEADER = "Authorization";
    public static final    String KEY = "Thesearehugenumbersforanewappevenwhentakingintoaccountthatthemorethantwobillionmonthlyactive" +
            "Instagramuserswerepromotedtoinstallthisnewapp";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(JWT_HEADER);
        if (null != authHeader && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {

                SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts
                        .parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(token)
                        .getBody();

//                String username = claims.getSubject();
//                List<GrantedAuthority> authorities = new ArrayList<>();
                String username = String.valueOf(claims.get("username"));
                String authorities = (String) claims.get("authorities");

                if (null != username && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    boolean isValid = jwtUtil.isValidToken(token, userDetails.getUsername());
                    if (isValid) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null,
                                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }

            } catch (Exception e) {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
//                throw new BadCredentialsException("Invalid Token Received.");
            }
        }
        filterChain.doFilter(request, response);

    }
}

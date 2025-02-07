package com.tuhinal.employeemanagement.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class JwtUtil {


    public static final String KEY = "Thesearehugenumbersforanewappevenwhentakingintoaccountthatthemorethantwobillionmonthlyactive" +
            "Instagramuserswerepromotedtoinstallthisnewapp";

    private final Map<String, Long> tokenBlackList = new ConcurrentHashMap<>();

    public String generateJwtToken(String username, Authentication authentication) {
        String token = "";
        if (StringUtils.isNotBlank(username)) {
            SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
            token = Jwts.builder()
                    .setIssuer("Tuhin")
                    .setSubject(username)
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date((System.currentTimeMillis() + 600000000000L)))
                    .signWith(secretKey)
                    .compact();
        }
        return token;
    }


    public Claims getClaims(String token) {
        log.debug("Inside claims method.");
        SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims;
    }


    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    public boolean isValidToken(String token, String username) {
        String tokenUserName = getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authSet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            authSet.add(grantedAuthority.getAuthority());
        }
        return String.join(",", authSet);
    }

    public void invalidateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        long remainingTime = claims.getExpiration().getTime() - System.currentTimeMillis();
        tokenBlackList.put(token, System.currentTimeMillis() + remainingTime);
    }

}

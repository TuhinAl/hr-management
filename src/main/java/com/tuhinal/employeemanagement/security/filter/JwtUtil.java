package com.tuhinal.employeemanagement.security.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
@Component
public class JwtUtil{
    
    
    public String generateJwtToken(String username) {
        Map<String,Object> claims=new HashMap<>();
        String token = "";
        if (StringUtils.isNotBlank(username)) {
            String key = "Thesearehugenumbersforanewappevenwhentakingintoaccountthatthemorethantwobillionmonthlyactive" +
                    "Instagramuserswerepromotedtoinstallthisnewapp";
            SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
            token = Jwts.builder()
//                    .setIssuer("Tuhin")
                    .setSubject(username)
                    .setClaims(claims)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date((System.currentTimeMillis() + 600)))
                    .signWith(secretKey)
                    .compact();
        }
        return token;
    }
    
    
    public Claims getClaims(String token) {
    
        String key = "Thesearehugenumbersforanewappevenwhentakingintoaccountthatthemorethantwobillionmonthlyactive" +
                "Instagramuserswerepromotedtoinstallthisnewapp";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
    
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims;
    }
    
    
    
    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }
    
    public boolean isValidToken(String token,String username) {
        String tokenUserName=getSubject(token);
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

}

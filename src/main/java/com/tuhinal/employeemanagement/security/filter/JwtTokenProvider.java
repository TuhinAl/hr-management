package com.tuhinal.employeemanagement.security.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class JwtTokenProvider {


    private final String jwtSecret = generateSecretKey();

    private final Map<String, Long> tokenBlackList = new ConcurrentHashMap<>();

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
       /* String token = "";
        if (StringUtils.isNotBlank(username)) {
            SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
            token = Jwts.builder()
                    .subject(username)
                    .claim("username", authentication.getName())
                    .claim("authorities", populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date((System.currentTimeMillis() + 600000000000L)))
                    .signWith(key(), SignatureAlgorithm.HS256)
                    .compact();
        }*/
        String token = Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date((System.currentTimeMillis() + 600000000000L)))
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();


        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

   /* public Claims getClaims(String token) {
        log.debug("Inside claims method.");
        SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));

        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims;
    }*/

    // extract username from JWT token
    public String getUsername(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parse(token);
        return true;

    }

  /*  public boolean isValidToken(String token, String username) {
        String tokenUserName = getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }*/

   /* public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }*/

    /*public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }*/

   /* public String getSubject(String token) {
        return getClaims(token).getSubject();
    }*/

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authSet = new HashSet<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            authSet.add(grantedAuthority.getAuthority());
        }
        return String.join(",", authSet);
    }

    public String generateSecretKey() {
        // length means (32 bytes are required for 256-bit key)
        int length = 32;

        // Create a secure random generator
        SecureRandom secureRandom = new SecureRandom();

        // Create a byte array to hold the random bytes
        byte[] keyBytes = new byte[length];

        // Generate the random bytes
        secureRandom.nextBytes(keyBytes);

        // Encode the key in Base64 format for easier storage and usage
        return Base64.getEncoder().encodeToString(keyBytes);
    }



    // todo: please do not remove this code snippet

 /*   public void invalidateToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        long remainingTime = claims.getExpiration().getTime() - System.currentTimeMillis();
        tokenBlackList.put(token, System.currentTimeMillis() + remainingTime);
    }*/

}

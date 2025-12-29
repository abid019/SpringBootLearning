package com.abid123.module1introduction.services;

import com.abid123.module1introduction.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {
    @Value("${jwt.secretKey}")
    private String jwtToken;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtToken.getBytes(StandardCharsets.UTF_8));
    }

    //create jwt token
    public String generateJwtToken(User user) {
        return Jwts
                .builder()
                .subject(user.getId().toString())
                .claim("email",user.getEmail())
                .claim("roles", Set.of("ADMIN","USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60) )
                .signWith(getSecretKey())
                .compact();
    }


    //validate jwt token
    public Long getUserIdFromJwtToken(String token){
        Claims claims = Jwts.parser()
                            .verifyWith(getSecretKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();

        return Long.valueOf(claims.getSubject());
    }

}

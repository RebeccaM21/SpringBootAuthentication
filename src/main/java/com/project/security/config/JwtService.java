package com.project.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
public class JwtService {

    private static final String SECRET_KEY = "RYaktk4alloV8zQnJpsnguWSGQb6cf3T8ILMqhMlYhVFhq207VkFrrGjEtyq2m4K";

    public String extractUsername(String token) {
        return null;
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                // need to use sign in key to create, decode or generate a token
                // Sign in key is a secret that is used to digitally sign JWT - used to create signature part of JWT, to verify the sender of JWT is correct and message wasn't changed along the way
                .build()
                .parseClaimsJws(token)
                // to parse the token
                .getBody();
                // can get all the claims from the token
    }

    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}

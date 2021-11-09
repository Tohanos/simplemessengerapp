package com.example.simplemessengerapp.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class TokenAuthenticationService {
    private static final Long TOKEN_EXPIRY = 864000000L;
    private static final String SECRET = "de23gt50opq23gy2";
    private static final String TOKEN_PREFIX = "name";

    private HashMap<String, String> tokenMap = new HashMap<>();

    public String generateToken(String name) {
        if (tokenMap.containsKey(name))
            return tokenMap.get(name);
        String token = Jwts.builder()
                .setSubject(name)
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRY))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        tokenMap.put(name, token);
        return token;
    }

    public boolean checkToken(String name, String token) {
        if (!tokenMap.containsValue(token))
            return false;
        return name.equals(extractFromToken(token));
    }

    public String extractFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody().getSubject();
    }
}

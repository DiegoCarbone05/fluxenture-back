package com.servisub.core.shared.security.jwt;

import com.servisub.core.users.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    //Seteo la clave
    private final String SECRET_STRING = "DiegoCarbone-Fluxenture-Proyecto-Servisub-FRGP-2026";
    //La convierto en tipo Key
    private final Key SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());

    public String createToken(User user) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(user.getUsername()) // Agrega al token un campo sub que contiene el USERNAME
                .claim("userId", user.getId()) // Agrega al token un campo ID
                .claim("userMail", user.getMail()) // Agrega al token un campo Mail
                .issuedAt(new Date(now))      // Antes era setIssuedAt
                .expiration(new Date(now + 3600000)) // Antes era setExpiration
                .signWith(SECRET_KEY)        // Se usa el objeto Key que creamos antes
                .compact();
    }

    //Extrae solo el USERNAME
    public String extractUsername(String token) {
        try{
            return Jwts.parser()                 // Ya no existe parserBuilder()
                    .verifyWith((SecretKey) SECRET_KEY)      // Antes era setSigningKey()
                    .build()
                    .parseSignedClaims(token)    // Antes era parseClaimsJws()
                    .getPayload()                // Antes era getBody()
                    .getSubject();
        }catch (Exception e){
            return null;
        }

    }

    //Extrae todos los DATOS
    public Claims extractAllClaims(String token) {
        try{
            return Jwts.parser()
                    .verifyWith((SecretKey) SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }catch (Exception e){
            return null;
        }
    }


    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) SECRET_KEY)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false; // Firma inválida o token expirado
        }
    }
}

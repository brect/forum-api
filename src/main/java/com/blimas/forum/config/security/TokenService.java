package com.blimas.forum.config.security;

import com.blimas.forum.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${forum.jtw.expiration}")
    private Long expiration;

    @Value("${forum.jtw.secret}")
    private String secret;


    public String generateToken(Authentication authenticate) {

        Usuario usuario = (Usuario) authenticate.getPrincipal();
        Date date = new Date();
        Date expirationSession = new Date(date.getTime() + expiration);
        return Jwts.builder()
                .setIssuer("API do forúm")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(date)
                .setExpiration(expirationSession)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }
}

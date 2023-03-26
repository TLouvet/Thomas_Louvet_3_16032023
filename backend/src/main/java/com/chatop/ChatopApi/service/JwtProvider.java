package com.chatop.ChatopApi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatop.ChatopApi.dto.response.JwtResponse;
import com.chatop.ChatopApi.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtResponse provideJwt(User user){
        JwtResponse jwtResponse = new JwtResponse();
        final String token = this.encodeJwt(user);
        jwtResponse.setToken(token);

        return jwtResponse;
    }

    public String encodeJwt(User user) {
        final int TOKEN_DURATION = 60 * 60 * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_DURATION);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expirationDate)
                .sign(getAlgorithm());
    }

    public DecodedJWT decodeJwt(String token){
        return JWT
                .require(getAlgorithm())
                .build()
                .verify(token);
    }

    public Long getSubject(DecodedJWT decodedJwt){
        String sub = decodedJwt.getSubject();
        return Long.parseLong(sub);
    }

    private Algorithm getAlgorithm(){
        return Algorithm.HMAC256(jwtSecret);
    }
}

package com.chatop.ChatopApi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.chatop.ChatopApi.dto.response.JwtResponse;
import com.chatop.ChatopApi.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtProvider {

    private final String secretKey = "U2VjcmV0QXBpS2V5VGhhdE5vT25lU2hvdWxkRXZlckZpbmQ=";

    private final Algorithm algorithm = Algorithm.HMAC256(secretKey);

    public JwtResponse provideJwt(User user){
        JwtResponse jwtResponse = new JwtResponse();
        final String token = this.encodeJwt(user);
        jwtResponse.setToken(token);

        return jwtResponse;
    }

    public String encodeJwt(User user) {
        int duration = 60 * 60 * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + duration);

        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("name", user.getName())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(expirationDate)
                .sign(algorithm);
    }

    public DecodedJWT decodeJwt(String token){
        return JWT
                .require(algorithm)
                .build()
                .verify(token);
    }

    public Long getSubject(DecodedJWT decodedJwt){
        String sub = decodedJwt.getSubject();
        return Long.parseLong(sub);
    }
}

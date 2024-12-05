package com.desafio.ibm.demo.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.desafio.ibm.demo.models.ModelUserDetailsImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    @Value("${token.jwt.secret.key}")
    private String secret_Key;

    @Value("${token.jwt.issuer}")
    private String issuer;

    public String generateToken(ModelUserDetailsImpl user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret_Key);
            return JWT.create()
                    .withIssuer(issuer)
                    .withIssuedAt(dataCriacao())
                    .withExpiresAt(dataExpiracao())
                    .withSubject(user.getUsername())
                    .withClaim("roles", user.getAuthorities().stream()
                            .map(authority -> authority.getAuthority())
                            .toList())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new JWTCreationException("Erro ao gerar o token: ", exception);
        }
    }

    public String pegarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret_Key);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Token inválido ou expirado!");
        }
    }

    private Instant dataExpiracao() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .plusHours(5).toInstant();
    }

    private Instant dataCriacao() {
        return ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant();
    }

}
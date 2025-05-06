package br.com.fiap.money_flow_api.service;

import br.com.fiap.money_flow_api.dto.TokenDto;
import br.com.fiap.money_flow_api.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {


    private Instant expiresAt = LocalDateTime.now().plusMinutes(10).toInstant(ZoneOffset.ofHours(-3));

    private Algorithm algorithm = Algorithm.HMAC256("secret");

    public TokenDto createToken(User user) {
        var jwt = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("email", user.getEmail())
                .withClaim("role", user.getRole())
                .withExpiresAt(expiresAt)
                .sign(algorithm);

        return new TokenDto(jwt, user.getEmail());
    }
}

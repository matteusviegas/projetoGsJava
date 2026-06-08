package br.com.globalsolution.agrosat.core.infrastructure.config.security;

import java.time.Instant;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import br.com.globalsolution.agrosat.core.domainmodel.User;

@Component
public class TokenConfig {

    private String secret = "CHAVE_SECRETA";

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withClaim("userId", user.getUserId())
                .withSubject(user.getEmail())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .sign(algorithm);
    }

    public Optional<JwtUserData> validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm).build().verify(token);
            return Optional.of(JwtUserData.builder()
                    .userId(decode.getClaim("userId").asLong())
                    .email(decode.getSubject())
                    .build());
        } catch (JWTVerificationException ex) {
            return Optional.empty();
        }
    }

}

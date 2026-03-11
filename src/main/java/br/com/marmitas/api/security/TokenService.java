package br.com.marmitas.api.security;

import br.com.marmitas.api.model.Usuario;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service //por que service?
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Usuario usuario){

        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("marmitaria-api")
                .withSubject(usuario.getEmail())
                .withClaim("id", usuario.getId())
                .withClaim("tipo", usuario.getTipo().name())
                .withExpiresAt(dataExpiracao())
                .sign(algorithm);
    }

    private Instant dataExpiracao(){
        return LocalDateTime.now()
                .plusHours(2)
                .toInstant(ZoneOffset.of("-03:00"));
    }

}

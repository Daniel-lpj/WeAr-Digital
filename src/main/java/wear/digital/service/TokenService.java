package wear.digital.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import wear.digital.model.Credencial;
import wear.digital.model.Token;
import wear.digital.model.Usuario;
import wear.digital.repository.UsuarioRepository;

@Service
public class TokenService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Value("${jwt.secret}")
    String secret;

    public Token generateToken(Credencial credencial) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var token = JWT.create()
                .withSubject(credencial.email())
                .withIssuer("Wear")
                .withExpiresAt(Instant.now().plus(20, ChronoUnit.MINUTES))
                .sign(alg);
        return new Token(token, "JWT", "Bearer");
    }

    public Usuario getUserByToken(String token) {
        Algorithm alg = Algorithm.HMAC256(secret);
        var email = JWT.require(alg)
                .withIssuer("Wear")
                .build()
                .verify(token)
                .getSubject();
        ;
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new JWTVerificationException("Usuario invalido"));
    }

}
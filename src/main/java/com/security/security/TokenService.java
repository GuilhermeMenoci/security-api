package com.security.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.security.domain.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;

	public String gerarToken(Usuario usuario) {
		try {
			
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("security-api") //marcando o servico
					.withSubject(usuario.getLogin()) //marcando o usuario
					.withExpiresAt(gerarExpiracao()) // gerando a hora da expiracao do token
					.sign(algoritmo);
			
			
		} catch(JWTCreationException exception) {
			throw new RuntimeException("Error enquanto gera o token", exception);
		}
	}
	
	public String validarToken(String token) {
		try {
			
			Algorithm algoritmo = Algorithm.HMAC256(secret);
			return JWT.require(algoritmo)
					.withIssuer("security-api")
					.build()
					.verify(token)
					.getSubject();
			
			
		} catch(JWTVerificationException exception) {
			return "";
		}
	}
	
	private Instant gerarExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
	
}

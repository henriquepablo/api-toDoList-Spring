package com.lacerda.toDoList.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.lacerda.toDoList.model.User;

@Service
public class TokenService {
	
	// vai gerar um validar um token
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
		try {
			
			// responsável por criptografar as informações
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			String token = JWT.create()
					.withIssuer("apiToDoList")
					.withSubject(user.getEmail())
					.withExpiresAt(this.generateExpirationDate())
					.sign(algorithm);
			
			return token;
			
		} 
		catch (JWTCreationException e) {
			throw new RuntimeException("Erro to create a token");
		}
	}
	
	// valida o token
	public String validateToken(String token) {
		
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			
			return JWT.require(algorithm)
					.withIssuer("apiToDoList")
					.build()
					.verify(token)
					.getSubject();
		} 
		catch (JWTVerificationException e) {
			return null;
		}
	}
	
	public Instant generateExpirationDate() {
		// deixa o token válido por 2 horas
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("GMT-3"));
	}
	
}

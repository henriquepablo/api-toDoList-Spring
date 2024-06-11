package com.lacerda.toDoList.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lacerda.toDoList.DTO.RegisterRequestUserDTO;
import com.lacerda.toDoList.DTO.ResponseDTO;
import com.lacerda.toDoList.DTO.LoginRequestDTO;
import com.lacerda.toDoList.infra.security.TokenService;
import com.lacerda.toDoList.model.User;
import com.lacerda.toDoList.repositories.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	// acessa o banco de dados
	@Autowired
	private UserRepository userRepository;
	
	// criptografa senha
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// valida e cria token
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequestDTO body) {
		// retorna o usuário salvo no db
		User user = userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException());
		
		// verifica se a senhas são as mesmas
		if(passwordEncoder.matches(body.password(), user.getPassword())) {
			// gera um token e associa ele ao usuário
			String token = tokenService.generateToken(user);
			
			// responde com as informações geradas
			return ResponseEntity.ok(new ResponseDTO(user.getId(), user.getNome(), token, user.getEmail()));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<ResponseDTO> register(@RequestBody RegisterRequestUserDTO body) {
		
		Optional<User> user = userRepository.findByEmail(body.email());
		
		// verifica se o user já existe
		if (user.isEmpty()) {

			User newUser = new User();
			
			newUser.setPassword(passwordEncoder.encode(body.password()));
			newUser.setNome(body.name());
			newUser.setEmail(body.email());
			
			// salva o usuário no db
			userRepository.save(newUser);

			// gera o token
			String token = tokenService.generateToken(newUser);
			
			return ResponseEntity.ok(new ResponseDTO(newUser.getId(), newUser.getNome(), token, newUser.getEmail()));
			
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/data")
	public ResponseEntity<ResponseDTO> userDertails(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
		String authHeader = token.replace("Bearer ", "");
		
		String userEmail = tokenService.validateToken(authHeader);
		
		User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
		
		return ResponseEntity.ok(new ResponseDTO(user.getId(), user.getNome(), authHeader, user.getEmail()));
		
	}
	
}

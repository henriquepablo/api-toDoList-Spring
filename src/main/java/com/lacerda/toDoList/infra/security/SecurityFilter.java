package com.lacerda.toDoList.infra.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lacerda.toDoList.model.User;
import com.lacerda.toDoList.repositories.UserRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// recupera o token
		String token = this.recoverToken(request);
		// valida o token
		String login = tokenService.validateToken(token);
		
		// verifica se o token é null
		if (login != null) {
			// recupera o usuário no db
			User user = userRepository.findByEmail(login).orElseThrow(() -> new RuntimeException("User not found"));
			
			// cria as roles (papéis) do usuário
			var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
			
			// cria o objeto de autenticação
			var authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
			
			//adiciona o usuário no contexto do spring security
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	// esse método recupera o token enviado e retorna o mesmo
	private String recoverToken(HttpServletRequest request) {
		var authHeader = request.getHeader("Authorization");
		if(authHeader == null) return null;
		return authHeader.replace("Bearer ", "");
	}
}

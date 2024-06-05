package com.lacerda.toDoList.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private SecurityFilter securityFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
		// a api não guarda o estado de login dentro dela, por isso ela é Stateless
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(authorize -> authorize
				// estou informando que essas duas requisições não precisam de autorização
				.requestMatchers(HttpMethod.POST, "user/login").permitAll()
				.requestMatchers(HttpMethod.POST, "user/register").permitAll()
				// qualquer outra requisição que não seja as de cima, vai precisar de autorização
				.anyRequest().authenticated()
		)
		// antes dos authorizes serem executados, ele vai executar o securityFilter primeiro 
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	// criptografa a senha
    @Bean
    PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}
    
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    	return authenticationConfiguration.getAuthenticationManager();
    }
}

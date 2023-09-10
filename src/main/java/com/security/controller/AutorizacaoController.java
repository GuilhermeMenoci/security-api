package com.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.domain.usuario.AutorizadorDTO;
import com.security.domain.usuario.LoginResponseDTO;
import com.security.domain.usuario.RegistrarDTO;
import com.security.domain.usuario.Usuario;
import com.security.repository.UsuarioRepository;
import com.security.security.TokenService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class AutorizacaoController {

	private final AuthenticationManager manager;
	
	private final UsuarioRepository usuarioRepository;
	
	private final TokenService tokenService;
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AutorizadorDTO autorizador) {
		var usuario = new UsernamePasswordAuthenticationToken(autorizador.login(), autorizador.senha());
		Authentication auth = this.manager.authenticate(usuario); 
		
		var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/registrar")
	public ResponseEntity registrar(@RequestBody @Valid RegistrarDTO registrar) {
		if(usuarioRepository.findByLogin(registrar.login()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String senhaEncrypt = new BCryptPasswordEncoder().encode(registrar.senha());
		Usuario novoUsuario = new Usuario(registrar.login(), senhaEncrypt, registrar.regra());
		
		usuarioRepository.save(novoUsuario);
		
		return ResponseEntity.ok().build();
	}
	
}

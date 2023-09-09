package com.security.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProdutoRequestDTO(
		
		@NotBlank
		String nome,
		
		@NotNull
		Integer preco
		
		) 
{}

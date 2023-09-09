package com.security.domain;

public record ProdutoResponseDTO(Long id, String nome, Integer preco) {
	
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
    
}

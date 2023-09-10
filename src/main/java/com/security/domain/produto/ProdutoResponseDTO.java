package com.security.domain.produto;

public record ProdutoResponseDTO(Long id, String nome, Integer preco) {
	
    public ProdutoResponseDTO(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getPreco());
    }
    
}

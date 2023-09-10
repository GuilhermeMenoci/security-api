package com.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.security.domain.produto.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String>{

}

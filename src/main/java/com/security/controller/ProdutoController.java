package com.security.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.domain.Produto;
import com.security.domain.ProdutoRequestDTO;
import com.security.domain.ProdutoResponseDTO;
import com.security.repository.ProdutoRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController()
@RequestMapping("produtos")
public class ProdutoController {

	private final ProdutoRepository repository;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
    public ResponseEntity postProduct(@RequestBody @Valid ProdutoRequestDTO body){
        Produto newProduct = new Produto(body);

        this.repository.save(newProduct);
        return ResponseEntity.ok().build();
    }

    @SuppressWarnings("rawtypes")
	@GetMapping
    public ResponseEntity getAllProducts(){
        List<ProdutoResponseDTO> productList = this.repository.findAll().stream().map(ProdutoResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }
	
}

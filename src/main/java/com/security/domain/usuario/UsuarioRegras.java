package com.security.domain.usuario;

public enum UsuarioRegras {

	ADMIN("admin"),
	USUARIO("usuario");
	
	private String regra;
	
	UsuarioRegras(String regra) {
		this.regra = regra;
	}
	
	public String getRegra() {
		return regra;
	}
	
}

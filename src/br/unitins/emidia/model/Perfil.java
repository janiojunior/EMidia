package br.unitins.emidia.model;

public enum Perfil {
	ADMINISTRADOR(1, "Administrador"), 
	FUNCIONARIO(2, "Funcionário"), 
	USUARIO(3, "Usuário");
	
	private int id;
	private String label;
	
	Perfil(int id, String label) {
		this.id = id;
		this.label = label;
	}
	
	public int getId() {
		return id;
	}
	
	public String getLabel() {
		return label;
	}	
}

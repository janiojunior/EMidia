package br.unitins.emidia.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class TesteAjax implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1122690702742859509L;

	private String nome;
	
	public void imprimir() {
		System.out.println(nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}

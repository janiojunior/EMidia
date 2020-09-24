package br.unitins.emidia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.application.Util;
import br.unitins.emidia.model.Perfil;
import br.unitins.emidia.model.Sexo;
import br.unitins.emidia.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {
	
	private static final long serialVersionUID = -3955368378894625110L;
	
	private Usuario usuario; 
	private List<Usuario> listaUsuario; 
	private int id = 0;

	public void incluir() {
		
		getUsuario().setId(++id);
		
		int index = getListaUsuario().indexOf(getUsuario());
		if (index != -1) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão. O id do usuário ja existe.");
			return;
		}
		
		getListaUsuario().add(getUsuario());
		limpar();
		Util.addInfoMessage("Inclusão realizada com sucesso.");
	}
	
	public void editar(Usuario usu) {
//		Usuario novoUsuario = new Usuario();
//		novoUsuario.setId(usu.getId());
//		novoUsuario.setNome(usu.getNome());
//		novoUsuario.setEmail(usu.getEmail());
//		novoUsuario.setSenha(usu.getSenha());
		
		setUsuario(usu.getClone());
	}
	
	public void alterar() {
		System.out.println("Entrou no alterar");
		int index = getListaUsuario().indexOf(getUsuario());
		getListaUsuario().set(index, getUsuario());
		limpar();
	}
	
	public void excluir() {
		excluir(getUsuario());
	}
	
	public void excluir(Usuario usu) {
		getListaUsuario().remove(usu);
		limpar();
		Util.addInfoMessage("Exclusão realizada com sucesso.");
	}
  	
	public void limpar() {
		System.out.println("\n\n\n\n");
		System.out.println(getUsuario().getId());
		System.out.println("\n\n\n\n");
		usuario = null;
	}
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}
	
	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null)
			listaUsuario = new ArrayList<Usuario>();
		return listaUsuario;
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

}

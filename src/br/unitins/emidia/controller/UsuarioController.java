package br.unitins.emidia.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.application.Util;
import br.unitins.emidia.dao.DAO;
import br.unitins.emidia.dao.UsuarioDAO;
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


	public void incluir()  {
		UsuarioDAO dao = new UsuarioDAO();
		try {
			dao.inserir(getUsuario());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			limpar();
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
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
		usuario = null;
		listaUsuario = null;
	}

	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			UsuarioDAO dao = new UsuarioDAO();
			try {
				listaUsuario = dao.obterTodos();
			} catch (Exception e) {
				e.printStackTrace();
				listaUsuario = new ArrayList<Usuario>();
			}
		}	
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

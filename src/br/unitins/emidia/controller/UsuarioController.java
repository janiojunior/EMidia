package br.unitins.emidia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.dao.UsuarioDAO;
import br.unitins.emidia.model.Perfil;
import br.unitins.emidia.model.Sexo;
import br.unitins.emidia.model.Usuario;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = -3955368378894625110L;
	
	public UsuarioController() {
		super(new UsuarioDAO());
	}

	@Override
	public Usuario getEntity() {
		if (entity == null)
			entity = new Usuario();
		return entity;
	}
	
	public Sexo[] getListaSexo() {
		return Sexo.values();
	}

	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	
}

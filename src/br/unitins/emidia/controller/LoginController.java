package br.unitins.emidia.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.unitins.emidia.application.Util;

@Named
@RequestScoped
public class LoginController  {

//	@NotBlank(message = "O usuario não pode ser nulo.")
	private String usuario;
	
//	@Size(min = 6, max = 10, message = "A senha deve conter no mínimo 6 dígitos e maximo 10.")
//	@NotBlank(message = "A senha não pode ser nula.")
	private String senha;
	
//	private UIComponent usuarioUIComponent;

	public void entrar() {

		if (getUsuario().isBlank()) {
			Util.addErrorMessage("O campo usuario nao pode ser nulo. 2");
			return;
		}
		
		if (getUsuario().equals("janio") && 
				getSenha().equals("123")) {
			Util.redirect("usuario.xhtml");
		}
		UIComponent component = Util.findComponent("itUsuario");
		Util.addErrorMessage(component.getClientId(),
				"O usuário não existe.");
				
//		System.out.println("Cliente Id: ".concat(getUsuarioUIComponent().getClientId()));
//		Util.addErrorMessage(getUsuarioUIComponent().getClientId(),
//							"O usuário não existe.");
	}
	
	public void validarLogin(String idComponent) {
		UIComponent comp = Util.findComponent(idComponent);
		
		if (!usuario.equals("janio")) {
			Util.addErrorMessage(comp.getClientId(), "Usuário inválido.");
		}
	}
	
	public void limpar() {
		System.out.println("Limpar");
		usuario = "blah";
		senha = "";
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}

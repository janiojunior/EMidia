package br.unitins.emidia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.application.Util;
import br.unitins.emidia.dao.MidiaDAO;
import br.unitins.emidia.model.Midia;

@Named
@ViewScoped
public class ConsultaMidiaController implements Serializable {

	private static final long serialVersionUID = -7064857362220414218L;

	private Integer tipoFiltro;
	private String filtro;
	private List<Midia> listaMidia;
	
	public void novaMidia() {
		Util.redirect("midia.xhtml");
	}
	
	public void pesquisar() {
		MidiaDAO dao = new MidiaDAO();
		try {
			setListaMidia(dao.obterListaMidia(tipoFiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaMidia(null);
		}
	}
	
	public void editar(Midia midia) {
		MidiaDAO dao = new MidiaDAO();
		Midia editarMidia = null;
		try {
			editarMidia = dao.obterUm(midia);
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar a midia no banco de dados.");
			return;
		}
		
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("midiaFlash", editarMidia);
		novaMidia();
	}

	public Integer getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(Integer tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Midia> getListaMidia() {
		if (listaMidia == null)
			listaMidia = new ArrayList<Midia>();
		return listaMidia;
	}

	public void setListaMidia(List<Midia> listaMidia) {
		this.listaMidia = listaMidia;
	}

}

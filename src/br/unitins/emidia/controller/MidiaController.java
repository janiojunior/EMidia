package br.unitins.emidia.controller;

import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.dao.MidiaDAO;
import br.unitins.emidia.model.Midia;
import br.unitins.emidia.model.TipoMidia;

@Named
@ViewScoped
public class MidiaController extends Controller<Midia> implements Serializable {

	private static final long serialVersionUID = -5553263731195677602L;

	public MidiaController() {
		super(new MidiaDAO());
		Flash flash =  FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.keep("midiaFlash");
		setEntity((Midia)flash.get("midiaFlash"));
	}

	@Override
	public Midia getEntity() {
		if (entity == null)
			entity = new Midia();
		return entity;
	}
	
	public TipoMidia[] getListaTipoMidia() {
		return TipoMidia.values();
	}

}

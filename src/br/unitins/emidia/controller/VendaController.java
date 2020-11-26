package br.unitins.emidia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.application.Session;
import br.unitins.emidia.application.Util;
import br.unitins.emidia.dao.MidiaDAO;
import br.unitins.emidia.model.ItemVenda;
import br.unitins.emidia.model.Midia;

@Named
@ViewScoped
public class VendaController implements Serializable {
	private static final long serialVersionUID = -1050498066665380767L;
	
	private Integer tipoFiltro;
	private String filtro;
	private List<Midia> listaMidia;
	
	public void pesquisar() {
		MidiaDAO dao = new MidiaDAO();
		try {
			setListaMidia(dao.obterListaMidiaComEstoque(tipoFiltro, filtro));
		} catch (Exception e) {
			e.printStackTrace();
			setListaMidia(null);
		}
	}
	
	public void addCarrinho(Midia midia) {
		try {
			MidiaDAO dao = new MidiaDAO();
			// obtendo os dados atuais da midia
			midia = dao.obterUm(midia);
			
			List<ItemVenda> listaItemVenda = null;
			Object obj = Session.getInstance().getAttribute("carrinho");
			
			if (obj == null) 
				listaItemVenda = new ArrayList<ItemVenda>();
			else 
				listaItemVenda = (List<ItemVenda>) obj;
			
			// montando o item de venda
			ItemVenda item = new ItemVenda();
			item.setMidia(midia);
			item.setPreco(midia.getPreco());
			listaItemVenda.add(item);
			
			// atualizando a sessao do carrinho de compras
			Session.getInstance().setAttribute("carrinho", listaItemVenda);
			
			Util.addInfoMessage("O produto: " + midia.getNome() + " foi adicionado ao carrinho.");
			
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Problema ao adicionar o produto ao carrinho. Tente novamente.");
		}
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

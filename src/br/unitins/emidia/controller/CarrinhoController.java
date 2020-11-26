package br.unitins.emidia.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.emidia.application.Session;
import br.unitins.emidia.application.Util;
import br.unitins.emidia.dao.VendaDAO;
import br.unitins.emidia.model.ItemVenda;
import br.unitins.emidia.model.Usuario;
import br.unitins.emidia.model.Venda;

@Named
@ViewScoped
public class CarrinhoController implements Serializable {

	private static final long serialVersionUID = -6599596517959321297L;
	
	private Venda venda;

	public Venda getVenda() {
		if (venda == null) {
			venda = new Venda();
			venda.setListaItemVenda(new ArrayList<ItemVenda>());
		}
		// obtendo o carrinho da sessao
		Object obj = Session.getInstance().getAttribute("carrinho");
		if (obj != null)
			venda.setListaItemVenda((List<ItemVenda>) obj);
		
		return venda;
	}
	
	public void remover(ItemVenda itemVenda) {
		// vcs devem implementar
	}
	
	public void finalizar() {
		// obtendo o usuario da sessao
		Object obj = Session.getInstance().getAttribute("usuarioLogado");
		if (obj == null) {
			Util.addErrorMessage("Para finalizar a venda o usuário deve estar logado.");
			return;
		}
		
		// adicionando o usuario logado na venda
		getVenda().setUsuario((Usuario) obj);
		
		VendaDAO dao = new VendaDAO();
		try {
			dao.inserir(getVenda());
			Util.addInfoMessage("Inclusão realizada com sucesso.");
			
			// limpando o carrinho
			Session.getInstance().setAttribute("carrinho", null);
			setVenda(null);
			
		} catch (Exception e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
		}
		
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}

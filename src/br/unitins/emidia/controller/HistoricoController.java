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
public class HistoricoController implements Serializable {

	private static final long serialVersionUID = 5504191298370509667L;

	private List<Venda> listaVenda;

	public List<Venda> getListaVenda() {
		if (listaVenda == null) {
			VendaDAO dao = new VendaDAO();
			Object obj = Session.getInstance().getAttribute("usuarioLogado");
			
			if (obj != null)
				try {
					listaVenda = dao.obterTodos((Usuario) obj);
				} catch (Exception e) {
					Util.addErrorMessage("Não foi possível obter o histórico de vendas.");
					listaVenda = new ArrayList<Venda>();
				}
		}
		return listaVenda;
	}
	
	public void detalhes(Venda venda) {
		
	}

	public void setListaVenda(List<Venda> listaVenda) {
		this.listaVenda = listaVenda;
	}
	
}

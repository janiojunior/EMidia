package br.unitins.emidia.model;

import java.time.LocalDateTime;
import java.util.List;

public class Venda {
	private Integer id;
	private LocalDateTime data;
	private Usuario usuario;
	private List<ItemVenda> listaItemVenda;

	// método calculado
	public Double getTotalVenda() {
		return 0.0;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemVenda> getListaItemVenda() {
		return listaItemVenda;
	}

	public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
		this.listaItemVenda = listaItemVenda;
	}
	
	
}

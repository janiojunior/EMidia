package br.unitins.emidia.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	private static Connection getConnection() {
		Connection conn = null;
		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");

			// estabelecendo a conexao com o banco de dados
			conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/emidiadb", "topicos1", "123456");

			// obrigando a trabalhar com commit e rollback
			conn.setAutoCommit(false);

		} catch (SQLException e) {
			System.out.println("Erro ao conectar ao banco de dados.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao registar a conexao.");
			e.printStackTrace();
		}

		return conn;
	}

	public void incluir() {
		Connection conn = getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("usuario ");
		sql.append("  (nome, cpf, email, senha) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?) ");
		PreparedStatement stat = null;
		try {
			Usuario usuario = getUsuario();
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, usuario.getNome());
			stat.setString(2, usuario.getCpf());
			stat.setString(3, usuario.getEmail());
			stat.setString(4, usuario.getSenha());

			stat.execute();
			// efetivando a transacao
			conn.commit();
			limpar();
			Util.addInfoMessage("Inclusão realizada com sucesso.");

		} catch (SQLException e) {
			Util.addErrorMessage("Não é possivel fazer uma inclusão.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
		} finally {
			try {
				if (!stat.isClosed())
					stat.close();
			} catch (SQLException e) {
				System.out.println("Erro ao fechar o Statement");
				e.printStackTrace();
			}

			try {
				if (!conn.isClosed())
					conn.close();
			} catch (SQLException e) {
				System.out.println("Erro a o fechar a conexao com o banco.");
				e.printStackTrace();
			}
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
			listaUsuario = new ArrayList<Usuario>();

			Connection conn = getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append("  u.id, ");
			sql.append("  u.data_nascimento, ");
			sql.append("  u.sexo, ");
			sql.append("  u.perfil, ");
			sql.append("  u.nome, ");
			sql.append("  u.cpf, ");
			sql.append("  u.email, ");
			sql.append("  u.senha ");
			sql.append("FROM  ");
			sql.append("  usuario u ");
			sql.append("ORDER BY u.nome ");

			PreparedStatement stat = null;
			try {
				
				stat = conn.prepareStatement(sql.toString());
				
				ResultSet rs = stat.executeQuery();
				
				while (rs.next()) {
					Usuario usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					//usuario.setDataNascimento(rs.getDate("data_nascimento"));
					//rs.getInt("sexo");
					//rs.getInt("perfil");
					usuario.setNome(rs.getString("nome"));
					usuario.setCpf(rs.getString("cpf"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
					
					listaUsuario.add(usuario);
				}
				
			} catch (SQLException e) {
				Util.addErrorMessage("Não foi possivel buscar os dados do usuario.");
				e.printStackTrace();
			} finally {
				try {
					if (!stat.isClosed())
						stat.close();
				} catch (SQLException e) {
					System.out.println("Erro ao fechar o Statement");
					e.printStackTrace();
				}

				try {
					if (!conn.isClosed())
						conn.close();
				} catch (SQLException e) {
					System.out.println("Erro a o fechar a conexao com o banco.");
					e.printStackTrace();
				}
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

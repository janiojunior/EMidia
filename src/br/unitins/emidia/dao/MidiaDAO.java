package br.unitins.emidia.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.unitins.emidia.application.Util;
import br.unitins.emidia.model.Midia;
import br.unitins.emidia.model.Perfil;
import br.unitins.emidia.model.Sexo;
import br.unitins.emidia.model.TipoMidia;
import br.unitins.emidia.model.Midia;

public class MidiaDAO implements DAO<Midia> {

	@Override
	public void inserir(Midia obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("midia ");
		sql.append("  (nome, descricao, preco, estoque, tipo_midia) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?, ?) ");
		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getDescricao());
			stat.setDouble(3, obj.getPreco());
			stat.setInt(4, obj.getEstoque());
			// ternario java
			stat.setObject(5, (obj.getTipoMidia() == null ? null : obj.getTipoMidia().getId()));

			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

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

		if (exception != null)
			throw exception;
		
	}

	@Override
	public void alterar(Midia obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE midia SET ");
		sql.append("  nome = ?, ");
		sql.append("  descricao = ?, ");
		sql.append("  preco = ?, ");
		sql.append("  estoque = ?, ");
		sql.append("  tipo_midia = ? ");
		sql.append("WHERE ");
		sql.append("  id = ? ");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, obj.getNome());
			stat.setString(2, obj.getDescricao());
			stat.setDouble(3, obj.getPreco());
			stat.setInt(4, obj.getEstoque());
			// ternario java
			stat.setObject(5, (obj.getTipoMidia() == null ? null : obj.getTipoMidia().getId()));
			stat.setInt(6, obj.getId());

			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

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

		if (exception != null)
			throw exception;

		
	}

	@Override
	public void excluir(Midia obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM midia WHERE id = ?");

		PreparedStatement stat = null;

		try {
			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());
			stat.execute();
			// efetivando a transacao
			conn.commit();

		} catch (SQLException e) {

			System.out.println("Erro ao realizar um comando sql de insert.");
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
			exception = new Exception("Erro ao inserir");

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

		if (exception != null)
			throw exception;
	
	}

	@Override
	public List<Midia> obterTodos() throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Midia> listaMidia = new ArrayList<Midia>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  m.id, ");
		sql.append("  m.nome, ");
		sql.append("  m.descricao, ");
		sql.append("  m.preco, ");
		sql.append("  m.estoque, ");
		sql.append("  m.tipo_midia ");
		sql.append("FROM  ");
		sql.append("  midia m ");
		sql.append("ORDER BY m.nome ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Midia midia = new Midia();
				midia.setId(rs.getInt("id"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setPreco(rs.getDouble("preco"));
				midia.setEstoque(rs.getInt("estoque"));
				midia.setTipoMidia(TipoMidia.valueOf(rs.getInt("tipo_midia")));

				listaMidia.add(midia);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do midia.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MidiaDAO.");
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

		if (exception != null)
			throw exception;

		return listaMidia;
	}

	@Override
	public Midia obterUm(Midia obj) throws Exception {
		Exception exception = null;
		Connection conn = DAO.getConnection();
		
		Midia midia = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  m.id, ");
		sql.append("  m.nome, ");
		sql.append("  m.descricao, ");
		sql.append("  m.preco, ");
		sql.append("  m.estoque, ");
		sql.append("  m.tipo_midia ");
		sql.append("FROM  ");
		sql.append("  midia m ");
		sql.append("WHERE m.id = ? ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setInt(1, obj.getId());

			ResultSet rs = stat.executeQuery();

			if (rs.next()) {
				midia = new Midia();
				midia.setId(rs.getInt("id"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setPreco(rs.getDouble("preco"));
				midia.setEstoque(rs.getInt("estoque"));
				midia.setTipoMidia(TipoMidia.valueOf(rs.getInt("tipo_midia")));
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do midia.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MidiaDAO.");
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

		if (exception != null)
			throw exception;

		return midia;
	}

	public List<Midia> obterListaMidia(Integer tipo, String filtro) throws Exception {
		// tipo - 1 Nome; 2 Descricao
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Midia> listaMidia = new ArrayList<Midia>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  m.id, ");
		sql.append("  m.nome, ");
		sql.append("  m.descricao, ");
		sql.append("  m.preco, ");
		sql.append("  m.estoque, ");
		sql.append("  m.tipo_midia ");
		sql.append("FROM  ");
		sql.append("  midia m ");
		sql.append("WHERE ");
		sql.append("  upper(m.nome) LIKE upper( ? ) ");
		sql.append("  AND upper(m.descricao) LIKE upper( ? ) ");
		sql.append("ORDER BY m.nome ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, tipo == 1 ? "%"+ filtro +"%" : "%");
			stat.setString(2, tipo == 2 ? "%"+ filtro +"%" : "%");

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Midia midia = new Midia();
				midia.setId(rs.getInt("id"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setPreco(rs.getDouble("preco"));
				midia.setEstoque(rs.getInt("estoque"));
				midia.setTipoMidia(TipoMidia.valueOf(rs.getInt("tipo_midia")));

				listaMidia.add(midia);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do midia.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MidiaDAO.");
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

		if (exception != null)
			throw exception;

		return listaMidia;
	}
	
	public List<Midia> obterListaMidiaComEstoque(Integer tipo, String filtro) throws Exception {
		// tipo - 1 Nome; 2 Descricao
		Exception exception = null;
		Connection conn = DAO.getConnection();
		List<Midia> listaMidia = new ArrayList<Midia>();

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ");
		sql.append("  m.id, ");
		sql.append("  m.nome, ");
		sql.append("  m.descricao, ");
		sql.append("  m.preco, ");
		sql.append("  m.estoque, ");
		sql.append("  m.tipo_midia ");
		sql.append("FROM  ");
		sql.append("  midia m ");
		sql.append("WHERE ");
		sql.append("  upper(m.nome) LIKE upper( ? ) ");
		sql.append("  AND upper(m.descricao) LIKE upper( ? ) ");
		sql.append("  AND m.estoque > 0 ");
		sql.append("ORDER BY m.nome ");

		PreparedStatement stat = null;
		try {

			stat = conn.prepareStatement(sql.toString());
			stat.setString(1, tipo == 1 ? "%"+ filtro +"%" : "%");
			stat.setString(2, tipo == 2 ? "%"+ filtro +"%" : "%");

			ResultSet rs = stat.executeQuery();

			while (rs.next()) {
				Midia midia = new Midia();
				midia.setId(rs.getInt("id"));
				midia.setNome(rs.getString("nome"));
				midia.setDescricao(rs.getString("descricao"));
				midia.setPreco(rs.getDouble("preco"));
				midia.setEstoque(rs.getInt("estoque"));
				midia.setTipoMidia(TipoMidia.valueOf(rs.getInt("tipo_midia")));

				listaMidia.add(midia);
			}

		} catch (SQLException e) {
			Util.addErrorMessage("Não foi possivel buscar os dados do midia.");
			e.printStackTrace();
			exception = new Exception("Erro ao executar um sql em MidiaDAO.");
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

		if (exception != null)
			throw exception;

		return listaMidia;
	}
}

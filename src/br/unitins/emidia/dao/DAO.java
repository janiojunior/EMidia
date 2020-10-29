package br.unitins.emidia.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
	public void inserir(T obj) throws Exception;
	public void alterar(T obj) throws Exception;
	public void excluir(Integer id) throws Exception;
	public List<T> obterTodos() throws Exception;
	
	public static Connection getConnection() {
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
}

package br.unitins.emidia.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TesteBanco {

	public static void main(String[] args) {
		Connection conn = getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ");
		sql.append("usuario ");
		sql.append("  (nome, cpf, email, senha) ");
		sql.append("VALUES ");
		sql.append("  ( ?, ?, ?, ?) ");
		
		try {
			PreparedStatement stat = conn.prepareStatement(sql.toString());
			stat.setString(1, "Silvano");
			stat.setString(2, "222");
			stat.setString(3, "silvano@gmail.com");
			stat.setString(4, "123");
			
			stat.execute();
			// efetivando a transacao
			conn.commit();
			System.out.println("Insert realizado com sucesso.");
			
		} catch (SQLException e) {
			e.printStackTrace();
			// cancelando a transacao
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Erro ao realizar o rollback.");
				e1.printStackTrace();
			}
		}
		
		
	}

	private static Connection getConnection() {
		Connection conn = null;
		try {
			// registrando o driver do postgres
			Class.forName("org.postgresql.Driver");
			
			// estabelecendo a conexao com o banco de dados
			conn = DriverManager.
				getConnection("jdbc:postgresql://127.0.0.1:5432/emidiadb", 
						"topicos1", "123456");
			
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

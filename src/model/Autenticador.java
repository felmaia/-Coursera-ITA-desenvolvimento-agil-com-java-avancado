package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Autenticador {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String autenticar(String login, String senha) throws Exception {
		
		try (Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/coursera", "root", "")) {
			
			String sql = "SELECT NOME FROM USUARIO WHERE LOGIN=? AND SENHA=?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("NOME");
			} else {
				throw new Exception("Não foi possível autenticar o usuário.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao acessar banco de dados.", e);
		}
	}

}

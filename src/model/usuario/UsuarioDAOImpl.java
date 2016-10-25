package model.usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.ConnectionFactory;
import reflection.ReflectionUtils;

import static model.ConnectionFactory.DATABASE_DRIVER;

public class UsuarioDAOImpl implements UsuarioDAO {

	static {
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Erro ao iniciar driver do banco de dados.", e);
		}
	}
	
	@Override
	public void inserir(Usuario u) {

		try (Connection c = ConnectionFactory.getConnection()) {
		
			String sql  = "INSERT INTO USUARIO (LOGIN, EMAIL, NOME, SENHA, PONTOS) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, u.getLogin());
			stm.setString(2, u.getEmail());
			stm.setString(3, u.getNome());
			stm.setString(4, u.getSenha());
			stm.setInt(5, u.getPontos());
			stm.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir usuário.", e);
		}
	}

	@Override
	public Usuario recuperar(String login) {
		
		try (Connection c = ConnectionFactory.getConnection()) {
		
			Usuario usuario = null;
			String sql  = "SELECT * FROM USUARIO WHERE LOGIN = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, login);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				usuario = new Usuario();
				ReflectionUtils.resultSetToObject(usuario, rs);
			}
			return usuario;
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao recuperar usuário.", e);
		}
	}

	@Override
	public void adicionarPontos(String login, int pontos) {

		try (Connection c = ConnectionFactory.getConnection()) {
		
			String sql  = "UPDATE USUARIO SET PONTOS = PONTOS + ? WHERE LOGIN = ?";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setInt(1, pontos);
			stm.setString(2, login);
			stm.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao adicionar pontos ao usuário.", e);
		}
	}

	@Override
	public List<Usuario> ranking() {
		
		try (Connection c = ConnectionFactory.getConnection()) {
		
			List<Usuario> resultado = new ArrayList<>();
			String sql  = "SELECT * FROM USUARIO ORDER BY PONTOS DESC";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				ReflectionUtils.resultSetToObject(usuario, rs);
				resultado.add(usuario);
			}
			return resultado;
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao recuperar ranking de usuários.", e);
		}
	}

}

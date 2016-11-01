package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Usuario;
import util.ReflectionUtils;


public class UsuarioDAO implements IUsuarioDAO {

	private Connection connection = null;
	
	
	public UsuarioDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void inserir(Usuario u) throws Exception {

		try {
		
			u.validar();
			
			String sql  = "INSERT INTO USUARIO (LOGIN, EMAIL, NOME, SENHA, PONTOS) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
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

	public Usuario autenticar(String login, String senha) throws Exception {
		
		try  {
			
			Usuario usuario = null;
			String sql = "SELECT * FROM USUARIO WHERE LOGIN=? AND SENHA=?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ps.setString(2, senha);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				ReflectionUtils.resultSetToObject(usuario, rs);
			} else {
				throw new Exception("Não foi possível autenticar o usuário.");
			}
			return usuario;
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar autenticar usuário.", e);
		}
	}	
	
	@Override
	public Usuario buscar(String login) throws Exception {
		
		try {
			
			Usuario usuario = null;
			String sql = "SELECT * FROM USUARIO WHERE LOGIN = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				usuario = new Usuario();
				ReflectionUtils.resultSetToObject(usuario, rs);
			}	
			else {
				throw new Exception("Usuário não encontrado.");
			}
			return usuario;
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao tentar autenticar usuário.", e);
		}
	}	
	
	@Override
	public void adicionarPontos(String login, int pontos) {

		try {
		
			String sql  = "UPDATE USUARIO SET PONTOS = PONTOS + ? WHERE LOGIN = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, pontos);
			stm.setString(2, login);
			stm.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao adicionar pontos ao usuário.", e);
		}
	}

	@Override
	public List<Usuario> ranking() {
		
		try {
		
			List<Usuario> resultado = new ArrayList<>();
			String sql  = "SELECT * FROM USUARIO ORDER BY PONTOS DESC";
			PreparedStatement stm = connection.prepareStatement(sql);
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

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}

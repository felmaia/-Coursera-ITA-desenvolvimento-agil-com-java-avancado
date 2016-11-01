package model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.dao.IUsuarioDAO;
import model.dao.UsuarioDAO;
import model.domain.Usuario;
import model.infra.ConnectionFactory;

public class UsuarioService {
	
	IUsuarioDAO usuarioDAO;
	
	public Usuario autenticar(String login, String senha) throws Exception {
		
		Usuario usuario = null;
		try (Connection connection = ConnectionFactory.getConnection()) {
			usuarioDAO = new UsuarioDAO(connection);
			usuario = usuarioDAO.autenticar(login, senha);
		} 
		return usuario;
	}
	
	public void inserir(Usuario u) throws Exception {
		try (Connection connection = ConnectionFactory.getConnection()) {
			usuarioDAO = new UsuarioDAO(connection);
			usuarioDAO.inserir(u);
		}
	}
	
	public List<Usuario> ranking() throws Exception {
		
		List<Usuario> usuarios = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			usuarioDAO = new UsuarioDAO(connection);
			usuarios.addAll(usuarioDAO.ranking());
		} 
		return usuarios;
	}

}

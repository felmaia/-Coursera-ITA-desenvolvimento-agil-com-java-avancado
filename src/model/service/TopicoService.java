package model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import model.dao.ITopicoDAO;
import model.dao.TopicoDAO;
import model.dao.UsuarioDAO;
import model.domain.Topico;
import model.infra.ConnectionFactory;

public class TopicoService {
	
	ITopicoDAO topicoDAO;
	
	public List<Topico> listar() throws Exception {
		
		List<Topico> topicos = new ArrayList<>();
		try (Connection connection = ConnectionFactory.getConnection()) {
			topicoDAO = new TopicoDAO(connection);
			topicos.addAll(topicoDAO.listar());
		} 
		return topicos;
	}
	
	public Topico buscar(Integer id) throws Exception {

		Topico topico = null;
		try (Connection connection = ConnectionFactory.getConnection()) {
			topicoDAO = new TopicoDAO(connection);
			topico = topicoDAO.buscar(id);
		}	
		return topico;
	}
	
	public void inserir(Topico topico) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			connection.setAutoCommit(false);
			topicoDAO = new TopicoDAO(connection);
			topicoDAO.inserir(topico);
			UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			usuarioDAO.adicionarPontos(topico.getUsuario().getLogin(), 10);
			connection.commit();
			
		} catch (Exception e) {
			connection.rollback();
			throw new Exception(e.getMessage());
		} finally {
			connection.close();
		}
	}
}

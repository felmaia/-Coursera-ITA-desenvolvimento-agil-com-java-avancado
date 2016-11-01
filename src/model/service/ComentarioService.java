package model.service;

import java.sql.Connection;

import model.dao.ComentarioDAO;
import model.dao.IComentarioDAO;
import model.dao.UsuarioDAO;
import model.domain.Comentario;
import model.infra.ConnectionFactory;

public class ComentarioService {
	
	IComentarioDAO comentarioDAO;
	
	public void inserir(Comentario comentario) throws Exception {
		
		Connection connection = ConnectionFactory.getConnection();
		
		try {
			connection.setAutoCommit(false);
			comentarioDAO = new ComentarioDAO(connection);
			comentarioDAO.inserir(comentario);
			UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			usuarioDAO.adicionarPontos(comentario.getUsuario().getLogin(), 3);
			connection.commit();
			
		} catch (Exception e) {
			connection.rollback();
			throw new Exception(e.getMessage());
		} finally {
			connection.close();
		}
	}
	
}

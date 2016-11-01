package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Comentario;
import util.ReflectionUtils;


public class ComentarioDAO implements IComentarioDAO {

	private Connection connection = null;
	
	public ComentarioDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void inserir(Comentario comentario) throws Exception {

		try {
		
			comentario.validar();
			
			String sql  = "INSERT INTO COMENTARIO (LOGIN, ID_TOPICO, COMENTARIO) VALUES (?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, comentario.getUsuario().getLogin());
			stm.setInt(2, comentario.getTopico().getId());
			stm.setString(3, comentario.getTexto());
			stm.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir comentário.", e);
		}
	}

	@Override
	public List<Comentario> listarPeloTopico(Integer topicoId) {
		
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			List<Comentario> resultado = new ArrayList<>();
			String sql  = "SELECT * FROM COMENTARIO WHERE ID_TOPICO = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, topicoId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Comentario comentario = new Comentario();
				ReflectionUtils.resultSetToObject(comentario, rs);
				comentario.setUsuario(usuarioDAO.buscar(rs.getString("login")));
				resultado.add(comentario);
			}
			
			return resultado;
		
		} catch (Exception e) {
			throw new RuntimeException("Erro ao listar comentários do tópico.", e);
		}
	}
	
	@Override
	public Comentario buscar(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comentario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}

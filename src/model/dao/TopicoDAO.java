package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Comentario;
import model.domain.Topico;
import util.ReflectionUtils;


public class TopicoDAO implements ITopicoDAO {

	private Connection connection = null;
	

	public TopicoDAO(Connection connection) {
		super();
		this.connection = connection;
	}

	@Override
	public void inserir(Topico topico) throws Exception {

		try {
		
			topico.validar();
			
			String sql  = "INSERT INTO TOPICO (LOGIN, TITULO, CONTEUDO) VALUES (?, ?, ?)";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setString(1, topico.getUsuario().getLogin());
			stm.setString(2, topico.getTitulo());
			stm.setString(3, topico.getConteudo());
			stm.executeUpdate();
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao inserir tópico.", e);
		}
	}

	@Override
	public List<Topico> listar() {
		
		try {
		
			UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			List<Topico> resultado = new ArrayList<>();
			String sql  = "SELECT * FROM TOPICO";
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Topico topico = new Topico();
				ReflectionUtils.resultSetToObject(topico, rs);
				topico.setUsuario(usuarioDAO.buscar(rs.getString("login")));
				resultado.add(topico);
			}
			
			return resultado;
		
		} catch (Exception e) {
			throw new RuntimeException("Erro ao listar tópicos.", e);
		}
	}

	@Override
	public Topico buscar(Integer id) throws Exception {
		
		try {
			
			ComentarioDAO comentarioDAO = new ComentarioDAO(connection);
			UsuarioDAO usuarioDAO = new UsuarioDAO(connection);
			Topico topico = null;
			String sql  = "SELECT * FROM TOPICO WHERE ID_TOPICO = ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				topico = new Topico();
				ReflectionUtils.resultSetToObject(topico, rs);
				topico.setUsuario(usuarioDAO.buscar(rs.getString("login")));
				List<Comentario> comentarios = comentarioDAO.listarPeloTopico(id);
				topico.getComentarios().addAll(comentarios);
			}
			else {
				throw new Exception("Tópico não encontrado.");
			}
			return topico;
		
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao buscar tópico.", e);
		}
	}

}

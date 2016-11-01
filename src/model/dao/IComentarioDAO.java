package model.dao;

import java.util.List;

import model.domain.Comentario;

public interface IComentarioDAO extends IBaseDAO<Comentario, Integer> {

	public List<Comentario> listarPeloTopico(Integer topicoId);
}

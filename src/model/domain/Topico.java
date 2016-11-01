package model.domain;

import java.util.ArrayList;
import java.util.List;

import model.infra.Column;
import util.ReflectionUtils;

public class Topico {

	@Column(name="id_topico")
	private Integer id;
	
	private Usuario usuario;
	
	@Column(name="titulo", required=true)
	private String titulo;
	
	@Column(name="conteudo", required=true)
	private String conteudo;
	
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Topico(){}
	
	public Topico(Integer id){
		this.id = id;
	}
	
	public Topico(Usuario usuario, String titulo, String conteudo) {
		super();
		this.usuario = usuario;
		this.titulo = titulo;
		this.conteudo = conteudo;
	}
	
	public void validar() throws Exception {
		if (!ReflectionUtils.validateObject(this))
			throw new Exception("É necessário preencher todos os campos obrigatórios.");
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
}

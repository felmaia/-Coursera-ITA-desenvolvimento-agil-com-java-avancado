package model.domain;

import model.infra.Column;
import util.ReflectionUtils;

public class Comentario {

	@Column(name="id_comentario")
	private Integer id;
	
	private Usuario usuario;
	
	private  Topico topico;
	
	@Column(name="comentario", required=true)
	private String texto;
	
	public Comentario(){}
	
	public Comentario(Usuario usuario, Topico topico, String texto) {
		super();
		this.usuario = usuario;
		this.topico = topico;
		this.texto = texto;
	}
	
	public void validar() throws Exception {
		if (!ReflectionUtils.validateObject(this))
			throw new Exception("Favor inserir um texto para o comentário.");
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
	public Topico getTopico() {
		return topico;
	}
	public void setTopico(Topico topico) {
		this.topico = topico;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	
}

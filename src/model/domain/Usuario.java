package model.domain;

import model.infra.Column;
import util.ReflectionUtils;

public class Usuario {

	@Column(name="login", required=true)
	private String login;
	
	@Column(name="email", required=true)
	private String email;
	
	@Column(name="nome", required=true)
	private String nome;
	
	@Column(name="senha", required=true)
	private String senha;
	
	@Column(name="pontos", required=true)
	private Integer pontos = 0;
	
	public Usuario() {}
	
	public void validar() throws Exception {
		if (!ReflectionUtils.validateObject(this))
			throw new Exception("É necessário preencher todos os campos obrigatórios.");
	}
	
	public Usuario(String nome, String email, String login, String senha) {
		this.nome = nome;
		this.email = email;
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getPontos() {
		return pontos;
	}
	
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}
	
	@Override
	public String toString() {
		return "Usuario [login=" + login + ", email=" + email + ", nome=" + nome + ", senha=" + senha + ", pontos="
				+ pontos + "]";
	}
	
}

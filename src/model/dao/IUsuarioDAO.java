package model.dao;

import java.util.List;

import model.domain.Usuario;

public interface IUsuarioDAO extends IBaseDAO<Usuario, String> {

   public Usuario autenticar(String login, String senha) throws Exception;
   public void adicionarPontos(String login, int pontos);
   public List<Usuario> ranking();
}

package model.dao;

import java.util.List;

public interface IBaseDAO<E, PK> {

   public void inserir(E e) throws Exception;
   public E buscar(PK id) throws Exception;
   public List<E> listar();
}

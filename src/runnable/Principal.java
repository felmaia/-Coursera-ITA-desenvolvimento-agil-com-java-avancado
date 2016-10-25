package runnable;

import java.util.List;

import model.usuario.Usuario;
import model.usuario.UsuarioDAO;
import model.usuario.UsuarioDAOImpl;

public class Principal {

	public static void main(String[] args) {
		
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		
		Usuario u = usuarioDAO.recuperar("maia");
		System.out.println(u);
		
		System.out.println("-------------------");
		
		List<Usuario> lista = usuarioDAO.ranking();
		lista.forEach(System.out::println);
	}
}

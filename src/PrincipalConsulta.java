import java.util.List;

public class PrincipalConsulta {

	public static void main(String[] args) {
		
		List<Usuario> lista = UsuarioDAO.todosUsuarios();
		
		lista.forEach(System.out::println);
	}

}

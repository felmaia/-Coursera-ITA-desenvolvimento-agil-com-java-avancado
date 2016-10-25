
public class PrincipalInsert {


	public static void main(String[] args) {

		Usuario u = new Usuario();
		u.setLogin("maia");
		u.setNome("Felipe Maia");
		u.setEmail("maia@ita.br");
		
		UsuarioDAO.inserir(u);
	}

}

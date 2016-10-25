import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Usuario> todosUsuarios() {
		List<Usuario> todos = new ArrayList<>();
		
		try (Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/usuarios", "root", "")) {
			
			String sql = "SELECT LOGIN, NOME, EMAIL FROM USUARIO";
			PreparedStatement stm = c.prepareStatement(sql);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Usuario u = new Usuario();
				u.setLogin(rs.getString("login"));
				u.setNome(rs.getString("nome"));
				u.setEmail(rs.getString("email"));
				todos.add(u);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao acessar banco de dados.", e);
		}
		
		return todos;
	}
	
	public static void inserir(Usuario usuario) {
		
		try (Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/usuarios", "root", "")) {
			
			String sql ="INSERT INTO USUARIO (`login`, `nome`, `email`) VALUES (?, ?, ?)";
			PreparedStatement stm = c.prepareStatement(sql);
			stm.setString(1, usuario.getLogin());
			stm.setString(2, usuario.getNome());
			stm.setString(3, usuario.getEmail());
			stm.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao acessar banco de dados.", e);
		}
	}
}

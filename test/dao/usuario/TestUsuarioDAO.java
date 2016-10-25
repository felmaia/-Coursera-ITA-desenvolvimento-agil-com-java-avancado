package dao.usuario;

import static model.ConnectionFactory.DATABASE_CONNECTION_URL;
import static model.ConnectionFactory.DATABASE_DRIVER;
import static model.ConnectionFactory.DATABASE_LOGIN;
import static model.ConnectionFactory.DATABASE_PASSWORD;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.Before;
import org.junit.Test;

import model.usuario.Usuario;
import model.usuario.UsuarioDAO;
import model.usuario.UsuarioDAOImpl;

public class TestUsuarioDAO {

	JdbcDatabaseTester jdt;
	UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(DATABASE_DRIVER, DATABASE_CONNECTION_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load("/dao/usuario/inicio.xml");
		jdt.setDataSet(dataSet);
		jdt.onSetup();
	}
	
	@Test
	public void recuperar() {
		Usuario u = usuarioDAO.recuperar("maia");
		assertEquals("Felipe", u.getNome());
	}
	
	@Test
	public void ranking() throws Exception {
		List<Usuario> lista = usuarioDAO.ranking();
		assertEquals(3, lista.size());
		assertEquals("maia", lista.get(0).getLogin());
		
		List<Integer> pontosOrdernadosDesc = lista.stream().map(Usuario::getPontos).collect(Collectors.toList());
		Collections.sort(pontosOrdernadosDesc);
		Collections.reverse(pontosOrdernadosDesc);
		assertEquals(pontosOrdernadosDesc, lista.stream().map(Usuario::getPontos).collect(Collectors.toList()));
	}
	
	@Test
	public void inserir() throws Exception {
		
		Usuario u = new Usuario();
		u.setLogin("silva");
		u.setNome("Mayara Silva");
		u.setEmail("silva@email.com.br");
		u.setSenha("345");
		u.setPontos(7);
		
		usuarioDAO.inserir(u);
		
		ITable currentTable = getTableFromDatabase("USUARIO");
		ITable expectedTable = getTableFromXMLFile("/dao/usuario/verificaInserir.xml", "USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void adicionarPontos() throws Exception {
		
		usuarioDAO.adicionarPontos("maia", 2);
		Usuario u = usuarioDAO.recuperar("maia");
		assertEquals(new Integer(12), u.getPontos());
		
		ITable currentTable = getTableFromDatabase("USUARIO");
		ITable expectedTable = getTableFromXMLFile("/dao/usuario/verificaAdicionarPontos.xml", "USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	private ITable getTableFromDatabase(String table) throws SQLException, Exception {
		
		IDataSet dataSet = jdt.getConnection().createDataSet();
		return dataSet.getTable(table);
	}
	
	private ITable getTableFromXMLFile(String file, String table) throws DataSetException {
		
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load(file);
		return expectedDataSet.getTable("USUARIO");
	}
	
}

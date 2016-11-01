package dao.usuario;

import static model.infra.ConnectionFactory.DATABASE_CONNECTION_URL;
import static model.infra.ConnectionFactory.DATABASE_DRIVER;
import static model.infra.ConnectionFactory.DATABASE_LOGIN;
import static model.infra.ConnectionFactory.DATABASE_PASSWORD;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.dbunit.Assertion;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import model.dao.IUsuarioDAO;
import model.dao.UsuarioDAO;
import model.domain.Usuario;
import model.infra.ConnectionFactory;
import util.DBUnitUtils;



public class TestUsuarioDAO {

	static Connection connection = ConnectionFactory.getConnection();
	
	JdbcDatabaseTester jdt;
	IUsuarioDAO usuarioDAO = new UsuarioDAO(connection);
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(DATABASE_DRIVER, DATABASE_CONNECTION_URL+"?&sessionVariables=FOREIGN_KEY_CHECKS=0", 
				DATABASE_LOGIN, DATABASE_PASSWORD);
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load("/dao/usuario/inicio.xml");
		jdt.setDataSet(dataSet);
		jdt.onSetup();
	}
	
	@AfterClass
	public static void closeConnection() throws SQLException {
		connection.close();
	}
	
	@Test
	public void buscar() throws Exception {
		Usuario u = usuarioDAO.buscar("maia");
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
		
		ITable currentTable = DBUnitUtils.getTableFromDatabase(jdt, "USUARIO");
		ITable expectedTable = DBUnitUtils.getTableFromXMLFile("/dao/usuario/verificaInserir.xml", "USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
	@Test
	public void adicionarPontos() throws Exception {
		
		usuarioDAO.adicionarPontos("maia", 2);
		Usuario u = usuarioDAO.buscar("maia");
		assertEquals(new Integer(12), u.getPontos());
		
		ITable currentTable = DBUnitUtils.getTableFromDatabase(jdt, "USUARIO");
		ITable expectedTable = DBUnitUtils.getTableFromXMLFile("/dao/usuario/verificaAdicionarPontos.xml", "USUARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
}

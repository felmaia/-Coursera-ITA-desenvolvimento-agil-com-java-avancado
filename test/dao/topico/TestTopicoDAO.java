package dao.topico;

import static model.infra.ConnectionFactory.DATABASE_CONNECTION_URL;
import static model.infra.ConnectionFactory.DATABASE_DRIVER;
import static model.infra.ConnectionFactory.DATABASE_LOGIN;
import static model.infra.ConnectionFactory.DATABASE_PASSWORD;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.dbunit.Assertion;
import org.dbunit.DatabaseUnitException;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import model.dao.ITopicoDAO;
import model.dao.TopicoDAO;
import model.dao.UsuarioDAO;
import model.domain.Topico;
import model.domain.Usuario;
import model.infra.ConnectionFactory;
import util.DBUnitUtils;



public class TestTopicoDAO {

	static Connection connection = ConnectionFactory.getConnection();
	
	JdbcDatabaseTester jdt;
	ITopicoDAO topicoDAO = new TopicoDAO(connection);
	
	/**
	 * MySQL ONLY: HACK PARA RESETAR AUTOINCREMENT DA CHAVE PRIMARIA
	 */
	public class SequenceResetOperation extends DatabaseOperation {
	   
		@Override
	    public void execute(IDatabaseConnection connection, IDataSet dataSet)
	                throws DatabaseUnitException, SQLException {
	        Connection conn = connection.getConnection();
	        Statement stmt = conn.createStatement();
	        stmt.execute("ALTER TABLE TOPICO AUTO_INCREMENT = 1");
	        stmt.close();
        }
	}	
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(DATABASE_DRIVER, DATABASE_CONNECTION_URL+"?&sessionVariables=FOREIGN_KEY_CHECKS=0", 
				DATABASE_LOGIN, DATABASE_PASSWORD);
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load("/dao/topico/inicio.xml");
		
		// COMENTAR LINHA ABAIXO SE BANCO NAO FOR MySQL
		DatabaseOperation.TRANSACTION(new SequenceResetOperation()).execute(jdt.getConnection(), dataSet);
		
		jdt.setDataSet(dataSet);
		jdt.onSetup();
	}
	
	@AfterClass
	public static void closeConnection() throws SQLException {
		connection.close();
	}
	
	@Test
	public void buscar() throws Exception {
		Topico t = topicoDAO.buscar(1);
		assertEquals("Titulo Topico 1", t.getTitulo());
	}
	
	@Test
	public void listar() throws Exception {
		List<Topico> lista = topicoDAO.listar();
		assertEquals(2, lista.size());
		assertEquals("Titulo Topico 1", lista.get(0).getTitulo());
		assertEquals("Titulo Topico 2", lista.get(1).getTitulo());
	}
	
	@Test
	public void inserir() throws Exception {
		
		Usuario usuario = new UsuarioDAO(connection).buscar("maia");
		Topico t = new Topico();
		t.setId(3);
		t.setTitulo("Titulo Topico 3");
		t.setConteudo("Este é o tópico 3.");
		t.setUsuario(usuario);
		topicoDAO.inserir(t);
		
		ITable currentTable = DBUnitUtils.getTableFromDatabase(jdt, "TOPICO");
		ITable expectedTable = DBUnitUtils.getTableFromXMLFile("/dao/topico/verificaInserir.xml", "TOPICO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
	
}

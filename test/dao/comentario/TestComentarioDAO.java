package dao.comentario;

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

import model.dao.ComentarioDAO;
import model.dao.IComentarioDAO;
import model.dao.TopicoDAO;
import model.dao.UsuarioDAO;
import model.domain.Comentario;
import model.domain.Topico;
import model.domain.Usuario;
import model.infra.ConnectionFactory;
import util.DBUnitUtils;



public class TestComentarioDAO {

	static Connection connection = ConnectionFactory.getConnection();
	
	JdbcDatabaseTester jdt;
	IComentarioDAO comentarioDAO = new ComentarioDAO(connection);
	
	/**
	 * MySQL ONLY: HACK PARA RESETAR AUTOINCREMENT DA CHAVE PRIMARIA
	 */
	public class SequenceResetOperation extends DatabaseOperation {
	   
		@Override
	    public void execute(IDatabaseConnection connection, IDataSet dataSet)
	                throws DatabaseUnitException, SQLException {
	        Connection conn = connection.getConnection();
	        Statement stmt = conn.createStatement();
	        stmt.execute("ALTER TABLE COMENTARIO AUTO_INCREMENT = 1");
	        stmt.close();
        }
	}	
	
	@Before
	public void setUp() throws Exception {
		jdt = new JdbcDatabaseTester(DATABASE_DRIVER, DATABASE_CONNECTION_URL+"?&sessionVariables=FOREIGN_KEY_CHECKS=0", 
				DATABASE_LOGIN, DATABASE_PASSWORD);
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet dataSet = loader.load("/dao/comentario/inicio.xml");
		
		// COMENTAR LINHA ABAIXO SE BANCO NAO FOR MySQL
		DatabaseOperation.TRANSACTION(new SequenceResetOperation()).execute(jdt.getConnection(), dataSet);
		
		//jdt.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		jdt.setDataSet(dataSet);
		jdt.onSetup();
	}
	
	@AfterClass
	public static void closeConnection() throws SQLException {
		connection.close();
	}
	
	@Test
	public void listarPeloTopico() throws Exception {
		List<Comentario> lista = comentarioDAO.listarPeloTopico(1);
		assertEquals(2, lista.size());
		assertEquals("Primeiro Comentario do Topico 1", lista.get(0).getTexto());
		assertEquals("Segundo Comentario do Topico 1", lista.get(1).getTexto());
	}
	
	@Test
	public void inserir() throws Exception {
		
		Usuario usuario = new UsuarioDAO(connection).buscar("maia");
		Topico topico = new TopicoDAO(connection).buscar(1);
		Comentario c = new Comentario(usuario, topico, "Terceiro Comentario do Topico 1");
		comentarioDAO.inserir(c);
		
		ITable currentTable = DBUnitUtils.getTableFromDatabase(jdt, "COMENTARIO");
		ITable expectedTable = DBUnitUtils.getTableFromXMLFile("/dao/comentario/verificaInserir.xml", "COMENTARIO");
		
		Assertion.assertEquals(expectedTable, currentTable);
	}
}

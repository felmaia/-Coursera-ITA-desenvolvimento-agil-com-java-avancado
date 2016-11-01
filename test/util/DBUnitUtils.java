package util;

import java.sql.SQLException;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.util.fileloader.DataFileLoader;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

public class DBUnitUtils {

	public static ITable getTableFromDatabase(JdbcDatabaseTester jdt, String table) throws SQLException, Exception {
		
		IDataSet dataSet = jdt.getConnection().createDataSet();
		return dataSet.getTable(table);
	}
	
	public static ITable getTableFromXMLFile(String file, String table) throws DataSetException {
		
		DataFileLoader loader = new FlatXmlDataFileLoader();
		IDataSet expectedDataSet = loader.load(file);
		return expectedDataSet.getTable(table);
	}

}

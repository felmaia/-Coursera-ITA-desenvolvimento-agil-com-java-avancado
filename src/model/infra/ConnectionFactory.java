package model.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public static final String DATABASE_CONNECTION_URL = "jdbc:mysql://localhost/coursera";
	public static final String DATABASE_LOGIN = "root";
	public static final String DATABASE_PASSWORD = "";
	public static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
	
    private ConnectionFactory(){};
    
    static {
		try {
			Class.forName(DATABASE_DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Erro ao iniciar driver do banco de dados.", e);
		}
	}

	public static Connection getConnection(){
		try{
			return DriverManager.getConnection(
					DATABASE_CONNECTION_URL, DATABASE_LOGIN, DATABASE_PASSWORD);
		}catch (SQLException e){
			throw new RuntimeException("Erro ao acessar o banco de dados.",e);
		}
	}
	
}

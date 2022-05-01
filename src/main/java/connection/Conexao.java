package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static String linkDB = "jdbc:mysql://localhost/db_users";
	private static String userDB = "root";
	private static String pswDB = "JairS218";
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");//Nome do driver utilizado
			
			return DriverManager.getConnection(linkDB, userDB, pswDB);	
		} 
		catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}
	}
}

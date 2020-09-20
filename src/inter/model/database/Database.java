package inter.model.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
	private static Properties prop;
	private static String propFileName = "/config/config_db.properties";
	
	private Database(){
		
	}
	
	public static void loadProperties() {
		if(prop == null) {
			InputStream is = Database.class.getResourceAsStream(propFileName);
			try {
				prop = new Properties();
				prop.load(is);
			} catch (IOException e) {
				System.out.println("Erro ao carregar as propriedades de conexão!");
				e.printStackTrace();
			}
		}		
	}
	
	public static Connection getConnection() {
		loadProperties();
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(prop.getProperty("url"),
			prop.getProperty("user"),
			prop.getProperty("pwd"));			
		} catch (SQLException e) {
			System.out.println("Erro ao criar uma conexão com o banco");
			e.printStackTrace();
		}
		return conn;
	}
}

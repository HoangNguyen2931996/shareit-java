package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class LibraryConnectDb {
	private Connection conn;
	private String url;
	private String user;
	private String password;
	private ReadPropertiesLibrary propertiesLibrary;
	private Properties properties;
	public LibraryConnectDb(){
		propertiesLibrary = new ReadPropertiesLibrary();
		properties = propertiesLibrary.readProperties();
		this.url = properties.getProperty("url");
		this.user = properties.getProperty("user");
		this.password = properties.getProperty("password");
	}

	public Connection getConnectMySql(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) {
		LibraryConnectDb lDb = new LibraryConnectDb();
		System.out.println(lDb.getConnectMySql());
	}
}

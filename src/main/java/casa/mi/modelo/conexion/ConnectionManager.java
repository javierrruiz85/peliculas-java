package casa.mi.modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private final static String URL = "jdbc:mysql://localhost/peliculas";
	private final static String USUARIO = "debian-sys-maint";
	private final static String PASS = "o8lAkaNtX91xMUcV";
	
	/*
	private final static String URL = "jdbc:mysql://localhost:36972/peliculas";
	private final static String USUARIO = "ipartek";
	private final static String PASS = "ipartek";
	*/
	
	//importante, arriba en el import cambiarlo por el que necesita, por defecto pone otro
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
			
		Connection conexion = null;
		
		// comprobar que tengamos el .jar de MySQL
		Class.forName("com.mysql.jdbc.Driver");
		
		//establecer la conexion
		conexion = DriverManager.getConnection(URL, USUARIO, PASS);
		
		return conexion;
	};

}

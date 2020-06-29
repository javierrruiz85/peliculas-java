package casa.mi.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import casa.mi.modelo.conexion.ConnectionManager;
import casa.mi.modelo.pojo.Usuario;

public class UsuarioDao {
	
	// Inicio singleton
	
	public static UsuarioDao INSTANCE = null;
	
	private UsuarioDao() {
		super();
	}
	
	public static synchronized UsuarioDao getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDao();
		}
		
		return INSTANCE;
		
	}
	
	// Fin Singleton
	
	// executeQuery => ResultSet
	private final String SQL_EXISTE = "SELECT id, nombre, pass, imagen FROM usuario WHERE nombre = ? AND pass = ? LIMIT 500;" ;
	
	// executeUpdate => int de numero de filas afectadas (affectedRows)
	
	
	
	/////////////////////////////////////////////////   EXISTE   ////////////////////////////////////////////////////
	
	public Usuario existe(String nombre, String pass) {
		
		Usuario registro = null;
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_EXISTE);
			) {
			
			pst.setString(1, nombre);
			pst.setString(2, pass);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				
				if ( rs.next() ) {
					
					registro = new Usuario();
					registro.setId(rs.getInt("id"));
					registro.setNombre(rs.getString("nombre"));
					registro.setPass(rs.getString("pass"));
					registro.setImagen(rs.getString("imagen"));
					
				} // if
				
			} // try 2
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} // try-catch 1
		
		return registro;
		
	} // existe
	

}

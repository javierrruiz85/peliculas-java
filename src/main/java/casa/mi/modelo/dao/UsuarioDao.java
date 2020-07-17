package casa.mi.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import casa.mi.modelo.conexion.ConnectionManager;
import casa.mi.modelo.pojo.Rol;
import casa.mi.modelo.pojo.Usuario;

public class UsuarioDao {
	
	// Inicio singleton
	
	public static UsuarioDao INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(UsuarioDao.class);
	
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
	private final String SQL_EXISTE = "SELECT u.id, u.nombre, pass, imagen, id_rol, r.nombre 'nombre_rol' FROM usuario u, rol r WHERE u.id_rol = r.id AND u.nombre = ? AND pass = ? LIMIT 500;" ;
	
	// executeUpdate => int de numero de filas afectadas (affectedRows)
	
	
	
	/////////////////////////////////////////////////   EXISTE   ////////////////////////////////////////////////////
	
	public Usuario existe(String nombre, String pass) {
		
		Usuario usuario = null;
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_EXISTE);
			) {
			
			pst.setString(1, nombre);
			pst.setString(2, pass);
			
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				
				if ( rs.next() ) {
					
					// usuario
					usuario = new Usuario();
					usuario.setId(rs.getInt("id"));
					usuario.setNombre(rs.getString("nombre"));
					usuario.setPass(rs.getString("pass"));
					usuario.setImagen(rs.getString("imagen"));
					
					// rol
					Rol rol = new Rol();
					rol.setId(rs.getInt("id_rol"));
					rol.setNombre(rs.getString("nombre_rol"));
					
					// setear el rol al usuario
					usuario.setRol(rol);
					
				} // if
				
			} // try 2
			
		} catch (Exception e) {
			
			//e.printStackTrace();
			LOG.error(e);
			
		} // try-catch 1
		
		return usuario;
		
	} // existe
	

}

package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.Connection;

import model.pojo.Pelicula;
import modelo.conexion.ConnectionManager;

public class PeliculaDao {
	
	// Inicio singleton
	
	public static PeliculaDao INSTANCE = null;
	
	private PeliculaDao() {
		super();
	}
	
	public static synchronized PeliculaDao getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new PeliculaDao();
		}
		
		return INSTANCE;
		
	}
	
	// Fin Singleton
	
	public ArrayList<Pelicula> getAll() {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		String sql = "SELECT id, nombre FROM peliculas ORDER BY id ASC; ";
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				){
			
			while( rs.next() ) {
				
				//recuperar columnas del resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				
				//crear el pojo con los datos del resultset
				Pelicula pelicula = new Pelicula();
				pelicula.setId(id);
				pelicula.setNombre(nombre);
				
				//guardar en el arraylist
				registros.add(pelicula);
				
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return registros;
		
	}
	

}

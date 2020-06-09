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
	
	
	// executeQuery => ResultSet
	private final String SQL_GET_ALL = " SELECT id, nombre, duracion, anio, caratula FROM peliculas ORDER BY id ASC; ";
	private final String SQL_GET_BY_ID = " SELECT id, nombre, duracion, anio, caratula FROM peliculas WHERE id = ? ; " ;
	private final String SQL_GET_BY_NOMBRE = " SELECT id, nombre, duracion, anio, caratula FROM peliculas WHERE nombre LIKE ?; " ;
	
	// executeUpdate => int de numero de filas afectadas (affectedRows)
	private final String SQL_INSERT = " INSERT INTO peliculas (nombre, duracion, anio, caratula) VALUES (?, ?, ?, ?); " ;
	
	
	
	///////////////////////////////////////////////////////////////////		getAll	  ///////////////////////////////////////////////////////////////
	
	
	public ArrayList<Pelicula> getAll() {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		//String sql = "SELECT id, nombre, duracion, anio, caratula FROM peliculas ORDER BY id ASC; ";
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();
				){
			
			while( rs.next() ) {
				
				//recuperar columnas del resultset
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				int duracion = rs.getInt("duracion");
				int anio = rs.getInt("anio");
				String caratula = rs.getString("caratula");
				
				//crear el pojo con los datos del resultset
				Pelicula pelicula = new Pelicula();
				pelicula.setId(id);
				pelicula.setNombre(nombre);
				pelicula.setDuracion(duracion);
				pelicula.setAnio(anio);
				pelicula.setCaratula(caratula);
				
				//guardar en el arraylist
				registros.add(pelicula);
				
			}
			
		} catch (Exception e) {		
			e.printStackTrace();
		}
		
		return registros;
		
	}
	
	
	///////////////////////////////////////////////////////////////////		getById	  ///////////////////////////////////////////////////////////////

	
	public Pelicula getById(int id) throws Exception {
		
		Pelicula registro = new Pelicula();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
			) {
			
			// le decimos que la primera interrogacion de la sql (el numero 1) es el parametro id
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				
				registro.setId(rs.getInt("id"));
				registro.setNombre(rs.getString("nombre"));
				registro.setDuracion(rs.getInt("duracion"));
				registro.setAnio(rs.getInt("anio"));
				registro.setCaratula(rs.getString("caratula"));
				
			} else {
				
				throw new Exception("No se puede encontrar registro con id= " + id);
				
			} // ifelse
			
		}
		
		return registro;
	}
	
	
	
	///////////////////////////////////////////////////////////////////		getByNombre	  ///////////////////////////////////////////////////////////////

	public ArrayList<Pelicula> getAllByNombre (String palabraBuscada) {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_NOMBRE);
			) {
			
			pst.setString(1, "%" + palabraBuscada + "%");
			
			try ( ResultSet rs = pst.executeQuery(); ) {
				
				while ( rs.next() ) {
					
					Pelicula pelicula = new Pelicula();
					
					pelicula.setId(rs.getInt("id"));
					pelicula.setNombre(rs.getString("nombre"));
					pelicula.setDuracion(rs.getInt("duracion"));
					pelicula.setAnio(rs.getInt("anio"));
					pelicula.setCaratula(rs.getString("caratula"));
					
					registros.add(pelicula);
					
				} // while
				
			} // try2
			
		} catch (Exception e) {

			e.printStackTrace();

		} // catch
		
		
		return registros;
	}
	
	
	
	///////////////////////////////////////////////////////////////////		INSERT	  ///////////////////////////////////////////////////////////////
	
	public Pelicula insert(Pelicula pojo) throws Exception {
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
			) {
			
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getDuracion());
			pst.setInt(3, pojo.getAnio());
			pst.setString(4, pojo.getCaratula());
			
			
			int affectedRows = pst.executeUpdate();
			if (affectedRows == 1) {
				
				try ( ResultSet rsKeys = pst.getGeneratedKeys() ){
					
					if ( rsKeys.next() ) {
						pojo.setId(rsKeys.getInt(1));
					}
				}//try2
				
			} else {
				
				throw new Exception ("No se ha podido guardar el registro " + pojo);
			}
						
		}//try 
		
		return pojo;
	}
	
	
	
}


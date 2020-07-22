package casa.mi.modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import casa.mi.modelo.conexion.ConnectionManager;
import casa.mi.modelo.pojo.Distribuidora;
import casa.mi.modelo.pojo.Pelicula;
import casa.mi.modelo.pojo.ResumenUsuario;

import java.sql.Connection;

public class PeliculaDao {
	
	// Inicio singleton
	
	private final static Logger LOG = Logger.getLogger(PeliculaDao.class);
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
	// private final String SQL_GET_ALL = " SELECT id, nombre, duracion, anio, caratula FROM peliculas ORDER BY id ASC LIMIT 500; ";
	private final String SQL_GET_ALL = " SELECT " + 
											"p.id 'pelicula_id', " + 
											"p.nombre 'pelicula_titulo', " + 
											"duracion, " + 
											"anio, " + 
											"caratula, " + 
											"d.id 'distribuidora_id', " + 
											"d.nombre 'distribuidora_nombre' " + 
										"FROM peliculas p, distribuidora d " + 
										"WHERE p.id_distribuidora = d.id " + 
										"ORDER BY p.id ASC " + 
										"LIMIT 500; " ;
	
	private final String SQL_GET_BY_ID = " SELECT " + 
											"p.id 'pelicula_id', " + 
											"p.nombre 'pelicula_titulo', " + 
											"duracion, " + 
											"anio, " + 
											"caratula, " + 
											"d.id 'distribuidora_id', " + 
											"d.nombre 'distribuidora_nombre' " + 
										"FROM peliculas p, distribuidora d " + 
										"WHERE p.id_distribuidora = d.id AND p.id = ?; " ;
	
	private final String SQL_GET_LAST = " SELECT " + 
											"p.id 'pelicula_id', " + 
											"p.nombre 'pelicula_titulo', " + 
											"p.duracion, " + 
											"p.anio, " + 
											"p.caratula, " + 
											"d.id 'distribuidora_id', " + 
											"d.nombre 'distribuidora_nombre'" + 
										"FROM " + 
											"peliculas p, distribuidora d " + 
										"WHERE " + 
											"p.id_distribuidora = d.id " + 
										"ORDER BY p.id DESC " + 
										"LIMIT ?; ";
	
	private final String SQL_GET_BY_DISTRIBUIDORA = " SELECT " + 
															"p.id 'pelicula_id', " + 
															"p.nombre 'pelicula_titulo', " + 
															"p.duracion, " + 
															"p.anio, " + 
															"p.caratula, " + 
															"d.id 'distribuidora_id', " + 
															"d.nombre 'distribuidora_nombre' " + 
														"FROM " + 
															"peliculas p, distribuidora d " + 
														"WHERE " + 
															"p.id_distribuidora = d.id " + 
														"AND d.id = ? " + 
														"ORDER BY d.id DESC LIMIT ? ; ";
	
	/* 
	 * Para crear la vista:
	 * 
	CREATE VIEW v_usuario_peliculas AS 
	SELECT  
	  id_usuario,  
	  COUNT( id_usuario ) as total , 
	  SUM( ISNULL( fecha_validacion )) as pendiente ,
	  COUNT( fecha_validacion ) as aprobado 
	FROM peliculas 
	GROUP BY id_usuario;
	*/
	
	// la sql que usamos para sacar los datos de la vista
	private final String SQL_VIEW_RESUMEN_USUARIO = " SELECT id_usuario, total, aprobado, pendiente FROM v_usuario_peliculas WHERE id_usuario = ?; ";
	
	private final String SQL_GET_BY_USUARIO_PELICULA_APROBADA = "SELECT \n" + 
																	"p.id 'pelicula_id', \n" + 
																	"p.nombre 'pelicula_titulo', \n" + 
																	"duracion, \n" + 
																	"anio, \n" + 
																	"caratula, \n" + 
																	"d.id 'distribuidora_id', \n" + 
																	"d.nombre 'distribuidora_nombre'  \n" + 
																"FROM \n" + 
																	"peliculas p , distribuidora d\n" + 
																"WHERE \n" + 
																	"p.id_distribuidora  = d.id \n" + 
																"AND fecha_validacion IS NOT NULL \n" + 
																"AND p.id_usuario = ? \n" + 
																"ORDER BY p.id ASC LIMIT 500;";
	
	private final String SQL_GET_BY_USUARIO_PELICULA_SIN_APROBAR = 	"SELECT \n" + 
																		"p.id 'pelicula_id', \n" + 
																		"p.nombre 'pelicula_titulo', \n" + 
																		"duracion, \n" + 
																		"anio, \n" + 
																		"caratula, \n" + 
																		"d.id 'distribuidora_id', \n" + 
																		"d.nombre 'distribuidora_nombre'  \n" + 
																	"FROM \n" + 
																		"peliculas p , distribuidora d\n" + 
																	"WHERE \n" + 
																		"p.id_distribuidora  = d.id \n" + 
																	"AND fecha_validacion IS NULL \n" + 
																	"AND p.id_usuario = ? \n" + 
																	"ORDER BY p.id ASC LIMIT 500;";
	
	private final String SQL_GET_BY_NOMBRE = " SELECT id, nombre, duracion, anio, caratula FROM peliculas WHERE nombre LIKE ? LIMIT 500; ";
	
	
	// executeUpdate => int de numero de filas afectadas (affectedRows)
	private final String SQL_INSERT = " INSERT INTO peliculas (nombre, duracion, anio, caratula, id_distribuidora) VALUES (?, ?, ?, ?, ?); ";
	private final String SQL_UPDATE = " UPDATE peliculas SET nombre = ?, duracion = ?, anio = ?, caratula = ?, id_distribuidora = ? WHERE id = ?; ";
	private final String SQL_DELETE = " DELETE FROM peliculas WHERE id = ?; ";
	
	
	///////////////////////////////////////////////////////////////////		getAll	  ///////////////////////////////////////////////////////////////
	
	
	public ArrayList<Pelicula> getAll() {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		//String sql = "SELECT id, nombre, duracion, anio, caratula FROM peliculas ORDER BY id ASC; ";
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				ResultSet rs = pst.executeQuery();
				){
			
			LOG.debug(pst);
			while( rs.next() ) {
				
				//guardar en el arraylist
				registros.add( mapper(rs) );
				
			}
			
		} catch (Exception e) {		
			//e.printStackTrace();
			LOG.error(e);
		} // try-catch
		
		return registros;
		
	}
	
	
	///////////////////////////////////////////////////////////////////		getById	  ///////////////////////////////////////////////////////////////

	
	public Pelicula getById(int id) throws Exception {
		
		Pelicula registro = new Pelicula();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_ID);
			) {
			
			// le decimos que la primera interrogacion de la sql (el numero 1) es el parametro id
			pst.setInt(1, id);
			LOG.debug(pst);
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				
				registro = mapper(rs);
				
			} else {
				
				throw new Exception("No se puede encontrar registro con id= " + id);
				
			} // ifelse
			
		} // try
		
		return registro;
	} // getById
	
	
	
	///////////////////////////////////////////////////////////////////		getByNombre	  ///////////////////////////////////////////////////////////////

	public ArrayList<Pelicula> getAllByNombre (String palabraBuscada) {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_NOMBRE);
			) {
			
			pst.setString(1, "%" + palabraBuscada + "%");
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery(); ) {
				
				while ( rs.next() ) {
					
					registros.add( mapper(rs) );
					
				} // while
				
			} // try2
			
		} catch (Exception e) {

			//e.printStackTrace();
			LOG.error(e);

		} // catch
		
		
		return registros;
		
	} // getAllByNombre
	
	
	
	///////////////////////////////////////////////////////////////////		getAllByUser	  ///////////////////////////////////////////////////////////////
	
	public ArrayList<Pelicula> getAllByUser(int idUsuario, boolean isAprobada) {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		String sql = ( isAprobada) ? SQL_GET_BY_USUARIO_PELICULA_APROBADA : SQL_GET_BY_USUARIO_PELICULA_SIN_APROBAR;
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(sql);
			) {
			
			// TODO ¿se podria hacer con una sola SQL?
			
			pst.setInt(1, idUsuario);
			
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				while ( rs.next( )) {
					registros.add(mapper(rs));
				} // while
			} // try
			
		} catch (Exception e) {
			LOG.error(e);
		} // try-catch
		
		return registros;
		
	}
	
	
	
	///////////////////////////////////////////////////////////////////		getAllByUser	  ///////////////////////////////////////////////////////////////

	public ResumenUsuario getResumenByUsuario(int idUsuario) {
		
		ResumenUsuario resultado = new ResumenUsuario();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_VIEW_RESUMEN_USUARIO);
			) {
			
			pst.setInt(1, idUsuario);
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				if ( rs.next() ) {
					// mapper de ResultSet a pojo
					// con este mapper nos ahorramos setear campos que no necesitamos, como duracion, anio, caratula, etc
					resultado.setIdUsuario(idUsuario);
					resultado.setPeliculasTotal(rs.getInt("total"));
					resultado.setPeliculasAprobadas(rs.getInt("aprobadas"));
					resultado.setPeliculasPendientes(rs.getInt("pendientes"));
				}
			}
			
		} catch (Exception e) {
			LOG.error(e);
		}
		
		return resultado;
	}
	
	
	
	///////////////////////////////////////////////////////////////////		SQL_GET_LAST	  ///////////////////////////////////////////////////////////////
	
	public ArrayList<Pelicula> getLast(int numRegistros) {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_LAST);
			) {
			
			pst.setInt(1, numRegistros);
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				while ( rs.next() ) {
					registros.add( mapper(rs) );
				}
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error(e);
		}
	
		return registros;
	} // getLast
	
	
	
	///////////////////////////////////////////////////////////		SQL_GET_BY_DISTRIBUIDORA	  ///////////////////////////////////////////////////////
	
	public ArrayList<Pelicula> getAllByDistribuidora(int idDistribuidora, int numRegistros) {
		
		ArrayList<Pelicula> registros = new ArrayList<Pelicula>();
		
		try (	Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_DISTRIBUIDORA);
			) {
			
			pst.setInt(1, idDistribuidora);
			pst.setInt(2, numRegistros);
			LOG.debug(pst);
			
			try ( ResultSet rs = pst.executeQuery() ) {
				while ( rs.next() ) {
					registros.add( mapper(rs) );	
				}
			} // try
			
			 
			
		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error(e);
		} // try-catch
		
		return registros;
	} // getByDistribuidora
	
	
	
	///////////////////////////////////////////////////////////////////		INSERT		///////////////////////////////////////////////////////////////
	
	public Pelicula insert(Pelicula pojo) throws Exception {
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
				
			) {
			
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getDuracion());
			pst.setInt(3, pojo.getAnio());
			pst.setString(4, pojo.getCaratula());
			pst.setInt(5, pojo.getDistribuidora().getId());
			
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows == 1) {
				
				try ( ResultSet rsKeys = pst.getGeneratedKeys() ){
					
					if ( rsKeys.next() ) {
						pojo.setId(rsKeys.getInt(1));
					} // if
				}//try2
				
			} else {
				
				// en caso de que la Exception sea que ya esta repetida, en este caso, la pelicula, no muestra esta Exception, sino que va al controller y muestra las que tiene ahi
				throw new Exception ("No se ha podido guardar el registro " + pojo);
			} // if-else
						
		}//try 
		
		return pojo;
		
	} // insert
	
	
	
	///////////////////////////////////////////////////////////////////		UPDATE		///////////////////////////////////////////////////////////////
	
	public Pelicula update(Pelicula pojo) throws Exception {
		
		if (pojo == null) {
			throw new Exception("No se puede modificar si es NULL");
		} // if
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_UPDATE);
				
			) {
			
			pst.setString(1, pojo.getNombre());
			pst.setInt(2, pojo.getDuracion());
			pst.setInt(3, pojo.getAnio());
			pst.setString(4, pojo.getCaratula());
			pst.setInt(5, pojo.getDistribuidora().getId());
			pst.setInt(6, pojo.getId());
			
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No se puede modificar la pelicula con ID " + pojo.getId() );
			} // if
			
		} catch (Exception e) {
			
			throw new Exception( "Ya existe la pelicula " + pojo.getNombre() );
			
		} // try-catch
		
		return pojo;
		
	} // update
	
	
	
	///////////////////////////////////////////////////////////////////		DELETE		///////////////////////////////////////////////////////////////
	
	public Pelicula delete(int id) throws Exception {
		
		Pelicula registro = getById(id);
		
		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL_DELETE);
				
			) {
			
			pst.setInt(1, id);
			LOG.debug(pst);
			int affectedRows = pst.executeUpdate();
			
			if (affectedRows != 1) {
				throw new Exception("No se pudo eliminar la pelicula con ID " + id);
			} // if
			
		} //try
		
		return registro;
		
	} // delete
	
	
	
	///////////////////////////////////////////////////////////////////		validar		///////////////////////////////////////////////////////////////
	
	public void validar(int id) {
		// TODO hacer
	}
	
	
	
	///////////////////////////////////////////////////////////////////		MAPPER		///////////////////////////////////////////////////////////////
	
	public Pelicula mapper(ResultSet rs) throws SQLException {
		
		Pelicula p = new Pelicula();
		Distribuidora d = new Distribuidora();
		
		p.setId( rs.getInt("pelicula_id") );
		p.setNombre( rs.getString("pelicula_titulo") );
		p.setDuracion( rs.getInt("duracion") );
		p.setAnio( rs.getInt("anio") );
		p.setCaratula( rs.getString("caratula") );
		
		d.setId( rs.getInt("distribuidora_id") );
		d.setNombre( rs.getString("distribuidora_nombre") );
		
		p.setDistribuidora(d);
				
		return p;
		
	}
		
	
}


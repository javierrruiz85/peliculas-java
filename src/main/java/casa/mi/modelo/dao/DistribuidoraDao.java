package casa.mi.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import casa.mi.modelo.conexion.ConnectionManager;
import casa.mi.modelo.pojo.Distribuidora;
import casa.mi.modelo.pojo.Pelicula;

public class DistribuidoraDao {
	
	// Inicio singleton
	
	public static DistribuidoraDao INSTANCE = null;
	private final static Logger LOG = Logger.getLogger(DistribuidoraDao.class);
	
	private DistribuidoraDao() {
		super();
	}
	
	public static synchronized DistribuidoraDao getInstance() {
		
		if (INSTANCE == null) {
			INSTANCE = new DistribuidoraDao();
		}
		
		return INSTANCE;
		
	}
	
	// Fin Singleton
	
	// excuteQuery => ResultSet
	private final String SQL_GET_ALL = " SELECT id, nombre FROM distribuidora ORDER BY nombre ASC; ";
	private final String SQL_GET_ALL_WITH_PELICULAS = " SELECT d.id 'distribuidora_id', d.nombre 'distribuidora_nombre', p.id 'pelicula_id', p.nombre 'pelicula_nombre', duracion, anio, caratula FROM distribuidora d, peliculas p WHERE d.id = p.id_distribuidora ORDER BY d.nombre ASC; ";
	private final String SQL_GET_BY_ID = " SELECT id, nombre FROM distribuidora WHERE id = ?; ";

	// executeUpdate => int de numero de filas afectadas (affectedRows)
	private final String SQL_INSERT = " INSERT INTO distribuidora ( nombre ) VALUES ( ? ) ; ";
	private final String SQL_UPDATE = " UPDATE distribuidora SET nombre = ? WHERE id = ? ; ";
	private final String SQL_DELETE = " DELETE FROM distribuidora WHERE id = ? ; ";
	
	
	//////////////////////////////////////////////////////////////////// getAll ////////////////////////////////////////////////////////////////////
	
	public ArrayList<Distribuidora> getAll() {
		
		ArrayList<Distribuidora> registros = new ArrayList<Distribuidora>();

		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
			  ResultSet rs = pst.executeQuery();
		) {

			LOG.debug(pst);
			while (rs.next()) {
				registros.add(mapper(rs));
			} // while

		} catch (Exception e) {
			//e.printStackTrace();
			LOG.error(e);
		}
		return registros;

	} // getAll
	
	
	
	//////////////////////////////////////////////////////////////////// getAllWithPeliculas ////////////////////////////////////////////////////////////////////
	
	public ArrayList<Distribuidora> getAllWithPeliculas() {
		
		// la clave va a ser de tipo Integer con el id de la Distribuidora
		HashMap<Integer, Distribuidora> registros = new HashMap<Integer, Distribuidora>();

		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL_WITH_PELICULAS);
			  ResultSet rs = pst.executeQuery();
		) {

			System.out.println(pst); // para mostrar en consola el valor de la SQL
			
			LOG.debug(pst);
			
			while (rs.next()) {
				
				int idDistribuidora = rs.getInt("distribuidora_id"); // key del hashmap
				
				Distribuidora d = registros.get(idDistribuidora);
				
				if (d == null) {
					d = new Distribuidora();
					d.setId(idDistribuidora);
					d.setNombre(rs.getString("distribuidora_nombre"));
				}
				
				Pelicula p = new Pelicula();
				p.setId(rs.getInt("pelicula_id"));
				p.setNombre(rs.getString("pelicula_nombre"));
				p.setDuracion(rs.getInt("duracion"));
				p.setAnio(rs.getInt("anio"));
				p.setCaratula(rs.getString("caratula"));
				
				// recuperar las peliculas y añadir una nueva dentro de la distribuidora
				d.getPeliculas().add(p);
				
				// guardar en el hashmap la distribuidora
				registros.put(idDistribuidora, d);
				
				
			} // while

		} catch (Exception e) {
			
			//e.printStackTrace();
			LOG.error(e);
			
		}// try-catch
		
		return new ArrayList<Distribuidora>(registros.values());

	} // getAllWithPeliculas

	

	//////////////////////////////////////////////////////////////////// getById ////////////////////////////////////////////////////////////////////
	
	public Distribuidora getById(int id) throws Exception {
		
		Distribuidora registro = new Distribuidora();
		
		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_GET_BY_ID);
		) {
			
			pst.setInt(1, id);
			LOG.debug(pst);
			ResultSet rs = pst.executeQuery();
			
			if ( rs.next() ) {
				registro = mapper(rs);
			} else {
				throw new Exception("No se puede encontrar el registro con id=" + id);
			} // if-else
			
		} // try
		
		return registro;
		
	} // getById

	
			
	//////////////////////////////////////////////////////////////////// insert ////////////////////////////////////////////////////////////////////
	
	public Distribuidora insert(Distribuidora pojo) throws Exception {
		
		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		) {
			
			pst.setString( 1, pojo.getNombre() );
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			
			if ( affectedRows == 1 ) {
				try ( ResultSet rsKeys = pst.getGeneratedKeys() ) {
					if ( rsKeys.next() ) {
						pojo.setId(rsKeys.getInt(1));
					} // if-1
				} // try-2
			} else {
				throw new Exception("No se ha podido guardar el registro " + pojo);
			} // if-else-1
			
		} // try-1
		
		return pojo;
		
	} // insert

	
	
	//////////////////////////////////////////////////////////////////// update ////////////////////////////////////////////////////////////////////
	
	public Distribuidora update(Distribuidora pojo) throws Exception {
		
		if (pojo==null) {
			throw new Exception("No se puede modificar si es NULL");
		} // if
		
		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_UPDATE);
		) {
			
			pst.setString( 1, pojo.getNombre() );
			pst.setInt( 2, pojo.getId() );
			
			LOG.debug(pst);
			
			int affectedRows = pst.executeUpdate();
			
			if ( affectedRows != 1 ) {
				throw new Exception("No se puede modificar el registro con id=" + pojo.getId());
			} //if
			
		} catch (Exception e) {
			throw new Exception("No se puede modificar la distribuidora con id=" + pojo.getId());
		} // try-catch

		return pojo;
		
	} // update

	
	
	////////////////////////////////////////////////////////////////////delete ////////////////////////////////////////////////////////////////////
			
	public Distribuidora delete(int id) throws Exception {

		Distribuidora pojo = null;
		
		try ( Connection conexion = ConnectionManager.getConnection();
			  PreparedStatement pst = conexion.prepareStatement(SQL_DELETE);
		) {
			
			// recuperamos antes de eliminar
			pojo = getById(id);
			
			// eliminar
			pst.setInt(1, id);
			LOG.debug(pst);
			pst.executeUpdate();

		} // try
		
		return pojo;

	} // delete
	
	
	
	//////////////////////////////////////////////////////////////////// mapper ////////////////////////////////////////////////////////////////////
	
	private Distribuidora mapper(ResultSet rs) throws SQLException {
		Distribuidora d = new Distribuidora();
		d.setId(rs.getInt("id"));
		d.setNombre(rs.getString("nombre"));
		return d;
	} // mapper

}

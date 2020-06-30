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
		//private final String SQL_GET_BY_ID = " SELECT id, nombre FROM distribuidora WHERE id = ?; ";

		// executeUpdate => int de numero de filas afectadas (affectedRows)
		// crear las sql
		
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

		}
		
		
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

		}

		// implementar estos metodos cuando necesitemos

		
		public Distribuidora getById(int id) throws Exception {
			throw new Exception("Sin implementar de momento");
		}

		
		public Distribuidora delete(int id) throws Exception {
			throw new Exception("Sin implementar de momento");
		}

		
		public Distribuidora insert(Distribuidora pojo) throws Exception {
			throw new Exception("Sin implementar de momento");
		}

		
		public Distribuidora update(Distribuidora pojo) throws Exception {
			throw new Exception("Sin implementar de momento");
		}

		private Distribuidora mapper(ResultSet rs) throws SQLException {
			Distribuidora d = new Distribuidora();
			d.setId(rs.getInt("id"));
			d.setNombre(rs.getString("nombre"));
			return d;
		}

}

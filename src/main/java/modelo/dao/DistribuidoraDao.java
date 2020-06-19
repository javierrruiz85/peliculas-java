package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



import model.pojo.Distribuidora;
import modelo.conexion.ConnectionManager;

public class DistribuidoraDao {
	
	// Inicio singleton
	
		public static DistribuidoraDao INSTANCE = null;
		
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

		// executeUpdate => int de numero de filas afectadas (affectedRows)
		// TODO crear las sql
		
		public ArrayList<Distribuidora> getAll() {
			
			ArrayList<Distribuidora> registros = new ArrayList<Distribuidora>();

			try ( Connection conexion = ConnectionManager.getConnection();
				  PreparedStatement pst = conexion.prepareStatement(SQL_GET_ALL);
				  ResultSet rs = pst.executeQuery();
			) {

				while (rs.next()) {
					registros.add(mapper(rs));
				} // while

			} catch (Exception e) {
				e.printStackTrace();
			}
			return registros;

		}

		// TODO implementar estos metodos cuando necesitemos

		
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

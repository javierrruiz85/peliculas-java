package casa.mi.controller.backoffice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import casa.mi.modelo.conexion.ConnectionManager;

/*
 * @see Estoy trabajando con una tabla nueva dentro del proyecto, para no contaminar la tabla que uso normalmente con otros datos
 * la tabla en cuestion se llama usuarios_uf2177 dentro de la base de datos de peliculas
 * los campos de la tabla:
 * - id: int, PK, not null, autoincrement
 * - nombre: varchar, not null, unique key
 * - contrasenia: varchar, not null
 * - id_rol: int, not null
 */

/**
 * Servlet implementation class MigracionBackOfficeController
 */
@WebServlet("/vistas/backoffice/migracion")
public class MigracionBackOfficeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(MigracionBackOfficeController.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.trace("Inicio");
		
		// declaramos las variables que vamos a necesitar mas adelante, como la ruta del fichero, los contadores de lineas, etc
		String fichero = "/home/javaee/personas.txt";
		int totalLineas = 0;
		int totalInsertadas = 0;
		int totalErrores = 0;
		long tiempoInicio = System.currentTimeMillis();
		long tiempoFin = 0;
		String linea = "";
		
		// declaramos la sql que vamos a utilizar. Solo introducimos un dato (la ?), el resto esta hardcodeado, para cambiar eso basta con sustituir esos datos por ?
		final String SQL = " INSERT INTO usuarios_uf2177 (nombre, contrasenia, id_rol) VALUES ( ? ,'123456',1); ";

		try (
				Connection conexion = ConnectionManager.getConnection();
				PreparedStatement pst = conexion.prepareStatement(SQL);
		) {
			
			// hacemos que no se autocomite, ya lo haremos al final
			conexion.setAutoCommit(false);
			
			// hacemos un try para leer el fichero linea a linea
			try {
				FileReader fr = new FileReader(fichero);
				BufferedReader br = new BufferedReader(fr);
				
				// leemos la primera linea, que es la del encabezado, y no hacemos nada con ella, de esta manera la "lee" pero no la cuenta ni la ejecuta
				br.readLine();
				
				// creamos un bucle para que trate linea a linea
				while( (linea = br.readLine()) != null || !"".equals(linea) ) {
					//LOG.trace(linea);
					
					String[] campos = linea.split(";");  // creamos una array con los campos separados
					//LOG.trace(campos[0]);
					totalLineas++;
					
					// usamos un try-catch para controlar las lineas que no cumplen, por no tener 6 campos o por tener datos repetidos, asi cuenta correctamente los errores y continua con el codigo
					try {
						if (campos.length == 6) {
							pst.setString(1, campos[0]);
							LOG.debug(pst);
							pst.executeUpdate();
							totalInsertadas++;
							LOG.trace("Linea insertada");
						} else {
							totalErrores++;
						}
					} catch (Exception e) {
						// aqui controlamos las lineas repetidas
						LOG.error(e);
						e.printStackTrace();
						totalErrores++;
					} // try-catch

				} // while
				
				fr.close();
					
			} catch (Exception e) {
				
				LOG.error(e);
				e.printStackTrace();
				
			} // try-catch del insert
			
			// hacemos commit de todas las lineas a insertar
			conexion.commit();
			LOG.trace("Realizar commit al terminar para guardar en la BBDD");
			
		} catch (Exception e) {

			LOG.error(e);
			e.printStackTrace();

		} finally {
			
			// enviar los parametros
			request.setAttribute("fichero", fichero);
			request.setAttribute("total_lineas", totalLineas);
			request.setAttribute("total_insertadas", totalInsertadas);
			request.setAttribute("total_errores", totalErrores);
			
			// calcular los milisegundos y enviarlos
			tiempoFin = System.currentTimeMillis();
			request.setAttribute( "tiempo", (tiempoFin - tiempoInicio) );
			
			request.getRequestDispatcher("resumen-migracion.jsp").forward(request, response);
			
		} // try-catch-finally
		
		LOG.trace("Fin del proceso de migracion");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

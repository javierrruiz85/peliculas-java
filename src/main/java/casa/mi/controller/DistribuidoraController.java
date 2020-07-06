package casa.mi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.DistribuidoraDao;
import casa.mi.modelo.pojo.Distribuidora;

/**
 * Servlet implementation class DistribuidoraController
 */
@WebServlet("/distribuidora")
public class DistribuidoraController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DistribuidoraController.class);
	
	private static final DistribuidoraDao distribuidoraDao = DistribuidoraDao.getInstance();
	
	private static final String VER_TABLA = "vistas/distribuidoras/tabla-distribuidoras.jsp";
	private static final String VER_FORMULARIO = "vistas/distribuidoras/formulario-distribuidoras.jsp";
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * @param id id_distribuidora
	 * @param operacion para saber si queremos eliminar
	 * VER_FORMULARIO 		  ===> si se recibe el id de la distribuidora como parametro, va al formulario para añadirla
	 * VER_TABLA 			  ===> si id_distribuidora = null se muestran todas en la tabla
	 * ELIMINAR DISTRIBUIDORA ===> si recibe id_distribuidoras y operacion = 2 como parametros, esta se elimina y va a la tabla
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String vista = VER_TABLA;
		Alerta alerta = new Alerta();
		String paramId = request.getParameter("id");
		String operacionEliminar = request.getParameter("operacion");
		
		Distribuidora distribuidora = new Distribuidora();
		
		try {
			
			if ( paramId != null ) {
				
				int id = Integer.parseInt(paramId);
				
				if ( operacionEliminar != null ) {			// ELIMINA EL REGISTRO
					distribuidoraDao.delete(id);
					alerta = new Alerta("success", "Distribuidora eliminada con exito");
				} else {									// VA AL FORMULARIO
					if ( id > 0 ) {
						distribuidora = distribuidoraDao.getById(id);
					} // if-3
					vista = VER_FORMULARIO;
				} // if-else-2
				
			} // if-1
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("danger", "No se puede eliminar una distribuidora si esta tiene peliculas asociadas");
			
		} finally {
			
			request.setAttribute("alerta", alerta);
			request.setAttribute("distribuidora", distribuidora);
			request.setAttribute("distribuidoras", distribuidoraDao.getAll());
			request.getRequestDispatcher(vista).forward(request, response);
			
		} // try-catch-finally
		
	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Alerta alerta = new Alerta();
		
		String paramId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		
		Distribuidora distribuidora = new Distribuidora();
		
		try {
			
			int id = Integer.parseInt(paramId);
			distribuidora.setId(id);
			distribuidora.setNombre(nombre);
			
			if ( id > 0 ) {
				distribuidoraDao.update(distribuidora);
				alerta = new Alerta("success", "Distribuidora modificada con exito");
			} else {
				distribuidoraDao.insert(distribuidora);
				alerta = new Alerta("success", "Distribuidora creada con exito");
			} // if-else
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("warning", "La distribuidora <b>" + distribuidora.getNombre() + "<b> ya existe");
			
		} finally {
			
			request.setAttribute("distribuidora", distribuidora);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(VER_FORMULARIO).forward(request, response);
			
		} // try-catch-finally
		
	} // doPost

}

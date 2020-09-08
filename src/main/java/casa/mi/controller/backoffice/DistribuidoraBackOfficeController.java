package casa.mi.controller.backoffice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.DistribuidoraDao;
import casa.mi.modelo.pojo.Distribuidora;

/**
 * Servlet implementation class DistribuidoraBackOfficeController
 */
@WebServlet("/vistas/distribuidoras")
public class DistribuidoraBackOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(DistribuidoraBackOfficeController.class);
	private static final DistribuidoraDao dao = DistribuidoraDao.getInstance();
	private static final String VIEW_LISTA = "distribuidoras/index.jsp";
	private static final String VIEW_FORM = "distribuidoras/formulario-distribuidoras.jsp";
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
       
   
	/**
	 * Se encarga de listar, eliminar o mostrar una categoria en el fomrulario
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LOG.trace("Listado distribuidoras");
		String idParam = request.getParameter("id");
		String accionParam = request.getParameter("accion");
		
		String forward = VIEW_LISTA;
		
		try {
			
			// LISTAR
			if ( idParam == null ) {
				
				ArrayList<Distribuidora> listado = dao.getAll();
				request.setAttribute("distribuidoras", listado);
				
			// ELIMINAR
			} else if ( accionParam != null ) {
				
				int id = Integer.parseInt(idParam);
				
				try {
					dao.delete(id);
					request.setAttribute("alerta", new Alerta("success", "Distribuidora eliminada con exito"));
				} catch (Exception e) {
					request.setAttribute("alerta", new Alerta("warning", "Esta distribuidora tiene peliculas asociadas y no se puede eliminar"));
				} // try-catch block 2
				
				// despues de eliminar obtenemos todas las distribuidoras y vamos al listado
				request.setAttribute("categorias", dao.getAll());
				
			} else {
				
				Distribuidora distribuidora = new Distribuidora();
				int id = Integer.parseInt(idParam);
				
				if ( id > 0 ) {
					distribuidora = dao.getById(id);
				} // if 2
				
				request.setAttribute("distribuidora", distribuidora);
				forward = VIEW_FORM;
				
			} // if-elseif-else block 1
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			// hay que añadir la carpeta donde esta el index (u otro fichero) de las distribuidoras, ya que al hacer el forward pierde la ultima parte de la url
			request.getRequestDispatcher("forward").forward(request, response);
		} // try-catch-finnally block 1
		
	}

	/**
	 * recoge los datos del formulario y los guarda en la base datos.
	 * Nos sirve tanto para crear una distribuidora como para modificarla
	 * 
	 * 1) recoger parametro del formulario
	 * 2) validar que sean correctos
	 * 3) guardar en la bbdd
	 * 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1 todo bien
		// 2 los datos introducidos en el formulario no son correctos
		// 3 nombre distribuidora duplicado
		LOG.trace("Envio de datos desde un formulario");
		
		String idParam = request.getParameter("id");
		String nombreParam = request.getParameter("nombre");
		Alerta alerta = new Alerta();
		Distribuidora distribuidora = new Distribuidora();
		
		try {
			
			int id = Integer.parseInt(idParam);
			
			// Mapear datos del formulario al pojo
			distribuidora.setId(id);
			distribuidora.setNombre(nombreParam);
			
			// validar datos enviados antes de insertar
			Set<ConstraintViolation<Distribuidora>> violations = validator.validate(distribuidora);
			
			if ( !violations.isEmpty() ) {
				
				alerta = new Alerta("warning", "Los datos introducidos son incorrectos");
			
			// no hay errores de validacion, guardar en la bbdd	
			} else {
				
				try {
					
					if ( id > 0 ) {
						dao.update(distribuidora);
					} else {
						dao.insert(distribuidora);
					} // if-else 1
					
					alerta = new Alerta("success", "Distribuidora guardada con exito");
					
					// actualizar las distribuidoras del contexto
					ServletContext contextoAplicacion = request.getServletContext();
					contextoAplicacion.setAttribute("distribuidoras", dao.getAll());
					
				} catch (Exception e) {
					alerta = new Alerta("warning", "El nombre de la distribuidora ya exite, introduzca otro");
				} // try-catch block 2
				
			}
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("Danger", "Lo sentimos, ha ocurrido un fallo");
			
		} finally {
			
			request.setAttribute("distribuidora", distribuidora);
			request.setAttribute("alerta", alerta);
			request.getRequestDispatcher(VIEW_FORM).forward(request, response);
			
		} // try-catch-finally block 1
		
	}

}

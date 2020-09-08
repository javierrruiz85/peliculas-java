package casa.mi.controller.frontoffice;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.log4j.Logger;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.PeliculaDao;
import casa.mi.modelo.dao.SeguridadException;
import casa.mi.modelo.pojo.Distribuidora;
import casa.mi.modelo.pojo.Pelicula;
import casa.mi.modelo.pojo.Usuario;

/**
 * Servlet implementation class CrearPeliculaFrontOfficeController
 */
@WebServlet("/vistas/frontoffice/crear-pelicula")
public class CrearPeliculaFrontOfficeController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOG = Logger.getLogger(CrearPeliculaFrontOfficeController.class);
	private static final PeliculaDao daoPelicula = PeliculaDao.getInstance();
	
	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static Validator validator = factory.getValidator();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String parametroId = request.getParameter("id");
		Pelicula pelicula = new Pelicula();
		HttpSession session = request.getSession();
		Usuario usuario = new Usuario();
		String vista = "formulario.jsp";
		
		try {
			
			usuario = (Usuario)session.getAttribute("usuario_login");
			int idUsuario = usuario.getId();
			int idPelicula = Integer.parseInt(parametroId);
			
			// recuperar solo si es distinto a 0, si la id es 0 entonces es un nuevo producto
			if ( idPelicula != 0) {
				pelicula = daoPelicula.checkSeguridad(idPelicula, idUsuario);
			} // if
			
		} catch (SeguridadException e) {
			vista = "/vistas/frontoffice/inicio";
			LOG.error("Ha habido un intento de saltarse la seguridad del usuario " + usuario);
			
		} catch (Exception e) {
			LOG.error(e);
			
		} finally {
			request.setAttribute("pelicula", pelicula);
			request.getRequestDispatcher(vista).forward(request, response);
		} // try-catch-finally

	} // doGet

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Alerta alerta = new Alerta();
		Pelicula pelicula = new Pelicula();
		Usuario usuario = new Usuario();
		HttpSession session = request.getSession();
		String vista = "formulario.jsp";
		
		// recoger parametros del formulario
		String parametroId = request.getParameter("id");
		String nombre = request.getParameter("nombre");
		String parametroDuracion = request.getParameter("duracion");
		String parametroAnio = request.getParameter("anio");
		String caratula = request.getParameter("caratula");
		String parametroIdDistribuidora = request.getParameter("id_distribuidora");
		
		try {
			
			// parsear los parametros
			int peliculaId = Integer.parseInt(parametroId);
			int duracion = Integer.parseInt(parametroDuracion);
			int anio = Integer.parseInt(parametroAnio);
			int distribuidoraId = Integer.parseInt(parametroIdDistribuidora);
			
			// recoger el usuario de la sesion y obtener su id
			usuario = (Usuario)session.getAttribute("usuario_login");
			int usuarioId = usuario.getId();
			
			// comprobar la seguridad siempre que no sea una pelicula nueva
			if ( peliculaId != 0 ) {
				pelicula = daoPelicula.checkSeguridad(peliculaId, usuarioId); // si el usuario intenta manipular una pelicula que no le pertenece, entoces le salta SeguridadException
			} // if
			
			// crear el objeto con los parametros recogidos anteriormente
			pelicula.setId(peliculaId);
			pelicula.setNombre(nombre);
			pelicula.setDuracion(duracion);
			pelicula.setAnio(anio);
			pelicula.setCaratula(caratula);
			
			Distribuidora distribuidora = new Distribuidora();
			distribuidora.setId(distribuidoraId);
			pelicula.setDistribuidora(distribuidora);
			
			// recuperar el usuario de la sesion y setearlo a la pelicula
			pelicula.setUsuario(usuario);
			
			// validar pojo
			Set<ConstraintViolation<Pelicula>> violations = validator.validate(pelicula);
			
			if ( violations.isEmpty() ) {
				
				// guardar la pelicula en la bbdd
				if (peliculaId == 0) {
					daoPelicula.insert(pelicula);
				} else {
					daoPelicula.updateByUsuario(pelicula);
				} // if-else-2
				
				alerta = new Alerta("success", "Pelicula creada con exito. Debes esperar unas horas hasta que sea validada y publicada.");
				
			} else {
				
				String errores = "";
				
				for (ConstraintViolation<Pelicula> v : violations) {
					errores += "<p><b>" + v.getPropertyPath() + "</b>: " + v.getMessage() + "</p>";
				} // for
				
				alerta = new Alerta("warning", errores);
				
			} // if-else-1
			
		} catch (SeguridadException e) {
			
			vista = "/vistas/frontoffice/inicio";
			LOG.error("Ha habido un intento de saltarse la seguridad del usuario " + usuario);
			
		} catch (Exception e) {
			
			LOG.error(e);
			alerta = new Alerta("warning", "Ese nombre ya existe");
			
		} finally {
			
			request.setAttribute("alerta", alerta);
			request.setAttribute("pelicula", pelicula);
			request.getRequestDispatcher(vista).forward(request, response);
			
		} // try-catch-finally
		
	} // doPost

}

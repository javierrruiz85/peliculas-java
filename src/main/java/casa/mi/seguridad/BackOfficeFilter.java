package casa.mi.seguridad;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import casa.mi.modelo.pojo.Rol;
import casa.mi.modelo.pojo.Usuario;

/**
 * Servlet Filter implementation class BackOfficeFilter
 */
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD, 
				DispatcherType.INCLUDE, 
				DispatcherType.ERROR
		}
					, urlPatterns = { "/views/backoffice/*" })
public class BackOfficeFilter implements Filter {

    private final static Logger LOG = Logger.getLogger(BackOfficeFilter.class);

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() { 	// se ejecuta cuando se para el servidor del tomcat
		LOG.trace("se destruye el filtro");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { 	// se ejecuta cada vez que coincide el patron 
		
		// Hay que tener cuidado de castear de ServletRequest a HttpServletRequest, y lo mismo con los response
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		// es necesaria la url de inicio para construir una ruta absoluta, de lo contrario seria relativa y fallaria 
		String urlInicio = req.getContextPath();
		
		LOG.trace( "filtrando URI" + req.getRequestURI() );
		
		// recuperamos el usuario que tenemos guardado en session
		Usuario usuarioLogin = (Usuario) req.getSession().getAttribute("usuario_login");
		
		if (usuarioLogin == null) {
			LOG.warn("Usuario sin autentificar");
			res.sendRedirect(urlInicio + "/views/login.jsp");
		} else if (usuarioLogin.getRol().getId() != Rol.ADMIN) {
			LOG.warn("Usuario sin privilegios de Administrador, sin autorizacion");
			res.sendRedirect(urlInicio + "/views/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
		
		//chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException { 	// se ejecuta SOLO la primera vez que se llama al filtro
		LOG.trace("se inicializa el filtro");
	}

}

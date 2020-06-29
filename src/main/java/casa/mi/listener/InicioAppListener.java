package casa.mi.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import casa.mi.controller.pojo.Alerta;
import casa.mi.modelo.dao.DistribuidoraDao;

/**
 * Application Lifecycle Listener implementation class InicioAppListener
 *
 */
@WebListener
public class InicioAppListener implements ServletContextListener {

	static private final DistribuidoraDao distribuidoraDao = DistribuidoraDao.getInstance();
	
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // cuando se para la app
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // cuando se ejecuta la app en el servidor, al arrancar la primera vez
    	System.out.println("La App esta arrancando. Esto es un evento.");
    	
    	
    	// este contexto es para toda la app y es accesible desde cualquier jsp o servlet
    	ServletContext contextoAplicacion = sce.getServletContext();
    	
    	try {
    		contextoAplicacion.setAttribute( "distribuidoras", distribuidoraDao.getAll() );
    	} catch (Exception e) {
			contextoAplicacion.setAttribute( "alerta", new Alerta("danger", "Tenemos un problema sin determinar") );
		} // try-catch
    	
    }
	
}

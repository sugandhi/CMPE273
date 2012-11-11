package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class StartupListener
 *
 * @author Team 7
 */
@WebListener
public class StartupListener implements ServletContextListener {

    /** The logger. */
    static Logger logger = null;

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        // load log4J properties file
        String log4j = context.getRealPath("/WEB-INF/log4j.properties");
        if (log4j != null) {
            PropertyConfigurator.configureAndWatch(log4j);
        } else {
            BasicConfigurator.configure();
        }
        logger = Logger.getLogger(StartupListener.class);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
        logger.info("Context destroyed");
        // DO ANY OTHER CLEANUP HERE
    }
}

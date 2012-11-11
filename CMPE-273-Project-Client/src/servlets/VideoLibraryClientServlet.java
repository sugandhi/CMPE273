package servlets;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import common.ServletUtils;

import actions.BaseAction;

/**
 * Servlet implementation class SignInServlet
 * 
 * @author Team 7
 */
@WebServlet("/VideoLibraryClientServlet")
public class VideoLibraryClientServlet extends HttpServlet {

    private static final long serialVersionUID = 6994434815265139713L;

    /** The logger. */
    private static Logger logger = Logger.getLogger(VideoLibraryClientServlet.class);

    /** The action objects. */
    private Map<String, BaseAction> actionObjects = new HashMap<String, BaseAction>();       
    
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        String fileName = config.getServletContext().getRealPath(config.getInitParameter("mapping-file"));
        File file = new File(fileName);
        if (file.exists()) {
            Properties properties = new Properties();
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(file));
                properties.load(is);
            } catch (IOException ioe) {
                logger.fatal("Could not load actions mapping file " + fileName, ioe);
                throw new Error(ioe);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        logger.warn("Failed to close input stream.", e);
                    }
                }
            }
           
            Enumeration<Object> keys = properties.keys();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                String actionClassName = properties.getProperty(key);
                try {
                    BaseAction action = BaseAction.class.cast(Class.forName(actionClassName).newInstance());
                    if (action != null) {
                        this.actionObjects.put(key, action);
                        if(logger.isInfoEnabled()) 
                            logger.info("Action object created for action " + key + ", action class name: " + properties.getProperty(key));
                    } else {
                        logger.error("Could not create action for " + key + ", action class name: " + properties.getProperty(key));
                    }
                } catch (InstantiationException e) {
                    logger.error("Action class object could not be created: " + actionClassName, e);
                } catch (IllegalAccessException e) {
                    logger.error("Action class object could not be created: " + actionClassName, e);
                } catch (ClassNotFoundException e) {
                    logger.error("Action class not defined: " + actionClassName, e);
                } catch (ClassCastException e) {
                    logger.error("Action class not defined for operation: " + actionClassName, e);
                }
            }
        }
    }
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    processRequest(request, response);
	}
	
    /**
     * Process request.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String [] array = pathInfo.split("/");
        final String operation = (array != null && array.length > 0) ? array[1] : null;
        try {
           BaseAction action = operation != null ? actionObjects.get(operation) : null;
            if (action != null) {
                if (logger.isDebugEnabled()) 
                        logger.debug("request operation " + operation + " will be handled by " + action.getClass().getSimpleName() + " class");
                try {
                    action.execute(request, response);
                    ServletUtils.ok(response);
                    response.flushBuffer();
                } catch (ServletException e) {
                    logger.error("error executing request", e);
                    ServletUtils.servererror(response);
                } catch (IOException e) {
                    logger.error("error executing request", e);
                    ServletUtils.sendConnectionClose(response);
                    ServletUtils.servererror(response);
                }
            } else {
                logger.error("Unsupported operation: " + operation);
                ServletUtils.badRequest(response);
            }
        } catch(Exception e) {
            logger.error("error executing request", e);
            ServletUtils.servererror(response);
        } catch(Throwable e) {
            // we must re-throw ThreadDeath
            if (e instanceof ThreadDeath) {
                throw (ThreadDeath)e;
            }
            logger.error("error executing request", e);
            ServletUtils.servererror(response);
        } 
    }	
}

package actions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * The Class BaseAction.
 * It forms the structure for all action class.
 * 
 * @author Team 7
 */
public abstract class BaseAction {
    /** The class must not have ANY CLASS LEVEL variables ever. */
    protected final Logger logger = Logger.getLogger(getClass());
    
    public BaseAction() {
        // DO NOTHING 
    }
    
    /**
     * An abstract method which is inherited by all the classes extending the BaseAction class.
     * Process the request for the specified operation.
     *  
     * @param request The request object of the servlet.
     * @param response The response object of the servlet. 
     * @throws ServletException Signals that a ServletException has occurred.
     * @throws IOException Signals that an IOException has occurred.
     */
    public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
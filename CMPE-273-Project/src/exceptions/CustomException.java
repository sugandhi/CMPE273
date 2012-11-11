package exceptions;

import javax.xml.ws.WebFault;

/**
 * Custom exception to be thrown to client
 * 
 * @author Team 7
 */

@WebFault(name = "CustomException", targetNamespace = "")
public class CustomException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -252957439027923723L;

    /**
     * Instantiates a new data access exception.
     *
     * @param arg0 the arg0
     */
    public CustomException(String arg0) {
        super(arg0);
    }

    /**
     * Instantiates a new data access exception.
     *
     * @param arg0 the arg0
     */
    public CustomException(Throwable arg0) {
        super(arg0);
    }
    /**
     * Instantiates a new exception.
     * 
     * @param msg
     * @param arg0
     */
    public CustomException(String msg, Throwable arg0) {
        super(msg, arg0);
    }     
}

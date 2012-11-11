package exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "InvalidArgumentException", targetNamespace = "")
public class InvalidArgumentException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -1835314730196515741L;

    /**
     * Instantiates a new exception.
     *
     * @param arg0 the arg0
     */
    public InvalidArgumentException(String arg0) {
        super(arg0);
    }

    /**
     * Instantiates a new exception.
     *
     * @param arg0 the arg0
     */
    public InvalidArgumentException(Throwable arg0) {
        super(arg0);
    }
    /**
     * Instantiates a new exception.
     * 
     * @param msg
     * @param arg0
     */
    public InvalidArgumentException(String msg, Throwable arg0) {
        super(msg, arg0);
    }     
}

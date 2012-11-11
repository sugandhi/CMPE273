package exceptions;

import javax.xml.ws.WebFault;

@WebFault(name = "DataSourceException", targetNamespace = "")
public class DataSourceException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = -8833231333986662215L;

    /**
     * Instantiates a new exception.
     *
     * @param arg0 the arg0
     */
    public DataSourceException(String arg0) {
        super(arg0);
    }
    /**
     * Instantiates a new exception.
     *
     * @param arg0 the arg0
     */
    public DataSourceException(Throwable arg0) {
        super(arg0);
    }
    /**
     * Instantiates a new exception.
     * 
     * @param msg
     * @param arg0
     */
    public DataSourceException(String msg, Throwable arg0) {
        super(msg, arg0);
    }    
}

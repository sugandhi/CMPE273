package exceptions;
/**
 * Data access exception
 * 
 * @author Team 7
 */
public class DataAccessException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4631071437181345451L;

    /**
     * Instantiates a new data access exception.
     *
     * @param arg0 the arg0
     */
    public DataAccessException(String arg0) {
        super(arg0);
    }

    /**
     * Instantiates a new data access exception.
     *
     * @param arg0 the arg0
     */
    public DataAccessException(Throwable arg0) {
        super(arg0);
    }

}
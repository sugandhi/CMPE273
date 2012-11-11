package common;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Utils class
 * 
 * @author Team 7
 */
public class ServletUtils {
    private static Logger logger = Logger.getLogger(ServletUtils.class);
    
    private ServletUtils() {
        // do nothing
    }

    public static int ok(HttpServletResponse response) {
        return HttpServletResponse.SC_OK;
    }

    public static void sendConnectionClose(HttpServletResponse response) {
        response.setHeader("Connection", "close");
    }
    
    public static int badRequest(HttpServletResponse response) {
        handleErrorStatus(response, HttpServletResponse.SC_BAD_REQUEST);
        return HttpServletResponse.SC_BAD_REQUEST;
    }
    
    public static int servererror(HttpServletResponse response) {
        handleErrorStatus(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
    }
    
    public static void handleErrorStatus(HttpServletResponse response, int status) {
        if (!response.isCommitted()) {
            switch (status) {
            case 400:
            case 401:
            case 405:
            case 408:
            case 409:
            case 411:
            case 413:
            case 500:
            case 501:
            case 502:
            case 503:
            case 505:
            case 410:
                try {
                    response.setStatus(status);
                    response.flushBuffer();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
                break;
            }
        }
    }
}

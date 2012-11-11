package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
/**
 * Settings class
 * 
 * @author Team 7
 */
public final class Settings {

    private final static Logger logger = Logger.getLogger(Settings.class);

    private final static Properties properties = new Properties();
    
    private static ScheduledThreadPoolExecutor stpe = null;
    private static File propFile;
    private static long lastModified;
    
    private static boolean bUsePreparedStatement;
    private static boolean bUseConnectionPool;
    private static boolean bUseObjectCache;
    private static int fileWatchInterval;
    
    static {
        //This is a fixed pool executor, started with 5 core threads
        stpe = new ScheduledThreadPoolExecutor(5);        
    }
    
    private Settings() {
        // This class has only static methods
    }
    /**
     * Load library properties file
     * 
     * @param fileName
     */
    public static void loadProperties(String fileName) {
        propFile = new File(fileName);
        loadProperties(propFile);
        lastModified = propFile.lastModified();
    }
    /**
     * Private method to load properties file. Kept this method separate so that we can invoke from refresh thread when required.
     * 
     * @param file
     */
    private static void loadProperties(File file)
    {
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                properties.load(fis); 
                if(logger.isInfoEnabled()) 
                    logger.info("Initializing properties: " + System.currentTimeMillis());
                
                try {
                    bUsePreparedStatement = Boolean.parseBoolean(properties.getProperty(ServiceConstants.USE_PREPARED_STMT, String.valueOf(ServiceConstants.USE_PREPARED_STMT_DEFAULT)));
                }
                catch(Exception ex) {
                    logger.error("Error while reading use stored procedure property " + ex.getLocalizedMessage(), ex);
                    bUsePreparedStatement = ServiceConstants.USE_PREPARED_STMT_DEFAULT;
                }
                try {
                    bUseConnectionPool = Boolean.parseBoolean(properties.getProperty(ServiceConstants.USE_CONNECTION_POOL, String.valueOf(ServiceConstants.USE_CONNECTION_POOL_DEFAULT)));
                }
                catch(Exception ex) {
                    logger.error("Error while reading use connection pool property " + ex.getLocalizedMessage(), ex);
                    bUseConnectionPool = ServiceConstants.USE_CONNECTION_POOL_DEFAULT;
                }
                try {
                    bUseObjectCache = Boolean.parseBoolean(properties.getProperty(ServiceConstants.USE_OBJECT_CACHE, String.valueOf(ServiceConstants.USE_OBJECT_CACHE_DEFAULT)));
                }
                catch(Exception ex) {
                    logger.error("Error while reading use object cache property " + ex.getLocalizedMessage(), ex);
                    bUseObjectCache = ServiceConstants.USE_OBJECT_CACHE_DEFAULT;
                }
                try {
                    fileWatchInterval = Integer.parseInt(properties.getProperty(ServiceConstants.WATCH_REFRESH_INTERVAL, String.valueOf(ServiceConstants.USE_OBJECT_CACHE_DEFAULT)));
                }
                catch(Exception ex) {
                    logger.error("Error while reading use object cache property " + ex.getLocalizedMessage(), ex);
                    fileWatchInterval = ServiceConstants.WATCH_REFRESH_INTERVAL_DEFAULT;
                }                
            }
            catch (IOException ioe) {
                logger.error("Error loading properties" + ioe.getMessage(), ioe);
            }
            finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        logger.error("Ignoring exception in closing properties file.");
                    }
                }
            }
        }
    }
    
    /**
     * 
     */
    public static void watchPropertiesFile()
    {
        if(logger.isInfoEnabled()) 
            logger.info("Watching " + propFile.getName() + " every " + fileWatchInterval + " seconds.");
        Runnable command = new Runnable() {
            @Override
            public void run() {
                if (propFile != null && propFile.lastModified() > lastModified) {
                    loadProperties(propFile);
                    lastModified = propFile.lastModified();
                    if(logger.isInfoEnabled()) 
                        logger.info("Loading modified properties file " + propFile.getAbsolutePath() + " last modified at " + lastModified);
                }
            }
        };
        stpe.scheduleWithFixedDelay(command, fileWatchInterval, fileWatchInterval, TimeUnit.SECONDS);
    }
    
    /**
     * @return the bUseStoredProcedure
     */
    public static final boolean isUsePreparedStatement() {
        return bUsePreparedStatement;
    }

    /**
     * @return the bUseConnectionPool
     */
    public static final boolean isUseConnectionPool() {
        return bUseConnectionPool;
    }

    /**
     * @return the bUseObjectCache
     */
    public static final boolean isUseObjectCache() {
        return bUseObjectCache;
    }

    /**
     * @return the fileWatchInterval
     */
    public static final int getFileWatchInterval() {
        return fileWatchInterval;
    }    
}

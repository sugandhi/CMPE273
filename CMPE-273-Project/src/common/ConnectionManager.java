/**
 * 
 */
package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import exceptions.DataSourceException;

/**
 * Connection Manager class
 * 
 * @author Team 7
 */
public class ConnectionManager {

    private static Logger logger = Logger.getLogger(ConnectionManager.class);
    
    private static ConnectionManager instance;
    private DataSource dataSource;
    
    private static String driverClass;
    private static String connectionURL;
    private static String dbUsername;
    private static String dbPassword;
    
    private static int maxActiveConnections;
    private static int maxdleConnections;
    private static int connectionTimeout;
    private static boolean validateConnection;
    private static String validateQuery;

    /**
     * Private Constructor
     */
    private ConnectionManager(String datasourceFile) throws Exception {
        final Properties properties = new Properties(); 
        final File file = new File(datasourceFile);
        if (logger.isDebugEnabled()) logger.debug("loading database properties from: " + datasourceFile + " exists: " + file.exists());
        if (file.exists()) {
            InputStream is = null;
            try {
                is = new FileInputStream(file);
                properties.load(is);
            } catch (IOException ioe) {
                logger.fatal("Unable to load datasources properties file", ioe);
            }
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Database properties: " + properties);
        }
        configureDataSource(properties);
    }
    
    public static synchronized ConnectionManager initialize(String dataSourceFile) throws Exception {
        if (instance != null)
            throw new IllegalStateException("Connection manager is already initialized.");
        return instance = new ConnectionManager(dataSourceFile);
    }

    private void configureDataSource(Properties properties) {
        if(logger.isInfoEnabled()) 
            logger.info("Initializing database properties: " + System.currentTimeMillis());
        
        try {
            driverClass = properties.getProperty(ServiceConstants.DRIVER_CLASSNAME, ServiceConstants.DRIVER_CLASSNAME_DEFAULT);
        }
        catch(Exception ex) {
            logger.error("Error while reading driver class property " + ex.getLocalizedMessage(), ex);
            driverClass = ServiceConstants.DRIVER_CLASSNAME_DEFAULT;
        }
        try {
            connectionURL = properties.getProperty(ServiceConstants.CONNECTION_URL, ServiceConstants.CONNECTION_URL_DEFAULT);
        }
        catch(Exception ex) {
            logger.error("Error while reading connection URL property " + ex.getLocalizedMessage(), ex);
            connectionURL = ServiceConstants.CONNECTION_URL_DEFAULT;
        }
        try {
            dbUsername = properties.getProperty(ServiceConstants.DB_USERNAME, ServiceConstants.DB_USERNAME_DEFAULT);
        }
        catch(Exception ex) {
            logger.error("Error while reading database username property " + ex.getLocalizedMessage(), ex);
            dbUsername = ServiceConstants.DB_USERNAME_DEFAULT;
        }
        try {
            dbPassword = properties.getProperty(ServiceConstants.DB_PASSWORD, ServiceConstants.DB_PASSWORD_DEFAULT);
        }
        catch(Exception ex) {
            logger.error("Error while reading database password property " + ex.getLocalizedMessage(), ex);
            dbPassword = ServiceConstants.DB_PASSWORD_DEFAULT;
        }
        try {
            maxActiveConnections = Integer.parseInt(properties.getProperty(ServiceConstants.MAX_ACTIVE_CONNECTION, String.valueOf(ServiceConstants.MAX_ACTIVE_CONNECTION_DEFAULT)));
        }
        catch(Exception ex) {
            logger.error("Error while reading max active connection property " + ex.getLocalizedMessage(), ex);
            maxActiveConnections = ServiceConstants.MAX_ACTIVE_CONNECTION_DEFAULT;
        } 
        try {
            maxdleConnections = Integer.parseInt(properties.getProperty(ServiceConstants.MAX_IDLE_CONNECTION, String.valueOf(ServiceConstants.MAX_IDLE_CONNECTION_DEFAULT)));
        }
        catch(Exception ex) {
            logger.error("Error while reading max idle connection property " + ex.getLocalizedMessage(), ex);
            maxdleConnections = ServiceConstants.MAX_IDLE_CONNECTION_DEFAULT;
        }        
        try {
            connectionTimeout = Integer.parseInt(properties.getProperty(ServiceConstants.CONNECTION_TIMEOUT, String.valueOf(ServiceConstants.CONNECTION_TIMEOUT_DEFAULT)));
        }
        catch(Exception ex) {
            logger.error("Error while reading connection timeout property " + ex.getLocalizedMessage(), ex);
            connectionTimeout = ServiceConstants.CONNECTION_TIMEOUT_DEFAULT;
        }        
        try {
            validateConnection = Boolean.parseBoolean(properties.getProperty(ServiceConstants.VALIDATE_CONNECTION, String.valueOf(ServiceConstants.VALIDATE_CONNECTION_DEFAULT)));
        }
        catch(Exception ex) {
            logger.error("Error while reading validate connection property " + ex.getLocalizedMessage(), ex);
            validateConnection = ServiceConstants.VALIDATE_CONNECTION_DEFAULT;
        }            
        try {
            validateQuery = properties.getProperty(ServiceConstants.VALIDATE_QUERY, ServiceConstants.VALIDATE_QUERY_DEFAULT);
        }
        catch(Exception ex) {
            logger.error("Error while reading validate query property " + ex.getLocalizedMessage(), ex);
            validateQuery = ServiceConstants.VALIDATE_QUERY_DEFAULT;
        }
        
        try {
            BasicDataSource ds = new BasicDataSource();
            ds.setDriverClassName(driverClass);
            ds.setUrl(connectionURL);
            ds.setUsername(dbUsername);
            ds.setPassword(dbPassword);
            
            ds.setTestOnBorrow(validateConnection);
            ds.setValidationQuery(validateQuery);
            ds.setMaxActive(maxActiveConnections);
            ds.setMaxIdle(maxdleConnections);
            ds.setLoginTimeout(connectionTimeout);
            dataSource = ds;   
        } catch (SQLException sqe) {
            logger.error("Could not create DataSource " + sqe.getLocalizedMessage(), sqe);
        } catch (Exception ex) {
            logger.error("Could not create DataSource " + ex.getLocalizedMessage(), ex);
        }        
    }

    /**
     * Close all DB Connections
     * @throws SQLException
     */
    public void shutdownDataSource() throws SQLException {
        BasicDataSource bds = (BasicDataSource) dataSource;
        bds.close();
    }
    /**
     * Get DB Connection
     * @return Connection object
     * @throws SQLException
     */
    public synchronized Connection getConnection() throws Exception {
        Connection con = null;
        if(Settings.isUseConnectionPool()) {
            con =  dataSource.getConnection();
        }
        else {
            Class.forName(driverClass).newInstance();
            con = DriverManager.getConnection(connectionURL, dbUsername, dbPassword);
        }
        con.setAutoCommit(false);
        return con;
    }
    
    /*******************************helper methods*******************************/ 
    /* close resultset */
    public static void closeResultSet(ResultSet rs) throws DataSourceException {
        try {
            if(rs != null) {
                rs.close();
            }
        }
        catch(SQLException ex) {
           logger.error("Failed to close resultset: " + ex.getLocalizedMessage(), ex);
           throw new DataSourceException(ex);
        }
    }     
    /* close statement */
    public static void closeStatement(Statement stmt) throws DataSourceException {
        try {
            if(stmt != null) {
                stmt.close();
            }
        }
        catch(SQLException ex) {
           logger.error("Failed to close statement: " + ex.getLocalizedMessage(), ex);
           throw new DataSourceException(ex);
        }
    }    
    /* close Connection - Returns to Pool */
    public static void closeConnection(Connection con) throws DataSourceException {
        try {
            if(con != null) {
                con.close();
            }
        }
        catch(SQLException ex) {
            logger.error("Failed to close connection: " + ex.getLocalizedMessage(), ex);
            throw new DataSourceException(ex);
        }
    }
    /* rollback connection */
    public static void rollbackConnection(Connection con) throws DataSourceException {
        try {
            if(con != null) {
                con.rollback();
            }
        }
        catch(SQLException ex) {
            logger.error("Failed to rollback transaction: " + ex.getLocalizedMessage(), ex);
            throw new DataSourceException(ex);            
        }
    }    
    /* commit connection */
    public static void commitConnection(Connection con) throws DataSourceException {
        try {
            if(con != null) {
                con.commit();
            }
        }
        catch(SQLException ex) {
            logger.error("Failed to commit transaction: " + ex.getLocalizedMessage(), ex);
            throw new DataSourceException(ex);            
        }
    }     
}
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.jws.WebService;
import javax.xml.ws.WebServiceException;

import org.apache.axis.MessageContext;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import beans.InvoiceInfo;
import beans.MemberInfo;
import beans.MovieInfo;

import common.ConnectionManager;
import common.ServiceCache;
import common.ServiceConstants;
import common.Settings;

import exceptions.CustomException;
import exceptions.DataSourceException;
import exceptions.InvalidArgumentException;

/**
 * Video Library Web Service
 * 
 * @author Team 7
 */
@WebService
public class VideoLibrarySystem {
    private static Logger logger;
    private static ConnectionManager connMgr;
    static {
        // DO NOT CHANGE ORDER OF EVENTS HERE. WE NEED TO LOAD LOG4J PROPERTIES FIRST BEFORE USING LOGGER IN ANY CLASS.
        MessageContext context = MessageContext.getCurrentContext();
        String configPath = (String) context.getProperty(org.apache.axis.Constants.MC_CONFIGPATH);
        
        // configure log4J properties file
        String log4jFile = configPath + ServiceConstants.SEPARATOR_CHAR + ServiceConstants.LOG4J_PROPERTIES;
        PropertyConfigurator.configureAndWatch(log4jFile);        
        logger = Logger.getLogger(VideoLibrarySystem.class);

        // load properties file 
        String propertiesFile = configPath + ServiceConstants.SEPARATOR_CHAR + ServiceConstants.LIBRARY_PROPERTIES;
        Settings.loadProperties(propertiesFile);
        Settings.watchPropertiesFile();
        
        // load database connection properties and initialize in Connection Manager
        String dataSourceFile = configPath + ServiceConstants.SEPARATOR_CHAR + ServiceConstants.DATASOURCE_PROPERTIES;
        try {
            connMgr = ConnectionManager.initialize(dataSourceFile);
        }
        catch(Exception ex) {
            logger.error("Failed to initialize datasource " + ex.getLocalizedMessage(), ex);
            connMgr = null;
        }
        
        // initialize Cache
        if(connMgr != null) {
            try {
                ServiceCache.initialize(connMgr);
            }
            catch(Exception ex) {
                logger.error("Failed to initialize service cache " + ex.getLocalizedMessage(), ex);
                connMgr = null;
            }
        }
    }

    private MemberInfo getMemberInfo(String email) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }
        MemberInfo memberInfo = null;
        if(Settings.isUseObjectCache()) {
            memberInfo = ServiceCache.getInstance().getMemberInfo(email);
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            try {
                // get connection
                con = connMgr.getConnection();
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MEMBERS_SELECT);
                    ((PreparedStatement) selectStmt).setString(1, email);
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = ServiceConstants.SELECT_STR + "*" + ServiceConstants.FROM_STR + ServiceConstants.MEMBERS_TABLE +  ServiceConstants.WHERE_STR + 
                        ServiceConstants.MEMBERS_EMAIL + ServiceConstants.EQUAL_STR + ServiceConstants.QUOTES_STR + email + ServiceConstants.QUOTES_STR;
                    rs = selectStmt.executeQuery(query);
                }
                if(rs != null && rs.next()) {
                    memberInfo = new MemberInfo();
                    memberInfo.setMemberId(rs.getInt(ServiceConstants.MEMBERS_MEMBER_ID));
                    memberInfo.setFirstName(rs.getString(ServiceConstants.MEMBERS_FIRST_NAME));
                    memberInfo.setLastName(rs.getString(ServiceConstants.MEMBERS_LAST_NAME));
                    memberInfo.setStreet1(rs.getString(ServiceConstants.MEMBERS_STREET_1));
                    memberInfo.setStreet2(rs.getString(ServiceConstants.MEMBERS_STREET_2));
                    memberInfo.setCity(rs.getString(ServiceConstants.MEMBERS_CITY));
                    memberInfo.setState(rs.getString(ServiceConstants.MEMBERS_STATE));
                    memberInfo.setZipcode(rs.getString(ServiceConstants.MEMBERS_ZIPCODE));
                    memberInfo.setEmail(rs.getString(ServiceConstants.MEMBERS_EMAIL));
                    memberInfo.setPassword(rs.getString(ServiceConstants.MEMBERS_PASSWORD));
                    memberInfo.setPhoneNo(rs.getString(ServiceConstants.MEMBERS_PHONE_NO));
                    memberInfo.setMembership(rs.getString(ServiceConstants.MEMBERS_MEMBERSHIP_NO));
                    memberInfo.setMemberType(rs.getInt(ServiceConstants.MEMBERS_MEMBER_TYPE));
                    memberInfo.setStatus(rs.getInt(ServiceConstants.MEMBERS_STATUS));
                    memberInfo.setBalance(rs.getFloat(ServiceConstants.MEMBERS_BALANCE));
                    memberInfo.setLoginTime(rs.getLong(ServiceConstants.MEMBERS_LOGIN_TIME));
                }
                else {
                    throw new CustomException("Username/Email does not exist, please try with different email address.");
                }
                rs.close();
                rs = null;
            }
            catch(Exception ex) {
                memberInfo = null;
                String msg = "Failed to find MemberInfo for " + email + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);            
            }
            finally {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }
        }
        return memberInfo;
    }
    
    private boolean validUser(String email, boolean admin) {
        //TODO - Validate if its valid user and if admin flag is true then its an admin user
        return true;
    }
    
    private MovieInfo[] getRentedMovies(int memberId) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(memberId <= 0) {
            throw new InvalidArgumentException("Invalid input argument 'memberId' : " + memberId);
        }
        MovieInfo[] movies = null;
        MemberInfo memberInfo = null;
        if(Settings.isUseObjectCache()) {
            memberInfo = ServiceCache.getInstance().getMemberInfo(memberId);
            movies = memberInfo.getRentedMovies();
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            try {
                con = connMgr.getConnection();
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.RENTED_MOVIES_SELECT_BY_MEMBER);
                    ((PreparedStatement) selectStmt).setInt(1, memberId);
                    ((PreparedStatement) selectStmt).setInt(2, ServiceConstants.RENTED_MOVIE_STATUS_RENTED);
                    
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get Rented Movies for memberId:" + memberId + " == " + ServiceConstants.RENTED_MOVIES_SELECT_BY_MEMBER);                    
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                } 
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT RentedMovies.RentedMovieId, Movies.MovieId, Movies.MovieName, Movies.MovieBanner, Movies.ReleaseDate, Movies.RentAmount, " +
                            "Movies.Category, Movies.AvailableCopies, Movies.MovieSummary, RentedMovies.RentedDate FROM VideoLibrary.Movies, VideoLibrary.RentedMovies WHERE " +
                            "RentedMovies.MovieId=Movies.MovieId AND RentedMovies.MemberId=" + memberId + " AND RentedMovies.RentStatus=" + ServiceConstants.RENTED_MOVIE_STATUS_RENTED;
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get Rented Movies for memberId:" + memberId + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                
                if(rs != null) {
                    ArrayList<MovieInfo> alList = new ArrayList<MovieInfo>();
                    while(rs.next()) {
                        MovieInfo movie = new MovieInfo();
                        movie.setRentedMovieId(rs.getInt(ServiceConstants.RENTED_MOVIES_RENTED_MOVIE_ID));
                        movie.setMovieId(rs.getInt(ServiceConstants.MOVIES_MOVIE_ID));
                        movie.setMovieName(rs.getString(ServiceConstants.MOVIES_MOVIE_NAME));
                        movie.setMovieBanner(rs.getString(ServiceConstants.MOVIES_MOVIE_BANNER));
                        movie.setReleaseDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.MOVIES_RELEASE_DT)));
                        movie.setRentAmount(rs.getFloat(ServiceConstants.MOVIES_RENT_AMOUNT));
                        movie.setCategory(rs.getString(ServiceConstants.MOVIES_CATEGORY));
                        movie.setAvailableCopies(rs.getInt(ServiceConstants.MOVIES_AVAILABLE_COPIES));
                        movie.setRentedDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.RENTED_MOVIES_RENTED_DT)));
                        movie.setMovieSummary(rs.getString(ServiceConstants.MOVIES_MOVIE_SUMMARY));

                        alList.add(movie);
                    }
                    if(alList.size() > 0)
                        movies = (MovieInfo[]) alList.toArray(new MovieInfo[alList.size()]);
                }
                rs.close();
                rs = null;
            }
            catch(Exception ex) {
                movies = null;
                String msg = "Failed to find rented movies for 'memberId' : " + memberId + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);            
            }
            finally {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }        
        }
        return movies;
    }
    
    private MemberInfo[] getRentedMmembers(int movieId) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(movieId <= 0) {
            throw new InvalidArgumentException("Invalid input argument 'movieId' : " + movieId);
        }
        MemberInfo[] members = null;
        MovieInfo movieInfo = null;
        if(Settings.isUseObjectCache()) {
            movieInfo = ServiceCache.getInstance().getMovieInfo(movieId);
            members = movieInfo.getRentedMembers();
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            try {
                con = connMgr.getConnection();
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.RENTED_MOVIES_SELECT_BY_MOVIE);
                    ((PreparedStatement) selectStmt).setInt(1, movieId);
                    ((PreparedStatement) selectStmt).setInt(2, ServiceConstants.RENTED_MOVIE_STATUS_RENTED);
                    
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get Rented Members for movieId:" + movieId + " == " + ServiceConstants.RENTED_MOVIES_SELECT_BY_MEMBER);                    
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                } 
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT RentedMovies.RentedMovieId, RentedMovies.RentedDate, Members.MemberId, Members.FirstName, Members.LastName, Members.Street1, " +
                            "Members.Street2, Members.City, Members.State, Members.ZipCode, Members.Email, Members.Password, Members.MemberType, Members.PhoneNo, Members.MembershipNo " +
                            "Members.Balance, Members.createTime FROM VideoLibrary.RentedMovies, VideoLibrary.Members WHERE RentedMovies.MemberId=Members.MemberId " +
                            "AND RentedMovies.MovieId=" + movieId + " AND RentedMovies.RentStatus=" + ServiceConstants.RENTED_MOVIE_STATUS_RENTED;
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get Rented Members for movieId:" + movieId + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                
                if(rs != null) {
                    ArrayList<MemberInfo> alList = new ArrayList<MemberInfo>();
                    while(rs.next()) {
                        MemberInfo memberInfo = new MemberInfo();
                        memberInfo.setMemberId(rs.getInt(ServiceConstants.MEMBERS_MEMBER_ID));
                        memberInfo.setFirstName(rs.getString(ServiceConstants.MEMBERS_FIRST_NAME));
                        memberInfo.setLastName(rs.getString(ServiceConstants.MEMBERS_LAST_NAME));
                        memberInfo.setStreet1(rs.getString(ServiceConstants.MEMBERS_STREET_1));
                        memberInfo.setStreet2(rs.getString(ServiceConstants.MEMBERS_STREET_2));
                        memberInfo.setCity(rs.getString(ServiceConstants.MEMBERS_CITY));
                        memberInfo.setState(rs.getString(ServiceConstants.MEMBERS_STATE));
                        memberInfo.setZipcode(rs.getString(ServiceConstants.MEMBERS_ZIPCODE));
                        memberInfo.setEmail(rs.getString(ServiceConstants.MEMBERS_EMAIL));
                        memberInfo.setPassword(rs.getString(ServiceConstants.MEMBERS_PASSWORD));
                        memberInfo.setPhoneNo(rs.getString(ServiceConstants.MEMBERS_PHONE_NO));
                        memberInfo.setMembership(rs.getString(ServiceConstants.MEMBERS_MEMBERSHIP_NO));
                        memberInfo.setMemberType(rs.getInt(ServiceConstants.MEMBERS_MEMBER_TYPE));
                        memberInfo.setStatus(rs.getInt(ServiceConstants.MEMBERS_STATUS));
                        memberInfo.setBalance(rs.getFloat(ServiceConstants.MEMBERS_BALANCE));
                        memberInfo.setLoginTime(rs.getLong(ServiceConstants.MEMBERS_LOGIN_TIME));                        
                        memberInfo.setRentedMovieId(rs.getInt(ServiceConstants.RENTED_MOVIES_RENTED_MOVIE_ID));
                        memberInfo.setRentedDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.RENTED_MOVIES_RENTED_DT)));

                        alList.add(memberInfo);
                    }
                    if(alList.size() > 0)
                        members = (MemberInfo[]) alList.toArray(new MemberInfo[alList.size()]);
                }
                rs.close();
                rs = null;
            }
            catch(Exception ex) {
                members = null;
                String msg = "Failed to find members who rented 'movieId' : " + movieId + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);            
            }
            finally {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }        
        }
        return members;
    }    
    
    private InvoiceInfo[] getPendingInvoices(int memberId) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(memberId <= 0) {
            throw new InvalidArgumentException("Invalid input argument 'memberId' : " + memberId);
        }
        InvoiceInfo[] invoices = null;
        MemberInfo memberInfo = null;
        if(Settings.isUseObjectCache()) {
            memberInfo = ServiceCache.getInstance().getMemberInfo(memberId);
            invoices = memberInfo.getPendingInvoices();
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            try {
                con = connMgr.getConnection();
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.INVOICES_SELECT_BY_MEMBER);
                    ((PreparedStatement) selectStmt).setInt(1, memberId);
                    ((PreparedStatement) selectStmt).setInt(2, ServiceConstants.INVOICES_STATUS_PENDING);
                    
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get Pending Invoices for memberId:" + memberId + " == " + ServiceConstants.INVOICES_SELECT_BY_MEMBER);
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                } 
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT Invoices.InvoiceId, Invoices.InvoiceDate, Invoices.InvoiceAmount FROM VideoLibrary.Invoices WHERE " +
                            "Invoices.MemberId=" + memberId + " AND Invoices.InvoiceStatus=" + ServiceConstants.INVOICES_STATUS_PENDING; 
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get Pending Invoices for memberId:" + memberId + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                
                if(rs != null) {
                    ArrayList<InvoiceInfo> alList = new ArrayList<InvoiceInfo>();
                    while(rs.next()) {
                        InvoiceInfo movie = new InvoiceInfo();
                        movie.setInvoiceId(rs.getInt(ServiceConstants.INVOICES_INVOICE_ID));
                        movie.setInvoiceDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.INVOICES_INVOICE_DATE)));
                        movie.setInvoiceAmount(rs.getFloat(ServiceConstants.INVOICES_INVOICE_AMOUNT));

                        alList.add(movie);
                    }
                    if(alList.size() > 0)
                        invoices = (InvoiceInfo[]) alList.toArray(new InvoiceInfo[alList.size()]);
                }
                rs.close();
                rs = null;
            }
            catch(Exception ex) {
                invoices = null;
                String msg = "Failed to find pending invoices for 'memberId' : " + memberId + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);            
            }
            finally {
                ConnectionManager.closeResultSet(rs);
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }        
        }
        return invoices;
    }    
    /**
     * @param email
     * @param password
     * @return
     * @throws DataSourceException
     * @throws InvalidArgumentException
     * @throws CustomException
     */
    public MemberInfo signIn(String email, String password) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        StringBuilder builder = new StringBuilder();
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255) {
            builder.append("Invalid input argument 'email' : " + email);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(password == null || password.length() == 0 || password.length() > 64) {
            builder.append("Invalid input argument 'password' : " + password);
            builder.append(ServiceConstants.NEW_LINE);
        } 
        if(builder.length() > 0) {
            throw new InvalidArgumentException(builder.toString());
        }
        
        MemberInfo memberInfo = null;
        Connection con = null;
        Statement selectStmt = null;
        Statement updateStmt = null;
        ResultSet rs = null;

        try {
            MemberInfo userInfo = null;
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUseObjectCache()) {
                userInfo = ServiceCache.getInstance().getMemberInfo(email);
                if(userInfo == null)
                    throw new CustomException("Username/Email does not exist, please try with different email address.");
                if(! password.equals(userInfo.getPassword()))
                    throw new CustomException("Invalid username/password, please try again.");
                
                memberInfo = new MemberInfo();
                memberInfo.setMemberId(userInfo.getMemberId());
                memberInfo.setFirstName(userInfo.getFirstName());
                memberInfo.setLastName(userInfo.getLastName());
                memberInfo.setStreet1(userInfo.getStreet1());
                memberInfo.setStreet2(userInfo.getStreet2());
                memberInfo.setCity(userInfo.getCity());
                memberInfo.setState(userInfo.getState());
                memberInfo.setZipcode(userInfo.getZipcode());
                memberInfo.setEmail(userInfo.getEmail());
                memberInfo.setPassword(userInfo.getPassword());
                memberInfo.setPhoneNo(userInfo.getPhoneNo());
                memberInfo.setMembership(userInfo.getMembership());
                memberInfo.setMemberType(userInfo.getMemberType());
                memberInfo.setStatus(userInfo.getStatus());
                memberInfo.setBalance(userInfo.getBalance());
                memberInfo.setLoginTime(userInfo.getLoginTime());
                memberInfo.setCreateDate(userInfo.getCreateDate());
            }
            else {
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MEMBERS_SELECT);
                    ((PreparedStatement) selectStmt).setString(1, email);
                   
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get Member Info for email:" + email + " == " + ServiceConstants.MEMBERS_SELECT);
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT * FROM VideoLibrary.Members WHERE Members.Email=\"" + email + "\"";
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get Member Info for email:" + email + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                if(rs != null && rs.next()) {
                    String pwd = rs.getString(ServiceConstants.MEMBERS_PASSWORD);
                    if(password.equals(pwd)) {
                        memberInfo = new MemberInfo();
                        memberInfo.setMemberId(rs.getInt(ServiceConstants.MEMBERS_MEMBER_ID));
                        memberInfo.setFirstName(rs.getString(ServiceConstants.MEMBERS_FIRST_NAME));
                        memberInfo.setLastName(rs.getString(ServiceConstants.MEMBERS_LAST_NAME));
                        memberInfo.setStreet1(rs.getString(ServiceConstants.MEMBERS_STREET_1));
                        memberInfo.setStreet2(rs.getString(ServiceConstants.MEMBERS_STREET_2));
                        memberInfo.setCity(rs.getString(ServiceConstants.MEMBERS_CITY));
                        memberInfo.setState(rs.getString(ServiceConstants.MEMBERS_STATE));
                        memberInfo.setZipcode(rs.getString(ServiceConstants.MEMBERS_ZIPCODE));
                        memberInfo.setEmail(rs.getString(ServiceConstants.MEMBERS_EMAIL));
                        memberInfo.setPassword(rs.getString(ServiceConstants.MEMBERS_PASSWORD));
                        memberInfo.setPhoneNo(rs.getString(ServiceConstants.MEMBERS_PHONE_NO));
                        memberInfo.setMembership(rs.getString(ServiceConstants.MEMBERS_MEMBERSHIP_NO));
                        memberInfo.setMemberType(rs.getInt(ServiceConstants.MEMBERS_MEMBER_TYPE));
                        memberInfo.setStatus(rs.getInt(ServiceConstants.MEMBERS_STATUS));
                        memberInfo.setBalance(rs.getFloat(ServiceConstants.MEMBERS_BALANCE));
                        memberInfo.setLoginTime(rs.getLong(ServiceConstants.MEMBERS_LOGIN_TIME));
                        memberInfo.setCreateDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.MEMBERS_CREATE_TM)));
                    }
                    else {
                        throw new CustomException("Invalid username/password, please try again.");
                    }
                }
                else {
                    throw new CustomException("Username/Email does not exist, please try with different email address.");
                }
                rs.close();
                rs = null;
            }
            if(memberInfo != null) {
                // update last login time and status in database
                if(Settings.isUsePreparedStatement()) {
                    updateStmt = con.prepareStatement(ServiceConstants.MEMBERS_UPDATE_LOGIN_TM);
                    ((PreparedStatement) updateStmt).setInt(1, ServiceConstants.MEMBER_SIGN_IN);
                    ((PreparedStatement) updateStmt).setInt(2, memberInfo.getMemberId());
                    
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to update last login time for memberId:" + memberInfo.getMemberId() + " == " + ServiceConstants.MEMBERS_UPDATE_LOGIN_TM);
                    int numRow = ((PreparedStatement) updateStmt).executeUpdate();
                    if(numRow <= 0) {
                        throw new CustomException("Failed to update last login and status in database for memberId " + memberInfo.getMemberId() + ", please try again later.");
                    }
                }
                else {
                    updateStmt = con.createStatement();
                    String query = "UPDATE VideoLibrary.Members SET Members.LoginTime=NOW(), Members.Status=" + ServiceConstants.MEMBER_SIGN_IN + 
                            " WHERE Members.MemberId=" + memberInfo.getMemberId(); 

                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to update last login time for memberId:" + memberInfo.getMemberId() + " == " + query);
                    int numRow = updateStmt.executeUpdate(query);
                    if(numRow <= 0) {
                        throw new CustomException("Failed to update last login and status in database for memberId " + memberInfo.getMemberId() + ", please try again later.");
                    }
                }
                ConnectionManager.commitConnection(con);
                
                userInfo.setStatus(ServiceConstants.MEMBER_SIGN_IN);
                userInfo.setLoginTime(System.currentTimeMillis());

                memberInfo.setStatus(ServiceConstants.MEMBER_SIGN_IN);
                // get rented movies for this memeber
                if(Settings.isUseObjectCache() && userInfo != null) {
                    memberInfo.setRentedMovies(userInfo.getRentedMovies());
                    memberInfo.setPendingInvoices(userInfo.getPendingInvoices());
                } else {
                    memberInfo.setRentedMovies(getRentedMovies(memberInfo.getMemberId()));
                    memberInfo.setPendingInvoices(getPendingInvoices(memberInfo.getMemberId()));
                }
            }
        }
        catch(Exception ex) {
            memberInfo = null;
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to signin, Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);            
        }
        finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(selectStmt);
            ConnectionManager.closeStatement(updateStmt);
            ConnectionManager.closeConnection(con);
        }
        return memberInfo;
    }
    /**
     * @param email
     * @throws DataSourceException
     * @throws InvalidArgumentException
     * @throws CustomException
     */
    public void signOut(String email) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }
        Connection con = null;
        Statement updateStmt = null;
        try {
            MemberInfo userInfo = null;
            if(Settings.isUseObjectCache()) {
                userInfo = ServiceCache.getInstance().getMemberInfo(email);
                if(userInfo == null)
                    throw new CustomException("Member Info object not present in cache for " + email + ", this should never happen.");
                userInfo.setStatus(ServiceConstants.MEMBER_SIGN_OUT);
            }
            else {
                // get connection
                con = connMgr.getConnection();
                
                // update signout status in DB - If we ever decide to keep Sign-Signout status only in memory then we can remove following code.
                if(Settings.isUsePreparedStatement()) {
                    updateStmt = con.prepareStatement(ServiceConstants.MEMBERS_UPDATE_STATUS);
                    ((PreparedStatement) updateStmt).setInt(1, ServiceConstants.MEMBER_SIGN_OUT);
                    ((PreparedStatement) updateStmt).setString(2, email);
                    
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to update login status for email:" + email + " == " + ServiceConstants.MEMBERS_UPDATE_STATUS);
                    int numRow = ((PreparedStatement) updateStmt).executeUpdate();
                    if(numRow <= 0)
                        throw new CustomException("Failed to update status in database for " + email + ", this should never happen.");
                }
                else {
                    updateStmt = con.createStatement();
                    String query = "UPDATE VideoLibrary.Members SET Members.Status=" + ServiceConstants.MEMBER_SIGN_OUT + " WHERE Members.Email=\"" + email + "\""; 

                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to update login status for email:" + email + " == " + query);
                    int numRow = updateStmt.executeUpdate(query);
                    if(numRow <= 0)
                        throw new CustomException("Failed to update status in database for " + email + ", this should never happen.");                
                }        
                ConnectionManager.commitConnection(con);
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to signout, Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);            
        }
        finally {
            ConnectionManager.closeStatement(updateStmt);
            ConnectionManager.closeConnection(con);
        }        
    }
    /**
     * @param firstname
     * @param lastname
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zipCode
     * @param email
     * @param password
     * @param phoneNo
     * @param premium
     * @throws DataSourceException
     * @throws InvalidArgumentException
     * @throws CustomException
     */
    public void addMember(String firstname, String lastname, String street1, String street2, String city, String state, String zipCode, 
            String email, String password, String phoneNo, boolean premium) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        StringBuilder builder = new StringBuilder();
        // validate input parameters
        if(firstname == null || firstname.length() == 0 || firstname.length() > 64) {
            builder.append("Invalid input argument 'firstname' : " + firstname);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(lastname == null || lastname.length() == 0 || lastname.length() > 64) {
            builder.append("Invalid input argument 'lastname' : " + lastname);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(street1 == null || street1.length() == 0 || street1.length() > 255) {
            builder.append("Invalid input argument 'street1' : " + street1);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(street2 != null && street2.length() > 255) {
            builder.append("Invalid input argument 'street2' : " + street2);
            builder.append(ServiceConstants.NEW_LINE);
        }        
        if(city == null || city.length() == 0 || city.length() > 128) {
            builder.append("Invalid input argument 'city' : " + city);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(state == null || state.length() == 0 || state.length() > 128) {
            builder.append("Invalid input argument 'state' : " + state);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(zipCode == null || zipCode.length() == 0 || zipCode.length() > 10 || ! zipCode.matches(ServiceConstants.ZIPCODE_PATTERN)) {
            builder.append("Invalid input argument 'zipCode' : " + zipCode);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(email == null || email.length() == 0 || email.length() > 255) {
            builder.append("Invalid input argument 'email' : " + email);
            builder.append(ServiceConstants.NEW_LINE);
        }        
        if(password == null || password.length() == 0 || password.length() > 64) {
            builder.append("Invalid input argument 'password' : " + password);
            builder.append(ServiceConstants.NEW_LINE);
        } 
        if(phoneNo == null || phoneNo.length() == 0 || phoneNo.length() > 10) {
            builder.append("Invalid input argument 'phoneNo' : " + phoneNo);
            builder.append(ServiceConstants.NEW_LINE);
        }         
        if(builder.length() > 0) {
            throw new InvalidArgumentException(builder.toString());
        }
        
        String msg = null;
        Connection con = null;
        Statement selectStmt = null;
        Statement insertStmt = null;
        ResultSet rs = null;
        int memberId = -1;
        String memberShipNum = null;
        try {
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUseObjectCache()) {
                MemberInfo memberInfo = ServiceCache.getInstance().getMemberInfo(email);
                if(memberInfo != null) {
                    throw new CustomException("Username/Email already exists, please try with different email address.");
                }
            }
            else {
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MEMBERS_SELECT_STATUS);
                    ((PreparedStatement) selectStmt).setString(1, email);

                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to check if member already exists for email:" + email + " == " + ServiceConstants.MEMBERS_SELECT_STATUS);
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT Members.Status FROM VideoLibrary.Members where Members.Email=\"" + email + "\""; 

                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to check if member already exists for email:" + email + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                if(rs != null && rs.next()) {
                    throw new CustomException("Username/Email already exists, please try with different email address.");
                }
                rs.close();
                rs = null;
            }
            
            if(Settings.isUsePreparedStatement()) {

                memberShipNum = ServiceCache.generateMembershipNumber();
                insertStmt = con.prepareStatement(ServiceConstants.MEMBERS_INSERT, Statement.RETURN_GENERATED_KEYS);
                ((PreparedStatement) insertStmt).setString(1, firstname);
                ((PreparedStatement) insertStmt).setString(2, lastname);
                ((PreparedStatement) insertStmt).setString(3, street1);
                ((PreparedStatement) insertStmt).setString(4, street2);
                ((PreparedStatement) insertStmt).setString(5, city);
                ((PreparedStatement) insertStmt).setString(6, state);
                ((PreparedStatement) insertStmt).setString(7, zipCode);
                ((PreparedStatement) insertStmt).setString(8, email);
                ((PreparedStatement) insertStmt).setString(9, password);
                ((PreparedStatement) insertStmt).setInt(10, premium ? ServiceConstants.MEMBER_TYPE_PREMIUM : ServiceConstants.MEMBER_TYPE_SIMPLE);
                ((PreparedStatement) insertStmt).setString(11, phoneNo);
                ((PreparedStatement) insertStmt).setString(12, memberShipNum);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to insert new member for email:" + email + " == " + ServiceConstants.MEMBERS_INSERT);
                int numRow = ((PreparedStatement) insertStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to create account for " + email + ", please try again later.");
                }
                else {
                    rs = ((PreparedStatement) insertStmt).getGeneratedKeys();
                }
            }
            else {
                insertStmt = con.createStatement();
                String query = ServiceConstants.INSERT_STR + ServiceConstants.MEMBERS_TABLE + ServiceConstants.LEFT_BRACKET_STR + ServiceConstants.MEMBERS_FIRST_NAME + 
                        ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_LAST_NAME + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_STREET_1 + ServiceConstants.COMMA_STR;
                if(street2 != null) {
                    query += ServiceConstants.MEMBERS_STREET_2 + ServiceConstants.COMMA_STR;
                }
                query += ServiceConstants.MEMBERS_CITY + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_STATE + ServiceConstants.COMMA_STR +  ServiceConstants.MEMBERS_ZIPCODE +
                        ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_EMAIL + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_PASSWORD + ServiceConstants.COMMA_STR +
                        ServiceConstants.MEMBERS_MEMBER_TYPE + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_PHONE_NO + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_MEMBERSHIP_NO + 
                        ServiceConstants.COMMA_STR +  ServiceConstants.MEMBERS_STATUS + ServiceConstants.COMMA_STR + ServiceConstants.MEMBERS_BALANCE + ServiceConstants.COMMA_STR + 
                        ServiceConstants.MEMBERS_CREATE_TM + ServiceConstants.VALUES_STR + ServiceConstants.QUOTES_STR + firstname  + ServiceConstants.COMMA_QUOTE_STR + lastname + 
                        ServiceConstants.COMMA_QUOTE_STR + street1 + ServiceConstants.COMMA_QUOTE_STR;
                if(street2 != null) {
                    query += street2 + ServiceConstants.COMMA_QUOTE_STR;
                }
                query += city + ServiceConstants.COMMA_QUOTE_STR + state + ServiceConstants.COMMA_QUOTE_STR + zipCode + ServiceConstants.COMMA_QUOTE_STR + email + ServiceConstants.COMMA_QUOTE_STR + 
                        password + ServiceConstants.QUOTES_STR + ServiceConstants.COMMA_STR +  (premium ? ServiceConstants.MEMBER_TYPE_PREMIUM : ServiceConstants.MEMBER_TYPE_SIMPLE) + 
                        ServiceConstants.COMMA_STR + ServiceConstants.QUOTES_STR  + phoneNo + ServiceConstants.COMMA_QUOTE_STR + memberShipNum + ServiceConstants.QUOTES_STR + 
                        ServiceConstants.COMMA_STR + "0" + ServiceConstants.COMMA_STR + "0" + ServiceConstants.COMMA_STR + " NOW())";
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Query to insert new member for email:" + email + " == " + query);
                
                int numRow = insertStmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                if(numRow <= 0) {
                    throw new CustomException("Failed to create account for " + email + ", please try again later.");
                }
                else {
                    rs = insertStmt.getGeneratedKeys();
                }
            }
            if(rs != null && rs.next()) {
                memberId = rs.getInt(1);
            }
            else {
                throw new CustomException("Failed to create account for " + email + ", please try again later.");
            }
            rs.close();
            rs = null;

            ConnectionManager.commitConnection(con);
            
            if(memberId != -1 && memberShipNum != null) {
                MemberInfo userInfo = new MemberInfo();
                userInfo.setMemberId(memberId);
                userInfo.setFirstName(firstname);
                userInfo.setLastName(lastname);
                userInfo.setStreet1(street1);
                userInfo.setStreet2(street2);
                userInfo.setCity(city);
                userInfo.setState(state);
                userInfo.setZipcode(zipCode);
                userInfo.setEmail(email);
                userInfo.setPassword(password);
                userInfo.setPhoneNo(phoneNo);
                userInfo.setMembership(memberShipNum);
                userInfo.setMemberType((premium ? ServiceConstants.MEMBER_TYPE_PREMIUM : ServiceConstants.MEMBER_TYPE_SIMPLE));
                userInfo.setStatus(ServiceConstants.MEMBER_SIGN_OUT);
                userInfo.setBalance(0);
                userInfo.setCreateDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(new Date(System.currentTimeMillis())));
                ServiceCache.getInstance().addMemberInfo(userInfo);
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            msg = "Failed to create account for " + email + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(selectStmt);
            ConnectionManager.closeStatement(insertStmt);
            ConnectionManager.closeConnection(con);
        }
    }      
    /**
     * @param memberInfo
     */
    public void deleteMember(String email, String userEmail) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }         
        if(userEmail == null || userEmail.length() == 0 || userEmail.length() > 255 || !validUser(userEmail, false)) {
            throw new InvalidArgumentException("Invalid input argument 'userEmail' : " + userEmail);
        } 
        
        Connection con = null;
        Statement deleteStmt = null;
        try {
            if(Settings.isUseObjectCache()) {
                MemberInfo memberInfo = ServiceCache.getInstance().getMemberInfo(userEmail);
                if(memberInfo == null) {
                    throw new CustomException("Failed to find member with email address: " + userEmail);
                }
            }
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUsePreparedStatement()) {
                deleteStmt = con.prepareStatement(ServiceConstants.MEMBERS_DELETE);
                ((PreparedStatement) deleteStmt).setString(1, userEmail);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to delete member for email: " + userEmail + " == " + ServiceConstants.MEMBERS_DELETE);
                int numRow = ((PreparedStatement) deleteStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to delete member for " + userEmail + ", please try again later.");
                }
            }
            else {
                deleteStmt = con.createStatement();
                String query = "DELETE FROM VideoLibrary.Members WHERE Members.Email=\"" + userEmail + "\"" +
                        "AND Members.MemberId NOT IN (SELECT DISTINCT(RentedMovies.MemberId) FROM VideoLibrary.RentedMovies)";
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Query to delete member for email: " + userEmail + " == " + query);
                
                int numRow = deleteStmt.executeUpdate(query);
                if(numRow <= 0) {
                    throw new CustomException("Failed to delete member for " + userEmail + ", please try again later.");
                }
            }
            ConnectionManager.commitConnection(con);
            
            if(Settings.isUseObjectCache()) {
                ServiceCache.getInstance().removeMemberInfo(userEmail);
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to delete member for " + userEmail + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeStatement(deleteStmt);
            ConnectionManager.closeConnection(con);
        }
    }
    /**
     * @param email
     * @param firstname
     * @param lastname
     * @param street1
     * @param street2
     * @param city
     * @param state
     * @param zipCode
     * @param password
     * @param phoneNo
     * @param premium
     * @throws DataSourceException
     * @throws InvalidArgumentException
     * @throws CustomException
     */
    public void editMember(String email, String userEmail, String firstname, String lastname, String street1, String street2, String city, String state, String zipCode, 
            String password, String phoneNo, boolean premium) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        StringBuilder builder = new StringBuilder();
        // validate input parameters
        if(firstname == null || firstname.length() == 0 || firstname.length() > 64) {
            builder.append("Invalid input argument 'firstname' : " + firstname);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(lastname == null || lastname.length() == 0 || lastname.length() > 64) {
            builder.append("Invalid input argument 'lastname' : " + lastname);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(street1 == null || street1.length() == 0 || street1.length() > 255) {
            builder.append("Invalid input argument 'street1' : " + street1);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(street2 != null && street2.length() > 255) {
            builder.append("Invalid input argument 'street2' : " + street2);
            builder.append(ServiceConstants.NEW_LINE);
        }        
        if(city == null || city.length() == 0 || city.length() > 128) {
            builder.append("Invalid input argument 'city' : " + city);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(state == null || state.length() == 0 || state.length() > 128) {
            builder.append("Invalid input argument 'state' : " + state);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(zipCode == null || zipCode.length() == 0 || zipCode.length() > 10 || ! zipCode.matches(ServiceConstants.ZIPCODE_PATTERN)) {
            builder.append("Invalid input argument 'zipCode' : " + zipCode);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(password == null || password.length() == 0 || password.length() > 64) {
            builder.append("Invalid input argument 'password' : " + password);
            builder.append(ServiceConstants.NEW_LINE);
        } 
        if(phoneNo == null || phoneNo.length() == 0 || phoneNo.length() > 10) {
            builder.append("Invalid input argument 'phoneNo' : " + phoneNo);
            builder.append(ServiceConstants.NEW_LINE);
        }  
        if(userEmail == null || userEmail.length() == 0 || userEmail.length() > 255 || !validUser(userEmail, false)) {
            builder.append("Invalid input argument 'userEmail' : " + userEmail);
            builder.append(ServiceConstants.NEW_LINE);
        }         
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            builder.append("Invalid input argument 'email' : " + email);
        }        
        if(builder.length() > 0) {
            throw new InvalidArgumentException(builder.toString());
        }        

        Connection con = null;
        Statement updateStmt = null;
        try {
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUsePreparedStatement()) {
                updateStmt = con.prepareStatement(ServiceConstants.MEMBERS_UPDATE);
                ((PreparedStatement) updateStmt).setString(1, firstname);
                ((PreparedStatement) updateStmt).setString(2, lastname);
                ((PreparedStatement) updateStmt).setString(3, street1);
                ((PreparedStatement) updateStmt).setString(4, street2);
                ((PreparedStatement) updateStmt).setString(5, city);
                ((PreparedStatement) updateStmt).setString(6, state);
                ((PreparedStatement) updateStmt).setString(7, zipCode);
                ((PreparedStatement) updateStmt).setString(8, phoneNo);
                ((PreparedStatement) updateStmt).setString(9, password);
                ((PreparedStatement) updateStmt).setString(10, userEmail);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to edit member for email: " + userEmail + " == " + ServiceConstants.MEMBERS_UPDATE);
                int numRow = ((PreparedStatement) updateStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to update member for email: " + userEmail + ", please try again later.");
                }
            }
            else {
                updateStmt = con.createStatement();
                String query = "UPDATE VideoLibrary.Members SET Members.FirstName=\"" + firstname + "\", Members.LastName=\"" + lastname + "\", " +
                		"Members.Street1=\"" + street1 + "\", ";
                if(street2 != null) {
                    query += "Members.Street2=\"" + street2 + "\", ";
                }
                query += "Members.City=\"" + city + "\", Members.State=\"" + state + "\", Members.ZipCode=\"" + zipCode + "\", " +
                		"Members.PhoneNo=\"" + phoneNo + "\", Members.Password=\"" + password + "\", Members.MemberType=" + 
                        (premium ? ServiceConstants.MEMBER_TYPE_PREMIUM : ServiceConstants.MEMBER_TYPE_SIMPLE) + " WHERE Members.Email=\"" + userEmail + "\"";
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Statement Query to edit member for email: " + userEmail + " == " + query);
                int numRow = updateStmt.executeUpdate(query);
                if(numRow <= 0) {
                    throw new CustomException("Failed to update member for email: " + userEmail + ", please try again later.");
                }
            }
            ConnectionManager.commitConnection(con);
            
            if(Settings.isUseObjectCache()) {
                MemberInfo userInfo = ServiceCache.getInstance().getMemberInfo(userEmail);
                if(userInfo != null) {
                    userInfo.setFirstName(firstname);
                    userInfo.setLastName(lastname);
                    userInfo.setStreet1(street1);
                    userInfo.setStreet2(street2);
                    userInfo.setCity(city);
                    userInfo.setState(state);
                    userInfo.setZipcode(zipCode);
                    userInfo.setEmail(email);
                    userInfo.setPassword(password);
                    userInfo.setPhoneNo(phoneNo);
                    userInfo.setMemberType((premium ? ServiceConstants.MEMBER_TYPE_PREMIUM : ServiceConstants.MEMBER_TYPE_SIMPLE));  
                }
                else {
                    throw new CustomException("Failed to find member with email address: " + userEmail);
                }
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to edit member for email: " + userEmail + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeStatement(updateStmt);
            ConnectionManager.closeConnection(con);
        }        
    }
    /**
     * @return
     */
    public MemberInfo[] getMembers(String email) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }
        
        MemberInfo[] memberInfos = null;
        if(Settings.isUseObjectCache()) {
            memberInfos = ServiceCache.getInstance().getMembers();
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            
            try {
                con = connMgr.getConnection();
                // TODO - Validate this user is ADMIN user
                
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MEMBERS_SELECT_ALL);
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get all members for email: " + email + " == " + ServiceConstants.MEMBERS_SELECT_ALL);
                
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT * FROM VideoLibrary.Members";
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get all members for email: " + email + " == " + query);
                
                    rs = selectStmt.executeQuery(query);           
                }
                if(rs != null) {
                    MemberInfo memberInfo = null;
                    ArrayList<MemberInfo> alList = new ArrayList<MemberInfo>();
                    while(rs.next()) {
                        memberInfo = new MemberInfo();
                        memberInfo.setMemberId(rs.getInt(ServiceConstants.MEMBERS_MEMBER_ID));
                        memberInfo.setFirstName(rs.getString(ServiceConstants.MEMBERS_FIRST_NAME));
                        memberInfo.setLastName(rs.getString(ServiceConstants.MEMBERS_LAST_NAME));
                        memberInfo.setStreet1(rs.getString(ServiceConstants.MEMBERS_STREET_1));
                        memberInfo.setStreet2(rs.getString(ServiceConstants.MEMBERS_STREET_2));
                        memberInfo.setCity(rs.getString(ServiceConstants.MEMBERS_CITY));
                        memberInfo.setState(rs.getString(ServiceConstants.MEMBERS_STATE));
                        memberInfo.setZipcode(rs.getString(ServiceConstants.MEMBERS_ZIPCODE));
                        memberInfo.setEmail(rs.getString(ServiceConstants.MEMBERS_EMAIL));
                        memberInfo.setPassword(rs.getString(ServiceConstants.MEMBERS_PASSWORD));
                        memberInfo.setPhoneNo(rs.getString(ServiceConstants.MEMBERS_PHONE_NO));
                        memberInfo.setMembership(rs.getString(ServiceConstants.MEMBERS_MEMBERSHIP_NO));
                        memberInfo.setMemberType(rs.getInt(ServiceConstants.MEMBERS_MEMBER_TYPE));
                        memberInfo.setStatus(rs.getInt(ServiceConstants.MEMBERS_STATUS));
                        memberInfo.setBalance(rs.getFloat(ServiceConstants.MEMBERS_BALANCE));
                        memberInfo.setLoginTime(rs.getLong(ServiceConstants.MEMBERS_LOGIN_TIME));
                        memberInfo.setCreateDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.MEMBERS_CREATE_TM)));
                        
                        memberInfo.setRentedMovies(getRentedMovies(memberInfo.getMemberId()));
                        memberInfo.setPendingInvoices(getPendingInvoices(memberInfo.getMemberId()));
                  
                        alList.add(memberInfo);
                    }
                    rs.close();
                    rs = null;

                    if(alList.size() > 0) {
                        memberInfos = (MemberInfo[]) alList.toArray(new MemberInfo[alList.size()]);
                    }
                }
            }
            catch(Exception ex) {
                ConnectionManager.rollbackConnection(con);
                String msg = "Failed to get members for " + email + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);
            }
            finally {
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }          
        }
        return memberInfos;
    }
    /**
     * Add item to sell
     * 
     * @param txId
     * @param nm
     * @param desc
     * @param seller
     * @param price
     * @param qty
     * @return
     */
    public void addMovie(String email, String name, String banner, long releaseTime, float rent, String category, int copies, String summary) 
            throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        StringBuilder builder = new StringBuilder();
        // validate input parameters
        if(name == null || name.length() == 0 || name.length() > 255) {
            builder.append("Invalid input argument 'movie name' : " + name);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(banner == null || banner.length() == 0 || banner.length() > 255) {
            builder.append("Invalid input argument 'movie banner' : " + banner);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(category == null || category.length() == 0 || category.length() > 255) {
            builder.append("Invalid input argument 'category' : " + category);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(summary != null && summary.length() > 1024) {
            builder.append("Invalid input argument 'summary' : " + summary);
            builder.append(ServiceConstants.NEW_LINE);
        }        
        if(copies <= 0) {
            builder.append("Invalid input argument 'copies' : " + copies);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(rent <= 0) {
            builder.append("Invalid input argument 'rent' : " + rent);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(releaseTime <= 0 || releaseTime > System.currentTimeMillis()) {
            builder.append("Invalid input argument 'releaseTime' : " + releaseTime);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            builder.append("Invalid input argument 'email' : " + email);
        }        
        if(builder.length() > 0) {
            throw new InvalidArgumentException(builder.toString());
        }
        
        String msg = null;
        Connection con = null;
        Statement selectStmt = null;
        Statement insertStmt = null;
        ResultSet rs = null;
        int movieId = -1;

        try {
            //TODO - Validate that user is ADMIN user
            
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUseObjectCache()) {
                MovieInfo movieInfo = ServiceCache.getInstance().getMovieInfo(name);
                if(movieInfo != null) {
                    throw new CustomException("Movie name " + name + " already exists, please try with different name.");
                }
            }
            else {
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MOVIES_SELECT_ID);
                    ((PreparedStatement) selectStmt).setString(1, name);

                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to check if movie already exists for name:" + name + " == " + ServiceConstants.MOVIES_SELECT_ID);
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT Movies.MovieId FROM VideoLibrary.Movies WHERE Movies.MovieName=\"" + name + "\""; 

                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to check if movie already exists for name:" + name + " == " + query);
                    rs = selectStmt.executeQuery(query);
                }
                if(rs != null && rs.next()) {
                    throw new CustomException("Movie name " + name + " already exists, please try with different name.");
                }
                rs.close();
                rs = null;
            }
            
            if(Settings.isUsePreparedStatement()) {
                insertStmt = con.prepareStatement(ServiceConstants.MOVIES_INSERT, Statement.RETURN_GENERATED_KEYS);
                ((PreparedStatement) insertStmt).setString(1, name);
                ((PreparedStatement) insertStmt).setString(2, banner);
                ((PreparedStatement) insertStmt).setDate(3, new Date(releaseTime));
                ((PreparedStatement) insertStmt).setFloat(4, rent);
                ((PreparedStatement) insertStmt).setString(5, category);
                ((PreparedStatement) insertStmt).setInt(6, copies);
                ((PreparedStatement) insertStmt).setString(7, summary);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to insert new movie :" + name + " == " + ServiceConstants.MOVIES_INSERT);
                int numRow = ((PreparedStatement) insertStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to insert movie " + name + ", please try again later.");
                }
                else {
                    rs = ((PreparedStatement) insertStmt).getGeneratedKeys();
                }
            }
            else {
                insertStmt = con.createStatement();
                String query = "INSERT INTO VideoLibrary.Movies (Movies.MovieName, Movies.MovieBanner, Movies.ReleaseDate, Movies.RentAmount, Movies.Category, " +
                        "Movies.AvailableCopies";
                if(summary != null) {
                    query += ", Movies.MovieSummary";
                }
                query += ") VALUES(\"" + name + "\", \"" + banner + "\", \"" + new Date(releaseTime) + "\", " + rent + ", \"" + category + "\", " + copies;
                if(summary != null) {
                    query += ", \"" + summary + "\"";
                }
                query += ")";
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Query to insert new movie :" + name + " == " + query);
                
                int numRow = insertStmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                if(numRow <= 0) {
                    throw new CustomException("Failed to insert movie " + name + ", please try again later.");
                }
                else {
                    rs = insertStmt.getGeneratedKeys();
                }
            }
            if(rs != null && rs.next()) {
                movieId = rs.getInt(1);
            }
            else {
                throw new CustomException("Failed to insert movie " + name + ", please try again later.");
            }
            rs.close();
            rs = null;

            ConnectionManager.commitConnection(con);
            
            if(movieId != -1) {
                MovieInfo movieInfo = new MovieInfo();
                movieInfo.setMovieName(name);
                movieInfo.setMovieBanner(banner);
                movieInfo.setReleaseDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(new Date(releaseTime)));
                movieInfo.setRentAmount(rent);
                movieInfo.setAvailableCopies(copies);
                movieInfo.setCategory(category);
                if(summary != null)
                    movieInfo.setMovieSummary(summary);

                ServiceCache.getInstance().addMovieInfo(movieInfo);
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            msg = "Failed to insert movie " + name + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeResultSet(rs);
            ConnectionManager.closeStatement(selectStmt);
            ConnectionManager.closeStatement(insertStmt);
            ConnectionManager.closeConnection(con);
        }        
    }
    /**
     * @param email
     * @param movieId
     * @param name
     * @param banner
     * @param releaseTime
     * @param rent
     * @param category
     * @param copies
     * @param summary
     * @throws DataSourceException
     * @throws InvalidArgumentException
     * @throws CustomException
     */
    public void editMovie(String email, int movieId, String name, String banner, long releaseTime, float rent, String category, int copies, String summary) 
            throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        StringBuilder builder = new StringBuilder();
        // validate input parameters
        if(movieId <= 0) {
            builder.append("Invalid input argument 'movie id' : " + movieId);
            builder.append(ServiceConstants.NEW_LINE);            
        }
        if(name == null || name.length() == 0 || name.length() > 255) {
            builder.append("Invalid input argument 'movie name' : " + name);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(banner == null || banner.length() == 0 || banner.length() > 255) {
            builder.append("Invalid input argument 'movie banner' : " + banner);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(category == null || category.length() == 0 || category.length() > 255) {
            builder.append("Invalid input argument 'category' : " + category);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(summary != null && summary.length() > 1024) {
            builder.append("Invalid input argument 'summary' : " + summary);
            builder.append(ServiceConstants.NEW_LINE);
        }        
        if(copies <= 0) {
            builder.append("Invalid input argument 'copies' : " + copies);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(rent <= 0) {
            builder.append("Invalid input argument 'rent' : " + rent);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(releaseTime <= 0 || releaseTime > System.currentTimeMillis()) {
            builder.append("Invalid input argument 'releaseTime' : " + releaseTime);
            builder.append(ServiceConstants.NEW_LINE);
        }
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            builder.append("Invalid input argument 'email' : " + email);
        }        
        if(builder.length() > 0) {
            throw new InvalidArgumentException(builder.toString());
        }        

        Connection con = null;
        Statement updateStmt = null;
        try {
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUsePreparedStatement()) {
                updateStmt = con.prepareStatement(ServiceConstants.MOVIES_UPDATE);
                ((PreparedStatement) updateStmt).setString(1, name);
                ((PreparedStatement) updateStmt).setString(2, banner);
                ((PreparedStatement) updateStmt).setDate(3, new Date(releaseTime));
                ((PreparedStatement) updateStmt).setFloat(4, rent);
                ((PreparedStatement) updateStmt).setString(5, category);
                ((PreparedStatement) updateStmt).setInt(6, copies);
                ((PreparedStatement) updateStmt).setString(7, summary);
                ((PreparedStatement) updateStmt).setInt(8, movieId);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to edit movie for name " + name + " == " + ServiceConstants.MEMBERS_UPDATE);
                int numRow = ((PreparedStatement) updateStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to update movie for name " + name + ", please try again later.");
                }
            }
            else {
                updateStmt = con.createStatement();
                String query = "UPDATE VideoLibrary.Movies SET Movies.MovieName=\"" + name + "\", Movies.MovieBanner=\"" + banner + "\", " +
                		"Movies.ReleaseDate=\"" + new Date(releaseTime) + "\", Movies.RentAmount=" + rent + ", Movies.Category=\"" + category + "\"," +
                        " Movies.AvailableCopies=" + copies;
                if(summary != null)
                    query += ", Movies.MovieSummary=\"" + summary + "\"";
                query += " WHERE Movies.MovieId=" + movieId;
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Query to edit movie for name " + name + " == " + query);
                int numRow = updateStmt.executeUpdate(query);
                if(numRow <= 0) {
                    throw new CustomException("Failed to update movie for name " + name + ", please try again later.");
                }
            }
            ConnectionManager.commitConnection(con);
            
            if(Settings.isUseObjectCache()) {
                MovieInfo movieInfo = ServiceCache.getInstance().getMovieInfo(movieId);
                if(movieInfo != null) {
                    movieInfo.setMovieName(name);
                    movieInfo.setMovieBanner(banner);
                    movieInfo.setReleaseDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(new Date(releaseTime)));
                    movieInfo.setCategory(category);
                    movieInfo.setRentAmount(rent);
                    movieInfo.setAvailableCopies(copies);
                    movieInfo.setMovieSummary(summary);
                }
                else {
                    throw new CustomException("Failed to find movie with id: " + movieId);
                }
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to edit movie for id: " + movieId + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeStatement(updateStmt);
            ConnectionManager.closeConnection(con);
        }           
    }
    
    public void deleteMovie(String email, String name) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, true)) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }         
        if(name == null || name.length() == 0 || name.length() > 255) {
            throw new InvalidArgumentException("Invalid input argument 'movie name' : " + name);
        }        

        Connection con = null;
        Statement deleteStmt = null;
        try {
            if(Settings.isUseObjectCache()) {
                MovieInfo movieInfo = ServiceCache.getInstance().getMovieInfo(name);
                if(movieInfo == null)
                    throw new CustomException("Failed to find movie with name: " + name);
            }
            // get connection
            con = connMgr.getConnection();
            if(Settings.isUsePreparedStatement()) {
                deleteStmt = con.prepareStatement(ServiceConstants.MOVIES_DELETE);
                ((PreparedStatement) deleteStmt).setString(1, name);
                
                if(logger.isInfoEnabled())
                    logger.info("Prepared Statement Query to delete movie for name: " + name + " == " + ServiceConstants.MOVIES_DELETE);
                int numRow = ((PreparedStatement) deleteStmt).executeUpdate();
                if(numRow <= 0) {
                    throw new CustomException("Failed to delete movie for name: " + name + ", please try again later.");
                }
            }
            else {
                deleteStmt = con.createStatement();
                String query = "DELETE FROM VideoLibrary.Movies WHERE Movies.MovieName=\"" + name + "\"" +
                        " AND Movies.MovieId NOT IN (SELECT DISTINCT(RentedMovies.MovieId) FROM VideoLibrary.RentedMovies)";
                
                if(logger.isInfoEnabled())
                    logger.info("Statement Query to delete movie for name: " + name + " == " + query);
                int numRow = deleteStmt.executeUpdate(query);
                if(numRow <= 0) {
                    throw new CustomException("Failed to delete movie for name: " + name + ", please try again later.");
                }
            }
            ConnectionManager.commitConnection(con);
            
            if(Settings.isUseObjectCache()) {
                ServiceCache.getInstance().removeMovieInfo(name);
            }
        }
        catch(Exception ex) {
            ConnectionManager.rollbackConnection(con);
            String msg = "Failed to delete movie for name: " + name + ", Exception " + ex.getLocalizedMessage();
            logger.error(msg,  ex);
            throw new CustomException(msg, ex);
        }
        finally {
            ConnectionManager.closeStatement(deleteStmt);
            ConnectionManager.closeConnection(con);
        }        
    }

    public MovieInfo[] getMovies(String email) throws DataSourceException, InvalidArgumentException, CustomException {
        if(connMgr == null) {
            throw new DataSourceException(ServiceConstants.DATA_SOURCE_EXCEPTION);
        }
        // validate input parameters
        if(email == null || email.length() == 0 || email.length() > 255 || !validUser(email, false)) {
            throw new InvalidArgumentException("Invalid input argument 'email' : " + email);
        }
        
        MovieInfo[] movieInfos = null;
        if(Settings.isUseObjectCache()) {
            movieInfos = ServiceCache.getInstance().getMovies();
        }
        else {
            Connection con = null;
            Statement selectStmt = null;
            ResultSet rs = null;
            
            try {
                con = connMgr.getConnection();
                if(Settings.isUsePreparedStatement()) {
                    selectStmt = con.prepareStatement(ServiceConstants.MOVIES_SELECT_ALL);
                    if(logger.isInfoEnabled())
                        logger.info("Prepared Statement Query to get all movies for email: " + email + " == " + ServiceConstants.MOVIES_SELECT_ALL);
                
                    rs = ((PreparedStatement) selectStmt).executeQuery();
                }
                else {
                    selectStmt = con.createStatement();
                    String query = "SELECT * FROM VideoLibrary.Movies";
                    
                    if(logger.isInfoEnabled())
                        logger.info("Statement Query to get all movies for email: " + email + " == " + query);
                
                    rs = selectStmt.executeQuery(query);           
                }
                if(rs != null) {
                    MovieInfo movieInfo = null;
                    ArrayList<MovieInfo> alList = new ArrayList<MovieInfo>();
                    while(rs.next()) {
                        movieInfo = new MovieInfo();
                        movieInfo.setMovieId(rs.getInt(ServiceConstants.MOVIES_MOVIE_ID));
                        movieInfo.setMovieName(rs.getString(ServiceConstants.MOVIES_MOVIE_NAME));
                        movieInfo.setMovieBanner(rs.getString(ServiceConstants.MOVIES_MOVIE_BANNER));
                        movieInfo.setReleaseDate(new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(rs.getDate(ServiceConstants.MOVIES_RELEASE_DT)));
                        movieInfo.setRentAmount(rs.getFloat(ServiceConstants.MOVIES_RENT_AMOUNT));
                        movieInfo.setAvailableCopies(rs.getInt(ServiceConstants.MOVIES_AVAILABLE_COPIES));
                        movieInfo.setCategory(rs.getString(ServiceConstants.MOVIES_CATEGORY));
                        movieInfo.setMovieSummary(rs.getString(ServiceConstants.MOVIES_MOVIE_SUMMARY));                        

                        movieInfo.setRentedMembers(getRentedMmembers(movieInfo.getMovieId()));
                        alList.add(movieInfo);
                    }
                    rs.close();
                    rs = null;

                    if(alList.size() > 0) {
                        movieInfos = (MovieInfo[]) alList.toArray(new MovieInfo[alList.size()]);
                    }
                }
            }
            catch(Exception ex) {
                ConnectionManager.rollbackConnection(con);
                String msg = "Failed to get members for " + email + ", Exception " + ex.getLocalizedMessage();
                logger.error(msg,  ex);
                throw new CustomException(msg, ex);
            }
            finally {
                ConnectionManager.closeStatement(selectStmt);
                ConnectionManager.closeConnection(con);
            }          
        }
        return movieInfos;        
    }
    
    public MemberInfo generateBill(String email, String userEmail) throws DataSourceException, InvalidArgumentException, CustomException {
        //TODO
        return null;
    }
    
    public MemberInfo rentMovie(String email, int movieId) throws DataSourceException, InvalidArgumentException, CustomException {
        //TODO
        return null;
    }
    
    public MemberInfo returnMovie(String email, int movieId, int returnMovieId) throws DataSourceException, InvalidArgumentException, CustomException {
        //TODO
        return null;
    }
    
    public MemberInfo payBalance(String email, float amount, String ccNum, String ccName) throws DataSourceException, InvalidArgumentException, CustomException {
        //TODO
        return null;
    }
}
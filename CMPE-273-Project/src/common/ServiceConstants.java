package common;

import java.io.File;

/**
 * Constants class
 * 
 * @author Team 7
 */
public interface ServiceConstants {
    
    long SESSION_TIMEOUT = 30*60*1000;  //30 minutes

    char SEPARATOR = '$';
    char SPACE = ' ';
    char SEPARATOR_CHAR = File.separatorChar;
    
    String LOG4J_PROPERTIES = "log4j.properties";
    String LIBRARY_PROPERTIES = "library.properties";
    String DATASOURCE_PROPERTIES = "datasource.properties";
     
    String USE_PREPARED_STMT = "USE_PREPARED_STMT";
    boolean USE_PREPARED_STMT_DEFAULT = true;
    
    String USE_CONNECTION_POOL = "USE_CONNECTION_POOL";
    boolean USE_CONNECTION_POOL_DEFAULT = true;
    
    String USE_OBJECT_CACHE = "USE_OBJECT_CACHE";
    boolean USE_OBJECT_CACHE_DEFAULT = true;
    
    String WATCH_REFRESH_INTERVAL = "WATCH_REFRESH_INTERVAL";
    int WATCH_REFRESH_INTERVAL_DEFAULT = 60;
    
    String DRIVER_CLASSNAME = "DRIVER_CLASSNAME";
    String DRIVER_CLASSNAME_DEFAULT = "com.mysql.jdbc.Driver";
    
    String CONNECTION_URL = "CONNECTION_URL";
    String CONNECTION_URL_DEFAULT = "jdbc:mysql://localhost:3306/VideoLibrary";    

    String DB_USERNAME = "DB_USERNAME";
    String DB_USERNAME_DEFAULT = "geneva";    

    String DB_PASSWORD = "DB_PASSWORD";
    String DB_PASSWORD_DEFAULT = "";    

    String MAX_ACTIVE_CONNECTION = "MAX_ACTIVE_CONNECTION";
    int MAX_ACTIVE_CONNECTION_DEFAULT = 50;    
    
    String MAX_IDLE_CONNECTION = "MAX_IDLE_CONNECTION";
    int MAX_IDLE_CONNECTION_DEFAULT = 50;    
    
    String CONNECTION_TIMEOUT = "CONNECTION_TIMEOUT";
    int CONNECTION_TIMEOUT_DEFAULT = 60;   

    String VALIDATE_CONNECTION = "VALIDATE_CONNECTION";
    boolean VALIDATE_CONNECTION_DEFAULT = false;   

    String VALIDATE_QUERY = "VALIDATE_QUERY";
    String VALIDATE_QUERY_DEFAULT = "select 1 from dual";   
    
    String DATA_SOURCE_EXCEPTION = "Failed to initialize DataSource/Connection Manager instance, please contact system administrator.";
    
    String ZIPCODE_PATTERN = "\\d{5}(-\\d{4})?";
    String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    
    int MEMBER_TYPE_ADMIN = 0;
    int MEMBER_TYPE_PREMIUM = 1;
    int MEMBER_TYPE_SIMPLE = 2;
    
    int MEMBER_SIGN_IN = 1;
    int MEMBER_SIGN_OUT = 0;
    
    int RENTED_MOVIE_STATUS_RENTED = 0;
    int RENTED_MOVIE_STATUS_RETURNED = 1;

    int INVOICES_STATUS_PENDING = 0;
    int INVOICES_STATUS_PAID = 1;
   
    String SUCCESS = "Success";
    String FAILED = "Failed";
    
    String SELECT_STR = "SELECT ";
    String INSERT_STR = "INSERT INTO ";
    String UPDATE_STR = "UPDATE ";
    String FROM_STR = " FROM ";
    String WHERE_STR = " WHERE ";
    String SET_STR = " SET ";
    String AND_STR = " AND ";
    String EQUAL_STR = " = ";
    String VALUES_STR = ") VALUES (";
    String LEFT_BRACKET_STR = " (";
    String RIGHT_BRACKET_STR = ") ";
    String COMMA_STR = ", ";
    String COMMA_QUOTE_STR = "\", \"";
    String QUOTES_STR = "\"";
    char NEW_LINE = '\n';
    
    int SELL_ITEMS = 0;
    int BUY_ITEMS = 1;
    int SOLD_ITEMS = 2;
    int BOUGHT_ITEMS = 3;
    int CART_ITEMS = 4;
    
    /* Query to get Last Inserted ID */
    /* The ID that was generated is maintained in the server on a per-connection basis. This means that the value returned by the function 
     * to a given client is the first AUTO_INCREMENT value generated for most recent statement affecting an AUTO_INCREMENT column by that client. 
     * This value cannot be affected by other clients, even if they generate AUTO_INCREMENT values of their own. This behavior ensures that each 
     * client can retrieve its own ID without concern for the activity of other clients, and without the need for locks or transactions.
     */
    String DATABASE_SELECT_LAST_ID = "SELECT LAST_INSERT_ID()";
    
    /* Database Schema Name */
    String DATABASE_SCHEMA = "VideoLibrary";
    
    /* Members Table */
    String MEMBERS_TABLE = "VideoLibrary.Members";
    String MEMBERS_MEMBER_ID = "Members.MemberId";
    String MEMBERS_FIRST_NAME = "Members.FirstName";
    String MEMBERS_LAST_NAME = "Members.LastName";
    String MEMBERS_STREET_1 = "Members.Street1";
    String MEMBERS_STREET_2 = "Members.Street2";
    String MEMBERS_CITY = "Members.City";
    String MEMBERS_STATE = "Members.State";
    String MEMBERS_ZIPCODE = "Members.ZipCode";
    String MEMBERS_EMAIL = "Members.Email";
    String MEMBERS_PASSWORD = "Members.Password";
    String MEMBERS_MEMBER_TYPE = "Members.MemberType";
    String MEMBERS_PHONE_NO = "Members.PhoneNo";
    String MEMBERS_MEMBERSHIP_NO = "Members.MembershipNo";
    String MEMBERS_LOGIN_TIME = "Members.LoginTime";
    String MEMBERS_STATUS = "Members.Status";
    String MEMBERS_BALANCE = "Members.Balance";
    String MEMBERS_CREATE_TM = "Members.createTime";
    
    
    String MEMBERS_SELECT_ALL = "SELECT * FROM VideoLibrary.Members";
    String MEMBERS_SELECT = "SELECT * FROM VideoLibrary.Members WHERE Members.Email=?";
    String MEMBERS_SELECT_STATUS = "SELECT Members.Status FROM VideoLibrary.Members where Members.Email=?";
    String MEMBERS_UPDATE = "UPDATE VideoLibrary.Members SET Members.FirstName=?, Members.LastName=?, Members.Street1=?, Members.Street2=?, Members.City=?," +
    		" Members.State=?, Members.ZipCode=?, Members.PhoneNo=?, Members.Password=?, Members.MemberType=? WHERE Members.Email=?";
    String MEMBERS_UPDATE_LOGIN_TM = "UPDATE VideoLibrary.Members SET Members.LoginTime=NOW(), Members.Status=? WHERE Members.MemberId=?";
    String MEMBERS_UPDATE_STATUS = "UPDATE VideoLibrary.Members SET Members.Status=? WHERE Members.Email=?";
    String MEMBERS_UPDATE_BALANCE = "UPDATE VideoLibrary.Members SET Members.Balance=0 WHERE Members.MemberId=?";
    String MEMBERS_INSERT = "INSERT INTO VideoLibrary.Members (Members.FirstName, Members.LastName, Members.Street1, Members.Street2, Members.City, Members.State," +
    		"Members.ZipCode, Members.Email, Members.Password, Members.MemberType, Members.PhoneNo, Members.MembershipNo, Members.Status, Members.Balance, Members.createTime) " +
    		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 0, 0.0, NOW())";
    String MEMBERS_DELETE = "DELETE FROM VideoLibrary.Members WHERE Members.Email=? " +
            "AND Members.MemberId NOT IN (SELECT DISTINCT(RentedMovies.MemberId) FROM VideoLibrary.RentedMovies)";
    
    /* Movies Table */
    String MOVIES_TABLE = "VideoLibrary.Movies";
    String MOVIES_MOVIE_ID = "Movies.MovieId";
    String MOVIES_MOVIE_NAME = "Movies.MovieName";
    String MOVIES_MOVIE_BANNER = "Movies.MovieBanner";
    String MOVIES_RELEASE_DT = "Movies.ReleaseDate";
    String MOVIES_RENT_AMOUNT = "Movies.RentAmount";
    String MOVIES_CATEGORY = "Movies.Category";
    String MOVIES_AVAILABLE_COPIES = "Movies.AvailableCopies";
    String MOVIES_MOVIE_SUMMARY = "Movies.MovieSummary";
    
    String MOVIES_SELECT_ALL = "SELECT * FROM VideoLibrary.Movies";
    String MOVIES_SELECT_ID = "SELECT Movies.MovieId FROM VideoLibrary.Movies WHERE Movies.MovieName=?";
    String MOVIES_SELECT = "SELECT * FROM VideoLibrary.Movies WHERE Movies.MovieName=?";
    String MOVIES_UPDATE = "UPDATE VideoLibrary.Movies SET Movies.MovieName=?, Movies.MovieBanner=?, Movies.ReleaseDate=?, Movies.RentAmount=?, Movies.Category=?," +
    		" Movies.AvailableCopies=?, Movies.MovieSummary=? WHERE Movies.MovieId=?";
    String MOVIES_INSERT = "INSERT INTO VideoLibrary.Movies (Movies.MovieName, Movies.MovieBanner, Movies.ReleaseDate, Movies.RentAmount, Movies.Category, " +
    		"Movies.AvailableCopies, Movies.MovieSummary) VALUES (?, ?, ?, ?, ?, ?, ?)";    
    String MOVIES_DELETE = "DELETE FROM VideoLibrary.Movies WHERE Movies.MovieName=? " +
    		"AND Movies.MovieId NOT IN (SELECT DISTINCT(RentedMovies.MovieId) FROM VideoLibrary.RentedMovies)"; 
    
    /* Rented Movies Table */
    String RENTED_MOVIES_TABLE = "VideoLibrary.RentedMovies";
    String RENTED_MOVIES_RENTED_MOVIE_ID = "RentedMovies.RentedMovieId";
    String RENTED_MOVIES_MOVIE_ID = "RentedMovies.MovieId";
    String RENTED_MOVIES_MEMBER_ID = "RentedMovies.MemberId";
    String RENTED_MOVIES_RENTED_DT = "RentedMovies.RentedDate";
    String RENTED_MOVIES_RENT_STATUS = "RentedMovies.RentStatus";
    String RENTED_MOVIES_RETURNED_DT = "RentedMovies.ReturnedDate";
    String RENTED_MOVIES_RENT_AMOUNT = "RentedMovies.RentAmount";

    String RENTED_MOVIES_SELECT_BY_MEMBER = "SELECT RentedMovies.RentedMovieId, Movies.MovieId, Movies.MovieName, Movies.MovieBanner, Movies.ReleaseDate, Movies.RentAmount, " +
    		"Movies.Category, Movies.AvailableCopies, Movies.MovieSummary, RentedMovies.RentedDate FROM VideoLibrary.Movies, VideoLibrary.RentedMovies WHERE " +
    		"RentedMovies.MovieId=Movies.MovieId AND RentedMovies.MemberId=? AND RentedMovies.RentStatus=?";
    String RENTED_MOVIES_SELECT_BY_MOVIE = "SELECT RentedMovies.RentedMovieId, RentedMovies.RentedDate, Members.MemberId, Members.FirstName, Members.LastName, Members.Street1, " +
    		"Members.Street2, Members.City, Members.State, Members.ZipCode, Members.Email, Members.Password, Members.MemberType, Members.PhoneNo, Members.MembershipNo " +
    		"Members.Balance, Members.createTime FROM VideoLibrary.RentedMovies, VideoLibrary.Members WHERE RentedMovies.MemberId=Members.MemberId " +
    		"AND RentedMovies.MovieId=? AND RentedMovies.RentStatus=?";
    String RENTED_MOVIES_UPDATE = "UPDATE VideoLibrary.RentedMovies SET RentedMovies.RentStatus=?, RentedMovies.ReturnedDate=?, RentedMovies.RentAmount=? WHERE RentedMovies.RentedMovieId=?";
    String RENTED_MOVIES_INSERT = "INSERT INTO VideoLibrary.RentedMovies (RentedMovies.MovieId, RentedMovies.MemberId, RentedMovies.RentedDate, RentedMovies.RentStatus) " +
    		"VALUES (?, ?, NOW(), ?)";       

    /* Invoices Table */
    String INVOICES_TABLE = "VideoLibrary.Invoices";
    String INVOICES_INVOICE_ID = "Invoices.InvoiceId";
    String INVOICES_INVOICE_DATE = "Invoices.InvoiceDate";
    String INVOICES_INVOICE_AMOUNT = "Invoices.InvoiceAmount";
    String INVOICES_MEMBER_ID = "Invoices.MemberId";
    String INVOICES_CREDIT_CARD_NUM = "Invoices.CreditCardNum";
    String INVOICES_CREDIT_CARD_NAME = "Invoices.CreditCardName";
    String INVOICES_PAYMENT_DATE = "Invoices.PaymentDate";
    String INVOICES_INVOICE_STATUS = "Invoices.InvoiceStatus";
    
    String INVOICES_SELECT_BY_MEMBER = "SELECT Invoices.InvoiceId, Invoices.InvoiceDate, Invoices.InvoiceAmount FROM VideoLibrary.Invoices WHERE " +
    		"Invoices.MemberId=?  AND Invoices.InvoiceStatus=?";
    String INVOICES_INSERT = "INSERT INTO VideoLibrary.Invoices (Invoices.InvoiceDate, Invoices.InvoiceAmount, Invoices.MemberId) VALUES (?, ?, ?)";
    String INVOICES_UPDATE = "UPDATE VideoLibrary.Invoices SET Invoices.CreditCardNum=?, Invoices.CreditCardName=?, Invoices.PaymentDate=NOW(), Invoices.InvoiceStatus=? WHERE Invoices.InvoiceId=?";
}
package common;

/**
 * Constants class
 * 
 * @author Team 7
 */
public interface ClientConstants {

    String END_POINT = "http://localhost:8080/CMPE-273-Project/services/VideoLibraySystem?wsld";
    
    char SEPARATOR = '$';
    String SESSION_ID = "userID";
    String MEMBER = "member";

    int SELL_ITEMS = 0;
    int BUY_ITEMS = 1;
    int SOLD_ITEMS = 2;
    int BOUGHT_ITEMS = 3;
    int CART_ITEMS = 4;
    
    int MEMBER_TYPE_ADMIN = 0;
    int MEMBER_TYPE_PREMIUM = 1;
    int MEMBER_TYPE_SIMPLE = 2;    
}
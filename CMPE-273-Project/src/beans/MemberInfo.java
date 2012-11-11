package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

import common.ServiceConstants;

/**
 * User object
 * 
 * @author Team 7
 */
public final class MemberInfo {
    
    private int memberId;
    private String firstName;
    private String lastName;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private String email;
    private String password;
    private int memberType;
    private String phoneNo;
    private String membership;
    private long loginTime;
    private String loginDate;
    private int status;
    private float balance;
    private String createDate;
    
    private boolean active;
    private long accessTime;
    
    private int rentedMovieId;
    private String rentedDate;
    private MovieInfo[] rentedMovies;
    private InvoiceInfo[] pendingInvoices;

    /**
     * Default constructor so that we can use this class in client
     */
    public MemberInfo() {
        active = true;
        accessTime = System.currentTimeMillis();
        rentedDate = null;
        rentedMovieId = -1;
    }
    /**
     * @return the memberId
     */
    public final int getMemberId() {
        return memberId;
    }
    /**
     * @param memberId the memberId to set
     */
    public final void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    /**
     * @return the firstName
     */
    public final String getFirstName() {
        return firstName;
    }
    /**
     * @param firstName the firstName to set
     */
    public final void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * @return the lastName
     */
    public final String getLastName() {
        return lastName;
    }
    /**
     * @param lastName the lastName to set
     */
    public final void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * @return the street1
     */
    public final String getStreet1() {
        return street1;
    }
    /**
     * @param street1 the street1 to set
     */
    public final void setStreet1(String street1) {
        this.street1 = street1;
    }
    /**
     * @return the street2
     */
    public final String getStreet2() {
        return street2;
    }
    /**
     * @param street2 the street2 to set
     */
    public final void setStreet2(String street2) {
        this.street2 = street2;
    }
    /**
     * @return the city
     */
    public final String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public final void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the state
     */
    public final String getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public final void setState(String state) {
        this.state = state;
    }
    /**
     * @return the zipcode
     */
    public final String getZipcode() {
        return zipcode;
    }
    /**
     * @param zipcode the zipcode to set
     */
    public final void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    /**
     * @return the email
     */
    public final String getEmail() {
        return email;
    }
    /**
     * @param email the email to set
     */
    public final void setEmail(String email) {
        this.email = email;
    }
    /**
     * @return the password
     */
    public final String getPassword() {
        return password;
    }
    /**
     * @param password the password to set
     */
    public final void setPassword(String password) {
        this.password = password;
    }
    /**
     * @return the memberType
     */
    public final int getMemberType() {
        return memberType;
    }
    /**
     * @param memberType the memberType to set
     */
    public final void setMemberType(int memberType) {
        this.memberType = memberType;
    }
    /**
     * @return the phoneNo
     */
    public final String getPhoneNo() {
        return phoneNo;
    }
    /**
     * @param phoneNo the phoneNo to set
     */
    public final void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
    /**
     * @return the membership
     */
    public final String getMembership() {
        return membership;
    }
    /**
     * @param membership the membership to set
     */
    public final void setMembership(String membership) {
        this.membership = membership;
    }
    /**
     * @return
     */
    public final String getLoginDate() {
        return loginDate;
    }
    /**
     * @return the loginTime
     */
    public final long getLoginTime() {
        return loginTime;
    }
    /**
     * @param loginTime the loginTime to set
     */
    public final void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
        this.loginDate = new SimpleDateFormat(ServiceConstants.DATE_FORMAT).format(new Date(this.loginTime));
    }
    /**
     * @return the createDate
     */
    public final String getCreateDate() {
        return createDate;
    }
    /**
     * @param createDate the createDate to set
     */
    public final void setCreateDate(String createDate) {
        this.createDate = createDate;
    }    
    /**
     * @return the status
     */
    public final int getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public final void setStatus(int status) {
        this.status = status;
    }
    /**
     * @return the balance
     */
    public final float getBalance() {
        return balance;
    }
    /**
     * @param balance the balance to set
     */
    public final void setBalance(float balance) {
        this.balance = balance;
    }
    /**
     * @return the accessTime
     */
    public final long getAccessTime() {
        return accessTime;
    }
    /**
     * @param accessTime the accessTime to set
     */
    public final void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }  
    /**
     * @return the dbState
     */
    public final boolean isActive() {
        return active;
    }
    /**
     * @param dbState the dbState to set
     */
    public final void setActive(boolean dbState) {
        this.active = dbState;
    }    
    /**
     * @return true/false if session has expired
     */
    public boolean isExpired() {
        return ((System.currentTimeMillis() - accessTime) > ServiceConstants.SESSION_TIMEOUT); 
    }
    /**
     * @return the rentedMovies
     */
    public final MovieInfo[] getRentedMovies() {
        return rentedMovies;
    }
    /**
     * @param rentedMovies the rentedMovies to set
     */
    public final void setRentedMovies(MovieInfo[] rentedMovies) {
        this.rentedMovies = rentedMovies;
    }    
    /**
     * @return the pendingInvoices
     */
    public final InvoiceInfo[] getPendingInvoices() {
        return pendingInvoices;
    }
    /**
     * @param pendingInvoices the pendingInvoices to set
     */
    public final void setPendingInvoices(InvoiceInfo[] pendingInvoices) {
        this.pendingInvoices = pendingInvoices;
    } 
    /**
     * @return the rentedMovieId
     */
    public final int getRentedMovieId() {
        return rentedMovieId;
    }
    /**
     * @param rentedMovieId the rentedMovieId to set
     */
    public final void setRentedMovieId(int rentedMovieId) {
        this.rentedMovieId = rentedMovieId;
    }    
    /**
     * @return the rentedDate
     */
    public final String getRentedDate() {
        return rentedDate;
    }
    /**
     * @param rentedDate the rentedDate to set
     */
    public final void setRentedDate(String rentedDate) {
        this.rentedDate = rentedDate;
    }    
}
package beans;

/**
 * Item class - object of this gets serialized / deserialized with client
 * Use only String and primitive datatypes in this class.
 * 
 * @author Team 7
 */
public final class MovieInfo {
    private int movieId;
    private String movieName;
    private String movieBanner;
    private String releaseDate;
    private float rentAmount;
    private String category;
    private String movieSummary;
    private int availableCopies;

    private int rentedMovieId;
    private String rentedDate;
    private MemberInfo[] rentedMembers;

    public MovieInfo() {
        rentedDate = null;
        rentedMovieId = -1;
    }
    /**
     * @return the movieId
     */
    public final int getMovieId() {
        return movieId;
    }
    /**
     * @param movieId the movieId to set
     */
    public final void setMovieId(int movieId) {
        this.movieId = movieId;
    }
    /**
     * @return the movieName
     */
    public final String getMovieName() {
        return movieName;
    }
    /**
     * @param movieName the movieName to set
     */
    public final void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    /**
     * @return the movieBanner
     */
    public final String getMovieBanner() {
        return movieBanner;
    }
    /**
     * @param movieBanner the movieBanner to set
     */
    public final void setMovieBanner(String movieBanner) {
        this.movieBanner = movieBanner;
    }
    /**
     * @return the releaseDate
     */
    public final String getReleaseDate() {
        return releaseDate;
    }
    /**
     * @param releaseDate the releaseDate to set
     */
    public final void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    /**
     * @return the rentAmount
     */
    public final float getRentAmount() {
        return rentAmount;
    }
    /**
     * @param rentAmount the rentAmount to set
     */
    public final void setRentAmount(float rentAmount) {
        this.rentAmount = rentAmount;
    }
    /**
     * @return the category
     */
    public final String getCategory() {
        return category;
    }
    /**
     * @param category the category to set
     */
    public final void setCategory(String category) {
        this.category = category;
    }
    /**
     * @return the availableCopies
     */
    public final int getAvailableCopies() {
        return availableCopies;
    }
    /**
     * @param availableCopies the availableCopies to set
     */
    public final void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    /**
     * @return the movieSummary
     */
    public final String getMovieSummary() {
        return movieSummary;
    }
    /**
     * @param movieSummary the movieSummary to set
     */
    public final void setMovieSummary(String movieSummary) {
        this.movieSummary = movieSummary;
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
    /**
     * @return the rentedMembers
     */
    public final MemberInfo[] getRentedMembers() {
        return rentedMembers;
    }
    /**
     * @param rentedMembers the rentedMembers to set
     */
    public final void setRentedMembers(MemberInfo[] rentedMembers) {
        this.rentedMembers = rentedMembers;
    }  
}

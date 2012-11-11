/**
 * MovieInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class MovieInfo  implements java.io.Serializable {
    private int availableCopies;

    private java.lang.String category;

    private java.lang.String movieBanner;

    private int movieId;

    private java.lang.String movieName;

    private java.lang.String movieSummary;

    private java.lang.String releaseDate;

    private float rentAmount;

    private int rentedCopies;

    private java.lang.String rentedDate;

    private int rentedMovieId;

    public MovieInfo() {
    }

    public MovieInfo(
           int availableCopies,
           java.lang.String category,
           java.lang.String movieBanner,
           int movieId,
           java.lang.String movieName,
           java.lang.String movieSummary,
           java.lang.String releaseDate,
           float rentAmount,
           int rentedCopies,
           java.lang.String rentedDate,
           int rentedMovieId) {
           this.availableCopies = availableCopies;
           this.category = category;
           this.movieBanner = movieBanner;
           this.movieId = movieId;
           this.movieName = movieName;
           this.movieSummary = movieSummary;
           this.releaseDate = releaseDate;
           this.rentAmount = rentAmount;
           this.rentedCopies = rentedCopies;
           this.rentedDate = rentedDate;
           this.rentedMovieId = rentedMovieId;
    }


    /**
     * Gets the availableCopies value for this MovieInfo.
     * 
     * @return availableCopies
     */
    public int getAvailableCopies() {
        return availableCopies;
    }


    /**
     * Sets the availableCopies value for this MovieInfo.
     * 
     * @param availableCopies
     */
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }


    /**
     * Gets the category value for this MovieInfo.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this MovieInfo.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the movieBanner value for this MovieInfo.
     * 
     * @return movieBanner
     */
    public java.lang.String getMovieBanner() {
        return movieBanner;
    }


    /**
     * Sets the movieBanner value for this MovieInfo.
     * 
     * @param movieBanner
     */
    public void setMovieBanner(java.lang.String movieBanner) {
        this.movieBanner = movieBanner;
    }


    /**
     * Gets the movieId value for this MovieInfo.
     * 
     * @return movieId
     */
    public int getMovieId() {
        return movieId;
    }


    /**
     * Sets the movieId value for this MovieInfo.
     * 
     * @param movieId
     */
    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }


    /**
     * Gets the movieName value for this MovieInfo.
     * 
     * @return movieName
     */
    public java.lang.String getMovieName() {
        return movieName;
    }


    /**
     * Sets the movieName value for this MovieInfo.
     * 
     * @param movieName
     */
    public void setMovieName(java.lang.String movieName) {
        this.movieName = movieName;
    }


    /**
     * Gets the movieSummary value for this MovieInfo.
     * 
     * @return movieSummary
     */
    public java.lang.String getMovieSummary() {
        return movieSummary;
    }


    /**
     * Sets the movieSummary value for this MovieInfo.
     * 
     * @param movieSummary
     */
    public void setMovieSummary(java.lang.String movieSummary) {
        this.movieSummary = movieSummary;
    }


    /**
     * Gets the releaseDate value for this MovieInfo.
     * 
     * @return releaseDate
     */
    public java.lang.String getReleaseDate() {
        return releaseDate;
    }


    /**
     * Sets the releaseDate value for this MovieInfo.
     * 
     * @param releaseDate
     */
    public void setReleaseDate(java.lang.String releaseDate) {
        this.releaseDate = releaseDate;
    }


    /**
     * Gets the rentAmount value for this MovieInfo.
     * 
     * @return rentAmount
     */
    public float getRentAmount() {
        return rentAmount;
    }


    /**
     * Sets the rentAmount value for this MovieInfo.
     * 
     * @param rentAmount
     */
    public void setRentAmount(float rentAmount) {
        this.rentAmount = rentAmount;
    }


    /**
     * Gets the rentedCopies value for this MovieInfo.
     * 
     * @return rentedCopies
     */
    public int getRentedCopies() {
        return rentedCopies;
    }


    /**
     * Sets the rentedCopies value for this MovieInfo.
     * 
     * @param rentedCopies
     */
    public void setRentedCopies(int rentedCopies) {
        this.rentedCopies = rentedCopies;
    }


    /**
     * Gets the rentedDate value for this MovieInfo.
     * 
     * @return rentedDate
     */
    public java.lang.String getRentedDate() {
        return rentedDate;
    }


    /**
     * Sets the rentedDate value for this MovieInfo.
     * 
     * @param rentedDate
     */
    public void setRentedDate(java.lang.String rentedDate) {
        this.rentedDate = rentedDate;
    }


    /**
     * Gets the rentedMovieId value for this MovieInfo.
     * 
     * @return rentedMovieId
     */
    public int getRentedMovieId() {
        return rentedMovieId;
    }


    /**
     * Sets the rentedMovieId value for this MovieInfo.
     * 
     * @param rentedMovieId
     */
    public void setRentedMovieId(int rentedMovieId) {
        this.rentedMovieId = rentedMovieId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MovieInfo)) return false;
        MovieInfo other = (MovieInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.availableCopies == other.getAvailableCopies() &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.movieBanner==null && other.getMovieBanner()==null) || 
             (this.movieBanner!=null &&
              this.movieBanner.equals(other.getMovieBanner()))) &&
            this.movieId == other.getMovieId() &&
            ((this.movieName==null && other.getMovieName()==null) || 
             (this.movieName!=null &&
              this.movieName.equals(other.getMovieName()))) &&
            ((this.movieSummary==null && other.getMovieSummary()==null) || 
             (this.movieSummary!=null &&
              this.movieSummary.equals(other.getMovieSummary()))) &&
            ((this.releaseDate==null && other.getReleaseDate()==null) || 
             (this.releaseDate!=null &&
              this.releaseDate.equals(other.getReleaseDate()))) &&
            this.rentAmount == other.getRentAmount() &&
            this.rentedCopies == other.getRentedCopies() &&
            ((this.rentedDate==null && other.getRentedDate()==null) || 
             (this.rentedDate!=null &&
              this.rentedDate.equals(other.getRentedDate()))) &&
            this.rentedMovieId == other.getRentedMovieId();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getAvailableCopies();
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getMovieBanner() != null) {
            _hashCode += getMovieBanner().hashCode();
        }
        _hashCode += getMovieId();
        if (getMovieName() != null) {
            _hashCode += getMovieName().hashCode();
        }
        if (getMovieSummary() != null) {
            _hashCode += getMovieSummary().hashCode();
        }
        if (getReleaseDate() != null) {
            _hashCode += getReleaseDate().hashCode();
        }
        _hashCode += new Float(getRentAmount()).hashCode();
        _hashCode += getRentedCopies();
        if (getRentedDate() != null) {
            _hashCode += getRentedDate().hashCode();
        }
        _hashCode += getRentedMovieId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MovieInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "MovieInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availableCopies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "availableCopies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieBanner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "movieBanner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "movieId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "movieName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("movieSummary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "movieSummary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("releaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "releaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "rentAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentedCopies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "rentedCopies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "rentedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentedMovieId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "rentedMovieId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

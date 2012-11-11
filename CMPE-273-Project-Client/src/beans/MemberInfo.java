/**
 * MemberInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package beans;

public class MemberInfo  implements java.io.Serializable {
    private long accessTime;

    private boolean active;

    private float balance;

    private java.lang.String city;

    private java.lang.String createDate;

    private java.lang.String email;

    private boolean expired;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String loginDate;

    private long loginTime;

    private int memberId;

    private int memberType;

    private java.lang.String membership;

    private java.lang.String password;

    private beans.InvoiceInfo[] pendingInvoices;

    private java.lang.String phoneNo;

    private beans.MovieInfo[] rentedMovies;

    private java.lang.String state;

    private int status;

    private java.lang.String street1;

    private java.lang.String street2;

    private java.lang.String zipcode;

    public MemberInfo() {
    }

    public MemberInfo(
           long accessTime,
           boolean active,
           float balance,
           java.lang.String city,
           java.lang.String createDate,
           java.lang.String email,
           boolean expired,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String loginDate,
           long loginTime,
           int memberId,
           int memberType,
           java.lang.String membership,
           java.lang.String password,
           beans.InvoiceInfo[] pendingInvoices,
           java.lang.String phoneNo,
           beans.MovieInfo[] rentedMovies,
           java.lang.String state,
           int status,
           java.lang.String street1,
           java.lang.String street2,
           java.lang.String zipcode) {
           this.accessTime = accessTime;
           this.active = active;
           this.balance = balance;
           this.city = city;
           this.createDate = createDate;
           this.email = email;
           this.expired = expired;
           this.firstName = firstName;
           this.lastName = lastName;
           this.loginDate = loginDate;
           this.loginTime = loginTime;
           this.memberId = memberId;
           this.memberType = memberType;
           this.membership = membership;
           this.password = password;
           this.pendingInvoices = pendingInvoices;
           this.phoneNo = phoneNo;
           this.rentedMovies = rentedMovies;
           this.state = state;
           this.status = status;
           this.street1 = street1;
           this.street2 = street2;
           this.zipcode = zipcode;
    }


    /**
     * Gets the accessTime value for this MemberInfo.
     * 
     * @return accessTime
     */
    public long getAccessTime() {
        return accessTime;
    }


    /**
     * Sets the accessTime value for this MemberInfo.
     * 
     * @param accessTime
     */
    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }


    /**
     * Gets the active value for this MemberInfo.
     * 
     * @return active
     */
    public boolean isActive() {
        return active;
    }


    /**
     * Sets the active value for this MemberInfo.
     * 
     * @param active
     */
    public void setActive(boolean active) {
        this.active = active;
    }


    /**
     * Gets the balance value for this MemberInfo.
     * 
     * @return balance
     */
    public float getBalance() {
        return balance;
    }


    /**
     * Sets the balance value for this MemberInfo.
     * 
     * @param balance
     */
    public void setBalance(float balance) {
        this.balance = balance;
    }


    /**
     * Gets the city value for this MemberInfo.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this MemberInfo.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the createDate value for this MemberInfo.
     * 
     * @return createDate
     */
    public java.lang.String getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this MemberInfo.
     * 
     * @param createDate
     */
    public void setCreateDate(java.lang.String createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the email value for this MemberInfo.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this MemberInfo.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the expired value for this MemberInfo.
     * 
     * @return expired
     */
    public boolean isExpired() {
        return expired;
    }


    /**
     * Sets the expired value for this MemberInfo.
     * 
     * @param expired
     */
    public void setExpired(boolean expired) {
        this.expired = expired;
    }


    /**
     * Gets the firstName value for this MemberInfo.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this MemberInfo.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this MemberInfo.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this MemberInfo.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the loginDate value for this MemberInfo.
     * 
     * @return loginDate
     */
    public java.lang.String getLoginDate() {
        return loginDate;
    }


    /**
     * Sets the loginDate value for this MemberInfo.
     * 
     * @param loginDate
     */
    public void setLoginDate(java.lang.String loginDate) {
        this.loginDate = loginDate;
    }


    /**
     * Gets the loginTime value for this MemberInfo.
     * 
     * @return loginTime
     */
    public long getLoginTime() {
        return loginTime;
    }


    /**
     * Sets the loginTime value for this MemberInfo.
     * 
     * @param loginTime
     */
    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }


    /**
     * Gets the memberId value for this MemberInfo.
     * 
     * @return memberId
     */
    public int getMemberId() {
        return memberId;
    }


    /**
     * Sets the memberId value for this MemberInfo.
     * 
     * @param memberId
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }


    /**
     * Gets the memberType value for this MemberInfo.
     * 
     * @return memberType
     */
    public int getMemberType() {
        return memberType;
    }


    /**
     * Sets the memberType value for this MemberInfo.
     * 
     * @param memberType
     */
    public void setMemberType(int memberType) {
        this.memberType = memberType;
    }


    /**
     * Gets the membership value for this MemberInfo.
     * 
     * @return membership
     */
    public java.lang.String getMembership() {
        return membership;
    }


    /**
     * Sets the membership value for this MemberInfo.
     * 
     * @param membership
     */
    public void setMembership(java.lang.String membership) {
        this.membership = membership;
    }


    /**
     * Gets the password value for this MemberInfo.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this MemberInfo.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the pendingInvoices value for this MemberInfo.
     * 
     * @return pendingInvoices
     */
    public beans.InvoiceInfo[] getPendingInvoices() {
        return pendingInvoices;
    }


    /**
     * Sets the pendingInvoices value for this MemberInfo.
     * 
     * @param pendingInvoices
     */
    public void setPendingInvoices(beans.InvoiceInfo[] pendingInvoices) {
        this.pendingInvoices = pendingInvoices;
    }


    /**
     * Gets the phoneNo value for this MemberInfo.
     * 
     * @return phoneNo
     */
    public java.lang.String getPhoneNo() {
        return phoneNo;
    }


    /**
     * Sets the phoneNo value for this MemberInfo.
     * 
     * @param phoneNo
     */
    public void setPhoneNo(java.lang.String phoneNo) {
        this.phoneNo = phoneNo;
    }


    /**
     * Gets the rentedMovies value for this MemberInfo.
     * 
     * @return rentedMovies
     */
    public beans.MovieInfo[] getRentedMovies() {
        return rentedMovies;
    }


    /**
     * Sets the rentedMovies value for this MemberInfo.
     * 
     * @param rentedMovies
     */
    public void setRentedMovies(beans.MovieInfo[] rentedMovies) {
        this.rentedMovies = rentedMovies;
    }


    /**
     * Gets the state value for this MemberInfo.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this MemberInfo.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the status value for this MemberInfo.
     * 
     * @return status
     */
    public int getStatus() {
        return status;
    }


    /**
     * Sets the status value for this MemberInfo.
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }


    /**
     * Gets the street1 value for this MemberInfo.
     * 
     * @return street1
     */
    public java.lang.String getStreet1() {
        return street1;
    }


    /**
     * Sets the street1 value for this MemberInfo.
     * 
     * @param street1
     */
    public void setStreet1(java.lang.String street1) {
        this.street1 = street1;
    }


    /**
     * Gets the street2 value for this MemberInfo.
     * 
     * @return street2
     */
    public java.lang.String getStreet2() {
        return street2;
    }


    /**
     * Sets the street2 value for this MemberInfo.
     * 
     * @param street2
     */
    public void setStreet2(java.lang.String street2) {
        this.street2 = street2;
    }


    /**
     * Gets the zipcode value for this MemberInfo.
     * 
     * @return zipcode
     */
    public java.lang.String getZipcode() {
        return zipcode;
    }


    /**
     * Sets the zipcode value for this MemberInfo.
     * 
     * @param zipcode
     */
    public void setZipcode(java.lang.String zipcode) {
        this.zipcode = zipcode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MemberInfo)) return false;
        MemberInfo other = (MemberInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.accessTime == other.getAccessTime() &&
            this.active == other.isActive() &&
            this.balance == other.getBalance() &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            this.expired == other.isExpired() &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.loginDate==null && other.getLoginDate()==null) || 
             (this.loginDate!=null &&
              this.loginDate.equals(other.getLoginDate()))) &&
            this.loginTime == other.getLoginTime() &&
            this.memberId == other.getMemberId() &&
            this.memberType == other.getMemberType() &&
            ((this.membership==null && other.getMembership()==null) || 
             (this.membership!=null &&
              this.membership.equals(other.getMembership()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.pendingInvoices==null && other.getPendingInvoices()==null) || 
             (this.pendingInvoices!=null &&
              java.util.Arrays.equals(this.pendingInvoices, other.getPendingInvoices()))) &&
            ((this.phoneNo==null && other.getPhoneNo()==null) || 
             (this.phoneNo!=null &&
              this.phoneNo.equals(other.getPhoneNo()))) &&
            ((this.rentedMovies==null && other.getRentedMovies()==null) || 
             (this.rentedMovies!=null &&
              java.util.Arrays.equals(this.rentedMovies, other.getRentedMovies()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            this.status == other.getStatus() &&
            ((this.street1==null && other.getStreet1()==null) || 
             (this.street1!=null &&
              this.street1.equals(other.getStreet1()))) &&
            ((this.street2==null && other.getStreet2()==null) || 
             (this.street2!=null &&
              this.street2.equals(other.getStreet2()))) &&
            ((this.zipcode==null && other.getZipcode()==null) || 
             (this.zipcode!=null &&
              this.zipcode.equals(other.getZipcode())));
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
        _hashCode += new Long(getAccessTime()).hashCode();
        _hashCode += (isActive() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Float(getBalance()).hashCode();
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        _hashCode += (isExpired() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getLoginDate() != null) {
            _hashCode += getLoginDate().hashCode();
        }
        _hashCode += new Long(getLoginTime()).hashCode();
        _hashCode += getMemberId();
        _hashCode += getMemberType();
        if (getMembership() != null) {
            _hashCode += getMembership().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getPendingInvoices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPendingInvoices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPendingInvoices(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPhoneNo() != null) {
            _hashCode += getPhoneNo().hashCode();
        }
        if (getRentedMovies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRentedMovies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRentedMovies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        _hashCode += getStatus();
        if (getStreet1() != null) {
            _hashCode += getStreet1().hashCode();
        }
        if (getStreet2() != null) {
            _hashCode += getStreet2().hashCode();
        }
        if (getZipcode() != null) {
            _hashCode += getZipcode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MemberInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://beans", "MemberInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "accessTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("active");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "active"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "balance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "float"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "city"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "createDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "expired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "firstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "lastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "loginDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loginTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "loginTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "memberId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memberType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "memberType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("membership");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "membership"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pendingInvoices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "pendingInvoices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://beans", "InvoiceInfo"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://DefaultNamespace", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phoneNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "phoneNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rentedMovies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "rentedMovies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://beans", "MovieInfo"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://DefaultNamespace", "item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "street1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("street2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "street2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zipcode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://beans", "zipcode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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

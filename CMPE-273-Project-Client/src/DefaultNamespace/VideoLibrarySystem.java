/**
 * VideoLibrarySystem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public interface VideoLibrarySystem extends java.rmi.Remote {
    public beans.MemberInfo[] getMembers(java.lang.String email) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException;
    public void addMember(java.lang.String firstname, java.lang.String lastname, java.lang.String street1, java.lang.String street2, java.lang.String city, java.lang.String state, java.lang.String zipCode, java.lang.String email, java.lang.String password, java.lang.String phoneNo, boolean premium) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException;
    public beans.MemberInfo signIn(java.lang.String email, java.lang.String password) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException;
    public void signOut(java.lang.String email) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException;
    public void deleteMember(beans.MemberInfo memberInfo) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException;
    public void editMember(beans.MemberInfo memberInfo) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException;
    public beans.MovieInfo addMovie(java.lang.String email, java.lang.String name, java.lang.String banner, long releaseTime, float rent, java.lang.String category, int copies, java.lang.String summary) throws java.rmi.RemoteException, exceptions.DataSourceException;
    public void editMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException;
    public void deleteMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException;
    public beans.MovieInfo[] getMovies(java.lang.String email) throws java.rmi.RemoteException;
    public beans.MemberInfo rentMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException;
    public beans.MemberInfo returnMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException;
    public beans.MemberInfo payBalance(java.lang.String email, float amount, java.lang.String ccNum, java.lang.String ccName) throws java.rmi.RemoteException;
    public beans.MemberInfo generateBill(java.lang.String email) throws java.rmi.RemoteException;
}

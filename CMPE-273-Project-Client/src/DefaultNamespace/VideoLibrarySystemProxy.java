package DefaultNamespace;

public class VideoLibrarySystemProxy implements DefaultNamespace.VideoLibrarySystem {
  private String _endpoint = null;
  private DefaultNamespace.VideoLibrarySystem videoLibrarySystem = null;
  
  public VideoLibrarySystemProxy() {
    _initVideoLibrarySystemProxy();
  }
  
  public VideoLibrarySystemProxy(String endpoint) {
    _endpoint = endpoint;
    _initVideoLibrarySystemProxy();
  }
  
  private void _initVideoLibrarySystemProxy() {
    try {
      videoLibrarySystem = (new DefaultNamespace.VideoLibrarySystemServiceLocator()).getVideoLibrarySystem();
      if (videoLibrarySystem != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)videoLibrarySystem)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)videoLibrarySystem)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (videoLibrarySystem != null)
      ((javax.xml.rpc.Stub)videoLibrarySystem)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public DefaultNamespace.VideoLibrarySystem getVideoLibrarySystem() {
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem;
  }
  
  public beans.MemberInfo signIn(java.lang.String email, java.lang.String password) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.signIn(email, password);
  }
  
  public void signOut(java.lang.String email) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.signOut(email);
  }
  
  public beans.MovieInfo addMovie(java.lang.String email, java.lang.String name, java.lang.String banner, long releaseTime, float rent, java.lang.String category, int copies, java.lang.String summary) throws java.rmi.RemoteException, exceptions.DataSourceException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.addMovie(email, name, banner, releaseTime, rent, category, copies, summary);
  }
  
  public beans.MemberInfo payBalance(java.lang.String email, float amount, java.lang.String ccNum, java.lang.String ccName) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.payBalance(email, amount, ccNum, ccName);
  }
  
  public beans.MemberInfo generateBill(java.lang.String email) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.generateBill(email);
  }
  
  public beans.MemberInfo[] getMembers(java.lang.String email) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.getMembers(email);
  }
  
  public void addMember(java.lang.String firstname, java.lang.String lastname, java.lang.String street1, java.lang.String street2, java.lang.String city, java.lang.String state, java.lang.String zipCode, java.lang.String email, java.lang.String password, java.lang.String phoneNo, boolean premium) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.addMember(firstname, lastname, street1, street2, city, state, zipCode, email, password, phoneNo, premium);
  }
  
  public void deleteMember(beans.MemberInfo memberInfo) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.deleteMember(memberInfo);
  }
  
  public void editMember(beans.MemberInfo memberInfo) throws java.rmi.RemoteException, exceptions.DataSourceException, exceptions.CustomException, exceptions.InvalidArgumentException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.editMember(memberInfo);
  }
  
  public void editMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.editMovie(email, movie);
  }
  
  public void deleteMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    videoLibrarySystem.deleteMovie(email, movie);
  }
  
  public beans.MovieInfo[] getMovies(java.lang.String email) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.getMovies(email);
  }
  
  public beans.MemberInfo rentMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.rentMovie(email, movie);
  }
  
  public beans.MemberInfo returnMovie(java.lang.String email, beans.MovieInfo movie) throws java.rmi.RemoteException{
    if (videoLibrarySystem == null)
      _initVideoLibrarySystemProxy();
    return videoLibrarySystem.returnMovie(email, movie);
  }
  
  
}
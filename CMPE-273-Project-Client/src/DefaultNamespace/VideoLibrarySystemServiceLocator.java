/**
 * VideoLibrarySystemServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package DefaultNamespace;

public class VideoLibrarySystemServiceLocator extends org.apache.axis.client.Service implements DefaultNamespace.VideoLibrarySystemService {

    public VideoLibrarySystemServiceLocator() {
    }


    public VideoLibrarySystemServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VideoLibrarySystemServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for VideoLibrarySystem
    private java.lang.String VideoLibrarySystem_address = "http://localhost:8080/VideoLibraryServer/services/VideoLibrarySystem";

    public java.lang.String getVideoLibrarySystemAddress() {
        return VideoLibrarySystem_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String VideoLibrarySystemWSDDServiceName = "VideoLibrarySystem";

    public java.lang.String getVideoLibrarySystemWSDDServiceName() {
        return VideoLibrarySystemWSDDServiceName;
    }

    public void setVideoLibrarySystemWSDDServiceName(java.lang.String name) {
        VideoLibrarySystemWSDDServiceName = name;
    }

    public DefaultNamespace.VideoLibrarySystem getVideoLibrarySystem() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(VideoLibrarySystem_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getVideoLibrarySystem(endpoint);
    }

    public DefaultNamespace.VideoLibrarySystem getVideoLibrarySystem(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            DefaultNamespace.VideoLibrarySystemSoapBindingStub _stub = new DefaultNamespace.VideoLibrarySystemSoapBindingStub(portAddress, this);
            _stub.setPortName(getVideoLibrarySystemWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setVideoLibrarySystemEndpointAddress(java.lang.String address) {
        VideoLibrarySystem_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (DefaultNamespace.VideoLibrarySystem.class.isAssignableFrom(serviceEndpointInterface)) {
                DefaultNamespace.VideoLibrarySystemSoapBindingStub _stub = new DefaultNamespace.VideoLibrarySystemSoapBindingStub(new java.net.URL(VideoLibrarySystem_address), this);
                _stub.setPortName(getVideoLibrarySystemWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("VideoLibrarySystem".equals(inputPortName)) {
            return getVideoLibrarySystem();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://DefaultNamespace", "VideoLibrarySystemService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://DefaultNamespace", "VideoLibrarySystem"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("VideoLibrarySystem".equals(portName)) {
            setVideoLibrarySystemEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyminingbuddy.client;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import kittyMiningBuddyLibrary.MiningBuddyServerDetails;
import kittyMiningBuddyLibrary.MiningBuddyUserDetails;
import kittyMiningBuddyLibrary.enums.ConnectStatus;
import kittyMiningBuddyLibrary.interfaces.IXMLPersistable;

/**
 * This is the settings class for the mining buddy client
 *
 * @author Roy
 */
public class KMBSettings implements IXMLPersistable<KMBSettings> {

    private Socket connSocket = null;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private MiningBuddyUserDetails UserDetails;
    private MiningBuddyServerDetails ServerDetails;
    private ConnectStatus ConnStatus = ConnectStatus.DISCONNECTED;
    public static final String PROP_CONNECTIONSTATUS = "ConnectionStatus";

    
    public MiningBuddyServerDetails getServerDetails(){
        return ServerDetails;
    }
    
    public void setServerDetails(MiningBuddyServerDetails objNewServer){
        if(null != objNewServer){
            this.ServerDetails.setInternetAddress(objNewServer.getInternetAddress());
            this.ServerDetails.setPort(objNewServer.getPort());
        }
    }
    /**
     * Get the value of ConnStatus
     *
     * @return the value of ConnStatus
     */
    public ConnectStatus getConnectionStatus() {
        return ConnStatus;
    }

    /**
     * Set the value of ConnStatus
     *
     * @param ConnStatus new value of ConnStatus
     */
    public void setConnectionStatus(ConnectStatus ConnectionStatus) {
        ConnectStatus oldConnectionStatus = this.ConnStatus;
        this.ConnStatus = ConnectionStatus;
        propertyChangeSupport.firePropertyChange(PROP_CONNECTIONSTATUS, oldConnectionStatus, ConnectionStatus);
    }
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    
    public KMBSettings(){
        UserDetails = new MiningBuddyUserDetails("UNKNOWN", "UNKNOWN");
        ServerDetails = new MiningBuddyServerDetails();
    }

    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public Integer getPort() {
        return this.ServerDetails.getPort();
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     */
    public void setPort(Integer port) {
        this.ServerDetails.setPort(port);
    }

    /**
     * Get the value of connSocket
     *
     * @return the value of connSocket
     */
    public Socket getConnSocket() {
        return connSocket;
    }

    /**
     * Set the value of connSocket
     *
     * @param connSocket new value of connSocket
     */
    public void setConnSocket(Socket connSocket) throws IOException {
        this.connSocket = connSocket;
        if (null != this.connSocket && this.connSocket.isConnected() && !this.connSocket.isClosed()) {
            try {
                this.getObjectOutputStream();
                this.getObjectInputStream();
                this.setConnectionStatus(ConnectStatus.CONNECTED);
            } catch (Exception ex) {
                System.out.println("Exception Message: " + ex.getMessage());
            }
        }
    }

    /**
     * @return the InternetAddress
     */
    public InetAddress getInternetAddress() {
        return this.ServerDetails.getInternetAddress();
    }

    /**
     * @param InternetAddress the InternetAddress to set
     */
    public void setInternetAddress(InetAddress InternetAddress) {
        this.ServerDetails.setInternetAddress(InternetAddress);
    }

    public ObjectInputStream getObjectInputStream() throws IOException {
        if (null == inStream && null != this.connSocket && this.connSocket.isConnected() && !this.connSocket.isClosed()) {
            inStream = new ObjectInputStream(this.connSocket.getInputStream());
        }
        return inStream;
    }

    public ObjectOutputStream getObjectOutputStream() throws IOException {
        if (null == inStream && null != this.connSocket && this.connSocket.isConnected() && !this.connSocket.isClosed()) {
            outStream = new ObjectOutputStream(this.connSocket.getOutputStream());
            outStream.flush();
        }
        return outStream;
    }

    public void closeSocket() {
        if (null != this.connSocket && this.connSocket.isConnected() && !this.connSocket.isClosed()) {
            try {
                this.connSocket.close();
                this.inStream = null;
                this.outStream = null;
                this.connSocket = null;
            } catch (IOException ex) {
                Logger.getLogger(KMBSettings.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * @return the UserDetails
     */
    public MiningBuddyUserDetails getUserDetails() {
        return UserDetails;
    }

    /**
     * @param UserDetails the UserDetails to set
     */
    public void setUserDetails(MiningBuddyUserDetails UserDetails) {
        this.UserDetails = UserDetails;
    }

    @Override
    public Boolean writeXMLToOutputStream(OutputStream exOutStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public KMBSettings readObjectFromInputStream(InputStream exInStream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

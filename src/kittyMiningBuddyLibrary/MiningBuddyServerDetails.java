/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.net.InetAddress;

/**
 * This class encapsulates the details required to connect to a KittyMiningBuddy
 * Server
 * @author Roy
 */
public class MiningBuddyServerDetails {
    
    private Integer port = 15000;
    public static final String PROP_PORT = "port";
    private InetAddress internetAddress = InetAddress.getLoopbackAddress();
    public static final String PROP_INTERNETADDRESS = "internetAddress";

    /**
     * Get the value of internetAddress
     *
     * @return the value of internetAddress
     */
    public InetAddress getInternetAddress() {
        return internetAddress;
    }

    /**
     * Set the value of internetAddress
     *
     * @param internetAddress new value of internetAddress
     */
    public void setInternetAddress(InetAddress internetAddress) {
        InetAddress oldInternetAddress = this.internetAddress;
        this.internetAddress = internetAddress;
        propertyChangeSupport.firePropertyChange(PROP_INTERNETADDRESS, oldInternetAddress, internetAddress);
    }


    /**
     * Get the value of port
     *
     * @return the value of port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * Set the value of port
     *
     * @param port new value of port
     */
    public void setPort(Integer port) {
        Integer oldPort = this.port;
        this.port = port;
        propertyChangeSupport.firePropertyChange(PROP_PORT, oldPort, port);
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

    
}

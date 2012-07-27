/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

/**
 * This class encapsulates details of the User
 * @author Roy
 */
public class MiningBuddyUserDetails implements Serializable {
    
    private String UserName = "";
    public static final String PROP_USERNAME = "UserName";
    private String Password = "";
    public static final String PROP_PASSWORD = "Password";
    
    public MiningBuddyUserDetails(){
    }
    
    public MiningBuddyUserDetails(String strNewUser, String strNewPass){
        UserName = strNewUser;
        Password = strNewPass;
    }

    /**
     * Get the value of Password
     *
     * @return the value of Password
     */
    public String getPassword() {
        return Password;
    }

    /**
     * Set the value of Password
     *
     * @param Password new value of Password
     */
    public void setPassword(String Password) {
        String oldPassword = this.Password;
        this.Password = Password;
        propertyChangeSupport.firePropertyChange(PROP_PASSWORD, oldPassword, Password);
    }


    /**
     * Get the value of UserName
     *
     * @return the value of UserName
     */
    public String getUserName() {
        return UserName;
    }

    /**
     * Set the value of UserName
     *
     * @param UserName new value of UserName
     */
    public void setUserName(String UserName) {
        String oldUserName = this.UserName;
        this.UserName = UserName;
        propertyChangeSupport.firePropertyChange(PROP_USERNAME, oldUserName, UserName);
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

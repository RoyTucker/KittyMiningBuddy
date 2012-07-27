/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.enums;

/**
 * Enumeration used to represent the connection status to the server
 * @author Roy
 */
public enum ConnectStatus {
    CONNECTED,
    DISCONNECTED;

    @Override
    public String toString() {
        String strResult = "UNKNOWN";
        switch(this){
            case CONNECTED:
                strResult = "CONNECTED";
                break;
            case DISCONNECTED:
                strResult = "DISCONNECTED";
                break;
        }
        return strResult;
    }
    
    
}

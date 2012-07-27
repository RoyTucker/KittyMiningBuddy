/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.messageClasses;

import kittyMiningBuddyLibrary.MiningBuddyUserDetails;
import kittyMiningBuddyLibrary.enums.MessageType;
import kittyMiningBuddyLibrary.interfaces.IMessage;

/**
 * A Message requesting a connection with the server. The message carries the
 * connecting users details
 * @author Roy
 */
public class Connect implements IMessage {
    
    private MiningBuddyUserDetails UserDetails;
    
    public Connect(){
        UserDetails = null;
    }
    
    public Connect(MiningBuddyUserDetails objNewUser){
        UserDetails = objNewUser;
    }

    @Override
    public MessageType getMessageType() {
        return MessageType.CONNECT;
    }
    
    public MiningBuddyUserDetails getConnectingUserDetails(){
        return UserDetails;
    }
    
}

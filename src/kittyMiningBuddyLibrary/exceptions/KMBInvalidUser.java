/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.exceptions;

import java.io.Serializable;
import kittyMiningBuddyLibrary.MiningBuddyUserDetails;

/**
 * Exception to throw when an unrecognized user is encountered
 * @author Roy
 */
public class KMBInvalidUser extends KittyMiningBuddyException implements Serializable {
    
    private MiningBuddyUserDetails UserLogin;
    private Boolean blnInvalidUserName = true;
    private Boolean blnInvalidPassword = true;
    
    public KMBInvalidUser(MiningBuddyUserDetails userDetails, Boolean UserNameFlag, Boolean PasswordFlag){
        UserLogin = userDetails;
        blnInvalidUserName = UserNameFlag;
        blnInvalidPassword = PasswordFlag;
    }
    
    public MiningBuddyUserDetails getFailedUserLogin(){
        return UserLogin;
    }
    
    public Boolean isInvalidUserName(){
        return blnInvalidUserName;
    }
    
    public Boolean isInvalidPassword(){
        return blnInvalidPassword;
    }
    
}

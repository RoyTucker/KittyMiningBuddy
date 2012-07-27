/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.interfaces;

import kittyMiningBuddyLibrary.enums.MessageType;
import java.io.Serializable;

/**
 * Identifies the various message types that can be sent / received
 * @author Roy
 */
public interface IMessage extends Serializable {
    
    MessageType getMessageType();
    
}

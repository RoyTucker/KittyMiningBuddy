/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.messageClasses;

import kittyMiningBuddyLibrary.enums.MessageType;
import kittyMiningBuddyLibrary.interfaces.IMessage;

/**
 *
 * @author Roy
 */
public class CloseConnection implements IMessage{

    @Override
    public MessageType getMessageType() {
        return MessageType.CLOSECONNECTION;
    }
    
}

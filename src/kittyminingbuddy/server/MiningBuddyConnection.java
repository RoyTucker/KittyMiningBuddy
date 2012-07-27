/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyminingbuddy.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import kittyMiningBuddyLibrary.interfaces.IMessage;

/**
 *
 * @author Roy
 */
public class MiningBuddyConnection implements Runnable {
    
    private Socket conn;
    private ArrayList<IMessage> arlMessages;
    private ObjectInputStream inStream;
    private ObjectOutputStream outStream;
    private Boolean blnRepeat = true;
    
    public MiningBuddyConnection(Socket newConn){
        this.conn = newConn;
        this.arlMessages = new ArrayList<>();
        try {
            inStream = new ObjectInputStream(this.conn.getInputStream());
            outStream = new ObjectOutputStream(this.conn.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(MiningBuddyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        if(this.isValid()){
            while(blnRepeat){
                try {
                    Object readObject = inStream.readObject();
                    if(readObject instanceof IMessage){
                        IMessage msg = (IMessage)readObject;
                        switch(msg.getMessageType()){
                            case CONNECT:
                                break;
                            case CLOSECONNECTION:
                                blnRepeat = false;
                                conn.close();
                                break;
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(MiningBuddyConnection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public Boolean isValid()
    {
        Boolean blnResult = false;
        if(null != this.conn && this.conn.isConnected() && !this.conn.isClosed()){
            blnResult = true;
        }
        return blnResult;
    }
    
    public ObjectInputStream getObjectInputStream(){
        return inStream;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyminingbuddy.server;

import java.lang.Thread.State;

/**
 *
 * @author Roy
 */
public class ConnectionRecord {
    
    public Thread executingThread;
    public MiningBuddyConnection connection;
    
    public ConnectionRecord(Thread newThread, MiningBuddyConnection newConn){
        executingThread = newThread;
        connection = newConn;
    }
    
    public Boolean isAlive(){
        Boolean result = false;
        State state = executingThread.getState();
        if(State.TERMINATED != state){
            result = true;
        }
        return result;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyminingbuddy.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roy
 */
public class ServerEngine implements Runnable {

    private ServerSocket objServerSocket;
    private Integer intPortNo;
    private Integer intBacklog;
    private Boolean blnRunning = true;
    private List<ConnectionRecord> arlConnections = Collections.synchronizedList(new ArrayList<ConnectionRecord>());

    public ServerEngine() {
        setDefaults();
    }

    public ServerEngine(String[] args) {
        if (args != null && args.length == 2) {
            try {
                intPortNo = Integer.parseInt(args[0]);
                intBacklog = Integer.parseInt(args[1]);
                this.blnRunning = true;
            } catch (NumberFormatException ex) {
                setDefaults();
            }
        } else {
            setDefaults();
        }
    }

    private void setDefaults() {
        intPortNo = 15000;
        intBacklog = 50;
        this.blnRunning = true;
    }

    @Override
    public void run() {
        try {
            this.objServerSocket = new ServerSocket(intPortNo, intBacklog);
            this.objServerSocket.setSoTimeout(100);
            ScheduledExecutorService timerExecutor = Executors.newScheduledThreadPool(1);
            try {
                timerExecutor.scheduleAtFixedRate(new ConnectionMonitor(), 0, 1, TimeUnit.SECONDS);
                while (this.blnRunning) {
                    try {
                        if (null != this.objServerSocket) {
                            Socket socket = this.objServerSocket.accept();
                            if (null != socket) {
                                MiningBuddyConnection objNewConn = new MiningBuddyConnection(socket);
                                Thread objThread = new Thread(objNewConn);
                                ConnectionRecord objNewRec = new ConnectionRecord(objThread, objNewConn);
                                this.arlConnections.add(objNewRec);
                                objThread.start();
                            }
                        }
                    } catch (SocketTimeoutException ex) {
                        System.out.println("Server has " + this.arlConnections.size() + " connections at time: " + System.currentTimeMillis());
                    }
                }
            } finally {
                timerExecutor.shutdown();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private class ConnectionMonitor implements Runnable {

        @Override
        public void run() {
            try {
                for (ConnectionRecord objCurr : arlConnections) {
                    if (!objCurr.isAlive()) {
                        arlConnections.remove(objCurr);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Connection Monitor Exception: " + ex.getMessage());
            }
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyminingbuddy.server;

/**
 *
 * @author Roy
 */
public class KittyMiningBuddyServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServerEngine objServer = new ServerEngine(args);
        objServer.run();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author soab
 */
public class NodePings implements Runnable {

    private boolean scan = true;
    private int serverport;
    private DatagramSocket socketport;
    private String ServerIpaddr;

    public NodePings(int server, DatagramSocket socketnum, String serverIP) {
        serverport = server;
        socketport = socketnum;
        ServerIpaddr = serverIP;

    }

    @Override
    public void run() {

        try {

            InetAddress addr = InetAddress.getByName(ServerIpaddr);
            System.out.println("Sending Ping to Server");

            while (scan == true) {
                if (addr.isReachable(2000)) {
                    System.out.println("Server is available");

                } else {
                    System.out.println("Server is NOT reachable");
                    socketport.close();
                    
                }
                Thread.sleep(1000);
            }

        } catch (Exception error) {

        }
    }
}

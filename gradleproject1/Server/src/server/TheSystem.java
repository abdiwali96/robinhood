/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author soab
 */
public class TheSystem {

    private int serverPort;

    public TheSystem(int port) {
        serverPort = port;
    }

    public void runSystem() {
        
        DatagramSocket socket = null;
        DataManager manager = new DataManager();
        
        
        try {
            System.out.println("Server is running and listening to port: " + serverPort);
            socket = new DatagramSocket(serverPort);
            socket.setSoTimeout(0);

            //put in a loop to keep server listening
            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(buffer);
                System.out.println("Got message: " + message.trim());
                
                // some commands to send over the network: 
                //e.g. Stop command
                String []elements = message.trim().split(",");
                
                switch(elements[0]) {
                      
                    case "REGISTER" : 
                        System.out.println("Receieved a new registration message.");
                        // REGISTER, <Node name>, <Node IP ADDR> , <NODE PORT>,..
                        
                        //This is a bit dangerous as i am assuming element is filled
                        String nodeName = elements[1];
                        String nodeIP = elements[2];
                        int nodePort = Integer.parseInt(elements[3]); // YOU need an error check that its a number here!
                        
                        Data theNode = new Data (nodeName, nodeIP, nodePort );
                        
                        manager.addNewMachine(theNode);
                        
                        
                        break;
                        
                    case "STOP" : 
                        System.out.println("I've been told to stop");
                        System.exit(0);
                        //you need make the true boolean to false to close the port
                        break;
                        
                    case "STAR" : 
                        System.out.println("We are sending you to room 1 .. ");
                        
                        //you need make the true boolean to false to close the port
                        break;
                    default:
                        System.out.println("I dont understand " + message);
                        break;
                }
                
                
            }

        } catch (Exception error) {
            error.printStackTrace();
        } finally {

            try {
                if(socket != null) socket.close();
            } catch (Exception error) {
                //do nothing
            }
        }

    }

}

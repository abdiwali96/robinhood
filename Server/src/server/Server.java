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
public class Server {

    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String message = args[0];
        String[] argElements = args[0].trim().split(",");
        
        String host = argElements[0];
       
        int Serverport = Integer.parseInt(argElements[1]);
        
        TheSystem sys = new TheSystem(host,Serverport);
        sys.runSystem();
    }
    
}

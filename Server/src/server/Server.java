/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

    public static void main(String[] args) {
   
        
        String message = args[0];
        String[] argElements = args[0].trim().split(",");
        
        String host = argElements[0];
       
        int Serverport = Integer.parseInt(argElements[1]);
        
        TheSystem sys = new TheSystem(host,Serverport);
        sys.runSystem();
    }
    
}

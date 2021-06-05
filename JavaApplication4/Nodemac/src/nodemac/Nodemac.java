/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author soab
 */
public class Nodemac {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       String host = "localhost" ;
        try{
            InetAddress addr = InetAddress.getByName(host);
            String message = "REGISTER,node3,102.772.1.20,8000";
         
            DatagramPacket packet1 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            DatagramSocket socket = new DatagramSocket(3000);
            socket.send(packet1);
            socket.close();
        } catch (Exception error){
            
        }
        
        
    }
    
}

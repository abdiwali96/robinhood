/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author soab
 */
public class Sender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String host = "localhost" ;
        try{
            InetAddress addr = InetAddress.getByName(host);
            String message = "REGISTER,node2,102.772.1.20,8000";
            
            
            System.out.println("WELCOME TO DENTAL CARE: \n" + "Please Enter your name");
            
            BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
            String Username = reader.readLine();
            
            
            System.out.println("Thank you " + Username + ", Which dental service would you like \n" + "1) Quick dental check (5seconds)\n2)Medium dental check (10seconds)\n3)Full check (15seconds)");
            String servicenumber = reader.readLine();

            // Printing the read line
            System.out.println(servicenumber);
            DatagramPacket packet1 = new DatagramPacket(Username.getBytes(), Username.getBytes().length, addr, 5000);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            DatagramSocket socket = new DatagramSocket(6000);
            socket.send(packet1);
            socket.close();
        } catch (Exception error){
            
        }
        
        
    }
    
}

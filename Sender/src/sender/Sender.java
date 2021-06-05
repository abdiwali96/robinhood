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
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author soab
 */
public class Sender {
    
    
    private static int RandomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String host = "localhost" ;
        
       //  "Sender,this is my message,0,2300,localhost,localhost,5000"
        String[] argElements = args[0].trim().split(",");
        String Message = argElements[1];
        int messageWeight = Integer.parseInt(argElements[2]);
        int Senderport = Integer.parseInt(argElements[3]);
        String SenderIP = argElements[4];
        String SeverIP = argElements[5];
        int Severport = Integer.parseInt(argElements[6]);
        
        
        String test = argElements[7];
        
        String SenderMessage = "REGISTER," +  Message + "," + messageWeight + "," + Senderport + "," + SenderIP + "," + SeverIP + "," + Severport + "," + test;
        
        System.out.print(SenderMessage);
        try{
            InetAddress addr = InetAddress.getByName(SeverIP);
            //String message = "REGISTER,node2,102.772.1.20,8000";
                        
            System.out.println("WELCOME: \n" + "SEND THIS MESSAGE: " + Message );
            
           // BufferedReader reader = new BufferedReader(
            //new InputStreamReader(System.in));
           // String Inputmessage = reader.readLine();

            // Printing the read line
            
            DatagramPacket packet = new DatagramPacket(SenderMessage.getBytes(), SenderMessage.getBytes().length, addr, Severport);
            //DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, 5000);
            DatagramSocket socket = new DatagramSocket(Senderport);
            socket.send(packet);
            //socket.close();
        } catch (Exception error){
            
        }
        
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;


/**
 *
 * @author soab
 */
public class Serversender implements Runnable {
    
    private LinkedList<Data> connectedData;
    
    private int MessageCapactiy; 
    private String message2send;
    private int max = 0;
    private int port = 0;
    private int serverPort1;
    private String host1;
    
    
    public Serversender(LinkedList<Data> Tabledata, int RandomNum, String sendermessage, String host,int serverPort) {
        connectedData = Tabledata;
        MessageCapactiy = RandomNum;
        message2send = sendermessage;
        serverPort1 = serverPort;
        host1 = host;
    }
    
    
    
    @Override
    public void run() {
        sendMessage();

    }
    
    /*
    public Data CapacitySorter() {
        
        int port = 0;
        Data d = null;
        for (Data M : connectedData) {

            if (M.getCapactiy() > this.max) {
              //  this.max = M.getCapactiy();
              //  this.port = M.getPort();
               // connectedData.get(max).SetCapacity(50);
               this.max = M.getCapactiy();
               d = M;
               
            }

        }
        return d;

    }
*/
    
    public void sendMessage() {

        //String host = "localhost";
        
        
        // System.out.println("THE MESSAGE CAPACITY WAS " + this.MessageCapactiy );
         
         //System.out.println("THE CAPACITY fucntion WAS " + CapacitySorter().getCapactiy());
         
         
            try {
                System.out.println("ARE we here 1");
                
                
                int Nodeportnum = 0;
                int Biggestnodecap = 0;
                int BiggestnodecapIndex = 0;

            for (Data connectedDataNode : connectedData) {

                if (connectedDataNode.getCapactiy() > Biggestnodecap) {
                    Biggestnodecap = connectedDataNode.getCapactiy();
                    BiggestnodecapIndex = connectedData.indexOf(connectedDataNode);
                    Nodeportnum = connectedDataNode.getPort();
                }

            }
            
             if (this.MessageCapactiy <= Biggestnodecap) {
              
                 
               String message = message2send + "," + String.valueOf(this.MessageCapactiy) + "," + host1 + "," + serverPort1;
                 
                DatagramSocket client = new DatagramSocket();
                InetAddress addr = InetAddress.getByName(host1);
                DatagramPacket packet11 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr,connectedData.get(BiggestnodecapIndex).getPort());
                System.out.print("capactiy of node before process: "  + connectedData.get(BiggestnodecapIndex).getCapactiy());
                System.out.print("capactiy of the message: "  + this.MessageCapactiy);
                
                
                connectedData.get(BiggestnodecapIndex).SetCapacity(Biggestnodecap - this.MessageCapactiy);
               
                System.out.print("capactiy node After: "  + connectedData.get(BiggestnodecapIndex).getCapactiy());
                client.send(packet11);
               
                
                //System.out.print("Message send to node: " + CapacitySorter());

                client.close();
                 
             }
            
               
                 
                
               
        
            } catch (Exception error) {
                System.out.print("error");
            }
            
        
        
        

    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author soab
 */
public class SendbackProcess implements Runnable {

    private String messagepack;
    private String text;
    private int timeWait;
    //private int socketport;
    private DatagramSocket socketport;
    
    
     private boolean scan = true ;
    private int serverportnum;
    private String ServerIpaddr;
    
    //Nodemac j = new Nodemac();

    public SendbackProcess(String response, DatagramSocket socketnum, String serverIP , int serverport) {
        messagepack = response;
        socketport = socketnum;
        ServerIpaddr = serverIP;
        serverportnum =  serverport;
        
        
    }
    
    public void ToServerComplete(){
        try {
            //String host = "localhost";
            String Complete = "JOB COMPLETED";
             System.out.println("JOB IS COMPLETED..now sending back to server");
            InetAddress addr = InetAddress.getByName(ServerIpaddr);
            
           
            DatagramPacket packet1 = new DatagramPacket(Complete.getBytes(), Complete.getBytes().length, addr, serverportnum);
          
            this.socketport.send(packet1);
            
            //this.socket.close();
        } catch (Exception error) {
          
        }
    }
    
    public void Pingsys() {
        
        String messageAll = "HI EVERYONE";
 
    }

    @Override
    public void run() {

        
        try {
            
            
            
            String[] elements = this.messagepack.trim().split(",");
            String text = elements[0];  
            int timeWait = Integer.parseInt(elements[1]);
            System.out.println("Currently working on your message..it  will take " + elements[1] + " Seconds");
            
            Thread.sleep(timeWait*1000);
            ToServerComplete();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(SendbackProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}

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
public class NodeManager implements Runnable  {
    
    private LinkedList<Data> connectedData  = new LinkedList(); 
    
    private boolean scan = true;
    private int serverPort;
    private String host;
    
    public boolean addNewMachine ( Data newMachine ){        

        
        if(newMachine != null) newMachine.display();
        

        return (newMachine!= null)? connectedData.add(newMachine)  : false;
        
        
        
    }
    
    public NodeManager(String host,int serverPort) {
        serverPort = serverPort;
        host = host;
        
    }
    
    public LinkedList<Data> getConnectedData(){
        return this.connectedData;
    }
    
    public int getConnectedDatasize(){
        return this.connectedData.size();
    }
    
    public Data findIP(String IP){
        //search through all items, looking for objects with this ip adddress ? ]
        String z = connectedData.get(0).getNodeIP();
     
        for (Data M : connectedData) {
            if (M.getNodeIP() == IP) {
                System.out.println("weve found " + IP);
            } else {
                System.out.println("NOT found " + IP);
            }
        }

// System.out.println(this.connectedData.get(0));
        return null;
    }
    
    public Data findName (String machineName){
        //search through all items, looking for object with the same name
        
         for (Data M : connectedData) {
            if (M.getNodeName() == machineName) {
                System.out.println("weve found " + machineName);
            } else {
                System.out.println("NOT found " + machineName);
            }
        }
        return null;
    }
    
    public boolean removeMachine (Data machine) {
        
         for (Data M : connectedData) {
            if (M == machine) {
                connectedData.remove(M);
                System.out.println("weve have removed " + machine);
            } else {
                System.out.println("NOT found " + machine);
            }
        }
        
        //remove this
        return true;
    }
    
     public int size() {
         int num = connectedData.size();
         System.out.println(num);
          return num;
    }
     
    public void updateCapacity(int updateportnum, int updateweightnum){
        
        for (Data M : connectedData) {
            if (M.getPort() == updateportnum) {
                M.ADDChangeCapacity(Math.abs(updateweightnum));
                System.out.println("UPDATE CAPACITY2:" + M.getCapactiy());
                
            } else {
                System.out.println("node was not able to update the node capacity using the port: " + updateportnum);
            }
        }
        
    }
    
     
     //REMOVE THIS FUNCTION
    public void sendMessageToAll (){
        
        String messageAll = "HI EVERYONE";
        
        // check if list is empty 
        //String host = "localhost" ;
        try{
                           
   
            
            InetAddress addr = InetAddress.getByName(host);
            System.out.println("Sending Ping Request to " + host );
            
            
            while (scan == true){              
                for (Data M : connectedData) {
                  System.out.println("Sending Ping to " + M.getPort() );
            
                if(M.getavailability() == true && addr.isReachable(M.getPort())){
                System.out.println("Node is available");
                
                
                //System.out.println(addr.isReachable(4000) ? "Host is reachable" : "Host is NOT reachable");       
                }else {
                    System.out.println("Node is NOT available");
                    //change obejct atrr to False
                } 
                              
             } 
            }
            
            

            
            //addr.isReachable(4000)
           
            //System.out.println(addr.isReachable(4000) ? "Host is reachable" : "Host is NOT reachable");
          
            

           
        } catch (Exception error){
            
        }
        
    }
    
    
    

    @Override
    public void run() {
         String messageAll = "HI EVERYONE";
        
        // check if list is empty 
        String host = "localhost" ;
        try{
                           
            //String message = "REGISTER,node2,102.772.1.20,8000";
           
            //DatagramSocket client = new DatagramSocket();
          //  InetAddress addr = InetAddress.getByName(host);
          //  DatagramPacket packet11 = new DatagramPacket(messageAll.getBytes(), messageAll.getBytes().length, addr, 4000);    
          //  client.send(packet11);     
          
            //socket.close();
            
            InetAddress addr = InetAddress.getByName(host);
            System.out.println("Sending Ping Request to " + host );
            
            
            while (scan == true){              
                for (Data M : connectedData) {
                  System.out.println("Sending Ping to " + M.getPort() );
            
                if(M.getavailability() == true && addr.isReachable(M.getPort())){
                System.out.println("Node is available");
                //System.out.println(addr.isReachable(4000) ? "Host is reachable" : "Host is NOT reachable");       
                }else {
                    System.out.println("Node is NOT available");
                    //change obejct atrr to False
                } 
                              
             } Thread.sleep(25);
            }
            
            

            
            
            //addr.isReachable(4000)
           
            //System.out.println(addr.isReachable(4000) ? "Host is reachable" : "Host is NOT reachable");
          
            

           
        } catch (Exception error){
            
        }
    }

    
    
     
    
    
    
}

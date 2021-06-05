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
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author soab
 */
public class TheSystem {

    private int serverPort;
    private String host;
    private String nodeName ;
    private String nodeIP ;
    private int nodePort;
    //this rand num will be for the message size
    private int RandomNum = ThreadLocalRandom.current().nextInt(1, 20 + 1);
    private String sendermessage;
    
    private String MessageFromSender;
    //private int messageWeightFromSender;
    
    private LinkedList<String> MessageData  = new LinkedList(); 

    public TheSystem(String host1,int port) {
        serverPort = port;
        host = host1;
        
    }

    public void runSystem() {
        
        DatagramSocket socket = null;
       
        
         
        
        
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
                        
                        
                        
                        //boolean elementsContainsS = false;

                        
                        
                        System.out.println(elements[7]);
                        if(elements[7].equals("S"))
                        {
                          System.out.println("MESSAGE CAME");
                         sendermessage = elements[1];
                         RandomNum = Integer.parseInt(elements[2]);
                         int SenderportFromSender = Integer.parseInt(elements[3]);
                         String SenderIPFromSender = elements[4];
                         String SeverIPFromSender = elements[5];
                         int SeverportFromSender = Integer.parseInt(elements[6]);
                        //you need make the true boolean to false to close the port
                       
                         String concatenation = sendermessage + "," + RandomNum ;
                         
                         MessageData.add(concatenation) ;
                         System.out.println("ADDED TO LINKEDLIST");
                         
                        }else 
                        {
                            
                        


                          
                            while (true) {


                              if (MessageData.size() >= 1){
                                System.out.println("Nothing register message");

                                System.out.println("Receieved a new registration message.");
                                // REGISTER, <Node name>, <Node IP ADDR> , <NODE PORT>,..

                                //This is a bit dangerous as i am assuming element is filled
                                nodeName = elements[1];
                                nodeIP = elements[2];
                                nodePort = Integer.parseInt(elements[3]); // YOU need an error check that its a number here!

                                int nodecapacity = Integer.parseInt(elements[4]);

                                ///THIS NEEDS TO MOVE
                                System.out.println("checking" + MessageData);

                                this.sendermessage = this.MessageData.getFirst();
                                System.out.print("THIS IS FOR NODE LISTEN:" + sendermessage);

                                String[] senderelements = sendermessage.trim().split(",");

                                NodeManager manager = new NodeManager(host, serverPort);
                                Serversender sending = new Serversender(manager.getConnectedData(), Integer.parseInt(senderelements[1]), senderelements[0], host, serverPort);

                                Data theNode = new Data(nodeName, nodeIP, nodePort, true, nodecapacity);

                                manager.addNewMachine(theNode);

                                manager.findIP(theNode.getNodeIP());

                                Thread t1 = new Thread(manager);
                                // this will call run() method   
                                t1.start();

                                Thread t2 = new Thread(sending);

                                t2.start();
                                break;
                            }else {
                                 // System.out.println("THIS IS NODE CHECKING AGAIN LINKEDLIST");
                              }

                          }

                        }
                            break;
                        
                        
                         
                        
                        //manager.sendMessageToAll("d",serverPort );
                        
                        
                      
                        //manager.size();
                        
                         
                     
                        
                    case "STOP" : 
                        System.out.println("I've been told to stop");
                        System.exit(0);
                        //you need make the true boolean to false to close the port
                        break;
                        
                    case "STAR" : 
                        System.out.println("We are sending you to room 1 .. ");
                        
                     case "JOB COMPLETED" : 
                         
                         System.out.println("JOB IS DONE!");
                        //you need make the true boolean to false to close the port
                       
                        break;
                     case "Sender" : 
                         
                         System.out.println("MESSAGE CAME");
                         sendermessage = elements[1];
                         RandomNum = Integer.parseInt(elements[2]);
                         int SenderportFromSender = Integer.parseInt(elements[3]);
                         String SenderIPFromSender = elements[4];
                         String SeverIPFromSender = elements[5];
                         int SeverportFromSender = Integer.parseInt(elements[6]);
                        //you need make the true boolean to false to close the port
                       
                         String concatenation = sendermessage + "," + RandomNum ;
                         
                         MessageData.add(concatenation) ;
                         
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

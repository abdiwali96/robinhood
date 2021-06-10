/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soab
 */
public class NodeUpdater implements Runnable {
    
    private String[] elements;



    private LinkedList<String> MessageData;
    private int nodecapacity;
    private String sendermessage;

    private String host;
    private int serverPort;
    
    

    public NodeUpdater(String element, LinkedList<String> MessageData,String host,int serverPort) {
        this.elements = element.trim().split(",");
        this.MessageData = MessageData;
        this.host = host;
        this.serverPort = serverPort;
        
    }

    @Override
    public void run() {
        
        
        

        try {
            System.out.println("Receieved a new node registration");
            // REGISTER, <Node name>, <Node IP ADDR> , <NODE PORT>,..
            
            //This is a bit dangerous as i am assuming element is filled
            String nodeName = elements[1];
            String nodeIP = elements[2];
            int nodePort = Integer.parseInt(elements[3]); // YOU need an error check that its a number here!
            
            int nodecapacity = Integer.parseInt(elements[4]);
            
            ///THIS NEEDS TO MOVE
            //System.out.println("checking" + MessageData);
            
            //this.sendermessage = this.MessageData.getFirst();
            //System.out.print("THIS IS FOR NODE LISTEN:" + sendermessage);
            
            //String[] senderelements = sendermessage.trim().split(",");
            
            NodeManager manager = new NodeManager(host, serverPort);
            //Serversender sending = new Serversender(manager.getConnectedData(), Integer.parseInt(senderelements[1]), senderelements[0], host, serverPort, this.MessageData);
            
            Data theNode = new Data(nodeName, nodeIP, nodePort, true, nodecapacity);
            
            manager.addNewMachine(theNode);
            
            manager.findIP(theNode.getNodeIP());
            
            // Thread t1 = new Thread(manager);
            // this will call run() method
            // t1.start();

            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(NodeUpdater.class.getName()).log(Level.SEVERE, null, ex);
        }
       

    }

}

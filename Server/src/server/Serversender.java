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
    private LinkedList<String> messageData;

    private String nodename;

    public Serversender(LinkedList<Data> connectedTabledata, int RandomNum, String sendermessage, String host, int serverPort) {
        connectedData = connectedTabledata;
        MessageCapactiy = RandomNum;
        message2send = sendermessage;
        serverPort1 = serverPort;
        host1 = host;
    }

    @Override
    public void run() {
        sendMessage();

    }

    public void sendMessage() {

        try {
           

            int LargestNodeportnum = 0;
            int NEWLargestnodecap = 0;
            int NEWLargestnodecapIndex = 0;

            System.out.println("Number of Nodes in list: " + connectedData.size());
            //loop through each node in the linkedlist
            for (Data connectedDataNode : connectedData) {

                if (connectedDataNode.getCapactiy() > NEWLargestnodecap) {
                    
                    NEWLargestnodecap = connectedDataNode.getCapactiy();
                    
                    NEWLargestnodecapIndex = connectedData.indexOf(connectedDataNode);
                    
                    LargestNodeportnum = connectedDataNode.getPort();
                }else {
                     System.out.println("There is no nodes register in the system");
                }

            }

            if (this.MessageCapactiy <= NEWLargestnodecap) {

                String message = message2send + "," + String.valueOf(this.MessageCapactiy) + "," + host1 + "," + serverPort1 + "," + LargestNodeportnum + "," + this.MessageCapactiy;

                DatagramSocket client = new DatagramSocket();
                InetAddress addr = InetAddress.getByName(host1);
                DatagramPacket packet11 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, connectedData.get(NEWLargestnodecapIndex).getPort());
                System.out.print("capactiy of node before process: " + connectedData.get(NEWLargestnodecapIndex).getCapactiy());
                System.out.print("capactiy of the message: " + this.MessageCapactiy);

                connectedData.get(NEWLargestnodecapIndex).SetCapacity(NEWLargestnodecap - this.MessageCapactiy);

                System.out.print("capactiy node After: " + connectedData.get(NEWLargestnodecapIndex).getCapactiy());
                client.send(packet11);

                client.close();

            }

        } catch (Exception error) {
            System.out.print("error");
        }

    }

}

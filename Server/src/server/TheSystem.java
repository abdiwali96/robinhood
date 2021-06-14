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
    private int RandomNum;
    private String sendermessage;
    private String MessageFromSender;

    private LinkedList<String> MessageData = new LinkedList();

    public TheSystem(String host1, int port) {
        serverPort = port;
        host = host1;

    }

    public void runSystem() {

        DatagramSocket socket = null;

        NodeManager manager = new NodeManager(host, serverPort);

        try {
            System.out.println("Server is running and listening to port: " + serverPort);
            socket = new DatagramSocket(serverPort);
            socket.setSoTimeout(0);
            String[] elements = null;

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(buffer);
                System.out.println("Got message: " + message.trim());

                elements = message.trim().split(",");

                switch (elements[0]) {

                    case "REGISTER":

                        System.out.println("Receieved a new node registration");

                        String nodeName = elements[1];
                        String nodeIP = elements[2];
                        int nodePort = Integer.parseInt(elements[3]);

                        int nodecapacity = Integer.parseInt(elements[4]);

                        Data theNode = new Data(nodeName, nodeIP, nodePort, true, nodecapacity);

                        manager.addNewMachine(theNode);

                        manager.findIP(theNode.getNodeIP());
                        break;

                    case "STOP":
                        System.out.println("I've been told to stop");
                        System.exit(0);

                        break;

                    case "STAR":
                        System.out.println("We are sending you to room 1 .. ");

                    case "JOB COMPLETED":

                        System.out.println("JOB IS DONE!");
                        int updateportnum = Integer.parseInt(elements[1]);
                        int updateweightnum = Integer.parseInt(elements[2]);
                        manager.updateCapacity(updateportnum, updateweightnum);

                        break;
                    case "SENDER":

                        System.out.println("MESSAGE CAME");
                        sendermessage = elements[1];
                        RandomNum = Integer.parseInt(elements[2]);
                        int SenderportFromSender = Integer.parseInt(elements[3]);
                        String SenderIPFromSender = elements[4];
                        String SeverIPFromSender = elements[5];
                        int SeverportFromSender = Integer.parseInt(elements[6]);

                        String concatenation = sendermessage + "," + RandomNum;

                        System.out.println("ADDED TO LINKEDLIST");

                        Serversender sending = new Serversender(manager.getConnectedData(), RandomNum, sendermessage, host, serverPort);
                        Thread t1 = new Thread(sending);

                        t1.start();

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
                if (socket != null) {
                    socket.close();
                }
            } catch (Exception error) {
                //do nothing
            }
        }

    }

}

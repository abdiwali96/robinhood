/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodemac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author soab
 */
public class Nodemac {

    private String host;
    private String response = "";
    private int nodeportArg;

    private String[] ServerElements;

    SendbackProcess Sendback;
    NodePings Node2serverping;

    public void Connect2Server(String message, String serverIP, DatagramSocket socket, int serverport, int Nodeport) {
        try {

            InetAddress addr = InetAddress.getByName(serverIP);

            DatagramPacket packet1 = new DatagramPacket(message.getBytes(), message.getBytes().length, addr, serverport);

            socket.send(packet1);

            pingThread(socket, serverport, serverIP);
            listening(socket, Node2serverping, serverIP, serverport, Nodeport);

        } catch (Exception error) {

        }

    }

    public void listening(DatagramSocket socket, NodePings Node2serverping, String serverIP, int serverport, int Nodeport) {

        boolean x = true;

        while (x = true) {

            byte[] buffer = new byte[1024];
            DatagramPacket p = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(p);

            } catch (IOException ex) {
                Logger.getLogger(Nodemac.class.getName()).log(Level.SEVERE, null, ex);
            }
            String response = new String(p.getData());

            System.out.println("Got message: " + response.trim());
            Sendback = new SendbackProcess(response, socket, serverIP, serverport, Nodeport);

            Thread t2 = new Thread(Sendback);

            t2.start();

        }

    }

    public void pingThread(DatagramSocket socket, int serverport, String serverIP) {
        Node2serverping = new NodePings(serverport, socket, serverIP);
        Thread t3 = new Thread(Node2serverping);

        t3.start();
    }

    public static void main(String[] args) throws IOException {
        //String host = "localhost";
        String message = args[0];
        String[] argElements = args[0].trim().split(",");
        String NodeIP = argElements[2];
        int Nodeport = Integer.parseInt(argElements[3]);

        int nodeportArg = Integer.parseInt(argElements[3]);

        String serverIP = argElements[5];  // System.out.println(serverIP);
        int serverport = Integer.parseInt(argElements[6]);

        try {
            DatagramSocket socket = new DatagramSocket(nodeportArg);
            Nodemac sys = new Nodemac();
            sys.Connect2Server(message, serverIP, socket, serverport, Nodeport);

        } catch (SocketException ex) {

            System.out.println("Port number: " + argElements[3] + " is in use");
        }

    }

}

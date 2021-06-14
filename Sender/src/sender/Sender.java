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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String[] argElements = args[0].trim().split(",");
        String Message = argElements[1];
        int messageWeight = Integer.parseInt(argElements[2]);
        int Senderport = Integer.parseInt(argElements[3]);
        String SenderIP = argElements[4];
        String SeverIP = argElements[5];
        int Severport = Integer.parseInt(argElements[6]);
        String type = argElements[7];

        String SenderMessage = argElements[0] + "," + Message + "," + messageWeight + "," + Senderport + "," + SenderIP + "," + SeverIP + "," + Severport + "," + type;

        System.out.print(SenderMessage);
        try {
            InetAddress addr = InetAddress.getByName(SeverIP);

            System.out.println("WELCOME: \n" + "SEND THIS MESSAGE: " + Message);

            DatagramPacket packet = new DatagramPacket(SenderMessage.getBytes(), SenderMessage.getBytes().length, addr, Severport);

            DatagramSocket socket = new DatagramSocket(Senderport);
            socket.send(packet);

        } catch (Exception error) {

        }

    }

}

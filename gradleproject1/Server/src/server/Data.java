/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author soab
 */
public class Data {
    
    private String nodeName;
    private String nodeIP;
    private int nodePort; 
    private int capacity;
    
    public Data(String name, String ip, int port){
        nodeName = name;
        nodeIP = ip;
        nodePort = port;
        capacity = 5;
    }
    
    public String getNodeName(){
        return nodeName;
    }
    
    public String getNodeIP(){
        return nodeIP;
    }
    
    public int getPort(){
        return nodePort;
    }
    
    public void display(){
        System.out.println("Machine: <" + nodeName + "> <" + nodeIP + "> <" + nodePort + ">");
    }
    
    public int getcapacity(){
        return capacity;
    }
}

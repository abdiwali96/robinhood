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
    private boolean nodeAvailability;
    private int nodeCapactiy; 
    
    public Data(String name, String ip, int port, boolean availabile, int Capacity){
        nodeName = name;
        nodeIP = ip;
        nodePort = port;
        nodeAvailability = availabile;
        nodeCapactiy = Capacity;
    }
    
    public int getCapactiy(){      
        return nodeCapactiy;
    }
    
    public void SetCapacity(int nC){
       //
       if(nodeCapactiy > 0) {
           
           this.nodeCapactiy = nC;
       }else {
           this.nodeCapactiy = 0 ;
       }
       
    }
    
    public void ChangeCapacity(int addNum) {
        this.SetCapacity(this.nodeCapactiy + addNum);
    }
    
    public void ADDChangeCapacity(int addNum) {
        this.SetCapacity(this.nodeCapactiy + addNum);
        System.out.println("CAPACITY: " + getCapactiy());
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
    
    public boolean getavailability(){
        return nodeAvailability;
    }
}

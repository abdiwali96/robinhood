/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.LinkedList;

/**
 *
 * @author soab
 */
public class DataManager {
    
    private LinkedList connectedData  = new LinkedList(); 
    
    public boolean addNewMachine ( Data newMachine ){        
        
        
        /* method 1 ...
        
            if (newMachine  != null) return connectedData.add(newMachine);
        
        else return false;
        */
        
        /* method 2 ...
           
        */
        
         System.out.println(connectedData);
        
        if(newMachine != null) newMachine.display();
       
        return (newMachine!= null)? connectedData.add(newMachine) : false;
        
        
        
    }
    
    public Data findIP( String IP){
        //search through all items, looking for objects with this ip adddress ? 
    
        return null;
    }
    
    public Data findName (String machineName){
        //search through all items, looking for object with the same name
        return null;
    }
    
    public boolean removeMachine (Data machine) {
        //remove this
        return true;
    }
    
    public void sendMessageToAll (String message){
        
    }
    
}

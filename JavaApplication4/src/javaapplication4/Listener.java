

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Listener extends base {

  private LinkedList listOfAgents = new LinkedList();

  public Listener( String sIPAddressOfSocket, int iPortNumber, String sServiceName ) throws IOException {
    super( sIPAddressOfSocket, iPortNumber, sServiceName );
    connectListener();
  }

  public void connectListener() {
    // now create the listener thread to listen for any messages comming back of the group
    try {
      // now repeat until something recieved
      start();
      } catch( Exception e ) {
        System.err.println("Error occurred in <connectListenter>: " + e.getMessage() );
        }
    }

  public void run() {
    // set up buffer space for recieving things off address
    byte[] buffer = null;
    try {
      // now connect to the group
      socketConnection.joinGroup(groupAddress);
      // now repeat forever, waiting for packets to be sent
      for(;;) {
        buffer = new byte[256];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        // wait for packet to be sent
        socketConnection.receive(packet);

        // now check to see what the message is
        String sMessage = new String(packet.getData()).trim();

        // get int representation of action
        int iAction = getAction( sMessage );
        sMessage = stripAction(sMessage);

        switch( iAction ) {
          case 0: // need to add this to list of connected agents
                   if( !InList(sMessage) ) addToList(sMessage);
                   break;
          case 1: // force an election on the group
                  sendMessage("broadcast://"+sIPAddressOfSocket+"/"+sServiceName+":"+iPortNumber);
                  break;
          case 2: // leave the group
                  removeFromConnectedAgents( sMessage );
                  break;
          }
        }
      } catch( Exception e ) {
          System.err.println("Error occured in listener: "+ e.getMessage() );
        }
    }

  private String stripAction( String sAction ) {
    String sTemp = "";
    boolean delimiterFound = false;
    for(int i=0; i<sAction.length();i++ ) {
      if(delimiterFound) sTemp += sAction.charAt(i);
      if( sAction.charAt(i) == ':' ) delimiterFound = true;
      }
    return sTemp;
    }

  private int getAction( String sMessage ) {
    String actionList[] = { "broadcast", "force_election", "leave" };
    // need to go through received message and tokenise it.

    String sTemp = sMessage;
    StringTokenizer st = new StringTokenizer(sTemp,":");
    String sAction = st.nextToken();

    for(int i=0; i<actionList.length; i++ )
      if( sAction.toLowerCase().equals( actionList[i] ) ) return i;
    return -1;
    }

  private void addToList( String sAddressOfAgent ) {
    listOfAgents.add( sAddressOfAgent );
    }

  private boolean InList( String sAddressOfAgent ) {
    boolean hasBeenFound = false;
    for(int i=0; i<listOfAgents.size(); i++ ) {
      String temp = (String)listOfAgents.get(i);
      if(temp.equals(sAddressOfAgent.toString())) {
        hasBeenFound = true;
        break;
        }
      }
    return hasBeenFound;
  }

  private void removeFromConnectedAgents( String sAddressOfAgent ) {
    for(int i=0; i<listOfAgents.size(); i++ ) {
      String temp = (String)listOfAgents.get(i);
      if(temp.equals(sAddressOfAgent.toString())) {
        listOfAgents.remove(i);
        break;
        }
      }
  }

  public String [] availableAgents() {
    String[] availableAgentsList = new String[listOfAgents.size()];
    for(int i = 0; i<listOfAgents.size(); i++ ) {
      String temp = (String)listOfAgents.get(i);
      if( temp != null ) {
        availableAgentsList[i] = temp;
        }
      }
    return availableAgentsList;
    }

}
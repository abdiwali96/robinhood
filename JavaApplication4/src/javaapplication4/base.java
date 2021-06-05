

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

public class base extends Thread {

  // holders for current socket
  protected MulticastSocket socketConnection = null;
  // holder sting representation of the IP address of socket
  protected String sIPAddressOfSocket = "";
  // holder for group address
  protected static InetAddress groupAddress = null;
  // holder for the port for communicating
  protected int iPortNumber = -1;
  // store for service name
  protected String sServiceName = "";

  public base( String sIPAddressOfSocket, int iPortNumber, String sServiceName ) throws IOException {
    // we need to lookup and resolve the string representation of the IP address
    try {
      groupAddress = InetAddress.getByName( sIPAddressOfSocket );
      // now set and create socket
      this.sIPAddressOfSocket = sIPAddressOfSocket;
      this.iPortNumber = iPortNumber;
      this.sServiceName = sServiceName;
      socketConnection = new MulticastSocket( iPortNumber );

      // set the time to live flag to 15, as we need it to propagate through the network before it dies.
      // 15 = same site, 63 = same region, 127 = world wide.

      socketConnection.setTimeToLive(15);

      } catch( Exception e ) {
        // error occurred so display details
        System.err.println("Error occurred trying to create a new socket!");
        System.err.println("IP Address of new socket = <" + sIPAddressOfSocket + ">");
        System.err.println("Port = <"+iPortNumber+">");
        System.err.println("Error details:"+e.getMessage());
        // reset details
        socketConnection = null;
        }
  }

  public boolean joinGroup() throws IOException {
    // need to check to see if things are connected
    if( socketConnection != null ) {
      // there is a valid socket connection, so we now want to try and join the group
      try {

        socketConnection.joinGroup( groupAddress );

        // now that we have joined, we would normally send out a election
        // broadcast message, but controlling class will do this.

        return true; // return successful join
        } catch( Exception e) {
          // an error occurred so display details
          System.err.println("Error occurred while trying to join group <"+groupAddress.toString()+">");
          System.err.println("Error returned: "+e.getMessage());
          return false;
          }
      }
    else return false;  // no valid socket connection
  }

  public boolean leaveGroup() throws IOException {
    // need to check to see if things are connected
    if( socketConnection != null ) {
      // there is a valid socket connection, so we now want to try and leave the group
      try {
        // send out message to groups to say this agent is leaving
        sendMessage("leave://"+sIPAddressOfSocket+"/"+sServiceName+":"+iPortNumber);
        socketConnection.leaveGroup( groupAddress );
        return true; // return successful leaving
        } catch( Exception e) {
          // an error occurred so display details
          System.err.println("Error occurred while trying to leave group <"+groupAddress.toString()+">");
          System.err.println("Error returned: "+e.getMessage());
          return false;
          }
      }
    else return false;  // no valid socket connection
  }


  public void sendElectionBroadcast() {
    sendMessage("force_election:");
    }

  public void sendMessage(String sMessage) {
    // we need to send an election broadcast to this address to see if anyone responds
    if( socketConnection != null ) {
      try {
        // need to send out a message on this group to force an election
        DatagramPacket packet = new DatagramPacket( sMessage.getBytes(), sMessage.getBytes().length, this.groupAddress, this.iPortNumber );
        socketConnection.send(packet);
        } catch(Exception e) {
          System.err.println("Error occurred in <sendElectionBroadcast>:"+e.getMessage());
          }
      }
    else System.err.println("Unable to broadcast message due to an invalid socket.");
  }

}

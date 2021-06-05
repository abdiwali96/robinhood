

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.io.*;
import java.util.Timer;

public class agent {

  // holder for the multicast group that this agent is going to be connected to.
  public base groupAddressConnection = null;
  // holder for the listener process to bind to listening multicast group
  public Listener listenerAddressConnection = null;
  // holder to store name of agent
  public String sAgentName = "";
  // holder for force_elections timer
  private Timer reBroadcast = null;
  private timerObject broadcastObject = null;

  public int ELECTION_BROADCAST_DELAY = 5000; // 5 seconds

  public agent( String sIP, int iPort, String sName) {
    try {

      // we need to bind this agent to the multicast group it is going to communicate on

      groupAddressConnection = new base( sIP, iPort, sName );
      sAgentName = sName;

      // now join that group

      groupAddressConnection.joinGroup();

      // now connect the listener service

      listenerAddressConnection = new Listener( sIP, iPort, sName );

      reBroadcast = new Timer();
      broadcastObject = new timerObject(this);
      reBroadcast.schedule( broadcastObject, ELECTION_BROADCAST_DELAY, ELECTION_BROADCAST_DELAY );

      System.out.println("Agent <" + sAgentName + "> is running on multicast group <"+sIP+"> port <"+iPort+">");

      } catch( Exception e ) {
          System.err.println("Error in creating new agent: "+ e.getMessage() );
          System.exit(-1);
          }
  }

  public String [] findOtherAgents() {
    // to find other agents we need to force an election broadcast
    groupAddressConnection.sendElectionBroadcast();
    return listenerAddressConnection.availableAgents();
    }

  public void end() {
    // we need to destroy everything
    try {
      System.out.println("Exiting agent");
      reBroadcast.cancel();
      broadcastObject = null;
      groupAddressConnection.leaveGroup();
      listenerAddressConnection.leaveGroup();
      System.exit(0);
      } catch( Exception e ) {
        System.err.println("Error in agent <end> : " + e.getMessage() );
        System.exit(-1);
        }
  }

  public static void main( String []args) throws IOException {
    try {
      if( args.length != 3 ) {
        System.err.println("Usage: agent <IP Address> <Port number> <Service name>");
        System.exit(-1);
        }
      else {
        agent test = new agent( args[0], Integer.parseInt(args[1]), args[2] );
        // now we need to time out after a certain period of time
        Timer t = new Timer();
        killObject endAgent = new killObject(test);
        //;t.schedule( endAgent, 20000, 20000);
	while(true);
        }
      } catch( Exception e ) {
        System.err.println("Error: " + e.getMessage() );
        System.exit(-1);
        }
    }

}

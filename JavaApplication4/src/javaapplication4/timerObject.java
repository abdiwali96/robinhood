

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
import java.util.TimerTask;

public class timerObject extends TimerTask {
  public agent agentReference = null;

  public timerObject(agent agentReference) {
    this.agentReference = agentReference;
  }

  public void run() {
    String [] temp = agentReference.findOtherAgents();
    System.out.println();
    System.out.println("Available agents");
    System.out.println("================");
    System.out.println();

    for(int i=0; i<temp.length; i++ ) {
      System.out.println(" Connected agent : <"+temp[i]+">");
      }
    }

}
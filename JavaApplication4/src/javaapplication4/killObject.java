

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */

import java.util.TimerTask;

public class killObject extends TimerTask {
  public agent agentReference = null;

  public killObject(agent agentReference) {
    this.agentReference = agentReference;
  }

  public void run() {
    agentReference.end();
    }

}
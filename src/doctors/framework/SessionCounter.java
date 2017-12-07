package doctors.framework;

import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionEvent;

public class SessionCounter implements HttpSessionListener {

  private static int activeSessions = 0;

  public void sessionCreated(HttpSessionEvent se) {
    activeSessions++;
    System.out.println("new session id:" + se.getSession().getId());
  }

  public void sessionDestroyed(HttpSessionEvent se) {
	  System.out.println("destroyed session id:" + se.getSession().getId());
	  if(activeSessions > 0)
      activeSessions--;
  }

  public static int getActiveSessions() {
    return activeSessions;
  }

}


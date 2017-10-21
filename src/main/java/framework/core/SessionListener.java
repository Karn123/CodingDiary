package framework.core;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SessionListener implements HttpSessionListener {
	
	static Logger log = LoggerFactory.getLogger(SessionListener.class);
	
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		try{
			HttpSession session = arg0.getSession();
			if(null!=session)
			{
				log.info("start to destroy session,session id:"+session.getId());
				
				log.info("clear all session attribute data...");
				clearAllSessionData(session);
				log.info("session destroy success,session id:"+session.getId());
			}
		}catch (Exception e) {
			log.error("session destroy error!",e);
		}
	}
	
	private void clearAllSessionData(HttpSession session)
	{
		Enumeration en =  session.getAttributeNames();
		while(en.hasMoreElements())
		{
			String sessionKey = en.nextElement().toString();
			//System.out.println("!!-"+sessionKey);
			if(!sessionKey.equalsIgnoreCase("WW_TRANS_I18N_LOCALE")){
				session.removeAttribute(sessionKey);
			}
		}
	}
}
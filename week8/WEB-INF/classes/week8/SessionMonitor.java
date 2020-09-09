package week8;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener("Week8SessionMonitor")
public class SessionMonitor implements HttpSessionListener,HttpSessionAttributeListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		System.out.println("Session created with ID :  "+se.getSession().getId());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Session destroyed with ID :  "+se.getSession().getId());
		
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		System.out.println("Session Attributed just got added");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		System.out.println("Session Attributed just got removed");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		System.out.println("Session Attributed just got replaced");
	}
	

}

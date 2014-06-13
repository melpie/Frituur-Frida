package be.vdab.listeners;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
@WebListener
public class RequestListener implements ServletRequestListener, ServletContextListener {
	
	 private final static String STATISTIEK = "statistiek";
	 private final static Set<String> UITGESLOTEN_EXTENSIES = new HashSet<>(Arrays.asList("png", "gif", "jpg", "css", "js"));

	@Override
    public void requestInitialized(ServletRequestEvent event) {
        
		if (event.getServletRequest() instanceof HttpServletRequest) {
			HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
			String url = request.getRequestURI();
			boolean verwerkRequest = true;
			int laatstePuntPositie = url.lastIndexOf('.');
			if (laatstePuntPositie != -1) {
				String extensie = url.substring(laatstePuntPositie + 1).toLowerCase();
				if (UITGESLOTEN_EXTENSIES.contains(extensie)) {
					verwerkRequest = false;
				}
			}
			if (verwerkRequest) {
				int index = url.indexOf(";jsessionid=");
				if (index != -1) {
					url = url.substring(0, index);
				}
				voegRequestToe(url, request.getServletContext());
			}
		}
	}
	
	private synchronized void voegRequestToe(String url, ServletContext context) {
		@SuppressWarnings("unchecked")
		Map<String, Integer> statistiek = (Map<String, Integer>) context.getAttribute(STATISTIEK);
		if (statistiek.containsKey(url)) {
			statistiek.put(url, statistiek.get(url) + 1);
		} else {
			statistiek.put(url, 1);
		}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent event) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		event.getServletContext().setAttribute(STATISTIEK,
				new ConcurrentHashMap<String, Integer>());		
	}
	
}

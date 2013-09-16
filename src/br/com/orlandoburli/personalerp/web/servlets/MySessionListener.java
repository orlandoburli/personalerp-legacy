package br.com.orlandoburli.personalerp.web.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener, ServletContextListener {

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		System.out.print("Sessão iniciada. Id da sessão: ");
		System.out.println(e.getSession().getId());
		System.out.println(e.getSource());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {
//		Enumeration<String> enumerador = e.getServletContext().getAttributeNames();
//		while(enumerador.hasMoreElements()) {
//			System.out.println(enumerador.nextElement());
//		}
	}

}

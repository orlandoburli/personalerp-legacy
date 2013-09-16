package br.com.orlandoburli.personalerp.servlets;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import br.com.orlandoburli.SystemManager;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent e) {
		System.out.print("Sess�o iniciada. Id da sess�o: ");
		System.out.println(e.getSession().getId());
		SystemManager.listSessions.add(e.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent e) {
		System.out.println("Fim da sess�o : " + e.getSession().getId());
		SystemManager.listSessions.remove(e.getSession());
	}
}
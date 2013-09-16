package br.com.orlandoburli.personalerp.facades.geral;

import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class PingFacade extends BaseFacade {

	private static final long	serialVersionUID	= 1L;

	public void execute() {
		// Retorna somente o Id da sessão
		write(this.request.getSession().getId());
		if (SystemManager.getProperty("show.ping").equals("true")) {
			System.out.println("Ping de " + this.request.getSession().getId());
		}
	}
}
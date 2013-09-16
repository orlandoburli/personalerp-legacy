package br.com.orlandoburli.personalerp.pages.produto;

import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.pages.BasePage;

@IgnoreFacadeAuthentication
public class ProdutoPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public void execute() {
		
		
		forward("pages/produto_consulta.jsp");
	}
	
}

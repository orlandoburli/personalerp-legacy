package br.com.orlandoburli.personalerp.facades.financeiro.centrocusto;

import br.com.orlandoburli.core.dao.financeiro.CentroCustoDAO;
import br.com.orlandoburli.core.vo.financeiro.CentroCustoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

@IgnoreFacadeAuthentication
public class CentroCustoCadastroFacade extends BaseCadastroFlexFacade<CentroCustoVO, CentroCustoDAO> {

	private static final long serialVersionUID = 1L;
	
	public CentroCustoCadastroFacade() {
		this.addNewValidator(new NotEmptyValidator("NomeCentroCusto", "Nome"));
	}
}
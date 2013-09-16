package br.com.orlandoburli.personalerp.facades.financeiro.centrocusto;

import br.com.orlandoburli.core.dao.financeiro.CentroCustoDAO;
import br.com.orlandoburli.core.vo.financeiro.CentroCustoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class CentroCustoConsultaFacade extends BaseConsultaFlexFacade<CentroCustoVO, CentroCustoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeCentroCusto(getFiltro() + "%");
	}
}
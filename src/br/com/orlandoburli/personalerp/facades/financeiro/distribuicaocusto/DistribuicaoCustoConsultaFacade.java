package br.com.orlandoburli.personalerp.facades.financeiro.distribuicaocusto;

import br.com.orlandoburli.core.dao.financeiro.DistribuicaoCustoDAO;
import br.com.orlandoburli.core.vo.financeiro.DistribuicaoCustoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class DistribuicaoCustoConsultaFacade extends BaseConsultaFlexFacade<DistribuicaoCustoVO, DistribuicaoCustoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataInicialDistribuicaoCusto DESC, NomeLoja");
	}
}

package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.rateiocontapagar;

import br.com.orlandoburli.core.dao.financeiro.contasapagar.RateioContaPagarDAO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.RateioContaPagarVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class RateioContaPagarConsultaFacade extends BaseConsultaFlexFacade<RateioContaPagarVO, RateioContaPagarDAO>{

	private static final long serialVersionUID = 1L;
	
	@IgnoreMethodAuthentication
	@Override
	public void execute() {
		super.execute();
	}

	@Override
	protected void doBeforeFilter() {
		
	}
}
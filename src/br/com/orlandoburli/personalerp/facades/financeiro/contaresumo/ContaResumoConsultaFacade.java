package br.com.orlandoburli.personalerp.facades.financeiro.contaresumo;

import br.com.orlandoburli.core.dao.financeiro.contaresumo.ContaResumoDAO;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.ContaResumoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ContaResumoConsultaFacade extends BaseConsultaFlexFacade<ContaResumoVO, ContaResumoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoContaResumo(getFiltro() + "%");
		setOrderFields("DescricaoContaResumo");
	}

}

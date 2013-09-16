package br.com.orlandoburli.personalerp.facades.vendas.caixa.aberturacaixaloja;

import br.com.orlandoburli.core.dao.vendas.caixa.AberturaCaixaLojaDAO;
import br.com.orlandoburli.core.vo.vendas.caixa.AberturaCaixaLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class AberturaCaixaLojaConsultaFacade extends BaseConsultaFlexFacade<AberturaCaixaLojaVO, AberturaCaixaLojaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataHoraAberturaCaixa DESC");
	}
}
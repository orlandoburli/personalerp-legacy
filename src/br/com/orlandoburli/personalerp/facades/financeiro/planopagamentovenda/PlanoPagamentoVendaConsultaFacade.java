package br.com.orlandoburli.personalerp.facades.financeiro.planopagamentovenda;

import br.com.orlandoburli.core.dao.financeiro.PlanoPagamentoVendaDAO;
import br.com.orlandoburli.core.vo.financeiro.PlanoPagamentoVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PlanoPagamentoVendaConsultaFacade extends BaseConsultaFlexFacade<PlanoPagamentoVendaVO, PlanoPagamentoVendaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomePlanoPagamento(getFiltro() + "%");
		setOrderFields("NomePlanoPagamento");
	}
}
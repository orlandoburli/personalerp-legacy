package br.com.orlandoburli.personalerp.facades.financeiro.movimentacaocontabancaria;

import br.com.orlandoburli.core.dao.financeiro.MovimentacaoContaBancariaDAO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MovimentacaoContaBancariaConsultaFacade extends BaseConsultaFlexFacade<MovimentacaoContaBancariaVO, MovimentacaoContaBancariaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoMovimentacaoContaBancaria("%" + getFiltro() + "%");
		this.setOrderFields("DataMovimentacaoContaBancaria DESC, SequencialMovimentacaoContaBancaria DESC");
	}
}
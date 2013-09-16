package br.com.orlandoburli.personalerp.facades.vendas.caixa.caixaloja;

import br.com.orlandoburli.core.dao.vendas.caixa.CaixaLojaDAO;
import br.com.orlandoburli.core.vo.vendas.caixa.CaixaLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class CaixaLojaConsultaFacade extends BaseConsultaFlexFacade<CaixaLojaVO, CaixaLojaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoCaixa(getFiltro() + "%");
		setOrderFields("DescricaoCaixa");
	}
}
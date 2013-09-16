package br.com.orlandoburli.personalerp.facades.vendas.operacaotributacao;

import br.com.orlandoburli.core.dao.fiscal.OperacaoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class OperacaoTributacaoConsultaFacade extends BaseConsultaFlexFacade<OperacaoTributacaoVO, OperacaoTributacaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoOperacaoTributacao(getFiltro() + "%");
		setOrderFields("DescricaoOperacaoTributacao");
	}

}

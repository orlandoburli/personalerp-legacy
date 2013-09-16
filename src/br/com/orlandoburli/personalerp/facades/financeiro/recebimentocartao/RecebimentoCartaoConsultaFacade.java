package br.com.orlandoburli.personalerp.facades.financeiro.recebimentocartao;

import br.com.orlandoburli.core.dao.financeiro.RecebimentoCartaoDAO;
import br.com.orlandoburli.core.vo.financeiro.RecebimentoCartaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RecebimentoCartaoConsultaFacade extends BaseConsultaFlexFacade<RecebimentoCartaoVO, RecebimentoCartaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataHoraRecebimentoCartao DESC, CodigoRecebimentoCartao DESC");
	}
	
	
}
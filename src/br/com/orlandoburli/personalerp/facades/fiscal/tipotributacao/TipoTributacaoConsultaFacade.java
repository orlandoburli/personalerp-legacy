package br.com.orlandoburli.personalerp.facades.fiscal.tipotributacao;

import br.com.orlandoburli.core.dao.fiscal.TipoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.TipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoTributacaoConsultaFacade extends BaseConsultaFlexFacade<TipoTributacaoVO, TipoTributacaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setIdentificacaoTipoTributacao(getFiltro() + "%");
	}
}
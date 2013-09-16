package br.com.orlandoburli.personalerp.facades.fiscal.uftipotributacao;

import br.com.orlandoburli.core.dao.fiscal.UFTipoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.UFTipoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class UFTipoTributacaoConsultaFacade extends BaseConsultaFlexFacade<UFTipoTributacaoVO, UFTipoTributacaoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}
}
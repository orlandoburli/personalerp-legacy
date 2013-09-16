package br.com.orlandoburli.personalerp.facades.fiscal.nfsaida;

import br.com.orlandoburli.core.dao.fiscal.nfsaida.NFSaidaDAO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class NFSaidaConsultaFacade extends BaseConsultaFlexFacade<NFSaidaVO, NFSaidaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setCodigoEmpresa(getLojasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		setOrderFields("CodigoNFSaida DESC");
	}
}
package br.com.orlandoburli.personalerp.facades.fiscal.nfsaida.itemnfsaida;

import br.com.orlandoburli.core.dao.fiscal.nfsaida.ItemNFSaidaDAO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemNFSaidaConsultaFacade extends BaseConsultaFlexFacade<ItemNFSaidaVO, ItemNFSaidaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}
}
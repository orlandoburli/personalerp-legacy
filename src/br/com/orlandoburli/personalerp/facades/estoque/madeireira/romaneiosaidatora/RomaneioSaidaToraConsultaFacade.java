package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiosaidatora;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiosaidatora.RomaneioSaidaToraDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora.RomaneioSaidaToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioSaidaToraConsultaFacade extends BaseConsultaFlexFacade<RomaneioSaidaToraVO, RomaneioSaidaToraDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() { 
		getFilter().setNomeCliente(getFiltro() + "%");
		
		// Filtro para aparecer dados somente da empresa / loja em sessao
		this.getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
	}

	@Override
	protected Class<?> getDAOClass() {
		return RomaneioSaidaToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioSaidaToraVO.class;
	}
}
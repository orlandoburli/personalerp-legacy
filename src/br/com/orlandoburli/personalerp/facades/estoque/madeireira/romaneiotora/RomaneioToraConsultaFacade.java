package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiotora;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.RomaneioToraDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.RomaneioToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class RomaneioToraConsultaFacade extends BaseConsultaFlexFacade<RomaneioToraVO, RomaneioToraDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		try {
			Integer numRomaneio = Integer.parseInt(getFiltro());
			this.getFilter().setNumeroRomaneio(numRomaneio);
		} catch (Exception ex) {
			this.getFilter().setNomeFornecedor(getFiltro() + "%");
		}
		
		// Filtro para aparecer dados somente da empresa / loja em sessao
		this.getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		
		this.setOrderFields("DataRomaneio, NomeFornecedor, NumeroRomaneio");
	}

	@Override
	protected Class<?> getDAOClass() {
		return RomaneioToraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return RomaneioToraVO.class;
	}
}
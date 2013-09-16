package br.com.orlandoburli.personalerp.facades.estoque.fabricante;

import br.com.orlandoburli.core.dao.estoque.FabricanteDAO;
import br.com.orlandoburli.core.vo.estoque.FabricanteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class FabricanteConsultaFacade extends BaseConsultaFlexFacade<FabricanteVO, FabricanteDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeFabricante(getFiltro() + "%");
		this.setOrderFields("NomeFabricante");
	}

	@Override
	protected Class<?> getDAOClass() {
		return FabricanteDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FabricanteVO.class;
	}

}

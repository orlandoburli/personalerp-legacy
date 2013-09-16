package br.com.orlandoburli.personalerp.facades.estoque.unidade;

import br.com.orlandoburli.core.dao.estoque.UnidadeDAO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class UnidadeConsultaFacade extends BaseConsultaFlexFacade<UnidadeVO, UnidadeDAO> {

	private static final long serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeUnidadeSingular(getFiltro() + "%");
		this.setOrderFields("SiglaUnidade");
	}

	@Override
	protected Class<?> getDAOClass() {
		return UnidadeDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return UnidadeVO.class;
	}
}
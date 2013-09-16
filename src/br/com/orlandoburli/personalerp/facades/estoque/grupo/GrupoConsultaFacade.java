package br.com.orlandoburli.personalerp.facades.estoque.grupo;

import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class GrupoConsultaFacade extends BaseConsultaFlexFacade<GrupoVO, GrupoDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeGrupo(getFiltro() + "%");
		this.setOrderFields("NomeGrupo");
	}

	@Override
	protected Class<?> getDAOClass() {
		return GrupoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return GrupoVO.class;
	}

}

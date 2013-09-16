package br.com.orlandoburli.personalerp.facades.estoque.subgrupo;

import br.com.orlandoburli.core.dao.estoque.SubGrupoDAO;
import br.com.orlandoburli.core.vo.estoque.SubGrupoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class SubGrupoConsultaFacade extends BaseConsultaFlexFacade<SubGrupoVO, SubGrupoDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeSubGrupo(getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return SubGrupoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return SubGrupoVO.class;
	}

}

package br.com.orlandoburli.personalerp.facades.rh.tabelairrfdeducaodependente;

import br.com.orlandoburli.core.dao.rh.TabelaIrrfDeducaoDependenteDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfDeducaoDependenteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TabelaIrrfDeducaoDependenteConsultaFacade extends
		BaseConsultaFlexFacade<TabelaIrrfDeducaoDependenteVO, TabelaIrrfDeducaoDependenteDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaIrrfDeducaoDependenteDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaIrrfDeducaoDependenteVO.class;
	}
}
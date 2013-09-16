package br.com.orlandoburli.personalerp.facades.rh.tabelasalariominimo;

import br.com.orlandoburli.core.dao.rh.TabelaSalarioMinimoDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioMinimoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TabelaSalarioMinimoConsultaFacade extends BaseConsultaFlexFacade<TabelaSalarioMinimoVO, TabelaSalarioMinimoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaSalarioMinimoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaSalarioMinimoVO.class;
	}
}
package br.com.orlandoburli.personalerp.facades.rh.tabelasalariofamilia;

import br.com.orlandoburli.core.dao.rh.TabelaSalarioFamiliaDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioFamiliaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TabelaSalarioFamiliaConsultaFacade extends BaseConsultaFlexFacade<TabelaSalarioFamiliaVO, TabelaSalarioFamiliaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaSalarioFamiliaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaSalarioFamiliaVO.class;
	}

}

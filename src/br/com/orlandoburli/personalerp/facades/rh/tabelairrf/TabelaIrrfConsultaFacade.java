package br.com.orlandoburli.personalerp.facades.rh.tabelairrf;

import br.com.orlandoburli.core.dao.rh.TabelaIrrfDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TabelaIrrfConsultaFacade extends BaseConsultaFlexFacade<TabelaIrrfVO, TabelaIrrfDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataInicialVigenciaTabelaIrrf DESC, ValorInicialTabelaIrrf");
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaIrrfDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaIrrfVO.class;
	}
}

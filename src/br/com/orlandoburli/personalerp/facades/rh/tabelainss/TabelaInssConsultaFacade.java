package br.com.orlandoburli.personalerp.facades.rh.tabelainss;

import br.com.orlandoburli.core.dao.rh.TabelaInssDAO;
import br.com.orlandoburli.core.vo.rh.TabelaInssVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TabelaInssConsultaFacade extends BaseConsultaFlexFacade<TabelaInssVO, TabelaInssDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataInicialVigenciaTabelaInss DESC, ValorInicialTabelaInss");
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaInssDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaInssVO.class;
	}
}
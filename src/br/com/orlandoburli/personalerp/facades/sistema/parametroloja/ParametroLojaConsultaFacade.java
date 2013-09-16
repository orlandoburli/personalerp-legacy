package br.com.orlandoburli.personalerp.facades.sistema.parametroloja;

import br.com.orlandoburli.core.dao.sistema.ParametroDAO;
import br.com.orlandoburli.core.vo.sistema.ParametroVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ParametroLojaConsultaFacade extends BaseConsultaFlexFacade<ParametroVO, ParametroDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoParametro(getFiltro() + "%");
		setOrderFields("");
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ParametroDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ParametroVO.class;
	}
}
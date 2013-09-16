package br.com.orlandoburli.personalerp.facades.sistema.parametro;

import br.com.orlandoburli.core.dao.sistema.ParametroDAO;
import br.com.orlandoburli.core.vo.sistema.ParametroVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ParametroConsultaFacade extends BaseConsultaFlexFacade<ParametroVO, ParametroDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoParametro(getFiltro() + "%");
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
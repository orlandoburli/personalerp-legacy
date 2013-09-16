package br.com.orlandoburli.personalerp.facades.rh.faltafuncionario;

import br.com.orlandoburli.core.dao.rh.FaltaFuncionarioDAO;
import br.com.orlandoburli.core.vo.rh.FaltaFuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class FaltaFuncionarioConsultaFacade extends BaseConsultaFlexFacade<FaltaFuncionarioVO, FaltaFuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeFuncionario(getFiltro() + "%");
		setOrderFields("DataFaltaFuncionario DESC");
	}

	@Override
	protected Class<?> getDAOClass() {
		return FaltaFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FaltaFuncionarioVO.class;
	}
}
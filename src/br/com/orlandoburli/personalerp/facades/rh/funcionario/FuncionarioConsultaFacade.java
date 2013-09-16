package br.com.orlandoburli.personalerp.facades.rh.funcionario;

import br.com.orlandoburli.core.dao.rh.FuncionarioDAO;
import br.com.orlandoburli.core.vo.rh.FuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class FuncionarioConsultaFacade extends BaseConsultaFlexFacade<FuncionarioVO, FuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeFuncionario(getFiltro() + "%");
		setOrderFields("NomeFuncionario");
	}

	@Override
	protected Class<?> getDAOClass() {
		return FuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FuncionarioVO.class;
	}
}
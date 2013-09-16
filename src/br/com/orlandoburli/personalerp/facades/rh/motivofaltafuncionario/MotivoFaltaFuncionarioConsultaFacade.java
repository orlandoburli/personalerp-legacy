package br.com.orlandoburli.personalerp.facades.rh.motivofaltafuncionario;

import br.com.orlandoburli.core.dao.rh.MotivoFaltaFuncionarioDAO;
import br.com.orlandoburli.core.vo.rh.MotivoFaltaFuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MotivoFaltaFuncionarioConsultaFacade extends BaseConsultaFlexFacade<MotivoFaltaFuncionarioVO, MotivoFaltaFuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoMotivoFaltaFuncionario("%" + getFiltro() + "%");
		setOrderFields("DescricaoMotivoFaltaFuncionario");
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotivoFaltaFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotivoFaltaFuncionarioVO.class;
	}
}
package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.motorista;

import br.com.orlandoburli.core.dao.cadastro.madeireira.MotoristaDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.MotoristaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MotoristaConsultaFacade extends BaseConsultaFlexFacade<MotoristaVO, MotoristaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeMotorista(this.getFiltro() + "%");
		this.setOrderFields("NomeMotorista");
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotoristaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotoristaVO.class;
	}
}
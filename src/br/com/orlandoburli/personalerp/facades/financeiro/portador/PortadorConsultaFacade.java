package br.com.orlandoburli.personalerp.facades.financeiro.portador;

import br.com.orlandoburli.core.dao.financeiro.PortadorDAO;
import br.com.orlandoburli.core.vo.financeiro.PortadorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PortadorConsultaFacade extends BaseConsultaFlexFacade<PortadorVO, PortadorDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomePortador(getFiltro() + "%");
		this.setOrderFields("NomePortador");
	}

	@Override
	protected Class<?> getDAOClass() {
		return PortadorDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return PortadorVO.class;
	}

}

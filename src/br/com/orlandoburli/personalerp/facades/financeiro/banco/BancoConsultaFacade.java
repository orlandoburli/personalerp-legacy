package br.com.orlandoburli.personalerp.facades.financeiro.banco;

import br.com.orlandoburli.core.dao.financeiro.BancoDAO;
import br.com.orlandoburli.core.vo.financeiro.BancoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class BancoConsultaFacade extends BaseConsultaFlexFacade<BancoVO, BancoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeBanco("%" + getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return BancoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return BancoVO.class;
	}
}
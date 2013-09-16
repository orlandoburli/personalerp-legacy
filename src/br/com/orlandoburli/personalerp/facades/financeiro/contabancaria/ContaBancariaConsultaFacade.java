package br.com.orlandoburli.personalerp.facades.financeiro.contabancaria;

import br.com.orlandoburli.core.dao.financeiro.ContaBancariaDAO;
import br.com.orlandoburli.core.vo.financeiro.ContaBancariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ContaBancariaConsultaFacade extends BaseConsultaFlexFacade<ContaBancariaVO, ContaBancariaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoContaBancaria("%" + getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ContaBancariaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ContaBancariaVO.class;
	}
}
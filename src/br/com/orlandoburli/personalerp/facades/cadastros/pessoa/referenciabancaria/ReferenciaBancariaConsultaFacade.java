package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.referenciabancaria;

import br.com.orlandoburli.core.dao.cadastro.pessoa.ReferenciaBancariaDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.ReferenciaBancariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ReferenciaBancariaConsultaFacade extends BaseConsultaFlexFacade<ReferenciaBancariaVO, ReferenciaBancariaDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return ReferenciaBancariaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ReferenciaBancariaVO.class;
	}
}
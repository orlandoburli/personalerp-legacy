package br.com.orlandoburli.personalerp.facades.cadastros.pessoa.referenciacomercial;

import br.com.orlandoburli.core.dao.cadastro.pessoa.ReferenciaComercialDAO;
import br.com.orlandoburli.core.vo.cadastro.pessoa.ReferenciaComercialVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ReferenciaComercialConsultaFacade extends BaseConsultaFlexFacade<ReferenciaComercialVO, ReferenciaComercialDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {}

	@Override
	protected Class<?> getDAOClass() {
		return ReferenciaComercialDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ReferenciaComercialVO.class;
	}
}
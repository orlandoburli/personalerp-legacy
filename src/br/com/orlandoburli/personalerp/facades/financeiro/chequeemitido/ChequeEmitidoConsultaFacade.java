package br.com.orlandoburli.personalerp.facades.financeiro.chequeemitido;

import br.com.orlandoburli.core.dao.financeiro.ChequeEmitidoDAO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ChequeEmitidoConsultaFacade extends BaseConsultaFlexFacade<ChequeEmitidoVO, ChequeEmitidoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("StatusChequeEmitido DESC, BomParaChequeEmitido, DataEmissaoChequeEmitido");
		getFilter().setDestinatarioChequeEmitido(getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return ChequeEmitidoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ChequeEmitidoVO.class;
	}
}
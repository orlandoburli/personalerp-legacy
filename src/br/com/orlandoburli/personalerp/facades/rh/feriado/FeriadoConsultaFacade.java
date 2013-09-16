package br.com.orlandoburli.personalerp.facades.rh.feriado;

import br.com.orlandoburli.core.dao.rh.FeriadoDAO;
import br.com.orlandoburli.core.vo.rh.FeriadoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class FeriadoConsultaFacade extends BaseConsultaFlexFacade<FeriadoVO, FeriadoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoFeriado(getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return FeriadoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FeriadoVO.class;
	}
}
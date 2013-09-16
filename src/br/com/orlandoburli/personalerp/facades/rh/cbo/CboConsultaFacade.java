package br.com.orlandoburli.personalerp.facades.rh.cbo;

import br.com.orlandoburli.core.dao.rh.CboDAO;
import br.com.orlandoburli.core.vo.rh.CboVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class CboConsultaFacade extends BaseConsultaFlexFacade<CboVO, CboDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoCbo("%" + getFiltro() + "%");
		setOrderFields("DescricaoCbo");
	}

	@Override
	protected Class<?> getDAOClass() {
		return CboDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return CboVO.class;
	}
}
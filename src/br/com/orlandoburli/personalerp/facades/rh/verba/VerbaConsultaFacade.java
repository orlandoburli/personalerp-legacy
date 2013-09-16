package br.com.orlandoburli.personalerp.facades.rh.verba;

import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class VerbaConsultaFacade extends BaseConsultaFlexFacade<VerbaVO, VerbaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoVerba("%" + getFiltro() + "%");
		this.setOrderFields("DescricaoVerba");
	}

	@Override
	protected Class<?> getDAOClass() {
		return VerbaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return VerbaVO.class;
	}
}
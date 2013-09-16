package br.com.orlandoburli.personalerp.facades.rh.verbacontratotrabalho;

import br.com.orlandoburli.core.dao.rh.VerbaContratoTrabalhoDAO;
import br.com.orlandoburli.core.vo.rh.VerbaContratoTrabalhoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class VerbaContratoTrabalhoConsultaFacade extends BaseConsultaFlexFacade<VerbaContratoTrabalhoVO, VerbaContratoTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}

	@Override
	protected Class<?> getDAOClass() {
		return VerbaContratoTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return VerbaContratoTrabalhoVO.class;
	}
}
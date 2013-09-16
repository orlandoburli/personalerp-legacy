package br.com.orlandoburli.personalerp.facades.rh.contratotrabalho;

import br.com.orlandoburli.core.dao.rh.ContratoTrabalhoDAO;
import br.com.orlandoburli.core.vo.rh.ContratoTrabalhoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ContratoTrabalhoConsultaFacade extends BaseConsultaFlexFacade<ContratoTrabalhoVO, ContratoTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("DataInicioContratoTrabalho DESC");
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ContratoTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ContratoTrabalhoVO.class;
	}
}
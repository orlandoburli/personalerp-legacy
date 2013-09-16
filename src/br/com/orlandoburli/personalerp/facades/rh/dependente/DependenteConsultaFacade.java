package br.com.orlandoburli.personalerp.facades.rh.dependente;

import br.com.orlandoburli.core.dao.rh.DependenteDAO;
import br.com.orlandoburli.core.vo.rh.DependenteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class DependenteConsultaFacade extends BaseConsultaFlexFacade<DependenteVO, DependenteDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeDependente(getFiltro() + "%");
		setOrderFields("NomeDependente");
	}

	@Override
	protected Class<?> getDAOClass() {
		return DependenteDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return DependenteVO.class;
	}
}
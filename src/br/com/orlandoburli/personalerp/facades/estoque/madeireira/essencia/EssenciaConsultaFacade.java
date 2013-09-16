package br.com.orlandoburli.personalerp.facades.estoque.madeireira.essencia;

import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EssenciaConsultaFacade extends BaseConsultaFlexFacade<EssenciaVO, EssenciaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeEssencia(getFiltro() + "%");
		this.setOrderFields("NomeEssencia");
	}

	@Override
	protected Class<?> getDAOClass() {
		return EssenciaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return EssenciaVO.class;
	}
}
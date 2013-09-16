package br.com.orlandoburli.personalerp.facades.estoque.estoque;

import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EstoqueConsultaFacade extends BaseConsultaFlexFacade<EstoqueVO, EstoqueDAO> {

	private static final long serialVersionUID = 1L;
	private String MostrarTodasLojas;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeLoja(getFiltro() + "%");
//		if (getMostrarTodasLojas() == null || !getMostrarTodasLojas().equalsIgnoreCase("S")) {
//			filter.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
//			filter.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
//		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return EstoqueDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return EstoqueVO.class;
	}

	public void setMostrarTodasLojas(String mostrarTodasLojas) {
		MostrarTodasLojas = mostrarTodasLojas;
	}

	public String getMostrarTodasLojas() {
		return MostrarTodasLojas;
	}
}

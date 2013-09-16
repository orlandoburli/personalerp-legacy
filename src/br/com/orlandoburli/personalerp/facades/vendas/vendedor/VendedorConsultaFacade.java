package br.com.orlandoburli.personalerp.facades.vendas.vendedor;

import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class VendedorConsultaFacade extends BaseConsultaFlexFacade<VendedorVO, VendedorDAO> {

	private static final long	serialVersionUID	= 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeVendedor(getFiltro() + "%");
	}

	@Override
	protected Class<?> getDAOClass() {
		return VendedorDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return VendedorVO.class;
	}

}

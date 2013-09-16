package br.com.orlandoburli.personalerp.facades.estoque.madeireira.madeirabeneficiada;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MadeiraBeneficiadaConsultaFacade extends BaseConsultaFlexFacade<ProdutoVO, ProdutoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoProduto(getFiltro() + "%");
		this.getFilter().setTipoEstoqueProduto("B"); // Tipo Madeira Beneficiada
		if (getOrderFields() == null || getOrderFields().trim().equals("")) {
			setOrderFields("DescricaoProduto");
		}
	}

	@Override
	protected Class<?> getDAOClass() {
		return ProdutoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ProdutoVO.class;
	}
}
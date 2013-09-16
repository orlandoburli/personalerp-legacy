package br.com.orlandoburli.personalerp.facades.estoque.madeireira.madeiraserrada;

import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MadeiraSerradaConsultaFacade extends BaseConsultaFlexFacade<ProdutoVO, ProdutoDAO> {

	private static final long serialVersionUID = 1L;
	private String Tipo;
	
	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoProduto(getFiltro() + "%");
		this.getFilter().setTipoEstoqueProduto("S"); // Tipo Madeira Serrada
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

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getTipo() {
		return Tipo;
	}
}
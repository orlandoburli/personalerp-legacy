package br.com.orlandoburli.personalerp.facades.estoque.subproduto;

import br.com.orlandoburli.core.dao.estoque.SubProdutoDAO;
import br.com.orlandoburli.core.vo.estoque.SubProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class SubProdutoConsultaFacade extends BaseConsultaFlexFacade<SubProdutoVO, SubProdutoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoSubProduto(getFiltro() + "%");
		setOrderFields("DescricaoSubProduto");
	}
}
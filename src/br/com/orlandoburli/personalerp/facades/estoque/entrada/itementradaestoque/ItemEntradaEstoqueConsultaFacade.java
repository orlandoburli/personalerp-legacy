package br.com.orlandoburli.personalerp.facades.estoque.entrada.itementradaestoque;

import br.com.orlandoburli.core.dao.estoque.entradaestoque.ItemEntradaEstoqueDAO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.ItemEntradaEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemEntradaEstoqueConsultaFacade extends BaseConsultaFlexFacade<ItemEntradaEstoqueVO, ItemEntradaEstoqueDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	
	protected void doBeforeFilter() {
		getFilter().setDescricaoProduto(getFiltro() + "%");
		setOrderFields("DescricaoProduto");
	}
}
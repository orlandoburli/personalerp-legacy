package br.com.orlandoburli.personalerp.facades.vendas.pedidofrigorifico.itempedidofrigorifico;

import br.com.orlandoburli.core.dao.vendas.pedido.ItemPedidoDAO;
import br.com.orlandoburli.core.vo.vendas.pedido.ItemPedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemPedidoFrigorificoConsultaFacade extends BaseConsultaFlexFacade<ItemPedidoVO, ItemPedidoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeProduto(getFiltro() + "%");
		setOrderFields("NomeProduto");
	}
}
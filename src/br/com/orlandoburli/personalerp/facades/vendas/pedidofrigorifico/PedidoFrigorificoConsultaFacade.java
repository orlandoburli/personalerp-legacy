package br.com.orlandoburli.personalerp.facades.vendas.pedidofrigorifico;

import br.com.orlandoburli.core.dao.vendas.pedido.PedidoDAO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class PedidoFrigorificoConsultaFacade extends BaseConsultaFlexFacade<PedidoVO, PedidoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeCliente(getFiltro() + "%");
		setOrderFields("DataPedido DESC, CodigoPedido DESC");
	}
}
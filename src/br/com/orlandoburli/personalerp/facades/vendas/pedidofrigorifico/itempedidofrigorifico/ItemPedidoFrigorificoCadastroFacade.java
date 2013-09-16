package br.com.orlandoburli.personalerp.facades.vendas.pedidofrigorifico.itempedidofrigorifico;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.ItemPedidoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.vo.vendas.pedido.ItemPedidoVO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.facades.vendas.pedidofrigorifico.PedidoFrigorificoCadastroFacade;

public class ItemPedidoFrigorificoCadastroFacade extends BaseCadastroFlexFacade<ItemPedidoVO, ItemPedidoDAO>{

	private static final long serialVersionUID = 1L;

	public ItemPedidoFrigorificoCadastroFacade() {
		super();
	}
	
	@Override
	public void doAfterSave() {
		try {
			calculaTotalItem(getVo(), getGenericDao());
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
		}
	}
	
	@Override
	public void doAfterDelete() {
		try {
			calculaTotalItem(getVo(), getGenericDao());
		} catch (SQLException e) {
			this.messages.add(new MessageVO(e.getMessage()));
		}
	}
	
	public static void calculaTotalItem(ItemPedidoVO itemPedido, GenericDAO dao) throws SQLException {
		dao.setAutoCommit(false);
		
		itemPedido.setValorTotalItemPedido(
				itemPedido.getValorUnitarioItemPedido()
					.multiply(itemPedido.getQuantidadeItemPedido())
					.setScale(2, BigDecimal.ROUND_CEILING)
					.subtract(itemPedido.getValorDescontoItemPedido())
				);
		
		dao.persist(itemPedido);
		
		PedidoVO pedido = new PedidoVO();
		pedido.setCodigoEmpresa(itemPedido.getCodigoEmpresa());
		pedido.setCodigoLoja(itemPedido.getCodigoLoja());
		pedido.setCodigoPedido(itemPedido.getCodigoPedido());
		
		pedido = (PedidoVO) dao.get(pedido);
		
		PedidoFrigorificoCadastroFacade.calculaTotal(pedido, dao, false);
		
		dao.commit();
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoVO produto = new ProdutoVO();
			produto.setTipoEstoqueProduto("V"); // Somente itens para venda
			write(Utils.voToXml(getGenericDao().getList(produto), getGenericDao().getListCount(produto)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
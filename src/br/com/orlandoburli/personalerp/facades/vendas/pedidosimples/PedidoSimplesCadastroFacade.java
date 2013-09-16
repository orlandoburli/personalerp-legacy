package br.com.orlandoburli.personalerp.facades.vendas.pedidosimples;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.Constants;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.sistema.ParametroLojaDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.ItemPedidoDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.PedidoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.LinhaProdutoVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubProdutoVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.pedido.ItemPedidoVO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.vo.vendas.tabelapreco.TabelaPrecoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.personalerp.facades.estoque.produto.ProdutoConsultaFacade;

public class PedidoSimplesCadastroFacade extends BaseCadastroFlexFacade<PedidoVO, PedidoDAO> {

	private static final long serialVersionUID = 1L;

	@OutjectSession
	private List<ItemPedidoVO> itens;

	@OutjectSession
	// Array de itens que serão excluídos ao fechamento do pedido.
	private List<ItemPedidoVO> itensExclusao;

	private Integer PosicaoItem;
	@InstantiateObject
	private ProdutoVO produto;
	@InstantiateObject
	private ItemPedidoVO item;

	public PedidoSimplesCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
		addNewValidator(new NotEmptyValidator("DataPedido", "Data do Pedido"));

		this.itens = new ArrayList<ItemPedidoVO>();
		this.itensExclusao = new ArrayList<ItemPedidoVO>();
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		if (this.itens.size() <= 0) {
			this.messages.add(new MessageVO("Nenhum item digitado!"));
			return false;
		}
		return super.doBeforeSave();
	}

	@Override
	public boolean doBeforeUpdate() throws SQLException {
		try {
			PedidoVO copia = dao.get(getVo());

			if (!copia.getStatusPedido().equals("A")) {
				this.messages.add(new MessageVO("Pedido já foi processado e não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeUpdate();
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setStatusPedido("A"); // Aberto - Em digitacao
		getVo().setCodigoPdv(1); // Valor default temporario
		return super.doBeforeInsert();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		PedidoVO copia = dao.get(getVo());

		if (!copia.getStatusPedido().equals("A")) {
			this.messages.add(new MessageVO("Pedido já foi processado e não pode ser excluído!"));
			return false;
		}

		ItemPedidoVO itemFilter = new ItemPedidoVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoPedido(getVo().getCodigoPedido());
		itemFilter.setCodigoPdv(getVo().getCodigoPdv());
		
		List<IValueObject> itens = getGenericDao().getList(itemFilter);

		for (IValueObject item : itens) {
			getGenericDao().remove(item);
		}

		return super.doBeforeDelete();
	}

	@Override
	public void doAfterSave() throws SQLException {
		// Salvar os itens

		// Salva os itens...
		for (ItemPedidoVO item : itens) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setCodigoPedido(getVo().getCodigoPedido());
			item.setCodigoPdv(getVo().getCodigoPdv());
			getGenericDao().persist(item);
		}

		// Remove quem está na lista de exclusao
		for (ItemPedidoVO item : itensExclusao) {
			getGenericDao().remove(item);
		}

		calculaTotal(getVo(), getGenericDao(), false);

		itens = null;
		itensExclusao = null;

		super.doAfterSave();
	}

	@IgnoreMethodAuthentication
	public void limpar() {
		this.itens = new ArrayList<ItemPedidoVO>();
		this.itensExclusao = new ArrayList<ItemPedidoVO>();
	}

	@IgnoreMethodAuthentication
	public void listaritens() {
		write(Utils.voToXml(itens));
	}

	@IgnoreMethodAuthentication
	public void loaditens() {
		ItemPedidoVO itemFilter = new ItemPedidoVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoPedido(getVo().getCodigoPedido());

		try {
			this.itens = new ItemPedidoDAO().getList(itemFilter);
			listaritens();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void adicionaritem() {

		try {
			//
			if (item.getCodigoEmpresaProduto() == null || item.getCodigoLojaProduto() == null || item.getCodigoProduto() == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}
			if (item.getQuantidadeItemPedido() == null || item.getQuantidadeItemPedido().compareTo(BigDecimal.ZERO) <= 0) {
				writeErrorMessage("Informe a quantidade do item!");
				return;
			}
			if (item.getValorTotalItemPedido() == null) {
				writeErrorMessage("Informe o valor total do item!");
				return;
			}

			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
			produto.setCodigoLoja(item.getCodigoLojaProduto());
			produto.setCodigoProduto(item.getCodigoProduto());

			produto = (ProdutoVO) getGenericDao().get(produto);

			if (produto == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}

			item.setNewRecord(true);
			item.setNomeProduto(produto.getDescricaoProduto());
			item.setValorUnitarioItemPedido(BigDecimal.ZERO);

			itens.add(item);
			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void alteraritem() {
		if (PosicaoItem == null || PosicaoItem < 0) {
			writeErrorMessage("Selecione um item para alterar!");
			return;
		}

		if (PosicaoItem >= itens.size()) {
			writeErrorMessage("Posição inválida!");
			return;
		}

		try {
			//
			if (item.getCodigoEmpresaProduto() == null || item.getCodigoLojaProduto() == null || item.getCodigoProduto() == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}
			if (item.getQuantidadeItemPedido() == null || item.getQuantidadeItemPedido().compareTo(BigDecimal.ZERO) <= 0) {
				writeErrorMessage("Informe a quantidade do item!");
				return;
			}
			if (item.getValorTotalItemPedido() == null) {
				writeErrorMessage("Informe o valor total do item!");
				return;
			}

			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
			produto.setCodigoLoja(item.getCodigoLojaProduto());
			produto.setCodigoProduto(item.getCodigoProduto());

			produto = (ProdutoVO) getGenericDao().get(produto);

			if (produto == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}

			item.setNewRecord(false);
			item.setNomeProduto(produto.getDescricaoProduto());
			item.setValorUnitarioItemPedido(BigDecimal.ZERO);

			// itens.set(PosicaoItem, item);

			itens.get(PosicaoItem).setCodigoEmpresaProduto(item.getCodigoEmpresaProduto());
			itens.get(PosicaoItem).setCodigoLojaProduto(item.getCodigoLojaProduto());
			itens.get(PosicaoItem).setCodigoProduto(item.getCodigoProduto());
			itens.get(PosicaoItem).setFlagComissaoVendedorPedido(item.getFlagComissaoVendedorPedido());
			itens.get(PosicaoItem).setQuantidadeItemPedido(item.getQuantidadeItemPedido());
			itens.get(PosicaoItem).setValorTotalItemPedido(item.getValorTotalItemPedido());

			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void removeritem() {
		if (PosicaoItem == null || PosicaoItem < 0) {
			writeErrorMessage("Selecione um item para excluir!");
			return;
		}

		if (PosicaoItem >= itens.size()) {
			writeErrorMessage("Posição inválida!");
			return;
		}

		ItemPedidoVO item = itens.get(PosicaoItem);

		if (item.IsNew()) {
			// Simplesmente remove
			itens.remove(item);
		} else {
			// Guarda na lista de itens a excluir
			itens.remove(item);
			itensExclusao.add(item);
		}

		write("ok");
	}

	@IgnoreMethodAuthentication
	public void total() {
		BigDecimal total = BigDecimal.ZERO;

		for (ItemPedidoVO item : itens) {
			if (item.getValorTotalItemPedido() != null) {
				total = total.add(item.getValorTotalItemPedido());
			}
		}

		getVo().setValorTotalItensPedido(total);

		if (getVo().getValorFretePedido() != null) {
			total = total.add(getVo().getValorFretePedido());
		}

		if (getVo().getValorDescontoPedido() != null) {
			total = total.subtract(getVo().getValorDescontoPedido());
		}

		getVo().setValorTotalPedido(total);

		write(Utils.voToXml(getVo()));
	}

	public static void calculaTotal(PedidoVO pedido, GenericDAO dao, boolean commit) throws SQLException {
		if (!commit) {
			dao.setAutoCommit(false);
		}

		ItemPedidoVO _filter = new ItemPedidoVO();

		_filter.setCodigoEmpresa(pedido.getCodigoEmpresa());
		_filter.setCodigoLoja(pedido.getCodigoLoja());
		_filter.setCodigoPedido(pedido.getCodigoPedido());

		BigDecimal total = BigDecimal.ZERO;

		for (IValueObject ivo : dao.getList(_filter)) {
			ItemPedidoVO item = (ItemPedidoVO) ivo;

			if (item.getValorTotalItemPedido() != null) {
				total = total.add(item.getValorTotalItemPedido());
			}
		}

		pedido.setValorTotalItensPedido(total);

		if (pedido.getValorFretePedido() != null) {
			total = total.add(pedido.getValorFretePedido());
		}

		if (pedido.getValorDescontoPedido() != null) {
			total = total.subtract(pedido.getValorDescontoPedido());
		}

		pedido.setValorTotalPedido(total);

		dao.persist(pedido);

		if (commit) {
			dao.commit();
		}
	}

	@IgnoreMethodAuthentication
	public void dados() {
		try {
			// Limpa os dados anteriores...
			limpar();

			// List com todos os itens
			List<IValueObject> list = new ArrayList<IValueObject>();

			// Produtos
			ProdutoVO produto = new ProdutoVO();
			produto.setSituacaoProduto("N");
			List<IValueObject> listProdutos = getGenericDao().getList(produto);
			
			// Tabela de preco padrao do sistema
			Integer CodigoTabelaPreco = new ParametroLojaDAO().getIntegerParametro(Constants.Parameters.Estoque.TABELA_PRECO_PADRAO, getLojasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja());
			TabelaPrecoVO tabela = new TabelaPrecoVO();
			tabela.setCodigoTabelaPreco(CodigoTabelaPreco);
			tabela = (TabelaPrecoVO) getGenericDao().get(tabela);
			
			for (IValueObject i : listProdutos) {
				ProdutoConsultaFacade.precoProdutoTabela((ProdutoVO) i, getLojasessao(), tabela, getGenericDao());
			}
			
			list.addAll(listProdutos);

			// Vendedores
			VendedorVO vendedor = new VendedorVO();
			vendedor.setSituacaoVendedor("N"); // Somente ativos
			
			list.addAll(getGenericDao().getList(vendedor));
			
//			// Linha de produtos
//			LinhaProdutoVO linha = new LinhaProdutoVO();
//			list.addAll(_dao.getList(linha));

			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void linhas() {
		LinhaProdutoVO linhaFilter = new LinhaProdutoVO();
		linhaFilter.setCodigoEmpresaProduto(produto.getCodigoEmpresa());
		linhaFilter.setCodigoLojaProduto(produto.getCodigoLoja());
		linhaFilter.setCodigoProduto(produto.getCodigoProduto());
		
		try {
			write(Utils.voToXml(getGenericDao().getList(linhaFilter)));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void processar() {
		getGenericDao().setAutoCommit(false);
		try {
			// Le dados do pedido
			setVo((PedidoVO) getGenericDao().get(getVo()));

			if (getVo().getStatusPedido().equals("P")) {
				writeErrorMessage("Este pedido já foi processado!");
				return;
			}

			// Baixa Estoque
			ItemPedidoVO itemFilter = new ItemPedidoVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoPedido(getVo().getCodigoPedido());

			List<IValueObject> listItensPedido = getGenericDao().getList(itemFilter);

			for (IValueObject i : listItensPedido) {
				ItemPedidoVO item = (ItemPedidoVO) i;

				// Busca o produto
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				produto = (ProdutoVO) getGenericDao().get(produto);

				if (produto.getFlagTemSubProduto().equals("N")) {

					processarItem(produto, item.getQuantidadeItemPedido());

				} else {

					// Busca os subprodutos deste item
					List<IValueObject> subprodutos = getSubProdutos(produto, getGenericDao());

					for (IValueObject s : subprodutos) {
						SubProdutoVO subProduto = (SubProdutoVO) s;

						// Busca o produto deste subproduto
						ProdutoVO sub = new ProdutoVO();
						sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
						sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
						sub.setCodigoProduto(subProduto.getCodigoSubProduto());
						sub = (ProdutoVO) getGenericDao().get(sub);

						processarItem(sub, subProduto.getQuantidadeSubProduto());
					}
				}
			}

			getVo().setStatusPedido("P");
			getGenericDao().persist(getVo());
			getGenericDao().commit();

			write("ok");
		} catch (SQLException ex) {
			getGenericDao().rollback();
			ex.printStackTrace();
			writeErrorMessage(ex.getMessage());
		}
	}

	private void processarItem(ProdutoVO produto, BigDecimal quantidade) throws SQLException {
		EstoqueVO estoque = new EstoqueVO();
		estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
		estoque.setCodigoLoja(produto.getCodigoLoja());
		estoque.setCodigoProduto(produto.getCodigoProduto());
		estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
		estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());

		estoque = (EstoqueVO) getGenericDao().get(estoque);

		if (estoque == null) {
			estoque = new EstoqueVO();
			estoque.setNewRecord(true);

			estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
			estoque.setCodigoLoja(produto.getCodigoLoja());
			estoque.setCodigoProduto(produto.getCodigoProduto());
			estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
			estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());

			estoque.setEstoqueFiscal(BigDecimal.ZERO);
			estoque.setEstoqueFisico(BigDecimal.ZERO);
		}
		
		if (estoque.getQuantidadeDisplayEstoque() == null) {
			estoque.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
		}
		if (estoque.getQuantidadeGavetaEstoque() == null) {
			estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
		}

		MovimentacaoEstoqueVO movimentacao = new MovimentacaoEstoqueVO();
		movimentacao.setNewRecord(true);
		movimentacao.setCodigoEmpresa(produto.getCodigoEmpresa());
		movimentacao.setCodigoLoja(produto.getCodigoLoja());
		movimentacao.setCodigoProduto(produto.getCodigoProduto());
		movimentacao.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
		movimentacao.setCodigoLojaEstoque(getVo().getCodigoLoja());
		movimentacao.setDataHoraMovimentacaoEstoque(Utils.getNow());
		movimentacao.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR);

		movimentacao.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
		movimentacao.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
		movimentacao.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

		movimentacao.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
		movimentacao.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());

		// Baixa o estoque...
		estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(quantidade));
		movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());

		estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(quantidade));
		movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
		
		/// ESTOQUE GAVETA / DISPLAY
		
		// Verifica se o estoque gaveta tem os itens...
		BigDecimal quantidadeRestante = quantidade;
		
		// Se o estoque gaveta tiver o valor maior ou igual do pedido, diminui da gaveta
		if (estoque.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) >= 0) {
			estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().subtract(quantidadeRestante));
			quantidadeRestante = BigDecimal.ZERO;
		} else if (estoque.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) < 0 && estoque.getQuantidadeGavetaEstoque().compareTo(BigDecimal.ZERO) > 0) {
			// Se o estoque gaveta tiver o valor menor, mas for maior que zero, diminui do estoque gaveta e deixa o resto para o display.
			quantidadeRestante = quantidadeRestante.subtract(estoque.getQuantidadeGavetaEstoque());
			estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
		}
		
		// Se ainda tem quantidade a subtrair, subtrai-se do estoque display
		if (quantidadeRestante.compareTo(BigDecimal.ZERO) > 0) {
			estoque.setQuantidadeDisplayEstoque(estoque.getQuantidadeDisplayEstoque().subtract(quantidadeRestante));
		}

		movimentacao.setObservacaoMovimentacaoEstoque("Movimentação gerada pelo pedido n.° " + getVo().getCodigoPedido() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja());
		movimentacao.setDocumentoMovimentacaoEstoque(getVo().getCodigoPedido() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

		getGenericDao().persist(estoque);
		getGenericDao().persist(movimentacao);
	}

	public void estornar() {
		try {
			getGenericDao().setAutoCommit(false);

			// Le dados do pedido
			setVo((PedidoVO) getGenericDao().get(getVo()));

			if (getVo().getStatusPedido().equals("A")) {
				writeErrorMessage("Este pedido não foi processado ainda!");
				return;
			}

			// Baixa Estoque
			ItemPedidoVO itemFilter = new ItemPedidoVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoPedido(getVo().getCodigoPedido());

			List<IValueObject> list = getGenericDao().getList(itemFilter);

			for (IValueObject i : list) {
				ItemPedidoVO item = (ItemPedidoVO) i;

				// Busca o produto
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				produto = (ProdutoVO) getGenericDao().get(produto);

				//
				if (produto.getFlagTemSubProduto().equals("N")) {
					estornarItem(produto, item.getQuantidadeItemPedido());
				} else {
					// Busca os subprodutos deste item
					List<IValueObject> subprodutos = getSubProdutos(produto, getGenericDao());

					for (IValueObject s : subprodutos) {
						SubProdutoVO subProduto = (SubProdutoVO) s;

						// Busca o produto deste subproduto
						ProdutoVO sub = new ProdutoVO();
						sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
						sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
						sub.setCodigoProduto(subProduto.getCodigoSubProduto());
						sub = (ProdutoVO) getGenericDao().get(sub);

						estornarItem(sub, subProduto.getQuantidadeSubProduto());
					}
				}
			}

			getVo().setStatusPedido("A");
			getGenericDao().persist(getVo());
			getGenericDao().commit();
			write("ok");
		} catch (SQLException ex) {
			getGenericDao().rollback();
			ex.printStackTrace();
			writeErrorMessage(ex.getMessage());
		}
	}

	private void estornarItem(ProdutoVO produto, BigDecimal quantidade) throws SQLException {
		EstoqueVO estoque = new EstoqueVO();
		estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
		estoque.setCodigoLoja(produto.getCodigoLoja());
		estoque.setCodigoProduto(produto.getCodigoProduto());
		estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
		estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());

		estoque = (EstoqueVO) getGenericDao().get(estoque);

		if (estoque == null) {
			estoque = new EstoqueVO();
			estoque.setNewRecord(true);

			estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
			estoque.setCodigoLoja(produto.getCodigoLoja());
			estoque.setCodigoProduto(produto.getCodigoProduto());
			estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
			estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());

			estoque.setEstoqueFiscal(BigDecimal.ZERO);
			estoque.setEstoqueFisico(BigDecimal.ZERO);
		}
		
		if (estoque.getQuantidadeDisplayEstoque() == null) {
			estoque.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
		}
		if (estoque.getQuantidadeGavetaEstoque() == null) {
			estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
		}

		MovimentacaoEstoqueVO movimentacao = new MovimentacaoEstoqueVO();
		movimentacao.setNewRecord(true);
		movimentacao.setCodigoEmpresa(produto.getCodigoEmpresa());
		movimentacao.setCodigoLoja(produto.getCodigoLoja());
		movimentacao.setCodigoProduto(produto.getCodigoProduto());
		movimentacao.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
		movimentacao.setCodigoLojaEstoque(getVo().getCodigoLoja());
		movimentacao.setDataHoraMovimentacaoEstoque(Utils.getNow());
		movimentacao.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR);

		movimentacao.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
		movimentacao.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
		movimentacao.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

		movimentacao.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
		movimentacao.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());

		// Volta o estoque...
		estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(quantidade));
		movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());

		// Volta estoque fiscal
		estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(quantidade));
		movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
		
		// Volta os itens para o estoque GAVETA
		estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().add(quantidade));

		movimentacao.setObservacaoMovimentacaoEstoque("Estorno gerado pelo pedido n.° " + getVo().getCodigoPedido() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja());
		movimentacao.setDocumentoMovimentacaoEstoque(getVo().getCodigoPedido() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

		getGenericDao().persist(estoque);
		getGenericDao().persist(movimentacao);
	}

	private List<IValueObject> getSubProdutos(ProdutoVO produto, GenericDAO dao) throws SQLException {
		if (produto.getFlagTemSubProduto().equals("S")) {
			SubProdutoVO filter = new SubProdutoVO();
			filter.setCodigoEmpresa(produto.getCodigoEmpresa());
			filter.setCodigoLoja(produto.getCodigoLoja());
			filter.setCodigoProduto(produto.getCodigoProduto());

			return dao.getList(filter);
		}

		return null;
	}

	public void setItens(List<ItemPedidoVO> itens) {
		this.itens = itens;
	}

	public List<ItemPedidoVO> getItens() {
		return itens;
	}

	public void setItensExclusao(List<ItemPedidoVO> itensExclusao) {
		this.itensExclusao = itensExclusao;
	}

	public List<ItemPedidoVO> getItensExclusao() {
		return itensExclusao;
	}

	public void setPosicaoItem(Integer posicaoItem) {
		PosicaoItem = posicaoItem;
	}

	public Integer getPosicaoItem() {
		return PosicaoItem;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setItem(ItemPedidoVO item) {
		this.item = item;
	}

	public ItemPedidoVO getItem() {
		return item;
	}
}
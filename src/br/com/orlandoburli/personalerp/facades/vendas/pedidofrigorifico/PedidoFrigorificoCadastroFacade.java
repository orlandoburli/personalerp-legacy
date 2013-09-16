package br.com.orlandoburli.personalerp.facades.vendas.pedidofrigorifico;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.PedidoDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.financeiro.ParcelaPlanoPagamentoVendaVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoPagamentoVendaVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.pedido.ItemPedidoVO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoContaReceberVO;
import br.com.orlandoburli.core.vo.vendas.pedido.PedidoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.personalerp.facades.fiscal.nfsaida.NFSaidaCadastroFacade;

public class PedidoFrigorificoCadastroFacade extends BaseCadastroFlexFacade<PedidoVO, PedidoDAO>{

	private static final long serialVersionUID = 1L;
	
	private String FlagGerarNF;

	public PedidoFrigorificoCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
		addNewValidator(new NotEmptyValidator("DataPedido", "Data do Pedido"));
		addNewValidator(new NotEmptyValidator("CodigoPlanoPagamento", "Plano de Pagamento"));
		addNewValidator(new NotEmptyValidator("CodigoEnderecoPessoa", "Cliente"));
	}
	
	@Override
	public void doAfterSave() {
		try {
			calculaTotal(getVo(), getGenericDao(), true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setStatusPedido("A"); // Aberto - Em digitacao
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (this.getVo().getStatusPedido().equals("F")) {
			this.messages.add(new MessageVO("Este pedido foi fechado, não pode ser modificado!"));
			return false;
		}
		
		// Verifica se a pessoa do endereco de entrega é a mesma do pedido (caso informado)
		// Podem ser ENDERECOS diferentes, mas nao PESSOAS diferentes
		if (getVo().getCodigoEnderecoPessoaEntrega() != null) {
			if (!(getVo().getCodigoEmpresaEnderecoPessoa().equals(getVo().getCodigoEmpresaEntrega()) &&
			 	  getVo().getCodigoLojaEnderecoPessoa().equals(getVo().getCodigoLojaEntrega()) &&
				  getVo().getCodigoPessoa().equals(getVo().getCodigoPessoaEntrega()))) {
				
				this.messages.add(new MessageVO("Endereco de entrega diferente da pessoa do endereco do pedido!"));
				return false;
			}
		} else {
			// Seta o endereco de entrega igual ao de venda
			getVo().setCodigoEmpresaEntrega(getVo().getCodigoEmpresaEnderecoPessoa());
			getVo().setCodigoLojaEntrega(getVo().getCodigoLojaEnderecoPessoa());
			getVo().setCodigoPessoaEntrega(getVo().getCodigoPessoa());
			getVo().setCodigoEnderecoPessoaEntrega(getVo().getCodigoEnderecoPessoa());
		}
		return super.doBeforeSave();
	}
	
	public void processar() throws NfeException {
		boolean geraNF = false;
		if (getFlagGerarNF() != null && getFlagGerarNF().equals("S")) {
			geraNF = true;
		}
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
				
				EstoqueVO estoque = new EstoqueVO();
				estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				estoque.setCodigoLoja(item.getCodigoLojaProduto());
				estoque.setCodigoProduto(item.getCodigoProduto());
				estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());
				
				estoque = (EstoqueVO) getGenericDao().get(estoque);
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());
					
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO movimentacao = new MovimentacaoEstoqueVO();
				movimentacao.setNewRecord(true);
				movimentacao.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				movimentacao.setCodigoLoja(item.getCodigoLojaProduto());
				movimentacao.setCodigoProduto(item.getCodigoProduto());
				movimentacao.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				movimentacao.setCodigoLojaEstoque(getVo().getCodigoLoja());
				movimentacao.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacao.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR);
				
				movimentacao.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
				movimentacao.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				// Baixa o estoque...
				estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(item.getQuantidadeEstoqueItemPedido()));
				movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				// Somente baixa estoque fiscal se for gerada nota fiscal
				if (geraNF) {
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(item.getQuantidadeEstoqueItemPedido()));
					movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
				}
				movimentacao.setObservacaoMovimentacaoEstoque("Movimentação gerada pelo pedido n.° " + getVo().getCodigoPedido() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja());
				movimentacao.setDocumentoMovimentacaoEstoque(getVo().getCodigoPedido() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());
				
				getGenericDao().persist(estoque);
				getGenericDao().persist(movimentacao);
			}
			
			// Busca o plano de pagamento
			PlanoPagamentoVendaVO plano = new PlanoPagamentoVendaVO();
			plano.setCodigoPlanoPagamento(getVo().getCodigoPlanoPagamento());
			plano = (PlanoPagamentoVendaVO) getGenericDao().get(plano);
			
			// Calcula o Valor total da conta
			BigDecimal valorTotal = getVo().getValorTotalPedido();
			if (plano.getPercentualJurosPlanoPagamento() != null && plano.getPercentualJurosPlanoPagamento().compareTo(BigDecimal.ZERO) > 0) {
				valorTotal = valorTotal
						.add(valorTotal
						.multiply(plano.getPercentualJurosPlanoPagamento())
						.divide(new BigDecimal(100)));
			}
			
			// Busca parcelas do plano
			ParcelaPlanoPagamentoVendaVO _parc = new ParcelaPlanoPagamentoVendaVO();
			_parc.setCodigoPlanoPagamento(plano.getCodigoPlanoPagamento());
			List<IValueObject> listParcelas = getGenericDao().getList(_parc);
			
			// Divide o valor pelo numero de parcelas
			BigDecimal valorConta = valorTotal.divide(new BigDecimal(listParcelas.size()));
			int numParcela = 0;
			
			for (IValueObject i : listParcelas) {
				numParcela += 1;
				
				ParcelaPlanoPagamentoVendaVO parcela = (ParcelaPlanoPagamentoVendaVO) i;
				
				// Calcula o vencimento
				Date dataPedido = getVo().getDataPedido();
				Calendar dataVencimento = Utils.dateToCalendar(dataPedido);
				dataVencimento.add(Calendar.DAY_OF_MONTH, parcela.getDiasPagamentoParcela());
				
				// Gerar Conta a Receber
				ContaReceberVO contareceber = new ContaReceberVO();
				contareceber.setNewRecord(true);
				contareceber.setCodigoEmpresa(getVo().getCodigoEmpresa());
				contareceber.setCodigoLoja(getVo().getCodigoLoja());
				
				contareceber.setCodigoPlanoConta(1);
				contareceber.setCodigoCentroCusto(1);
				contareceber.setCodigoTipoDocumento(1);
				contareceber.setCodigoPortador(1);
				
				contareceber.setCodigoEmpresaCliente(getVo().getCodigoEmpresaEnderecoPessoa());
				contareceber.setCodigoLojaCliente(getVo().getCodigoLojaEnderecoPessoa());
				contareceber.setCodigoPessoaCliente(getVo().getCodigoPessoa());
				contareceber.setCodigoEnderecoPessoaCliente(getVo().getCodigoEnderecoPessoa());
				
				contareceber.setParcelaContaReceber(numParcela);
				contareceber.setNumeroParcelasContaReceber(listParcelas.size());
				contareceber.setDescricaoContaReceber("Conta a receber do pedido n° " + getVo().getCodigoPedido());
				contareceber.setDataLancamentoContaReceber(Utils.getNow());
				contareceber.setDataVencimentoContaReceber(Utils.calendarToDate(dataVencimento));
				contareceber.setDataEmissaoContaReceber(Utils.getToday());
				contareceber.setNumeroTituloContaReceber(getVo().getCodigoPedido() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());
				contareceber.setSituacaoContaReceber("A");
				
				contareceber.setValorContaReceber(valorConta);
				contareceber.setValorMultaContaReceber(BigDecimal.ZERO);
				contareceber.setPercentualMultaContaReceber(BigDecimal.ZERO);
				contareceber.setValorJurosDiarioContaReceber(BigDecimal.ZERO);
				contareceber.setPercentualJurosDiarioContaReceber(BigDecimal.ZERO);
				contareceber.setDescontoContaReceber(BigDecimal.ZERO);
				
				getGenericDao().persist(contareceber);
				
				// Gera vinculo entre a conta e o pedido
				PedidoContaReceberVO pedidoContaReceber = new PedidoContaReceberVO();
				
				pedidoContaReceber.setNewRecord(true);
				pedidoContaReceber.setCodigoEmpresa(getVo().getCodigoEmpresa());
				pedidoContaReceber.setCodigoLoja(getVo().getCodigoLoja());
				pedidoContaReceber.setCodigoPedido(getVo().getCodigoPedido());
				
				pedidoContaReceber.setCodigoEmpresaContaReceber(contareceber.getCodigoEmpresa());
				pedidoContaReceber.setCodigoLojaContaReceber(contareceber.getCodigoLoja());
				pedidoContaReceber.setCodigoContaReceber(contareceber.getCodigoContaReceber());
				
				getGenericDao().persist(pedidoContaReceber);
			}
			
			// Gerar NF de Saida
			
			NFSaidaVO nfsaida = null;
			if (geraNF) {
				nfsaida = new NFSaidaVO();
				nfsaida.setNewRecord(true);
				nfsaida.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				nfsaida.setCodigoLoja(getLojasessao().getCodigoLoja());
				nfsaida.setSerieNFSaida("1");
				nfsaida.setCodigoNFSaida(NFSaidaCadastroFacade.getNextNFSaida(getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja()));
				nfsaida.setDataEmissaoNFSaida(Utils.getToday());
				nfsaida.setDataHoraSaidaNFSaida(Utils.getNow());
				nfsaida.setFormaPagamentoNFSaida(0); // TODO - Forma de Pagamento
				nfsaida.setModeloNFSaida("55");
				nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_DIGITACAO);
				nfsaida.setModalidadeFreteNFSaida("9");
				
				// Destinatário
				nfsaida.setCodigoEmpresaDestinatarioNFSaida(getVo().getCodigoEmpresaEnderecoPessoa());
				nfsaida.setCodigoLojaDestinatarioNFSaida(getVo().getCodigoLojaEnderecoPessoa());
				nfsaida.setCodigoPessoaDestinatarioNFSaida(getVo().getCodigoPessoa());
				nfsaida.setCodigoEnderecoPessoaDestinatarioNFSaida(getVo().getCodigoEnderecoPessoa());
				
				// Transportadora
				nfsaida.setCodigoEmpresaTransportadoraNFSaida(getVo().getCodigoEmpresaFrete());
				nfsaida.setCodigoLojaTransportadoraNFSaida(getVo().getCodigoLojaFrete());
				nfsaida.setCodigoPessoaTransportadoraNFSaida(getVo().getCodigoPessoaFrete());
				nfsaida.setCodigoEnderecoPessoaTransportadoraNFSaida(getVo().getCodigoEnderecoPessoaFrete());
				
				// Zera totais
				nfsaida.setValorBaseCalculoIcmsNFSaida(BigDecimal.ZERO);
				nfsaida.setValorBaseCalculoIcmsSTNFSaida(BigDecimal.ZERO);
				nfsaida.setValorFreteNFSaida(BigDecimal.ZERO);
				nfsaida.setValorIcmsNFSaida(BigDecimal.ZERO);
				nfsaida.setValorIcmsSTNFSaida(BigDecimal.ZERO);
				nfsaida.setValorOutrasDespesasNFSaida(BigDecimal.ZERO);
				nfsaida.setValorSeguroNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalCofinsNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalDescontoNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalIpiNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalPisNFSaida(BigDecimal.ZERO);
				nfsaida.setValorTotalProdutosNFSaida(BigDecimal.ZERO);
				
				nfsaida.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				nfsaida.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				nfsaida.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				
				getGenericDao().persist(nfsaida);
				
				int sequencial = 0;
				
				// Itens do pedido
				for (IValueObject i : listItensPedido) {
					
					sequencial++;
					
					ItemPedidoVO itemPedido = (ItemPedidoVO) i;
					
					// Busca produto
					ProdutoVO produto = new ProdutoVO();
					produto.setCodigoEmpresa(itemPedido.getCodigoEmpresaProduto());
					produto.setCodigoLoja(itemPedido.getCodigoLojaProduto());
					produto.setCodigoProduto(itemPedido.getCodigoProduto());
					produto = (ProdutoVO) getGenericDao().get(produto);
					
					ItemNFSaidaVO itemNf = new ItemNFSaidaVO();
					itemNf.setNewRecord(true);
					
					itemNf.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
					itemNf.setCodigoLoja(nfsaida.getCodigoLoja());
					itemNf.setCodigoNFSaida(nfsaida.getCodigoNFSaida());
					itemNf.setSerieNFSaida(nfsaida.getSerieNFSaida());
					itemNf.setSequencialItemNFSaida(sequencial);
					
					itemNf.setCfopItemNFSaida(""); // Vai buscar depois na validacao...
					itemNf.setCodigoEmpresaProduto(itemPedido.getCodigoEmpresaProduto());
					itemNf.setCodigoLojaProduto(itemPedido.getCodigoLojaProduto());
					itemNf.setCodigoProduto(itemPedido.getCodigoProduto());
					itemNf.setDescricaoItemNFSaida(produto.getDescricaoProduto());
					
					itemNf.setUnidadeItemNFSaida(produto.getSiglaUnidade());
					itemNf.setNCMItemNFSaida(produto.getCodigoNCMProduto());
					
					itemNf.setQuantidadeItemNFSaida(itemPedido.getQuantidadeItemPedido());
					itemNf.setValorUnitarioItemNFSaida(itemPedido.getValorUnitarioItemPedido());
					itemNf.setValorTotalBrutoItemNFSaida(itemPedido.getValorTotalItemPedido());
					itemNf.setValorDescontoItemNFSaida(itemPedido.getValorDescontoItemPedido());
					
					itemNf.setValorFreteItemNFSaida(BigDecimal.ZERO);
					itemNf.setAliquotaCofinsItemNFSaida(BigDecimal.ZERO);
					itemNf.setAliquotaPisItemNFSaida(BigDecimal.ZERO);
					itemNf.setAliquotaIpiItemNFSaida(BigDecimal.ZERO);
					itemNf.setAliquotaIcmsItemNFSaida(BigDecimal.ZERO);
					itemNf.setAliquotaIcmsSTItemNFSaida(BigDecimal.ZERO);
					
					itemNf.setBaseCalculoIcmsItemNFSaida(BigDecimal.ZERO);
					itemNf.setBaseIcmsStItemNFSaida(BigDecimal.ZERO);
					
					itemNf.setValorCofinsItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorPisItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorIpiItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorIcmsItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorIcmsSTItemNFSaida(BigDecimal.ZERO);
					
					itemNf.setValorSeguroItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorDescontoItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorFreteItemNFSaida(BigDecimal.ZERO);
					itemNf.setValorOutrasDespesasItemNFSaida(BigDecimal.ZERO);
					
					itemNf.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					itemNf.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					itemNf.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					getGenericDao().persist(itemNf);
				}
				
				getVo().setCodigoNFSaida(nfsaida.getCodigoNFSaida());
				getVo().setCodigoEmpresaNFSaida(nfsaida.getCodigoEmpresa());
				getVo().setCodigoLojaNFSaida(nfsaida.getCodigoLoja());
				getVo().setSerieNFSaida(nfsaida.getSerieNFSaida());
			}
			
			getVo().setStatusPedido("P");
			getGenericDao().persist(getVo());
			getGenericDao().commit();
			
			if (nfsaida != null) {
				NFSaidaCadastroFacade.validarNF(getGenericDao(), nfsaida, getLojasessao());
			}
			
			write("ok");
		} catch (SQLException ex) {
			getGenericDao().rollback();
			ex.printStackTrace();
			writeErrorMessage(ex.getMessage());
		}
	}
	
	public void estornar() {
		try {
			getGenericDao().setAutoCommit(false);
			
			// Verifica se a NF Gerada não foi transmitida
			
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
				
				EstoqueVO estoque = new EstoqueVO();
				estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				estoque.setCodigoLoja(item.getCodigoLojaProduto());
				estoque.setCodigoProduto(item.getCodigoProduto());
				estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());
				
				estoque = (EstoqueVO) getGenericDao().get(estoque);
				
				if (estoque == null) {
					estoque = new EstoqueVO();
					estoque.setNewRecord(true);
					
					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getVo().getCodigoLoja());
					
					estoque.setEstoqueFiscal(BigDecimal.ZERO);
					estoque.setEstoqueFisico(BigDecimal.ZERO);
				}
				
				MovimentacaoEstoqueVO movimentacao = new MovimentacaoEstoqueVO();
				movimentacao.setNewRecord(true);
				movimentacao.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				movimentacao.setCodigoLoja(item.getCodigoLojaProduto());
				movimentacao.setCodigoProduto(item.getCodigoProduto());
				movimentacao.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				movimentacao.setCodigoLojaEstoque(getVo().getCodigoLoja());
				movimentacao.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacao.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR);
				
				movimentacao.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
				movimentacao.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				// Baixa o estoque...
				estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(item.getQuantidadeEstoqueItemPedido()));
				movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
				
				// Somente volta estoque fiscal se for gerada nota fiscal
				if (getVo().getCodigoNFSaida() != null) {
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(item.getQuantidadeEstoqueItemPedido()));
					movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
				}
				
				movimentacao.setObservacaoMovimentacaoEstoque("Estorno gerado pelo pedido n.° " + getVo().getCodigoPedido() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja());
				movimentacao.setDocumentoMovimentacaoEstoque(getVo().getCodigoPedido() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());
				
				getGenericDao().persist(estoque);
				getGenericDao().persist(movimentacao);
			}
			// Estornar Contas a Receber
			
			PedidoContaReceberVO _filter = new PedidoContaReceberVO();
			_filter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			_filter.setCodigoLoja(getVo().getCodigoLoja());
			_filter.setCodigoPedido(getVo().getCodigoPedido());
			
			List<IValueObject> listContas = getGenericDao().getList(_filter);
			
			for (IValueObject i : listContas) {
				PedidoContaReceberVO pedidoContaReceber = (PedidoContaReceberVO) i;
				
				ContaReceberVO conta = new ContaReceberVO();
				conta.setCodigoEmpresa(pedidoContaReceber.getCodigoEmpresaContaReceber());
				conta.setCodigoLoja(pedidoContaReceber.getCodigoLojaContaReceber());
				conta.setCodigoContaReceber(pedidoContaReceber.getCodigoContaReceber());
				
				conta = (ContaReceberVO) getGenericDao().get(conta);
				
				if (conta != null) {
					if (conta.getSituacaoContaReceber().equals("A") || conta.getSituacaoContaReceber().equals("C")) {
						getGenericDao().remove(pedidoContaReceber);
						getGenericDao().remove(conta);
					} else {
						getGenericDao().rollback();
						String situacao = "";
						if (conta.getSituacaoContaReceber().equals("Q")) {
							situacao = "quitados";
						} else if (conta.getSituacaoContaReceber().equals("R")) {
							situacao = "renegociados";
						} else if (conta.getSituacaoContaReceber().equals("P")) {
							situacao = "previstos";
						}
						writeErrorMessage("Não é possível estornar o pedido, pois possui títulos " + situacao + "!");
						return;
					}
				}
			}
			
			// Excluir NF de Saida
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresaNFSaida());
			nfsaida.setCodigoLoja(getVo().getCodigoLojaNFSaida());
			nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
			nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
			
			nfsaida = (NFSaidaVO) getGenericDao().get(nfsaida);
			
			if (nfsaida != null) {
				
				if (nfsaida.getStatusNFSaida().equals(NFEletronicaVO.STATUS_AUTORIZADA) ) {
					getGenericDao().rollback();
					writeErrorMessage("Nota Fiscal já foi autorizada, pedido não pode ser estornado!");
					return;
				} else if (nfsaida.getStatusNFSaida().equals(NFEletronicaVO.STATUS_CANCELADA) ) {
					getGenericDao().rollback();
					writeErrorMessage("Nota Fiscal já foi cancelada, pedido não pode ser estornado!");
					return;
				}
				
				ItemNFSaidaVO itemNFSaidaFilter = new ItemNFSaidaVO();
				itemNFSaidaFilter.setCodigoEmpresa(nfsaida.getCodigoEmpresa());
				itemNFSaidaFilter.setCodigoLoja(nfsaida.getCodigoLoja());
				itemNFSaidaFilter.setCodigoNFSaida(nfsaida.getCodigoNFSaida());
				itemNFSaidaFilter.setSerieNFSaida(nfsaida.getSerieNFSaida());
				
				List<IValueObject> listItemNFSaida = getGenericDao().getList(itemNFSaidaFilter);
				
				for (IValueObject i : listItemNFSaida) {
					getGenericDao().remove(i);
				}
				
				getVo().setCodigoEmpresaNFSaida(null);
				getVo().setCodigoLojaNFSaida(null);
				getVo().setCodigoNFSaida(null);
				getVo().setSerieNFSaida(null);
				
				getGenericDao().persist(getVo());
				
				getGenericDao().remove(nfsaida);
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
	
	@IgnoreMethodAuthentication
	public void planopagamentovenda() {
		PlanoPagamentoVendaVO plano = new PlanoPagamentoVendaVO();
		plano.setFlagAtivoPlanoPagamento("S");
		
		try {
			List<IValueObject> list = getGenericDao().getList(plano, "NomePlanoPagamento");
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void pessoas() {
		try {
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();
			List<IValueObject> list = getGenericDao().getList(endereco);
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			VendedorVO vendedor = new VendedorVO();
			vendedor.setSituacaoVendedor("A"); // Somente ativos
			List<IValueObject> list = getGenericDao().getList(vendedor);
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setFlagGerarNF(String flagGerarNF) {
		FlagGerarNF = flagGerarNF;
	}

	public String getFlagGerarNF() {
		return FlagGerarNF;
	}
}
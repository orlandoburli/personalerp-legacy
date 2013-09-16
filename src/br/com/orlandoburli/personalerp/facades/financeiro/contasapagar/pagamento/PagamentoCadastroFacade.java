package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.pagamento;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.financeiro.ContaBancariaDAO;
import br.com.orlandoburli.core.dao.financeiro.FormaPagamentoDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.financeiro.ChequeEmitidoVO;
import br.com.orlandoburli.core.vo.financeiro.ContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.FormaPagamentoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.contapagar.ContaPagarCadastroFacade;

public class PagamentoCadastroFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	@OutjectSession
	private List<ContaPagarVO> listContasPagar;
	
	@OutjectSession
	private List<PagamentoVO> listPagamentos;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private UsuarioVO usuariosessao;
	
	private BigDecimal NovoValorCalculado;
	private Integer CodigoEmpresaContaPagar;
	private Integer CodigoLojaContaPagar;
	private Integer CodigoContaPagar;
	
	private Integer CodigoFormaPagamento;
	
	private Date DataPagamento;
	
	@Precision(decimals=2)
	private BigDecimal ValorPagamento;
	
	/** Indica a posição no array do pagamento, para alteração ou exclusão */
	private Integer PosicaoPagamento;
	
	@InstantiateObject
	private MovimentacaoContaBancariaVO movimentacaoconta;
	
	@InstantiateObject
	private ChequeEmitidoVO cheque;

	public PagamentoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	/***********************************
	 *  Manutenção da Lista de Contas  *
	 ***********************************/
	@IgnoreMethodAuthentication
	public void adicionarconta() {
		ContaPagarDAO _dao = new ContaPagarDAO();
		try {
			if (CodigoEmpresaContaPagar == null || CodigoLojaContaPagar == null || CodigoContaPagar == null) {
				writeErrorMessage("Nenhuma conta foi selecionada!");
				return;
			}
			// Primeiro verifica se a conta ja nao está incluida
			for(ContaPagarVO conta : listContasPagar) {
				if (conta.getCodigoEmpresa().equals(CodigoEmpresaContaPagar) && conta.getCodigoLoja().equals(CodigoLojaContaPagar) && conta.getCodigoContaPagar().equals(CodigoContaPagar)) {
					writeErrorMessage("Conta já incluída!");
					return;
				}
			}
			ContaPagarVO _vo = _dao.get(new Object[]{CodigoContaPagar, CodigoEmpresaContaPagar, CodigoLojaContaPagar});
			if (_vo != null) {
				if (listContasPagar == null) {
					limpar();
				}
				ContaPagarCadastroFacade.calculaValorContaPagarVO(_vo, getDataPagamento());
				listContasPagar.add(_vo);
				write("ok");
			} else {
				writeErrorMessage("Conta a pagar não encontrada!");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void remover() {
		if (CodigoEmpresaContaPagar == null || CodigoLojaContaPagar == null || CodigoContaPagar == null) {
			writeErrorMessage("Nenhuma conta foi selecionada!");
			return;
		}
		for(ContaPagarVO conta : listContasPagar) {
			if (conta.getCodigoEmpresa().equals(CodigoEmpresaContaPagar) && conta.getCodigoLoja().equals(CodigoLojaContaPagar) && conta.getCodigoContaPagar().equals(CodigoContaPagar)) {
				listContasPagar.remove(conta);
				write("ok");
				return;
			}
		}
		writeErrorMessage("Conta a pagar não encontrada!");
	}
	
	@IgnoreMethodAuthentication
	public void limpar() {
		listContasPagar = new ArrayList<ContaPagarVO>();
		listPagamentos = new ArrayList<PagamentoVO>();
	}
	
	@IgnoreMethodAuthentication
	public void currentlist() {
		if (listContasPagar == null) {
			listContasPagar = new ArrayList<ContaPagarVO>();
		}
		ContaPagarCadastroFacade.calculaValorContaPagarVO(listContasPagar, getDataPagamento());
		write(Utils.voToXml(listContasPagar));
	}
	
	@IgnoreMethodAuthentication
	public void alterarvalor() {
		for(ContaPagarVO contapagar : listContasPagar) {
			if (contapagar.getCodigoEmpresa().equals(CodigoEmpresaContaPagar) && contapagar.getCodigoLoja().equals(CodigoLojaContaPagar) && contapagar.getCodigoContaPagar().equals(CodigoContaPagar)) {
				contapagar.setValorCalculado(NovoValorCalculado);
			}
		}
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void totais() {
		if (listContasPagar == null) {
			listContasPagar = new ArrayList<ContaPagarVO>();
		}
		BigDecimal totalJuros = BigDecimal.ZERO;
		BigDecimal totalMulta = BigDecimal.ZERO;
		BigDecimal totalDescontos = BigDecimal.ZERO;
		BigDecimal totalOriginal = BigDecimal.ZERO;
		BigDecimal totalAPagar = BigDecimal.ZERO;
		
		for (ContaPagarVO contapagar : listContasPagar) {
			totalJuros = totalJuros.add(contapagar.getValorJurosCalculado()==null?BigDecimal.ZERO:contapagar.getValorJurosCalculado());
			totalMulta = totalMulta.add(contapagar.getValorMultaCalculado()==null?BigDecimal.ZERO:contapagar.getValorMultaCalculado());
			totalDescontos = totalDescontos.add(contapagar.getValorDescontoCalculado()==null?BigDecimal.ZERO:contapagar.getValorDescontoCalculado());
			totalOriginal = totalOriginal.add(contapagar.getValorContaPagar()==null?BigDecimal.ZERO:contapagar.getValorContaPagar());
			totalAPagar = totalAPagar.add(contapagar.getValorAbertoContaPagar()==null?BigDecimal.ZERO:contapagar.getValorAbertoContaPagar());
		}
		
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		sb.append("<totais>");
		sb.append("		<juros><![CDATA[" + totalJuros.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></juros>");
		sb.append("		<multa><![CDATA[" + totalMulta.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></multa>");
		sb.append("		<descontos><![CDATA[" + totalDescontos.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></descontos>");
		sb.append("		<valororiginal><![CDATA[" + totalOriginal.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></valororiginal>");
		sb.append("		<valorcalculado><![CDATA[" + totalAPagar.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></valorcalculado>");
		sb.append("</totais>");
		
		write(sb.toString());
	}
	
	/*******************************
	 *  Manutenção dos Pagamentos  *
	 *******************************/
	@IgnoreMethodAuthentication
	public void limparpagamentos() {
		setListPagamentos(new ArrayList<PagamentoVO>());
	}
	
	@IgnoreMethodAuthentication
	public void currentlistpagamentos() {
		if (getListPagamentos() == null) {
			setListPagamentos(new ArrayList<PagamentoVO>());
		}
		write(Utils.voToXml(getListPagamentos()));
	}
	
	/** Retorna a lista das contas bancárias cadastradas (somente da empresa logada) */
	@IgnoreMethodAuthentication
	public void contas() {
		ContaBancariaDAO _dao = new ContaBancariaDAO();
		ContaBancariaVO _filter = new ContaBancariaVO();
		//_filter.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		try {
			List<ContaBancariaVO> list = _dao.getList(_filter);
			int count = _dao.getListCount(_filter);
			String xmlList = "<?xml version=\"1.0\" encoding=\"iso-8859-1\"?><list>";
			for (ContaBancariaVO _vo : list) {
				xmlList += Utils.voToXml(_vo, false);
			}
			xmlList += "<count>" + count + "</count>";
			xmlList += "</list>";
			write(xmlList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void adicionarpagamento() {
		if (CodigoFormaPagamento == null || CodigoFormaPagamento <= 0) {
			writeErrorMessage("Informe a forma de pagamento!", "FormaPagamento");
			return;
		}
		
		if (ValorPagamento == null || ValorPagamento.compareTo(BigDecimal.ZERO) <= 0) {
			writeErrorMessage("Informe o valor do pagamento!", "ValorPagamento");
			return;
		}
		ValorPagamento = ValorPagamento.setScale(2, BigDecimal.ROUND_CEILING);
		
		FormaPagamentoVO forma = null;
		
		try {
			forma = new FormaPagamentoDAO().get(CodigoFormaPagamento);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PagamentoVO pagamento = null;
		boolean novo = true;
		if (CodigoFormaPagamento == FormaPagamentoVO.FORMA_PAGTO_DINHEIRO) {
			// Se for em dinheiro, busca essa forma de pagamento, se já existe, e soma o valor.
			// nao há necessidade de 2 pagamentos em dinheiro
			for (PagamentoVO item : listPagamentos) {
				if (item.getCodigoFormaPagamento() == FormaPagamentoVO.FORMA_PAGTO_DINHEIRO) {
					pagamento = item;
					novo = false;
				}
			}
		}
		if (pagamento == null) {
			pagamento = new PagamentoVO();
			pagamento.setValorPagamento(BigDecimal.ZERO);
		}
		pagamento.setCodigoFormaPagamento(CodigoFormaPagamento);
		pagamento.setValorPagamento(pagamento.getValorPagamento().add(ValorPagamento));
		pagamento.setNomeFormaPagamento(forma.getNomeFormaPagamento());
		
		// Tratamento para Movimentacao em Conta Bancaria
		if (CodigoFormaPagamento == FormaPagamentoVO.FORMA_PAGTO_DEBITO_CONTA) {
			movimentacaoconta.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_DEBITO);
			movimentacaoconta.setValorMovimentacaoContaBancaria(ValorPagamento);
			pagamento.setMovimentacao(movimentacaoconta);
		}
		// Tratamento para cheques
		if (CodigoFormaPagamento == FormaPagamentoVO.FORMA_PAGTO_CHEQUE) {
			cheque.setValorChequeEmitido(ValorPagamento);
			pagamento.setCheque(cheque);
		}
		
		if (novo) {
			listPagamentos.add(pagamento);
		}
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void readpagamento() {
		if (PosicaoPagamento != null && PosicaoPagamento >= 0) {
			write(Utils.voToXml(listPagamentos.get(PosicaoPagamento)));
		}
	}
	
	@IgnoreMethodAuthentication
	public void alterarpagamento() {
		if (PosicaoPagamento == null || PosicaoPagamento < 0) {
			writeErrorMessage("Nenhum pagamento selecionado!");
			return;
		}
		if (ValorPagamento == null || ValorPagamento.compareTo(BigDecimal.ZERO) <= 0) {
			writeErrorMessage("Informe o valor do pagamento!", "ValorPagamento");
			return;
		}
		
		PagamentoVO pagamento = listPagamentos.get(PosicaoPagamento);
		pagamento.setValorPagamento(ValorPagamento);
		
		// Tratamento para Movimentacao em Conta Bancaria
		if (CodigoFormaPagamento == FormaPagamentoVO.FORMA_PAGTO_DEBITO_CONTA) {
			movimentacaoconta.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_DEBITO);
			movimentacaoconta.setValorMovimentacaoContaBancaria(ValorPagamento);
			pagamento.setMovimentacao(movimentacaoconta);
		}
		// Tratamento para cheques
		if (CodigoFormaPagamento == FormaPagamentoVO.FORMA_PAGTO_CHEQUE) {
			cheque.setValorChequeEmitido(ValorPagamento);
			pagamento.setCheque(cheque);
		}
		
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void excluirpagamento() {
		if (PosicaoPagamento == null || PosicaoPagamento < 0) {
			writeErrorMessage("Nenhum pagamento selecionado!");
			return;
		}
		listPagamentos.remove(PosicaoPagamento.intValue());
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void confirmar() {
		// Verifica se possui cheques
		boolean hasCheque = false;
		for (PagamentoVO pagamento : listPagamentos) {
			if (pagamento.getCodigoFormaPagamento() == FormaPagamentoVO.FORMA_PAGTO_CHEQUE) {
				hasCheque = true;
				break;
			}
		}
		// Se possui cheque, verifica se tem conta de mais de um fornecedor.
		if (hasCheque) {
			int codigoPessoa = 0;
			int codigoEmpresa = 0;
			int codigoLoja = 0;
			boolean pessoaDiferente = false;
			for (ContaPagarVO conta : listContasPagar) {
				if (codigoPessoa == 0) {
					codigoPessoa = conta.getCodigoPessoaContaPagar();
					codigoEmpresa = conta.getCodigoEmpresaPessoaContaPagar();
					codigoLoja = conta.getCodigoLojaPessoaContaPagar();
				} else {
					if (codigoPessoa != conta.getCodigoPessoaContaPagar() || codigoEmpresa != conta.getCodigoEmpresaPessoaContaPagar() || codigoLoja != conta.getCodigoLojaPessoaContaPagar()) {
						pessoaDiferente = true;
						break;
					}
				}
			}
			if (pessoaDiferente) {
				writeErrorMessage("Pagamento em cheque somente para contas do mesmo fornecedor!");
				return;
			}
		}
		
		// Calcula total dos pagamentos
		BigDecimal totalPagtos = BigDecimal.ZERO;
		
		for (PagamentoVO pagamento : listPagamentos) {
			totalPagtos = totalPagtos.add(pagamento.getValorPagamento());
		}
		
		// Calcula total das contas
		BigDecimal totalContas = BigDecimal.ZERO;
		
		for (ContaPagarVO conta : listContasPagar) {
			//conta.setValorAbertoContaPagar(BigDecimal.ZERO);
			totalContas = totalContas.add(conta.getValorAbertoContaPagar());
		}
		
		if (totalContas.setScale(2, BigDecimal.ROUND_HALF_EVEN).compareTo(totalPagtos.setScale(2, BigDecimal.ROUND_HALF_EVEN)) != 0) {
			// Pagamento parcial
			if (listContasPagar.size() > 1) {
				writeErrorMessage("Para pagamento parcial de contas, coloque somente uma conta a pagar!");
				return;
			}
		}
		
		// Ordenar arrays pelo valor
		Collections.sort(listPagamentos, new Comparator<PagamentoVO>() {
			@Override
			public int compare(PagamentoVO pag1, PagamentoVO pag2) {
				return pag1.getValorPagamento().compareTo(pag2.getValorPagamento());
			}
		});
		Collections.sort(listContasPagar, new Comparator<ContaPagarVO>() {
			@Override
			public int compare(ContaPagarVO conta1, ContaPagarVO conta2) {
				return conta1.getValorCalculado().compareTo(conta2.getValorCalculado());
			}
		});
		
//		PagamentoDAO pagtoDao = new PagamentoDAO();
//		ChequeEmitidoDAO chequeDao = new ChequeEmitidoDAO();
//		MovimentacaoContaBancariaDAO movDao = new MovimentacaoContaBancariaDAO();
		
		GenericDAO gDao = new GenericDAO();
		
		gDao.setAutoCommit(false);
		
		try {
			// Salva todos os pagamentos
			for (PagamentoVO pagamento : listPagamentos) {
				
				pagamento.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
				pagamento.setCodigoLoja(lojasessao.getCodigoLoja());
				pagamento.setDataHoraBaixaPagamento(Utils.getNow());
				pagamento.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				pagamento.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				pagamento.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				pagamento.setNewRecord(true);
				
				gDao.persist(pagamento);
				
				if (pagamento.getCodigoFormaPagamento() == FormaPagamentoVO.FORMA_PAGTO_DEBITO_CONTA) {
					// Debito em conta - Gera movimentacao conta bancaria
					pagamento.getMovimentacao().setCodigoPagamento(pagamento.getCodigoPagamento());
					pagamento.getMovimentacao().setCodigoEmpresaPagamento(pagamento.getCodigoEmpresa());
					pagamento.getMovimentacao().setCodigoLojaPagamento(pagamento.getCodigoLoja());
					
					pagamento.getMovimentacao().setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					pagamento.getMovimentacao().setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					pagamento.getMovimentacao().setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					pagamento.getMovimentacao().setDataMovimentacaoContaBancaria(getDataPagamento());
					pagamento.getMovimentacao().setDescricaoMovimentacaoContaBancaria("Pagamento de contas");
					
					pagamento.getMovimentacao().setNewRecord(true);
					gDao.persist(pagamento.getMovimentacao());
				} else if (pagamento.getCodigoFormaPagamento() == FormaPagamentoVO.FORMA_PAGTO_CHEQUE) {
					// Cheque - gera registro de cheque emitido
					pagamento.getCheque().setCodigoEmpresa(empresasessao.getCodigoEmpresa());
					pagamento.getCheque().setCodigoLoja(lojasessao.getCodigoLoja());
					
					pagamento.getCheque().setCodigoPagamento(pagamento.getCodigoPagamento());
					pagamento.getCheque().setCodigoEmpresaPagamento(pagamento.getCodigoEmpresa());
					pagamento.getCheque().setCodigoLojaPagamento(pagamento.getCodigoLoja());
					
					pagamento.getCheque().setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					pagamento.getCheque().setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					pagamento.getCheque().setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					pagamento.getCheque().setCodigoEmpresaDestinatario(listContasPagar.get(0).getCodigoEmpresaPessoaContaPagar());
					pagamento.getCheque().setCodigoLojaDestinatario(listContasPagar.get(0).getCodigoLojaPessoaContaPagar());
					pagamento.getCheque().setCodigoPessoaDestinatario(listContasPagar.get(0).getCodigoPessoaContaPagar());
					pagamento.getCheque().setStatusChequeEmitido("N");
					
					pagamento.getCheque().setNewRecord(true);
					gDao.persist(pagamento.getCheque());
				}
			}

//			BaixaContaPagarDAO baixaDao = new BaixaContaPagarDAO();
//			ContaPagarDAO contaDao = new ContaPagarDAO();
			BigDecimal valorRestante = BigDecimal.ZERO;
			//BigDecimal valorPago = BigDecimal.ZERO;
			
			// Loop para distribuicao dos pagamentos
			for (PagamentoVO pagamento : listPagamentos) {
				valorRestante = valorRestante.add(pagamento.getValorPagamento());
				for (ContaPagarVO conta : listContasPagar) {
					// Somente as contas em aberto e se tiver valor ainda a distribuir
					if (conta.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_ABERTO) && valorRestante.compareTo(BigDecimal.ZERO) > 0) {
						if (valorRestante.compareTo(conta.getValorAbertoContaPagar()) < 0) { 
							// Se o valor em aberto for menor que valor do pagamento
							// Passa todo o valor para esta conta, mas nao quita.
							conta.setValorAbertoContaPagar(conta.getValorAbertoContaPagar().subtract(valorRestante));
							
							BaixaContaPagarVO baixa = new BaixaContaPagarVO();
							
							baixa.setCodigoContaPagar(conta.getCodigoContaPagar());
							baixa.setCodigoLojaContaPagar(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(pagamento.getCodigoEmpresa());
							baixa.setCodigoLoja(pagamento.getCodigoLoja());
							baixa.setCodigoPagamento(pagamento.getCodigoPagamento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorBaixaContaPagar(valorRestante);
							baixa.setNewRecord(true);
							
							gDao.persist(baixa);
							valorRestante = BigDecimal.ZERO;
							break;
						} else if (valorRestante.compareTo(conta.getValorAbertoContaPagar()) == 0) { 
							// Se o valor em aberto for igual ao valor do pagamento
							// Passa todo o valor para a conta, e quita.
							conta.setValorAbertoContaPagar(conta.getValorAbertoContaPagar().subtract(valorRestante));
							conta.setSituacaoContaPagar(ContaPagarVO.SITUACAO_QUITADO);
							gDao.persist(conta);
							
							BaixaContaPagarVO baixa = new BaixaContaPagarVO();
							
							baixa.setCodigoContaPagar(conta.getCodigoContaPagar());
							baixa.setCodigoLojaContaPagar(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(pagamento.getCodigoEmpresa());
							baixa.setCodigoLoja(pagamento.getCodigoLoja());
							baixa.setCodigoPagamento(pagamento.getCodigoPagamento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorBaixaContaPagar(valorRestante);
							baixa.setNewRecord(true);
							
							gDao.persist(baixa);
							
							valorRestante = BigDecimal.ZERO;
							break;
						} else {
							// Se o valor em aberto for menor que o valor do pagamento
							// Passa todo o valor em aberto para a conta, quita, e deixa o restante para o proximo.
							//conta.setValorAbertoContaPagar(conta.getValorAbertoContaPagar().subtract(pagamento.getValorPagamento()));
							BigDecimal valorUsado = conta.getValorAbertoContaPagar();
							conta.setValorAbertoContaPagar(BigDecimal.ZERO);
							conta.setSituacaoContaPagar(ContaPagarVO.SITUACAO_QUITADO);
							gDao.persist(conta);
							
							BaixaContaPagarVO baixa = new BaixaContaPagarVO();
							
							baixa.setCodigoContaPagar(conta.getCodigoContaPagar());
							baixa.setCodigoLojaContaPagar(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(pagamento.getCodigoEmpresa());
							baixa.setCodigoLoja(pagamento.getCodigoLoja());
							baixa.setCodigoPagamento(pagamento.getCodigoPagamento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorBaixaContaPagar(valorUsado);
							baixa.setNewRecord(true);
							
							valorRestante = valorRestante.subtract(valorUsado);
							
							gDao.persist(baixa);
						}
					}
				}
			}
			
			gDao.commit();
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			return;
		}
		write("ok");
	}
	
	public void setListContasPagar(List<ContaPagarVO> listContasPagar) {
		this.listContasPagar = listContasPagar;
	}

	public List<ContaPagarVO> getListContasPagar() {
		return listContasPagar;
	}

	public void setNovoValorCalculado(BigDecimal novoValorCalculado) {
		NovoValorCalculado = novoValorCalculado;
	}

	public BigDecimal getNovoValorCalculado() {
		return NovoValorCalculado;
	}

	public void setCodigoEmpresaContaPagar(Integer codigoEmpresaContaPagar) {
		CodigoEmpresaContaPagar = codigoEmpresaContaPagar;
	}

	public Integer getCodigoEmpresaContaPagar() {
		return CodigoEmpresaContaPagar;
	}

	public void setCodigoLojaContaPagar(Integer codigoLojaContaPagar) {
		CodigoLojaContaPagar = codigoLojaContaPagar;
	}

	public Integer getCodigoLojaContaPagar() {
		return CodigoLojaContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setCodigoFormaPagamento(Integer codigoFormaPagamento) {
		CodigoFormaPagamento = codigoFormaPagamento;
	}

	public Integer getCodigoFormaPagamento() {
		return CodigoFormaPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		ValorPagamento = valorPagamento;
	}

	public BigDecimal getValorPagamento() {
		return ValorPagamento;
	}

	public void setListPagamentos(List<PagamentoVO> listPagamentos) {
		this.listPagamentos = listPagamentos;
	}

	public List<PagamentoVO> getListPagamentos() {
		return listPagamentos;
	}

	public void setPosicaoPagamento(Integer posicaoPagamento) {
		PosicaoPagamento = posicaoPagamento;
	}

	public Integer getPosicaoPagamento() {
		return PosicaoPagamento;
	}

	public void setMovimentacaoconta(MovimentacaoContaBancariaVO movimentacaoconta) {
		this.movimentacaoconta = movimentacaoconta;
	}

	public MovimentacaoContaBancariaVO getMovimentacaoconta() {
		return movimentacaoconta;
	}

	public void setCheque(ChequeEmitidoVO cheque) {
		this.cheque = cheque;
	}

	public ChequeEmitidoVO getCheque() {
		return cheque;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public void setDataPagamento(Date dataPagamento) {
		DataPagamento = dataPagamento;
	}

	public Date getDataPagamento() {
		return DataPagamento;
	}
}
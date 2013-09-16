package br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.recebimento;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.financeiro.FormaRecebimentoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.financeiro.BancoVO;
import br.com.orlandoburli.core.vo.financeiro.BandeiraCartaoVO;
import br.com.orlandoburli.core.vo.financeiro.ChequeRecebidoVO;
import br.com.orlandoburli.core.vo.financeiro.ContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.FormaRecebimentoVO;
import br.com.orlandoburli.core.vo.financeiro.MovimentacaoContaBancariaVO;
import br.com.orlandoburli.core.vo.financeiro.OperadoraCartaoVO;
import br.com.orlandoburli.core.vo.financeiro.RecebimentoCartaoVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.RecebimentoVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.contareceber.ContaReceberCadastroFacade;

public class RecebimentoCadastroFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	@InstantiateObject
	private GenericDAO _dao;
	
	@OutjectSession
	private List<ContaReceberVO> listContasReceber;
	
	@OutjectSession
	private List<RecebimentoVO> listRecebimentos;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private UsuarioVO usuariosessao;
	
	private BigDecimal NovoValorCalculado;
	private Integer CodigoEmpresaContaReceber;
	private Integer CodigoLojaContaReceber;
	private Integer CodigoContaReceber;
	
	private String NumeroTitulo;
	
	private Integer CodigoFormaRecebimento;
	
	private Date DataRecebimento;
	
	@Precision(decimals=2)
	private BigDecimal ValorRecebimento;
	
	/** Indica a posição no array do recebimento, para alteração ou exclusão */
	private Integer PosicaoRecebimento;
	
	@InstantiateObject
	private MovimentacaoContaBancariaVO movimentacaoconta;
	
	@InstantiateObject
	private ChequeRecebidoVO cheque;

	@InstantiateObject
	private RecebimentoCartaoVO recebimentocartao;
	
	private String DataRecebimentoCartao;
	private String HoraRecebimentoCartao;
	
	public RecebimentoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	/***********************************
	 *  Manutenção da Lista de Contas  *
	 ***********************************/
	@IgnoreMethodAuthentication
	public void adicionarconta() {
		try {
			boolean tituloInformado = false;
			if (NumeroTitulo != null && !NumeroTitulo.trim().equals("")) {
				tituloInformado = true;
			}
			if (!tituloInformado) {
				if (CodigoEmpresaContaReceber == null || CodigoLojaContaReceber == null || CodigoContaReceber == null) {
					writeErrorMessage("Nenhuma conta foi selecionada!");
					return;
				}
			}
			
			// Primeiro verifica se a conta ja nao está incluida
			for(ContaReceberVO conta : listContasReceber) {
				if (conta.getCodigoEmpresa().equals(getCodigoEmpresaContaReceber()) && 
					conta.getCodigoLoja().equals(getCodigoLojaContaReceber()) && 
					conta.getCodigoContaReceber().equals(getCodigoContaReceber())) {
					writeErrorMessage("Conta já incluída!");
					return;
				}
			}
			
			ContaReceberVO contareceberFilter = new ContaReceberVO();
			
			contareceberFilter.setCodigoEmpresa(getCodigoEmpresaContaReceber());
			contareceberFilter.setCodigoLoja(getCodigoLojaContaReceber());
			contareceberFilter.setCodigoContaReceber(getCodigoContaReceber());
			contareceberFilter.setNumeroTituloContaReceber(getNumeroTitulo());
			
			//contareceber = (ContaReceberVO) _dao.get(contareceber);
			List<IValueObject> listContas = _dao.getList(contareceberFilter);
			
			for (IValueObject item : listContas) {
				ContaReceberVO contareceber = (ContaReceberVO) item;
				if (contareceber != null) {
					if (listContasReceber == null) {
						limpar();
					}
					ContaReceberCadastroFacade.calculaValorContaReceberVO(contareceber, getDataRecebimento());
					listContasReceber.add(contareceber);
					write("ok");
				} else {
					writeErrorMessage("Conta a receber não encontrada!");
				}
			}
			
//			if (contareceber != null) {
//				if (listContasReceber == null) {
//					limpar();
//				}
//				ContaReceberCadastroFacade.calculaValorContaReceberVO(contareceber, getDataRecebimento());
//				listContasReceber.add(contareceber);
//				write("ok");
//			} else {
//				writeErrorMessage("Conta a receber não encontrada!");
//			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void remover() {
		if (getCodigoEmpresaContaReceber() == null || 
			getCodigoLojaContaReceber() == null || 
			getCodigoContaReceber() == null) {
			writeErrorMessage("Nenhuma conta foi selecionada!");
			return;
		}
		
		for(ContaReceberVO conta : listContasReceber) {
			if (conta.getCodigoEmpresa().equals(getCodigoEmpresaContaReceber()) && 
				conta.getCodigoLoja().equals(getCodigoLojaContaReceber()) && 
				conta.getCodigoContaReceber().equals(getCodigoContaReceber())) {
				listContasReceber.remove(conta);
				write("ok");
				return;
			}
		}
		writeErrorMessage("Conta a receber não encontrada!");
	}
	
	@IgnoreMethodAuthentication
	public void limpar() {
		listContasReceber = new ArrayList<ContaReceberVO>();
		listRecebimentos = new ArrayList<RecebimentoVO>();
	}
	
	@IgnoreMethodAuthentication
	public void currentlist() {
		if (listContasReceber == null) {
			listContasReceber = new ArrayList<ContaReceberVO>();
		}
		if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
			adicionarconta();
		}
		write(Utils.voToXml(listContasReceber));
	}
	
	@IgnoreMethodAuthentication
	public void alterarvalor() {
		
		for(ContaReceberVO contareceber : listContasReceber) {
			if (contareceber.getCodigoEmpresa().equals(getCodigoEmpresaContaReceber()) && 
				contareceber.getCodigoLoja().equals(getCodigoLojaContaReceber()) && 
				contareceber.getCodigoContaReceber().equals(getCodigoContaReceber())) {
				
				contareceber.setValorCalculado(NovoValorCalculado);
			}
		}
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void totais() {
		if (listContasReceber == null) {
			listContasReceber = new ArrayList<ContaReceberVO>();
		}
		
		BigDecimal totalJuros = BigDecimal.ZERO;
		BigDecimal totalMulta = BigDecimal.ZERO;
		BigDecimal totalDescontos = BigDecimal.ZERO;
		BigDecimal totalOriginal = BigDecimal.ZERO;
		BigDecimal totalAReceber = BigDecimal.ZERO;
		
		for (ContaReceberVO contareceber : listContasReceber) {
			totalJuros = totalJuros.add(contareceber.getValorJurosCalculado()==null?BigDecimal.ZERO:contareceber.getValorJurosCalculado());
			totalMulta = totalMulta.add(contareceber.getValorMultaCalculado()==null?BigDecimal.ZERO:contareceber.getValorMultaCalculado());
			totalDescontos = totalDescontos.add(contareceber.getValorDescontoCalculado()==null?BigDecimal.ZERO:contareceber.getValorDescontoCalculado());
			totalOriginal = totalOriginal.add(contareceber.getValorContaReceber()==null?BigDecimal.ZERO:contareceber.getValorContaReceber());
			totalAReceber = totalAReceber.add(contareceber.getValorAbertoContaReceber()==null?BigDecimal.ZERO:contareceber.getValorAbertoContaReceber());
		}
		
		StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		sb.append("<totais>");
		sb.append("		<juros><![CDATA[" + totalJuros.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></juros>");
		sb.append("		<multa><![CDATA[" + totalMulta.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></multa>");
		sb.append("		<descontos><![CDATA[" + totalDescontos.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></descontos>");
		sb.append("		<valororiginal><![CDATA[" + totalOriginal.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></valororiginal>");
		sb.append("		<valorcalculado><![CDATA[" + totalAReceber.setScale(4, BigDecimal.ROUND_CEILING).toString() + "]]></valorcalculado>");
		sb.append("</totais>");
		
		write(sb.toString());
	}
	
	/** Manutenção dos Recebimentos */
	@IgnoreMethodAuthentication
	public void limparrecebimentos() {
		setListRecebimentos(new ArrayList<RecebimentoVO>());
	}
	
	@IgnoreMethodAuthentication
	public void currentlistrecebimentos() {
		if (getListRecebimentos() == null) {
			setListRecebimentos(new ArrayList<RecebimentoVO>());
		}
		write(Utils.voToXml(getListRecebimentos()));
	}
	
	@IgnoreMethodAuthentication
	public void dados() {
		try {
			GenericDAO _dao = new GenericDAO();
			List<IValueObject> list = new ArrayList<IValueObject>();
			
			list.addAll(_dao.getList(new BancoVO()));
			list.addAll(_dao.getList(new ContaBancariaVO()));
			list.addAll(_dao.getList(new OperadoraCartaoVO()));
			list.addAll(_dao.getList(new BandeiraCartaoVO()));
			
			write(Utils.voToXml(list));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void adicionarrecebimento() {
		if (getCodigoFormaRecebimento() == null || getCodigoFormaRecebimento() <= 0) {
			writeErrorMessage("Informe a forma de recebimento!", "FormaRecebimento");
			return;
		}
		
		if (getValorRecebimento() == null || getValorRecebimento().compareTo(BigDecimal.ZERO) <= 0) {
			writeErrorMessage("Informe o valor do recebimento!", "ValorRecebimento");
			return;
		}
		
		// Arredonda em 2 casas decimais...
		setValorRecebimento(getValorRecebimento().setScale(2, BigDecimal.ROUND_CEILING));
		
		FormaRecebimentoVO forma = null;
		
		try {
			forma = new FormaRecebimentoDAO().get(getCodigoFormaRecebimento());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RecebimentoVO recebimento = null;
		boolean novo = true;
		
		if (getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_DINHEIRO) {
			// Se for em dinheiro, busca essa forma de recebimento, se já existe, e soma o valor.
			// nao há necessidade de 2 recebimentos em dinheiro
			for (RecebimentoVO item : listRecebimentos) {
				if (item.getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_DINHEIRO) {
					recebimento = item;
					novo = false;
				}
			}
		}
		
		if (recebimento == null) {
			recebimento = new RecebimentoVO();
			recebimento.setValorRecebimento(BigDecimal.ZERO);
		}
		
		recebimento.setCodigoFormaRecebimento(CodigoFormaRecebimento);
		recebimento.setValorRecebimento(recebimento.getValorRecebimento().add(ValorRecebimento));
		recebimento.setNomeFormaRecebimento(forma.getNomeFormaRecebimento());
		
		// Tratamento para Movimentacao em Conta Bancaria
		if (getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CREDITO_CONTA) {
			movimentacaoconta.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_DEBITO);
			movimentacaoconta.setValorMovimentacaoContaBancaria(ValorRecebimento);
			recebimento.setMovimentacao(movimentacaoconta);
		}
		
		// Tratamento para cheques
		if (getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CHEQUE) {
			cheque.setValorChequeRecebido(ValorRecebimento);
			recebimento.setCheque(cheque);
		}
		
		// Tratamento para cartao
		if (getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CARTAO) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			try {
				recebimentocartao.setDataHoraRecebimentoCartao(new Timestamp(sdf.parse(getDataRecebimentoCartao() + " " + getHoraRecebimentoCartao()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			recebimentocartao.setValorRecebimentoCartao(ValorRecebimento);
			recebimento.setRecebimentocartao(recebimentocartao);
		}
		
		if (novo) {
			listRecebimentos.add(recebimento);
		}
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void readrecebimento() {
		if (getPosicaoRecebimento() != null && getPosicaoRecebimento() >= 0) {
			write(Utils.voToXml(listRecebimentos.get(PosicaoRecebimento)));
		}
	}
	
	@IgnoreMethodAuthentication
	public void alterarrecebimento() {
		if (PosicaoRecebimento == null || PosicaoRecebimento < 0) {
			writeErrorMessage("Nenhum recebimento selecionado!");
			return;
		}
		if (ValorRecebimento == null || ValorRecebimento.compareTo(BigDecimal.ZERO) <= 0) {
			writeErrorMessage("Informe o valor do recebimento!", "ValorRecebimento");
			return;
		}
		
		RecebimentoVO recebimento = listRecebimentos.get(PosicaoRecebimento);
		recebimento.setValorRecebimento(ValorRecebimento);
		
		// Tratamento para Movimentacao em Conta Bancaria
		if (CodigoFormaRecebimento == FormaRecebimentoVO.FORMA_PAGTO_CREDITO_CONTA) {
			movimentacaoconta.setOperacaoMovimentacaoContaBancaria(MovimentacaoContaBancariaVO.OPERACAO_DEBITO);
			movimentacaoconta.setValorMovimentacaoContaBancaria(ValorRecebimento);
			recebimento.setMovimentacao(movimentacaoconta);
		}
		// Tratamento para cheques
		if (CodigoFormaRecebimento == FormaRecebimentoVO.FORMA_PAGTO_CHEQUE) {
			cheque.setValorChequeRecebido(ValorRecebimento);
			recebimento.setCheque(cheque);
		}
		
		// Tratamento para cartao
		if (getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CARTAO) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			try {
				recebimentocartao.setDataHoraRecebimentoCartao(new Timestamp(sdf.parse(getDataRecebimentoCartao() + " " + getHoraRecebimentoCartao()).getTime()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			recebimentocartao.setValorRecebimentoCartao(ValorRecebimento);
			recebimento.setRecebimentocartao(recebimentocartao);
		}
		
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void excluirrecebimento() {
		if (PosicaoRecebimento == null || PosicaoRecebimento < 0) {
			writeErrorMessage("Nenhum recebimento selecionado!");
			return;
		}
		listRecebimentos.remove(PosicaoRecebimento.intValue());
		write("ok");
	}
	
	@IgnoreMethodAuthentication
	public void confirmar() {
		// So aceita receber contas de um cliente por vez
		int codigoPessoa = 0;
		int codigoEmpresa = 0;
		int codigoLoja = 0;
		
		boolean pessoaDiferente = false;
		
		for (ContaReceberVO conta : listContasReceber) {
			if (codigoPessoa == 0 && conta.getCodigoPessoaCliente() != null) {
				codigoPessoa = conta.getCodigoPessoaCliente();
				codigoEmpresa = conta.getCodigoEmpresaCliente();
				codigoLoja = conta.getCodigoLojaCliente();
			} else {
				if (conta.getCodigoPessoaCliente() != null) {
					if (codigoPessoa != conta.getCodigoPessoaCliente() || codigoEmpresa != conta.getCodigoEmpresaCliente() || codigoLoja != conta.getCodigoLojaCliente()) {
						pessoaDiferente = true;
						break;
					}
				}
			}
		}
		
		if (pessoaDiferente) {
			writeErrorMessage("Recebimento somente para contas do mesmo fornecedor!");
			return;
		}
		
		// Calcula total dos recebimentos
		BigDecimal totalPagtos = BigDecimal.ZERO;
		
		for (RecebimentoVO recebimento : listRecebimentos) {
			totalPagtos = totalPagtos.add(recebimento.getValorRecebimento());
		}
		
		// Calcula total das contas
		BigDecimal totalContas = BigDecimal.ZERO;
		
		for (ContaReceberVO conta : listContasReceber) {
			//conta.setValorAbertoContaReceber(BigDecimal.ZERO);
			totalContas = totalContas.add(conta.getValorAbertoContaReceber());
		}
		
		if (totalContas.setScale(2, BigDecimal.ROUND_HALF_EVEN).compareTo(totalPagtos.setScale(2, BigDecimal.ROUND_HALF_EVEN)) != 0) {
			// Recebimento parcial
			if (listContasReceber.size() > 1) {
				writeErrorMessage("Para recebimento parcial de contas, coloque somente uma conta a receber!");
				return;
			}
		}
		
		// Ordenar arrays pelo valor
		Collections.sort(listRecebimentos, new Comparator<RecebimentoVO>() {
			@Override
			public int compare(RecebimentoVO pag1, RecebimentoVO pag2) {
				return pag1.getValorRecebimento().compareTo(pag2.getValorRecebimento());
			}
		});
		Collections.sort(listContasReceber, new Comparator<ContaReceberVO>() {
			@Override
			public int compare(ContaReceberVO conta1, ContaReceberVO conta2) {
				return conta1.getValorCalculado().compareTo(conta2.getValorCalculado());
			}
		});
		
		try {
			_dao.setAutoCommit(false);
			
			// Salva todos os recebimentos
			for (RecebimentoVO recebimento : listRecebimentos) {
				
				recebimento.setCodigoEmpresa(empresasessao.getCodigoEmpresa());
				recebimento.setCodigoLoja(lojasessao.getCodigoLoja());
				recebimento.setDataHoraRecebimento(Utils.getNow());
				recebimento.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				recebimento.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				recebimento.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				recebimento.setNewRecord(true);
				
				_dao.persist(recebimento);
				
				if (recebimento.getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CREDITO_CONTA) {
					// Debito em conta - Gera movimentacao conta bancaria
					recebimento.getMovimentacao().setCodigoRecebimento(recebimento.getCodigoRecebimento());
					recebimento.getMovimentacao().setCodigoEmpresaRecebimento(recebimento.getCodigoEmpresa());
					recebimento.getMovimentacao().setCodigoLojaRecebimento(recebimento.getCodigoLoja());
					
					recebimento.getMovimentacao().setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					recebimento.getMovimentacao().setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					recebimento.getMovimentacao().setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					recebimento.getMovimentacao().setDataMovimentacaoContaBancaria(getDataRecebimento());
					recebimento.getMovimentacao().setDescricaoMovimentacaoContaBancaria("Recebimento de contas");
					
					recebimento.getMovimentacao().setNewRecord(true);
					_dao.persist(recebimento.getMovimentacao());
				} else if (recebimento.getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CHEQUE) {
					
					// Cheque - gera registro de cheque recebido
					recebimento.getCheque().setCodigoEmpresa(empresasessao.getCodigoEmpresa());
					recebimento.getCheque().setCodigoLoja(lojasessao.getCodigoLoja());
					
					recebimento.getCheque().setCodigoRecebimento(recebimento.getCodigoRecebimento());
					recebimento.getCheque().setCodigoEmpresaRecebimento(recebimento.getCodigoEmpresa());
					recebimento.getCheque().setCodigoLojaRecebimento(recebimento.getCodigoLoja());
					
					recebimento.getCheque().setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					recebimento.getCheque().setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					recebimento.getCheque().setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					recebimento.getCheque().setCodigoEmpresaEmitente(listContasReceber.get(0).getCodigoEmpresaCliente());
					recebimento.getCheque().setCodigoLojaEmitente(listContasReceber.get(0).getCodigoLojaCliente());
					recebimento.getCheque().setCodigoPessoaEmitente(listContasReceber.get(0).getCodigoPessoaCliente());
					recebimento.getCheque().setCodigoEnderecoPessoaEmitente(listContasReceber.get(0).getCodigoEnderecoPessoaCliente());
					
					recebimento.getCheque().setNewRecord(true);
					_dao.persist(recebimento.getCheque());
				} else if (recebimento.getCodigoFormaRecebimento() == FormaRecebimentoVO.FORMA_PAGTO_CARTAO) {
					// Cartões - gera registro de recebimento por cartão
					recebimento.getRecebimentocartao().setCodigoEmpresa(empresasessao.getCodigoEmpresa());
					recebimento.getRecebimentocartao().setCodigoLoja(lojasessao.getCodigoLoja());
					
					recebimento.getRecebimentocartao().setCodigoRecebimento(recebimento.getCodigoRecebimento());
					recebimento.getRecebimentocartao().setCodigoEmpresaRecebimento(recebimento.getCodigoEmpresa());
					recebimento.getRecebimentocartao().setCodigoLojaRecebimento(recebimento.getCodigoLoja());
					
					recebimento.getRecebimentocartao().setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					recebimento.getRecebimentocartao().setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					recebimento.getRecebimentocartao().setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					recebimento.getRecebimentocartao().setCodigoEmpresaEmitente(listContasReceber.get(0).getCodigoEmpresaCliente());
					recebimento.getRecebimentocartao().setCodigoLojaEmitente(listContasReceber.get(0).getCodigoLojaCliente());
					recebimento.getRecebimentocartao().setCodigoPessoaEmitente(listContasReceber.get(0).getCodigoPessoaCliente());
					recebimento.getRecebimentocartao().setCodigoEnderecoPessoaEmitente(listContasReceber.get(0).getCodigoEnderecoPessoaCliente());
					
					recebimento.getRecebimentocartao().setStatusRecebimentoCartao("A"); // Aberto
					
					recebimento.getRecebimentocartao().setNewRecord(true);
					_dao.persist(recebimento.getRecebimentocartao());
				}
			}

			BigDecimal valorRestante = BigDecimal.ZERO;
			
			// Loop para distribuicao dos recebimentos
			for (RecebimentoVO recebimento : listRecebimentos) {
				valorRestante = valorRestante.add(recebimento.getValorRecebimento());
				for (ContaReceberVO conta : listContasReceber) {
					// Somente as contas em aberto e se tiver valor ainda a distribuir
					if (conta.getSituacaoContaReceber().equals(ContaReceberVO.SITUACAO_ABERTO) && valorRestante.compareTo(BigDecimal.ZERO) > 0) {
						if (valorRestante.compareTo(conta.getValorAbertoContaReceber()) < 0) { 
							// Se o valor em aberto for menor que valor do recebimento
							// Passa todo o valor para esta conta, mas nao quita.
							conta.setValorAbertoContaReceber(conta.getValorAbertoContaReceber().subtract(valorRestante));
							
							BaixaContaReceberVO baixa = new BaixaContaReceberVO();
							
							baixa.setCodigoContaReceber(conta.getCodigoContaReceber());
							baixa.setCodigoLojaContaReceber(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaReceber(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(recebimento.getCodigoEmpresa());
							baixa.setCodigoLoja(recebimento.getCodigoLoja());
							baixa.setCodigoRecebimento(recebimento.getCodigoRecebimento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorRecebimento(valorRestante);
							baixa.setNewRecord(true);
							
							_dao.persist(baixa);
							valorRestante = BigDecimal.ZERO;
							break;
						} else if (valorRestante.compareTo(conta.getValorAbertoContaReceber()) == 0) { 
							// Se o valor em aberto for igual ao valor do recebimento
							// Passa todo o valor para a conta, e quita.
							conta.setValorAbertoContaReceber(conta.getValorAbertoContaReceber().subtract(valorRestante));
							conta.setSituacaoContaReceber(ContaReceberVO.SITUACAO_QUITADO);
							_dao.persist(conta);
							
							BaixaContaReceberVO baixa = new BaixaContaReceberVO();
							
							baixa.setCodigoContaReceber(conta.getCodigoContaReceber());
							baixa.setCodigoLojaContaReceber(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaReceber(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(recebimento.getCodigoEmpresa());
							baixa.setCodigoLoja(recebimento.getCodigoLoja());
							baixa.setCodigoRecebimento(recebimento.getCodigoRecebimento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorRecebimento(valorRestante);
							baixa.setNewRecord(true);
							
							_dao.persist(baixa);
							
							valorRestante = BigDecimal.ZERO;
							break;
						} else {
							// Se o valor em aberto for menor que o valor do recebimento
							// Passa todo o valor em aberto para a conta, quita, e deixa o restante para o proximo.
							//conta.setValorAbertoContaReceber(conta.getValorAbertoContaReceber().subtract(recebimento.getValorRecebimento()));
							BigDecimal valorUsado = conta.getValorAbertoContaReceber();
							conta.setValorAbertoContaReceber(BigDecimal.ZERO);
							conta.setSituacaoContaReceber(ContaReceberVO.SITUACAO_QUITADO);
							_dao.persist(conta);
							
							BaixaContaReceberVO baixa = new BaixaContaReceberVO();
							
							baixa.setCodigoContaReceber(conta.getCodigoContaReceber());
							baixa.setCodigoLojaContaReceber(conta.getCodigoLoja());
							baixa.setCodigoEmpresaContaReceber(conta.getCodigoEmpresa());
							
							baixa.setCodigoEmpresa(recebimento.getCodigoEmpresa());
							baixa.setCodigoLoja(recebimento.getCodigoLoja());
							baixa.setCodigoRecebimento(recebimento.getCodigoRecebimento());
							
							baixa.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
							baixa.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
							baixa.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
							baixa.setValorRecebimento(valorUsado);
							baixa.setNewRecord(true);
							
							valorRestante = valorRestante.subtract(valorUsado);
							
							_dao.persist(baixa);
						}
					}
				}
			}
			_dao.commit();
		} catch (SQLException e) {
			_dao.rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
			return;
		}
		write("ok");
	}
	
	public void setListContasReceber(List<ContaReceberVO> listContasReceber) {
		this.listContasReceber = listContasReceber;
	}

	public List<ContaReceberVO> getListContasReceber() {
		return listContasReceber;
	}

	public void setNovoValorCalculado(BigDecimal novoValorCalculado) {
		NovoValorCalculado = novoValorCalculado;
	}

	public BigDecimal getNovoValorCalculado() {
		return NovoValorCalculado;
	}

	public void setCodigoEmpresaContaReceber(Integer codigoEmpresaContaReceber) {
		CodigoEmpresaContaReceber = codigoEmpresaContaReceber;
	}

	public Integer getCodigoEmpresaContaReceber() {
		return CodigoEmpresaContaReceber;
	}

	public void setCodigoLojaContaReceber(Integer codigoLojaContaReceber) {
		CodigoLojaContaReceber = codigoLojaContaReceber;
	}

	public Integer getCodigoLojaContaReceber() {
		return CodigoLojaContaReceber;
	}

	public void setCodigoContaReceber(Integer codigoContaReceber) {
		CodigoContaReceber = codigoContaReceber;
	}

	public Integer getCodigoContaReceber() {
		return CodigoContaReceber;
	}

	public void setValorRecebimento(BigDecimal valorRecebimento) {
		ValorRecebimento = valorRecebimento;
	}

	public BigDecimal getValorRecebimento() {
		return ValorRecebimento;
	}

	public void setListRecebimentos(List<RecebimentoVO> listRecebimentos) {
		this.listRecebimentos = listRecebimentos;
	}

	public List<RecebimentoVO> getListRecebimentos() {
		return listRecebimentos;
	}

	public void setPosicaoRecebimento(Integer posicaoRecebimento) {
		PosicaoRecebimento = posicaoRecebimento;
	}

	public Integer getPosicaoRecebimento() {
		return PosicaoRecebimento;
	}

	public void setMovimentacaoconta(MovimentacaoContaBancariaVO movimentacaoconta) {
		this.movimentacaoconta = movimentacaoconta;
	}

	public MovimentacaoContaBancariaVO getMovimentacaoconta() {
		return movimentacaoconta;
	}

	public void setCheque(ChequeRecebidoVO cheque) {
		this.cheque = cheque;
	}

	public ChequeRecebidoVO getCheque() {
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

	public void setCodigoFormaRecebimento(Integer codigoFormaRecebimento) {
		CodigoFormaRecebimento = codigoFormaRecebimento;
	}

	public Integer getCodigoFormaRecebimento() {
		return CodigoFormaRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		DataRecebimento = dataRecebimento;
	}

	public Date getDataRecebimento() {
		return DataRecebimento;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		NumeroTitulo = numeroTitulo;
	}

	public String getNumeroTitulo() {
		return NumeroTitulo;
	}

	public void setRecebimentocartao(RecebimentoCartaoVO recebimentocartao) {
		this.recebimentocartao = recebimentocartao;
	}

	public RecebimentoCartaoVO getRecebimentocartao() {
		return recebimentocartao;
	}

	public void setDataRecebimentoCartao(String dataRecebimentoCartao) {
		DataRecebimentoCartao = dataRecebimentoCartao;
	}

	public String getDataRecebimentoCartao() {
		return DataRecebimentoCartao;
	}

	public void setHoraRecebimentoCartao(String horaRecebimentoCartao) {
		HoraRecebimentoCartao = horaRecebimentoCartao;
	}

	public String getHoraRecebimentoCartao() {
		return HoraRecebimentoCartao;
	}

}
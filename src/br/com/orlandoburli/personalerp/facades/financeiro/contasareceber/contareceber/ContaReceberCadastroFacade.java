package br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.contareceber;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.core.dao.financeiro.CentroCustoDAO;
import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.PortadorDAO;
import br.com.orlandoburli.core.dao.financeiro.TipoDocumentoDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.BaixaContaReceberDAO;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.BaixaContaReceberVO;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ContaReceberCadastroFacade extends BaseCadastroFlexFacade<ContaReceberVO, ContaReceberDAO>{
	
	private static final long serialVersionUID = 1L;

	private String NumeroPlanoContaPesquisa;
	private Integer CodigoCentroCustoPesquisa;
	private Integer CodigoTipoDocumentoPesquisa;
	private Integer CodigoPortadorPesquisa;
	
	private String ClienteQuery;
	private Integer CodigoPessoaCliente;
	private Integer CodigoLojaCliente;
	private Integer CodigoEmpresaCliente;
	private Integer CodigoEnderecoPessoaCliente;

	public ContaReceberCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("NumeroTituloContaReceber", "Número do Título"));
		addNewValidator(new NotEmptyValidator("DescricaoContaReceber", "Descrição"));
		addNewValidator(new NotEmptyValidator("ValorContaReceber", "Valor da Conta"));
		addNewValidator(new NotEmptyValidator("DataVencimentoContaReceber", "Data de Vencimento"));
		addNewValidator(new NotEmptyValidator("CodigoPlanoConta", "Plano de Contas", "NumeroPlanoConta"));
		addNewValidator(new NotEmptyValidator("CodigoCentroCusto", "Centro de Custo"));
		addNewValidator(new NotEmptyValidator("CodigoTipoDocumento", "Tipo de Documento"));
		addNewValidator(new NotEmptyValidator("CodigoPortador", "Portador"));
	}
	
	@IgnoreMethodAuthentication
	public void cliente() {
		EnderecoPessoaVO filter = new EnderecoPessoaVO();
		
		if (getClienteQuery() != null) {
			filter.setNomeEnderecoPessoa(getClienteQuery() + "%");
		}
		filter.setCodigoEmpresa(getCodigoEmpresaCliente());
		filter.setCodigoLoja(getCodigoLojaCliente());
		filter.setCodigoPessoa(getCodigoPessoaCliente());
		filter.setCodigoEnderecoPessoa(getCodigoEnderecoPessoaCliente());
		
		try {
			List<IValueObject> list = getGenericDao().getList(filter);
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		if (!super.doBeforeSave()) {
			return false;
		}
		if (getVo().getSituacaoContaReceber().equalsIgnoreCase(ContaReceberVO.SITUACAO_QUITADO)) {
			messages.add(new MessageVO("Este título está quitado e não pode mais ser alterado!"));
			return false;
		}
		if (getVo().getValorContaReceber().compareTo(BigDecimal.ZERO) <= 0) {
			messages.add(new MessageVO("O valor da conta deve ser maior que zero!", "ValorContaReceber"));
			return false;
		}
		if (getVo().getPercentualJurosDiarioContaReceber() != null && getVo().getValorJurosDiarioContaReceber() != null && getVo().getPercentualJurosDiarioContaReceber().compareTo(BigDecimal.ZERO) > 0 && getVo().getValorJurosDiarioContaReceber().compareTo(BigDecimal.ZERO) > 0) {
			messages.add(new MessageVO("Informe somente o percentual (%) OU valor (R$) dos juros da conta.", "PercentualJurosDiarioContaReceber"));
			return false;
		}
		if (getVo().getPercentualMultaContaReceber() != null && getVo().getValorMultaContaReceber() != null && getVo().getPercentualMultaContaReceber().compareTo(BigDecimal.ZERO) > 0 && getVo().getValorMultaContaReceber().compareTo(BigDecimal.ZERO) > 0) {
			messages.add(new MessageVO("Informe somente o percentual (%) OU valor (R$) da multa da conta.", "PercentualMultaContaReceber"));
			return false;
		}
		if (getVo().getDescontoContaReceber() != null && getVo().getDescontoContaReceber().compareTo(BigDecimal.ZERO) > 0 && getVo().getDescontoAteContaReceber() == null) {
			messages.add(new MessageVO("Informe até quando este desconto está válido.", "DescontoAteContaReceber"));
			return false;
		}
		if (getVo().getDescontoAteContaReceber() != null) {
			if (Utils.getDiffDays(getVo().getDataVencimentoContaReceber(), getVo().getDescontoAteContaReceber()) >= 0) {
				messages.add(new MessageVO("A data do desconto não pode ser maior que a data de vencimento!", "DescontoAteContaReceber"));
				return false;
			}
		}
		
		// Se for do tipo previsao, nao pode ter baixas
		if (getVo().getSituacaoContaReceber() == ContaReceberVO.SITUACAO_PREVISTO) {
			BaixaContaReceberDAO _dao = new BaixaContaReceberDAO();
			BaixaContaReceberVO filter = new BaixaContaReceberVO();
			
			filter.setCodigoContaReceber(getVo().getCodigoContaReceber());
			filter.setCodigoEmpresaContaReceber(getVo().getCodigoEmpresa());
			filter.setCodigoLojaContaReceber(getVo().getCodigoLoja());
			
			try {
				List<BaixaContaReceberVO> list = _dao.getList(filter);
				if (list.size() > 0) {
					messages.add(new MessageVO("Essa conta possui baixas e não pode ser mais do tipo previsão!"));
					return false;
				}
			} catch (SQLException e) {
				writeErrorMessage(e.getMessage());
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		this.getVo().setCodigoLoja(getLojasessao().getCodigoLoja());
		this.getVo().setDataLancamentoContaReceber(Utils.getNow());
		if (this.getVo().getSituacaoContaReceber() == null ) {
			this.getVo().setSituacaoContaReceber(ContaReceberVO.SITUACAO_ABERTO);
		}
		if (this.getVo().getParcelaContaReceber() == null) {
			this.getVo().setParcelaContaReceber(1);
		}
		if (this.getVo().getNumeroParcelasContaReceber() == null) {
			this.getVo().setNumeroParcelasContaReceber(1);
		}
		return super.doBeforeInsert();
	}
	
	public void cancelar() {
		// Verifica se existe algum valor pago
		BaixaContaReceberDAO _dao = new BaixaContaReceberDAO();
		BaixaContaReceberVO filter = new BaixaContaReceberVO();
		
		filter.setCodigoContaReceber(getVo().getCodigoContaReceber());
		filter.setCodigoEmpresaContaReceber(getVo().getCodigoEmpresa());
		filter.setCodigoLojaContaReceber(getVo().getCodigoLoja());
		
		try {
			List<BaixaContaReceberVO> list = _dao.getList(filter);
			if (list.size() > 0) {
				writeErrorMessage("Essa conta possui baixas e não pode ser cancelada!");
				return;
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			return;
		}
		this.getVo().setSituacaoContaReceber(ContaReceberVO.SITUACAO_CANCELADO);
		alterar();
	}
	
	@Override
	public void excluir() {
		writeErrorMessage("Não é permitida a exclusão de títulos a pagar!");
	}
	
	@IgnoreMethodAuthentication
	public void planoconta() {
		PlanoContaVO _vo = new PlanoContaVO();
		PlanoContaDAO _dao = new PlanoContaDAO();
		_vo.setNumeroPlanoConta(getNumeroPlanoContaPesquisa());
		try {
			List<PlanoContaVO> list = _dao.getList(_vo);
			if (list.size() == 1) {
				write(Utils.voToXml(list.get(0)));
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void centrocusto() {
		CentroCustoDAO _dao = new CentroCustoDAO();
		try {
			write(Utils.voToXml(_dao.get(getCodigoCentroCustoPesquisa())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void tipodocumento() {
		TipoDocumentoDAO _dao = new TipoDocumentoDAO();
		try {
			write(Utils.voToXml(_dao.get(getCodigoTipoDocumentoPesquisa())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void portador() {
		PortadorDAO _dao = new PortadorDAO();
		try {
			write(Utils.voToXml(_dao.get(getCodigoPortadorPesquisa())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	/**
	 * Este método cadastra automaticamente as demais parcelas de uma conta a receber,
	 * baseado no numero de parcelas.
	 */
	public void autocadastro() {
		try {
			Date dataVencimentoBase = getVo().getDataVencimentoContaReceber();
			dao.setAutoCommit(false);
			
			// Verifica se tem mais de uma parcela
			if (this.getVo().getNumeroParcelasContaReceber() != null && this.getVo().getNumeroParcelasContaReceber().compareTo(1) > 0) {
				for (int i = this.getVo().getParcelaContaReceber(); i < this.getVo().getNumeroParcelasContaReceber(); i++) {
					
					this.getVo().setParcelaContaReceber(i + 1);
					Calendar dataVencimento = Utils.dateToCalendar(dataVencimentoBase);
					dataVencimento.add(Calendar.MONTH, i);
					this.getVo().setDataVencimentoContaReceber(Utils.calendarToDate(dataVencimento));
					this.getVo().setNewRecord(true);
					this.doBeforeInsert();
					dao.persist(getVo());
				}
			}
			dao.commit();
			write("ok");
		} catch (SQLException e) {
			dao.rollback();
			writeErrorMessage(e.getMessage());
		}
	}
	
	public static void calculaValorContaReceberVO (List<ContaReceberVO> contas, Date data) {
		for (ContaReceberVO conta : contas) {
			calculaValorContaReceberVO(conta, data);
		}
	}
	
//	public static void calculaValorContaReceberVO (ContaReceberVO contapagar) {
//		calculaValorContaReceberVO(contapagar, Utils.getToday());
//	}
	
	public static void calculaValorContaReceberVO (ContaReceberVO contareceber, Date date) {
		if (contareceber.getDataVencimentoContaReceber().compareTo(date) < 0 && contareceber.getSituacaoContaReceber().equals(ContaReceberVO.SITUACAO_ABERTO)) {
			contareceber.setSituacaoTituloAReceber("V"); // Vencido
			
			BigDecimal cem = new BigDecimal(100);
			BigDecimal valor = contareceber.getValorContaReceber();
			BigDecimal juros = BigDecimal.ZERO;
			BigDecimal multa = BigDecimal.ZERO;
			
			contareceber.setValorMultaCalculado(BigDecimal.ZERO);
			contareceber.setValorJurosCalculado(BigDecimal.ZERO);
			contareceber.setValorDescontoCalculado(BigDecimal.ZERO);
			
			// Verifica se tem multa em percentual
			if (contareceber.getPercentualMultaContaReceber() != null && contareceber.getPercentualMultaContaReceber().compareTo(BigDecimal.ZERO) > 0) {
				multa = multa.add(contareceber.getPercentualMultaContaReceber().multiply(contareceber.getValorContaReceber())).divide(cem, BigDecimal.ROUND_CEILING);
				contareceber.setValorMultaCalculado(multa);
			} else if (contareceber.getValorMultaContaReceber() != null && contareceber.getValorMultaContaReceber().compareTo(BigDecimal.ZERO) > 0) { // Verifica se tem multa em valor
				multa = multa.add(contareceber.getValorMultaContaReceber());
			}
			
			// Calcula o numero de dias do vencimento
			BigDecimal dias = new BigDecimal(Utils.getDiffDays(contareceber.getDataVencimentoContaReceber(), date));
			// Verifica se tem juros / dia em percentual
			if (contareceber.getPercentualJurosDiarioContaReceber() != null && contareceber.getPercentualJurosDiarioContaReceber().compareTo(BigDecimal.ZERO) > 0) {
				// Percentual de juros X dias de atraso
				juros = juros.add(contareceber.getPercentualJurosDiarioContaReceber().multiply(dias)).divide(cem);
				juros = juros.multiply(contareceber.getValorContaReceber()).setScale(2, BigDecimal.ROUND_CEILING);
			} else if (contareceber.getValorJurosDiarioContaReceber() != null && contareceber.getValorJurosDiarioContaReceber().compareTo(BigDecimal.ZERO) > 0) {
				// Valor dos juros / dia X dias de atraso
				juros = juros.multiply(dias);
			}
			
			// Calcula o valor final (multa + juros)
			valor = valor.add(multa);
			valor = valor.add(juros);
			
			// Seta a multa
			contareceber.setValorMultaCalculado(multa);
			// Seta os juros
			contareceber.setValorJurosCalculado(juros);
			// Seta Desconto (0)
			contareceber.setValorDescontoCalculado(BigDecimal.ZERO);
			// Seta o valor total calculado
			contareceber.setValorCalculado(valor);
		} else if (contareceber.getDataVencimentoContaReceber().compareTo(date) == 0 && contareceber.getSituacaoContaReceber().equals(ContaReceberVO.SITUACAO_ABERTO)) {
			contareceber.setSituacaoTituloAReceber("H"); // Vence hoje
			contareceber.setValorCalculado(contareceber.getValorContaReceber());
			
			contareceber.setValorMultaCalculado(BigDecimal.ZERO);
			contareceber.setValorJurosCalculado(BigDecimal.ZERO);
			contareceber.setValorDescontoCalculado(BigDecimal.ZERO);
		} else if (contareceber.getDataVencimentoContaReceber().compareTo(date) > 0 && contareceber.getSituacaoContaReceber().equals(ContaReceberVO.SITUACAO_ABERTO)) {
			contareceber.setSituacaoTituloAReceber("A"); // A vencer
			BigDecimal valor = contareceber.getValorContaReceber();
			
			contareceber.setValorMultaCalculado(BigDecimal.ZERO);
			contareceber.setValorJurosCalculado(BigDecimal.ZERO);
			
			// Calcula descontos, se houver
			if (contareceber.getDescontoAteContaReceber() != null && contareceber.getDescontoAteContaReceber().compareTo(date) >= 0 && contareceber.getDescontoContaReceber() != null && contareceber.getDescontoContaReceber().compareTo(BigDecimal.ZERO) > 0) {
				valor = valor.subtract(contareceber.getDescontoContaReceber());
				contareceber.setValorDescontoCalculado(contareceber.getDescontoContaReceber());
				contareceber.setValorCalculado(valor);
			} else {
				contareceber.setValorCalculado(contareceber.getValorContaReceber());
				contareceber.setValorDescontoCalculado(BigDecimal.ZERO);
			}
		} else if (contareceber.getSituacaoContaReceber().equals(ContaReceberVO.SITUACAO_CANCELADO)) {
			contareceber.setValorCalculado(BigDecimal.ZERO);
		} else {
			contareceber.setValorCalculado(contareceber.getValorContaReceber());
		}
		
		try {
			// Verifica se possui pagamentos (diminuir do valor em aberto)
			BaixaContaReceberDAO baixaDao = new BaixaContaReceberDAO();
			BaixaContaReceberVO _filter = new BaixaContaReceberVO();
			_filter.setCodigoContaReceber(contareceber.getCodigoContaReceber());
			_filter.setCodigoEmpresaContaReceber(contareceber.getCodigoEmpresa());
			_filter.setCodigoLojaContaReceber(contareceber.getCodigoLoja());
			List<BaixaContaReceberVO> listBaixas = baixaDao.getList(_filter);
			
			BigDecimal valorPago = BigDecimal.ZERO;
			
			for (BaixaContaReceberVO baixa : listBaixas) {
				valorPago = valorPago.add(baixa.getValorRecebimento());
			}
			
			contareceber.setValorAbertoContaReceber(contareceber.getValorCalculado().subtract(valorPago));
			
			if (contareceber.getValorAbertoContaReceber().compareTo(BigDecimal.ZERO) < 0) {
				contareceber.setValorAbertoContaReceber(BigDecimal.ZERO);
			}
			contareceber.setBaixas(listBaixas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setNumeroPlanoContaPesquisa(String numeroPlanoContaPesquisa) {
		NumeroPlanoContaPesquisa = numeroPlanoContaPesquisa;
	}

	public String getNumeroPlanoContaPesquisa() {
		return NumeroPlanoContaPesquisa;
	}

	public void setCodigoCentroCustoPesquisa(Integer codigoCentroCustoPesquisa) {
		CodigoCentroCustoPesquisa = codigoCentroCustoPesquisa;
	}

	public Integer getCodigoCentroCustoPesquisa() {
		return CodigoCentroCustoPesquisa;
	}

	public void setCodigoTipoDocumentoPesquisa(
			Integer codigoTipoDocumentoPesquisa) {
		CodigoTipoDocumentoPesquisa = codigoTipoDocumentoPesquisa;
	}

	public Integer getCodigoTipoDocumentoPesquisa() {
		return CodigoTipoDocumentoPesquisa;
	}

	public void setCodigoPortadorPesquisa(Integer codigoPortadorPesquisa) {
		CodigoPortadorPesquisa = codigoPortadorPesquisa;
	}

	public Integer getCodigoPortadorPesquisa() {
		return CodigoPortadorPesquisa;
	}

	public void setFornecedorQuery(String fornecedorQuery) {
		ClienteQuery = fornecedorQuery;
	}

	public String getClienteQuery() {
		return ClienteQuery;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaCliente = codigoPessoaFornecedor;
	}

	public Integer getCodigoPessoaCliente() {
		return CodigoPessoaCliente;
	}

	public void setCodigoLojaFornecedor(Integer codigoLojaFornecedor) {
		CodigoLojaCliente = codigoLojaFornecedor;
	}

	public Integer getCodigoLojaCliente() {
		return CodigoLojaCliente;
	}

	public void setCodigoEmpresaFornecedor(Integer codigoEmpresaFornecedor) {
		CodigoEmpresaCliente = codigoEmpresaFornecedor;
	}

	public Integer getCodigoEmpresaCliente() {
		return CodigoEmpresaCliente;
	}

	public void setCodigoEnderecoPessoaCliente(
			Integer codigoEnderecoPessoaCliente) {
		CodigoEnderecoPessoaCliente = codigoEnderecoPessoaCliente;
	}

	public Integer getCodigoEnderecoPessoaCliente() {
		return CodigoEnderecoPessoaCliente;
	}
}
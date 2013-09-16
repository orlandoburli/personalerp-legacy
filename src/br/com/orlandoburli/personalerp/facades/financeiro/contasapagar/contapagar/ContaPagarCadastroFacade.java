package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.contapagar;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.financeiro.CentroCustoDAO;
import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.PortadorDAO;
import br.com.orlandoburli.core.dao.financeiro.TipoDocumentoDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.BaixaContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ContaPagarCadastroFacade extends BaseCadastroFlexFacade<ContaPagarVO, ContaPagarDAO> {

	private static final long serialVersionUID = 1L;
	
	private String NumeroPlanoContaPesquisa;
	private Integer CodigoCentroCustoPesquisa;
	private Integer CodigoTipoDocumentoPesquisa;
	private Integer CodigoPortadorPesquisa;
	
	private String FornecedorQuery;
	private Integer CodigoPessoaFornecedor;
	private Integer CodigoLojaFornecedor;
	private Integer CodigoEmpresaFornecedor;

	public ContaPagarCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("NumeroTituloContaPagar", "Número do Título"));
		addNewValidator(new NotEmptyValidator("DescricaoContaPagar", "Descrição"));
		addNewValidator(new NotEmptyValidator("ValorContaPagar", "Valor da Conta"));
		addNewValidator(new NotEmptyValidator("DataVencimentoContaPagar", "Data de Vencimento"));
		addNewValidator(new NotEmptyValidator("CodigoPlanoConta", "Plano de Contas", "NumeroPlanoConta"));
		addNewValidator(new NotEmptyValidator("CodigoCentroCusto", "Centro de Custo"));
		addNewValidator(new NotEmptyValidator("CodigoTipoDocumento", "Tipo de Documento"));
		addNewValidator(new NotEmptyValidator("CodigoPortador", "Portador"));
	
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
	}
	
	@Override
	public void doBeforeWriteVo(ContaPagarVO vo) {
		vo.setNumeroPlanoConta("x" + vo.getNumeroPlanoConta() + "");
		super.doBeforeWriteVo(vo);
	}
	
	@IgnoreMethodAuthentication
	public void fornecedor() {
		PessoaVO filter = new PessoaVO();
		
		if (getFornecedorQuery() != null) {
			filter.setNomeRazaoSocialPessoa(getFornecedorQuery() + "%");
		}
		filter.setCodigoEmpresa(getCodigoEmpresaFornecedor());
		filter.setCodigoLoja(getCodigoLojaFornecedor());
		filter.setCodigoPessoa(getCodigoPessoaFornecedor());
		
		PessoaDAO _dao = new PessoaDAO();
		
		try {
			List<PessoaVO> list = _dao.getList(filter);
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
		if (getVo().getSituacaoContaPagar().equalsIgnoreCase(ContaPagarVO.SITUACAO_QUITADO)) {
			messages.add(new MessageVO("Este título está quitado e não pode mais ser alterado!"));
			return false;
		}
		if (getVo().getValorContaPagar().compareTo(BigDecimal.ZERO) <= 0) {
			messages.add(new MessageVO("O valor da conta deve ser maior que zero!", "ValorContaPagar"));
			return false;
		}
		if (getVo().getPercentualJurosDiarioContaPagar() != null && getVo().getValorJurosDiarioContaPagar() != null && getVo().getPercentualJurosDiarioContaPagar().compareTo(BigDecimal.ZERO) > 0 && getVo().getValorJurosDiarioContaPagar().compareTo(BigDecimal.ZERO) > 0) {
			messages.add(new MessageVO("Informe somente o percentual (%) OU valor (R$) dos juros da conta.", "PercentualJurosDiarioContaPagar"));
			return false;
		}
		if (getVo().getPercentualMultaContaPagar() != null && getVo().getValorMultaContaPagar() != null && getVo().getPercentualMultaContaPagar().compareTo(BigDecimal.ZERO) > 0 && getVo().getValorMultaContaPagar().compareTo(BigDecimal.ZERO) > 0) {
			messages.add(new MessageVO("Informe somente o percentual (%) OU valor (R$) da multa da conta.", "PercentualMultaContaPagar"));
			return false;
		}
		if (getVo().getDescontoContaPagar() != null && getVo().getDescontoContaPagar().compareTo(BigDecimal.ZERO) > 0 && getVo().getDescontoAteContaPagar() == null) {
			messages.add(new MessageVO("Informe até quando este desconto está válido.", "DescontoAteContaPagar"));
			return false;
		}
		if (getVo().getDescontoAteContaPagar() != null) {
			if (Utils.getDiffDays(getVo().getDataVencimentoContaPagar(), getVo().getDescontoAteContaPagar()) >= 0) {
				messages.add(new MessageVO("A data do desconto não pode ser maior que a data de vencimento!", "DescontoAteContaPagar"));
				return false;
			}
		}
		
		// Se for do tipo previsao, nao pode ter baixas
		if (getVo().getSituacaoContaPagar() == ContaPagarVO.SITUACAO_PREVISTO) {
			BaixaContaPagarDAO _dao = new BaixaContaPagarDAO();
			BaixaContaPagarVO filter = new BaixaContaPagarVO();
			
			filter.setCodigoContaPagar(getVo().getCodigoContaPagar());
			filter.setCodigoEmpresaContaPagar(getVo().getCodigoEmpresa());
			filter.setCodigoLojaContaPagar(getVo().getCodigoLoja());
			
			try {
				List<BaixaContaPagarVO> list = _dao.getList(filter);
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
		this.getVo().setDataLancamentoContaPagar(Utils.getToday());
		if (this.getVo().getSituacaoContaPagar() == null ) {
			this.getVo().setSituacaoContaPagar(ContaPagarVO.SITUACAO_ABERTO);
		}
		if (this.getVo().getParcelaContaPagar() == null) {
			this.getVo().setParcelaContaPagar(1);
		}
		if (this.getVo().getNumeroParcelasContaPagar() == null) {
			this.getVo().setNumeroParcelasContaPagar(1);
		}
		return super.doBeforeInsert();
	}
	
	public void cancelar() {
		// Verifica se existe algum valor pago
		BaixaContaPagarDAO _dao = new BaixaContaPagarDAO();
		BaixaContaPagarVO filter = new BaixaContaPagarVO();
		
		filter.setCodigoContaPagar(getVo().getCodigoContaPagar());
		filter.setCodigoEmpresaContaPagar(getVo().getCodigoEmpresa());
		filter.setCodigoLojaContaPagar(getVo().getCodigoLoja());
		
		try {
			List<BaixaContaPagarVO> list = _dao.getList(filter);
			if (list.size() > 0) {
				writeErrorMessage("Essa conta possui baixas e não pode ser cancelada!");
				return;
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			return;
		}
		this.getVo().setSituacaoContaPagar(ContaPagarVO.SITUACAO_CANCELADO);
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
	 * Este método cadastra automaticamente as demais parcelas de uma conta a pagar,
	 * baseado no numero de parcelas.
	 */
	public void autocadastro() {
		try {
			Date dataVencimentoBase = getVo().getDataVencimentoContaPagar();
			dao.setAutoCommit(false);
			
			// Verifica se tem mais de uma parcela
			if (this.getVo().getNumeroParcelasContaPagar() != null && this.getVo().getNumeroParcelasContaPagar().compareTo(1) > 0) {
				for (int i = this.getVo().getParcelaContaPagar(); i < this.getVo().getNumeroParcelasContaPagar(); i++) {
					
					this.getVo().setParcelaContaPagar(i + 1);
					Calendar dataVencimento = Utils.dateToCalendar(dataVencimentoBase);
					dataVencimento.add(Calendar.MONTH, i);
					this.getVo().setDataVencimentoContaPagar(Utils.calendarToDate(dataVencimento));
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
	
	public static void calculaValorContaPagarVO (List<ContaPagarVO> contas, Date data) {
		for (ContaPagarVO conta : contas) {
			calculaValorContaPagarVO(conta, data);
		}
	}
	
//	public static void calculaValorContaPagarVO (ContaPagarVO contapagar) {
//		calculaValorContaPagarVO(contapagar, Utils.getToday());
//	}
	
	public static void calculaValorContaPagarVO (ContaPagarVO contapagar, Date date) {
		if (contapagar.getDataVencimentoContaPagar().compareTo(date) < 0 && contapagar.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_ABERTO)) {
			contapagar.setSituacaoTituloAPagar("V"); // Vencido
			
			BigDecimal cem = new BigDecimal(100);
			BigDecimal valor = contapagar.getValorContaPagar();
			BigDecimal juros = BigDecimal.ZERO;
			BigDecimal multa = BigDecimal.ZERO;
			
			contapagar.setValorMultaCalculado(BigDecimal.ZERO);
			contapagar.setValorJurosCalculado(BigDecimal.ZERO);
			contapagar.setValorDescontoCalculado(BigDecimal.ZERO);
			
			// Verifica se tem multa em percentual
			if (contapagar.getPercentualMultaContaPagar() != null && contapagar.getPercentualMultaContaPagar().compareTo(BigDecimal.ZERO) > 0) {
				multa = multa.add(contapagar.getPercentualMultaContaPagar().multiply(contapagar.getValorContaPagar())).divide(cem, BigDecimal.ROUND_CEILING);
				contapagar.setValorMultaCalculado(multa);
			} else if (contapagar.getValorMultaContaPagar() != null && contapagar.getValorMultaContaPagar().compareTo(BigDecimal.ZERO) > 0) { // Verifica se tem multa em valor
				multa = multa.add(contapagar.getValorMultaContaPagar());
				
			}
			
			// Calcula o numero de dias do vencimento
			BigDecimal dias = new BigDecimal(Utils.getDiffDays(contapagar.getDataVencimentoContaPagar(), date));
			// Verifica se tem juros / dia em percentual
			if (contapagar.getPercentualJurosDiarioContaPagar() != null && contapagar.getPercentualJurosDiarioContaPagar().compareTo(BigDecimal.ZERO) > 0) {
				// Percentual de juros X dias de atraso
				juros = juros.add(contapagar.getPercentualJurosDiarioContaPagar().multiply(dias)).divide(cem);
				juros = juros.multiply(contapagar.getValorContaPagar()).setScale(2, BigDecimal.ROUND_CEILING);
			} else if (contapagar.getValorJurosDiarioContaPagar() != null && contapagar.getValorJurosDiarioContaPagar().compareTo(BigDecimal.ZERO) > 0) {
				// Valor dos juros / dia X dias de atraso
				juros = juros.multiply(dias);
			}
			
			// Calcula o valor final (multa + juros)
			valor = valor.add(multa);
			valor = valor.add(juros);
			
			// Seta a multa
			contapagar.setValorMultaCalculado(multa);
			// Seta os juros
			contapagar.setValorJurosCalculado(juros);
			// Seta Desconto (0)
			contapagar.setValorDescontoCalculado(BigDecimal.ZERO);
			// Seta o valor total calculado
			contapagar.setValorCalculado(valor);
		} else if (contapagar.getDataVencimentoContaPagar().compareTo(date) == 0 && contapagar.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_ABERTO)) {
			contapagar.setSituacaoTituloAPagar("H"); // Vence hoje
			contapagar.setValorCalculado(contapagar.getValorContaPagar());
			
			contapagar.setValorMultaCalculado(BigDecimal.ZERO);
			contapagar.setValorJurosCalculado(BigDecimal.ZERO);
			contapagar.setValorDescontoCalculado(BigDecimal.ZERO);
		} else if (contapagar.getDataVencimentoContaPagar().compareTo(date) > 0 && contapagar.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_ABERTO)) {
			contapagar.setSituacaoTituloAPagar("A"); // A vencer
			BigDecimal valor = contapagar.getValorContaPagar();
			
			contapagar.setValorMultaCalculado(BigDecimal.ZERO);
			contapagar.setValorJurosCalculado(BigDecimal.ZERO);
			
			// Calcula descontos, se houver
			if (contapagar.getDescontoAteContaPagar() != null && contapagar.getDescontoAteContaPagar().compareTo(date) >= 0 && contapagar.getDescontoContaPagar() != null && contapagar.getDescontoContaPagar().compareTo(BigDecimal.ZERO) > 0) {
				valor = valor.subtract(contapagar.getDescontoContaPagar());
				contapagar.setValorDescontoCalculado(contapagar.getDescontoContaPagar());
				contapagar.setValorCalculado(valor);
			} else {
				contapagar.setValorCalculado(contapagar.getValorContaPagar());
				contapagar.setValorDescontoCalculado(BigDecimal.ZERO);
			}
		} else if (contapagar.getSituacaoContaPagar().equals(ContaPagarVO.SITUACAO_CANCELADO)) {
			contapagar.setValorCalculado(BigDecimal.ZERO);
		} else {
			contapagar.setValorCalculado(contapagar.getValorContaPagar());
		}
		
		try {
			// Verifica se possui pagamentos (diminuir do valor em aberto)
			BaixaContaPagarDAO baixaDao = new BaixaContaPagarDAO();
			BaixaContaPagarVO _filter = new BaixaContaPagarVO();
			_filter.setCodigoContaPagar(contapagar.getCodigoContaPagar());
			_filter.setCodigoEmpresaContaPagar(contapagar.getCodigoEmpresa());
			_filter.setCodigoLojaContaPagar(contapagar.getCodigoLoja());
			List<BaixaContaPagarVO> listBaixas = baixaDao.getList(_filter);
			
			BigDecimal valorPago = BigDecimal.ZERO;
			
			for (BaixaContaPagarVO baixa : listBaixas) {
				valorPago = valorPago.add(baixa.getValorBaixaContaPagar());
			}
			contapagar.setValorAbertoContaPagar(contapagar.getValorCalculado().subtract(valorPago));
			
			if (contapagar.getValorAbertoContaPagar().compareTo(BigDecimal.ZERO) < 0) {
				contapagar.setValorAbertoContaPagar(BigDecimal.ZERO);
			}
			contapagar.setBaixas(listBaixas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ContaPagarDAO.class;
	}
	
	@Override
	protected Class<?> getVOClass() {
		return ContaPagarVO.class;
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
		FornecedorQuery = fornecedorQuery;
	}

	public String getFornecedorQuery() {
		return FornecedorQuery;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	public void setCodigoLojaFornecedor(Integer codigoLojaFornecedor) {
		CodigoLojaFornecedor = codigoLojaFornecedor;
	}

	public Integer getCodigoLojaFornecedor() {
		return CodigoLojaFornecedor;
	}

	public void setCodigoEmpresaFornecedor(Integer codigoEmpresaFornecedor) {
		CodigoEmpresaFornecedor = codigoEmpresaFornecedor;
	}

	public Integer getCodigoEmpresaFornecedor() {
		return CodigoEmpresaFornecedor;
	}
}
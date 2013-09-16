package br.com.orlandoburli.personalerp.facades.reports.financeiro.contaspagar;

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import br.com.orlandoburli.core.dao.cadastro.pessoa.PessoaDAO;
import br.com.orlandoburli.core.dao.financeiro.ChequeEmitidoDAO;
import br.com.orlandoburli.core.dao.financeiro.MovimentacaoContaBancariaDAO;
import br.com.orlandoburli.core.dao.financeiro.PlanoContaDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.BaixaContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.PagamentoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.PessoaVO;
import br.com.orlandoburli.core.vo.financeiro.FormaPagamentoVO;
import br.com.orlandoburli.core.vo.financeiro.PlanoContaVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.BaixaContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.PagamentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.contapagar.ContaPagarCadastroFacade;

public class RelatorioContasPagarFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private String DescricaoTitulo;
	private String NumeroTitulo;
	private Integer CodigoContaPagar;
	private String NomeFornecedor;
	private Date DataInicialVencimento;
	private Date DataFinalVencimento;
	private Double ValorInicial;
	private Double ValorFinal;

	private Integer CodigoEmpresaFornecedor;
	private Integer CodigoLojaFornecedor;
	private Integer CodigoPessoaFornecedor;

//	private String NumeroPlanoConta;
	private String PlanoContas;
	private Integer CodigoCentroCusto;
	private Integer CodigoTipoDocumento;
	private Integer CodigoPortador;

	private String NomePlanoConta;
	private String NomePlanoContas;
	private String NomeCentroCusto;
	private String NomeTipoDocumento;
	private String NomePortador;

	private String FlgAberto;
	private String FlgQuitado;
	private String FlgCancelado;
	private String FlgPrevisao;

	private String FlgTodasLojas;

	private String FlgExceto;

	public RelatorioContasPagarFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}

	@IgnoreMethodAuthentication
	public void dados() {
		PessoaVO filter = new PessoaVO();
		
		filter.setCodigoEmpresa(getCodigoEmpresaFornecedor());
		filter.setCodigoLoja(getCodigoLojaFornecedor());
		filter.setCodigoPessoa(getCodigoPessoaFornecedor());

		PessoaDAO pessoaDao = new PessoaDAO();
		PlanoContaDAO planoDao = new PlanoContaDAO();
		
		try {
			List<PessoaVO> listPessoas = pessoaDao.getList(filter);
			List<PlanoContaVO> listPlanos = planoDao.getList(null, "NomePlanoConta");
			
			List<IValueObject> listFinal = new ArrayList<IValueObject>();
			listFinal.addAll(listPessoas);
			listFinal.addAll(listPlanos);
			
			write(Utils.voToXml(listFinal));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JRDataSource getDataSource() {
		try {

			String sb = "";

			if (DataInicialVencimento != null) {
				sb += " DataVencimentoContaPagar >= '" + DataInicialVencimento.toString() + "' ";
			}
			if (DataFinalVencimento != null) {
				sb += (sb.length() == 0 ? "" : " AND ");
				sb += " DataVencimentoContaPagar <= '" + DataFinalVencimento.toString() + "' ";
			}
			if (ValorInicial != null) {
				sb += (sb.length() == 0 ? "" : " AND ");
				sb += " ValorContaPagar >= " + ValorInicial.toString();
			}
			if (ValorFinal != null) {
				sb += (sb.length() == 0 ? "" : " AND ");
				sb += " ValorContaPagar <= " + ValorFinal.toString();
			}

			String strIn = "";
			if (FlgAberto != null && FlgAberto.equalsIgnoreCase("S")) {
				strIn += "'A'";
			}
			if (FlgQuitado != null && FlgQuitado.equalsIgnoreCase("S")) {
				strIn += strIn.equals("") ? "" : ", ";
				strIn += "'Q'";
			}
			if (FlgCancelado != null && FlgCancelado.equalsIgnoreCase("S")) {
				strIn += strIn.equals("") ? "" : ", ";
				strIn += "'C'";
			}
			if (FlgPrevisao != null && FlgPrevisao.equalsIgnoreCase("S")) {
				strIn += strIn.equals("") ? "" : ", ";
				strIn += "'P'";
			}

			if (strIn != null && !strIn.trim().equals("")) {
				sb += (sb.length() == 0 ? "" : " AND ");
				sb += "\n SituacaoContaPagar IN (" + strIn + ")";
			}
			
			if (getPlanoContas() != null && !getPlanoContas().trim().equals("")) {
				sb += (sb.length() == 0 ? "" : " AND ");
				sb += " CodigoPlanoConta IN (" + getPlanoContas().substring(0, getPlanoContas().length() - 2) + ")";
			}

			ContaPagarVO _filter = new ContaPagarVO();

			_filter.setDescricaoContaPagar(getDescricaoTitulo() + "%");

			if (getCodigoPessoaFornecedor() != null) {
				if (getFlgExceto() != null && getFlgExceto().equalsIgnoreCase("S")) {
					sb += "\n AND (CodigoEmpresaPessoaContaPagar, CodigoLojaPessoaContaPagar, CodigoPessoaContaPagar) NOT IN (SELECT ";
					sb += getCodigoEmpresaFornecedor() + ", " + getCodigoLojaFornecedor() + ", " + getCodigoPessoaFornecedor() + ")";
				} else {
					_filter.setCodigoEmpresaPessoaContaPagar(getCodigoEmpresaFornecedor());
					_filter.setCodigoLojaPessoaContaPagar(getCodigoLojaFornecedor());
					_filter.setCodigoPessoaContaPagar(getCodigoPessoaFornecedor());
				}
			}

//			if (getNumeroPlanoConta() != null && !getNumeroPlanoConta().trim().equals("")) {
//				_filter.setNumeroPlanoConta(getNumeroPlanoConta());
//			}
			
			_filter.setCodigoCentroCusto(getCodigoCentroCusto());
			_filter.setCodigoTipoDocumento(getCodigoTipoDocumento());
			_filter.setCodigoPortador(getCodigoPortador());

			if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
				_filter.setNumeroTituloContaPagar(getNumeroTitulo());
			}
			_filter.setCodigoContaPagar(getCodigoContaPagar());

			if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
				_filter.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				_filter.setCodigoLoja(getLojasessao().getCodigoLoja());
			}

			ContaPagarDAO _dao = new ContaPagarDAO();
			_dao.setSpecialCondition(sb);

			List<ContaPagarVO> contas = _dao.getList(_filter, " DataVencimentoContaPagar ");

			ContaPagarCadastroFacade.calculaValorContaPagarVO(contas, Utils.getToday());

			// Baixas

			BaixaContaPagarDAO baixaDao = new BaixaContaPagarDAO();
			baixaDao.setAutoCommit(false);

			PagamentoDAO pagDao = new PagamentoDAO();
			pagDao.setAutoCommit(false);

			ChequeEmitidoDAO chequeDao = new ChequeEmitidoDAO();
			chequeDao.setAutoCommit(false);

			MovimentacaoContaBancariaDAO movDao = new MovimentacaoContaBancariaDAO();
			movDao.setAutoCommit(false);

			for (ContaPagarVO conta : contas) {
				BaixaContaPagarVO _baixaFilter = new BaixaContaPagarVO();
				_baixaFilter.setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
				_baixaFilter.setCodigoLojaContaPagar(conta.getCodigoLoja());
				_baixaFilter.setCodigoContaPagar(conta.getCodigoContaPagar());

				List<BaixaContaPagarVO> baixas = baixaDao.getList(_baixaFilter);

				// Pagamentos

				for (BaixaContaPagarVO baixa : baixas) {
					PagamentoVO pagamento = new PagamentoVO();
					pagamento.setCodigoEmpresa(baixa.getCodigoEmpresa());
					pagamento.setCodigoLoja(baixa.getCodigoLoja());
					pagamento.setCodigoPagamento(baixa.getCodigoPagamento());

					pagamento = pagDao.get(pagamento);

					if (pagamento.getCodigoFormaPagamento().equals(FormaPagamentoVO.FORMA_PAGTO_CHEQUE)) {
						// Busca cheque
						pagamento.setCheque(chequeDao.getChequeByPagamento(pagamento));
					} else if (pagamento.getCodigoFormaPagamento().equals(FormaPagamentoVO.FORMA_PAGTO_DEBITO_CONTA)) {
						// Busca movimentacao
						pagamento.setMovimentacao(movDao.getMovimentacaoByPagamento(pagamento));
					}

					baixa.setPagamento(pagamento);
				}

				conta.setBaixas(baixas);
			}

			baixaDao.commit();
			pagDao.commit();
			chequeDao.commit();
			movDao.commit();

			return new JRBeanCollectionDataSource(contas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.getDataSource();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		String quebralinha = "          ";
		String sb = "";

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
			sb += " Loja: " + getLojasessao().getNomeLoja();
		} else {
			sb += " Loja: TODAS";
		}

		if (getDescricaoTitulo() != null && !getDescricaoTitulo().trim().equals("")) {
			sb += " Descrição Título: " + getDescricaoTitulo() + quebralinha;
		}

		if (getNomeFornecedor() != null && !getNomeFornecedor().trim().equals("")) {
			if (getFlgExceto() != null && getFlgExceto().equalsIgnoreCase("S")) {
				sb += " Fornecedor: TODOS, exceto " + getNomeFornecedor() + quebralinha;
			} else {
				sb += " Fornecedor: " + getNomeFornecedor() + quebralinha;
			}
		}

		if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
			sb += " Título: " + getNumeroTitulo() + quebralinha;
		}

		if (DataInicialVencimento != null) {
			sb += " Vencto. Inicial: " + sdf.format(DataInicialVencimento) + quebralinha;
		}

		if (DataFinalVencimento != null) {
			sb += " Vencto. Final: " + sdf.format(DataFinalVencimento) + quebralinha;
		}

		if (ValorInicial != null) {
			sb += " Valor Inicial: " + NumberFormat.getCurrencyInstance().format(ValorInicial) + quebralinha;
		}
		if (ValorFinal != null) {
			sb += " Valor Final: " + NumberFormat.getCurrencyInstance().format(ValorFinal) + quebralinha;
		}
//		if (getNumeroPlanoConta() != null && !getNumeroPlanoConta().trim().equals("")) {
//			sb += " Plano de Contas: " + getNumeroPlanoConta() + " " + getNomePlanoConta();
//		}
		if (getCodigoCentroCusto() != null) {
			sb += " Centro de Custo: " + getNomeCentroCusto();
		}
		if (getCodigoTipoDocumento() != null) {
			sb += " Tipo de Documento: " + getNomeTipoDocumento();
		}
		if (getCodigoPortador() != null) {
			sb += " Portador: " + getNomePortador();
		}
		if (getNomePlanoContas() != null) {
			sb += " Plano de Contas: " + getNomePlanoContas().substring(0, getNomePlanoContas().length() - 2);
		}

		String strIn = "";
		if (FlgAberto != null && FlgAberto.equalsIgnoreCase("S")) {
			strIn += "Aberto";
		}
		if (FlgQuitado != null && FlgQuitado.equalsIgnoreCase("S")) {
			strIn += strIn.equals("") ? "" : ", ";
			strIn += "Quitado";
		}
		if (FlgCancelado != null && FlgCancelado.equalsIgnoreCase("S")) {
			strIn += strIn.equals("") ? "" : ", ";
			strIn += "Cancelado";
		}
		if (FlgPrevisao != null && FlgPrevisao.equalsIgnoreCase("S")) {
			strIn += strIn.equals("") ? "" : ", ";
			strIn += "Previsão";
		}

		if (strIn != null && !strIn.trim().equals("")) {
			sb += " Situação: " + strIn + quebralinha;
		}
		parameters.put("filtroDescricao", sb);
		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioContasPagar.jasper";
	}

	public String getNumeroTitulo() {
		return NumeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		NumeroTitulo = numeroTitulo;
	}

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public Date getDataInicialVencimento() {
		return DataInicialVencimento;
	}

	public void setDataInicialVencimento(Date dataInicialVencimento) {
		DataInicialVencimento = dataInicialVencimento;
	}

	public Date getDataFinalVencimento() {
		return DataFinalVencimento;
	}

	public void setDataFinalVencimento(Date dataFinalVencimento) {
		DataFinalVencimento = dataFinalVencimento;
	}

	public Double getValorInicial() {
		return ValorInicial;
	}

	public void setValorInicial(Double valorInicial) {
		ValorInicial = valorInicial;
	}

	public Double getValorFinal() {
		return ValorFinal;
	}

	public void setValorFinal(Double valorFinal) {
		ValorFinal = valorFinal;
	}

	public String getFlgAberto() {
		return FlgAberto;
	}

	public void setFlgAberto(String flgAberto) {
		FlgAberto = flgAberto;
	}

	public String getFlgQuitado() {
		return FlgQuitado;
	}

	public void setFlgQuitado(String flgQuitado) {
		FlgQuitado = flgQuitado;
	}

	public String getFlgCancelado() {
		return FlgCancelado;
	}

	public void setFlgCancelado(String flgCancelado) {
		FlgCancelado = flgCancelado;
	}

	public String getFlgPrevisao() {
		return FlgPrevisao;
	}

	public void setFlgPrevisao(String flgPrevisao) {
		FlgPrevisao = flgPrevisao;
	}

	public void setDescricaoTitulo(String descricaoTitulo) {
		DescricaoTitulo = descricaoTitulo;
	}

	public String getDescricaoTitulo() {
		return DescricaoTitulo;
	}

	public Integer getCodigoCentroCusto() {
		return CodigoCentroCusto;
	}

	public void setCodigoCentroCusto(Integer codigoCentroCusto) {
		CodigoCentroCusto = codigoCentroCusto;
	}

	public Integer getCodigoTipoDocumento() {
		return CodigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		CodigoTipoDocumento = codigoTipoDocumento;
	}

	public Integer getCodigoPortador() {
		return CodigoPortador;
	}

	public void setCodigoPortador(Integer codigoPortador) {
		CodigoPortador = codigoPortador;
	}

	public String getNomePlanoConta() {
		return NomePlanoConta;
	}

	public void setNomePlanoConta(String nomePlanoConta) {
		NomePlanoConta = nomePlanoConta;
	}

	public String getNomeCentroCusto() {
		return NomeCentroCusto;
	}

	public void setNomeCentroCusto(String nomeCentroCusto) {
		NomeCentroCusto = nomeCentroCusto;
	}

	public String getNomeTipoDocumento() {
		return NomeTipoDocumento;
	}

	public void setNomeTipoDocumento(String nomeTipoDocumento) {
		NomeTipoDocumento = nomeTipoDocumento;
	}

	public String getNomePortador() {
		return NomePortador;
	}

	public void setNomePortador(String nomePortador) {
		NomePortador = nomePortador;
	}

	public Integer getCodigoEmpresaFornecedor() {
		return CodigoEmpresaFornecedor;
	}

	public void setCodigoEmpresaFornecedor(Integer codigoEmpresaFornecedor) {
		CodigoEmpresaFornecedor = codigoEmpresaFornecedor;
	}

	public Integer getCodigoLojaFornecedor() {
		return CodigoLojaFornecedor;
	}

	public void setCodigoLojaFornecedor(Integer codigoLojaFornecedor) {
		CodigoLojaFornecedor = codigoLojaFornecedor;
	}

	public Integer getCodigoPessoaFornecedor() {
		return CodigoPessoaFornecedor;
	}

	public void setCodigoPessoaFornecedor(Integer codigoPessoaFornecedor) {
		CodigoPessoaFornecedor = codigoPessoaFornecedor;
	}

	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}

	public String getFlgExceto() {
		return FlgExceto;
	}

	public void setFlgExceto(String flgExceto) {
		FlgExceto = flgExceto;
	}

	public String getPlanoContas() {
		return PlanoContas;
	}

	public void setPlanoContas(String planoContas) {
		PlanoContas = planoContas;
	}

	public String getNomePlanoContas() {
		return NomePlanoContas;
	}

	public void setNomePlanoContas(String nomePlanoContas) {
		NomePlanoContas = nomePlanoContas;
	}
}
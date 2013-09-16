package br.com.orlandoburli.personalerp.facades.reports.financeiro.contasreceber;

import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.contareceber.ContaReceberCadastroFacade;

public class RelatorioContasReceberFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;
	
	private String DescricaoTitulo;
	private String NumeroTitulo;
	private Integer CodigoContaReceber;
	private String NomeCliente;
	private Date DataInicialVencimento;
	private Date DataFinalVencimento;
	private Double ValorInicial;
	private Double ValorFinal;
	
	private String FlgAberto;
	private String FlgQuitado;
	private String FlgCancelado;
	private String FlgPrevisao;

	public RelatorioContasReceberFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@Override
	public JRDataSource getDataSource() {
		try {
			
			String sb = "";
			
			if (DataInicialVencimento != null) {
				sb += " DataVencimentoContaReceber >= '" + DataInicialVencimento.toString() + "' ";
			}
			if (DataFinalVencimento != null) {
				sb += (sb.length()==0?"":" AND ");
				sb += " DataVencimentoContaReceber <= '" + DataFinalVencimento.toString() + "' ";
			}
			if (ValorInicial != null) {
				sb += (sb.length()==0?"":" AND ");
				sb += " ValorContaReceber >= " + ValorInicial.toString();
			}
			if (ValorFinal != null) {
				sb += (sb.length()==0?"":" AND ");
				sb += " ValorContaReceber <= " + ValorFinal.toString();
			}
			
			String strIn = "";
			if (FlgAberto != null && FlgAberto.equalsIgnoreCase("S")) {
				strIn += "'A'";
			}
			if (FlgQuitado != null && FlgQuitado.equalsIgnoreCase("S")) {
				if (strIn.equals("")) {
					
				}
				strIn += strIn.equals("")?"":", ";
				strIn += "'Q'";
			}
			if (FlgCancelado != null && FlgCancelado.equalsIgnoreCase("S")) {
				if (strIn.equals("")) {
					
				}
				strIn += strIn.equals("")?"":", ";
				strIn += "'C'";
			}
			if (FlgPrevisao != null && FlgPrevisao.equalsIgnoreCase("S")) {
				if (strIn.equals("")) {
					
				}
				strIn += strIn.equals("")?"":", ";
				strIn += "'P'";
			}
			
			if (strIn != null && !strIn.trim().equals("")) {
				sb += (sb.length()==0?"":" AND ");
				sb += "\n SituacaoContaReceber IN (" + strIn + ")";
			}
			
			ContaReceberDAO _dao = new ContaReceberDAO();
			_dao.setSpecialCondition(sb);
			
			ContaReceberVO _filter = new ContaReceberVO();
			
			_filter.setDescricaoContaReceber(getDescricaoTitulo() + "%");
			if (getNomeCliente() != null && !getNomeCliente().trim().equals("")) {
				_filter.setNomeClienteContaReceber(getNomeCliente() + "%");
			}
			if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
				_filter.setNumeroTituloContaReceber(getNumeroTitulo());
			}
			_filter.setCodigoContaReceber(getCodigoContaReceber());
			
			List<ContaReceberVO> contas = _dao.getList(_filter, " DataVencimentoContaReceber ");
			
			ContaReceberCadastroFacade.calculaValorContaReceberVO(contas, Utils.getToday());
			
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
		
		if (getDescricaoTitulo() != null && !getDescricaoTitulo().trim().equals("")) {
			sb += "Descrição Título: " + getDescricaoTitulo() + quebralinha;
		}
		
		if (getNomeCliente() != null && !getNomeCliente().trim().equals("")) {
			sb += "Cliente: " + getNomeCliente() + quebralinha;
		}
		
		if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
			sb += "Título: " + getNumeroTitulo() + quebralinha;
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
		
		String strIn = "";
		if (FlgAberto != null && FlgAberto.equalsIgnoreCase("S")) {
			strIn += "Aberto";
		}
		if (FlgQuitado != null && FlgQuitado.equalsIgnoreCase("S")) {
			strIn += strIn.equals("")?"":", ";
			strIn += "Quitado";
		}
		if (FlgCancelado != null && FlgCancelado.equalsIgnoreCase("S")) {
			strIn += strIn.equals("")?"":", ";
			strIn += "Cancelado";
		}
		if (FlgPrevisao != null && FlgPrevisao.equalsIgnoreCase("S")) {
			strIn += strIn.equals("")?"":", ";
			strIn += "Previsão";
		}
		
		if (strIn != null && !strIn.trim().equals("")) {
			sb += "Situação: " + strIn + quebralinha;
		}
		parameters.put("filtroDescricao", sb);
		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioContasReceber.jasper";
	}

	public String getNumeroTitulo() {
		return NumeroTitulo;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		NumeroTitulo = numeroTitulo;
	}

	public Integer getCodigoContaReceber() {
		return CodigoContaReceber;
	}

	public void setCodigoContaReceber(Integer codigoContaReceber) {
		CodigoContaReceber = codigoContaReceber;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
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
}
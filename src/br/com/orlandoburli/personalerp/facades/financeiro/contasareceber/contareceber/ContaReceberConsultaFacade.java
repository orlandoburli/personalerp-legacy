package br.com.orlandoburli.personalerp.facades.financeiro.contasareceber.contareceber;

import java.sql.Date;

import br.com.orlandoburli.core.dao.financeiro.contareceber.ContaReceberDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.contasareceber.ContaReceberVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ContaReceberConsultaFacade extends BaseConsultaFlexFacade<ContaReceberVO, ContaReceberDAO>{

	private static final long serialVersionUID = 1L;
	
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
	private String FlgTodasLojas;

	@Override
	protected void doBeforeFilter() {
		String sb = "";
		
		if (getDataInicialVencimento() != null) {
			sb += " DataVencimentoContaReceber >= '" + getDataInicialVencimento().toString() + "' ";
		}
		if (getDataFinalVencimento() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataVencimentoContaReceber <= '" + getDataFinalVencimento().toString() + "' ";
		}
		if (getValorInicial() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " ValorContaReceber >= " + getValorInicial().toString();
		}
		if (getValorFinal() != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " ValorContaReceber <= " + getValorFinal().toString();
		}
		
		String strIn = "";
		if (getFlgAberto() != null && getFlgAberto().equalsIgnoreCase("S")) {
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
		
		dao.setSpecialCondition(sb);
	
		getFilter().setDescricaoContaReceber(getFiltro() + "%");
		if (getNomeCliente() != null && !getNomeCliente().trim().equals("")) {
			getFilter().setNomeClienteContaReceber(getNomeCliente() + "%");
		}
		if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
			getFilter().setNumeroTituloContaReceber(getNumeroTitulo());
		}
		
		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
			getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
			getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		}
		
		getFilter().setCodigoContaReceber(getCodigoContaReceber());
		setOrderFields(" DataVencimentoContaReceber ");
	}
	
	public void doBeforeWrite() {
		ContaReceberCadastroFacade.calculaValorContaReceberVO(listSource, Utils.getToday());
		super.doBeforeWrite();
	}

	public void setNumeroTitulo(String numeroTitulo) {
		NumeroTitulo = numeroTitulo;
	}

	public String getNumeroTitulo() {
		return NumeroTitulo;
	}

	public void setCodigoContaReceber(Integer codigoContaReceber) {
		CodigoContaReceber = codigoContaReceber;
	}

	public Integer getCodigoContaReceber() {
		return CodigoContaReceber;
	}

	public void setNomeCliente(String nomeCliente) {
		NomeCliente = nomeCliente;
	}

	public String getNomeCliente() {
		return NomeCliente;
	}

	public void setDataInicialVencimento(Date dataInicialVencimento) {
		DataInicialVencimento = dataInicialVencimento;
	}

	public Date getDataInicialVencimento() {
		return DataInicialVencimento;
	}

	public void setDataFinalVencimento(Date dataFinalVencimento) {
		DataFinalVencimento = dataFinalVencimento;
	}

	public Date getDataFinalVencimento() {
		return DataFinalVencimento;
	}

	public void setValorInicial(Double valorInicial) {
		ValorInicial = valorInicial;
	}

	public Double getValorInicial() {
		return ValorInicial;
	}

	public void setValorFinal(Double valorFinal) {
		ValorFinal = valorFinal;
	}

	public Double getValorFinal() {
		return ValorFinal;
	}

	public void setFlgAberto(String flgAberto) {
		FlgAberto = flgAberto;
	}

	public String getFlgAberto() {
		return FlgAberto;
	}

	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}
}
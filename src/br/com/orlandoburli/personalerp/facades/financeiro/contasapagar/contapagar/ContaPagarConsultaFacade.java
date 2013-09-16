package br.com.orlandoburli.personalerp.facades.financeiro.contasapagar.contapagar;

import java.sql.Date;

import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ContaPagarConsultaFacade extends BaseConsultaFlexFacade<ContaPagarVO, ContaPagarDAO> {

	private static final long serialVersionUID = 1L;
	
	private String NumeroTitulo;
	private Integer CodigoContaPagar;
	private String NomeFornecedor;
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
		
		if (DataInicialVencimento != null) {
			sb += " DataVencimentoContaPagar >= '" + DataInicialVencimento.toString() + "' ";
		}
		if (DataFinalVencimento != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " DataVencimentoContaPagar <= '" + DataFinalVencimento.toString() + "' ";
		}
		if (ValorInicial != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " ValorContaPagar >= " + ValorInicial.toString();
		}
		if (ValorFinal != null) {
			sb += (sb.length()==0?"":" AND ");
			sb += " ValorContaPagar <= " + ValorFinal.toString();
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
			sb += "\n SituacaoContaPagar IN (" + strIn + ")";
		}
		
		dao.setSpecialCondition(sb);
	
		getFilter().setDescricaoContaPagar(getFiltro() + "%");
		if (getNomeFornecedor() != null && !getNomeFornecedor().trim().equals("")) {
			getFilter().setNomeFornecedorContaPagar(getNomeFornecedor() + "%");
		}
		if (getNumeroTitulo() != null && !getNumeroTitulo().trim().equals("")) {
			getFilter().setNumeroTituloContaPagar(getNumeroTitulo());
		}
		getFilter().setCodigoContaPagar(getCodigoContaPagar());
		
		if (getFlgTodasLojas() == null || getFlgTodasLojas().equals("N")) {
			getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
			getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		}
		
		setOrderFields(" DataVencimentoContaPagar ");
	}
	
	public void doBeforeWrite() {
		ContaPagarCadastroFacade.calculaValorContaPagarVO(listSource, Utils.getToday());
		super.doBeforeWrite();
	}
	
	@Override
	protected Class<?> getDAOClass() {
		return ContaPagarDAO.class;
	}
	
	@Override
	protected Class<?> getVOClass() {
		return ContaPagarVO.class;
	}

	public void setNumeroTitulo(String numeroTitulo) {
		NumeroTitulo = numeroTitulo;
	}

	public String getNumeroTitulo() {
		return NumeroTitulo;
	}

	public void setCodigoContaPagar(Integer codigoContaPagar) {
		CodigoContaPagar = codigoContaPagar;
	}

	public Integer getCodigoContaPagar() {
		return CodigoContaPagar;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		NomeFornecedor = nomeFornecedor;
	}

	public String getNomeFornecedor() {
		return NomeFornecedor==null?"":NomeFornecedor;
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

	public void setFlgQuitado(String flgQuitado) {
		FlgQuitado = flgQuitado;
	}

	public String getFlgQuitado() {
		return FlgQuitado;
	}

	public void setFlgCancelado(String flgCancelado) {
		FlgCancelado = flgCancelado;
	}

	public String getFlgCancelado() {
		return FlgCancelado;
	}

	public void setFlgPrevisao(String flgPrevisto) {
		FlgPrevisao = flgPrevisto;
	}

	public String getFlgPrevisao() {
		return FlgPrevisao;
	}

	public String getFlgTodasLojas() {
		return FlgTodasLojas;
	}

	public void setFlgTodasLojas(String flgTodasLojas) {
		FlgTodasLojas = flgTodasLojas;
	}
}
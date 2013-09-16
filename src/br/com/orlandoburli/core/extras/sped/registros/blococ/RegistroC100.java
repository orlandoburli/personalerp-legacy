package br.com.orlandoburli.core.extras.sped.registros.blococ;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatDateSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC100 extends RegistroSped implements BlocoC {

	private String indicadorOperacao;
	private String indicadorEmitente;
	private String codigoParticipante;
	private String codigoModelo;
	private String codigoSituacao;
	private String serie;
	private Integer numeroDocumento;
	private String chaveNfe;
	private Date dataDocumento;
	private Date dataEntradaSaida;
	private BigDecimal valorDocumento;
	private Integer indicadorPagamento;
	private BigDecimal valorDesconto;
	private BigDecimal valorAbatimento;
	private BigDecimal valorMercadorias;
	private String indicadorFrete;
	private BigDecimal valorFrete;
	private BigDecimal valorSeguro;
	private BigDecimal valorOutrasDespesas;
	private BigDecimal valorBaseIcms;
	private BigDecimal valorIcms;
	private BigDecimal valorBaseIcmsSt;
	private BigDecimal valorIcmsSt;
	private BigDecimal valorIpi;
	private BigDecimal valorPis;
	private BigDecimal valorCofins;
	private BigDecimal valorPisSt;
	private BigDecimal valorCofinsSt;
	
	private List<RegistroC170> itens;
	
	public RegistroC100() {
		super();
		this.itens = new ArrayList<RegistroC170>();
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		
		// Adiciona os itens
		for (RegistroC170 item : itens) {
			sb.append("\n");
			sb.append(item);
		}
		
		return sb.toString();
	}

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C100";
	}

	@CampoSped(name = "IND_OPER", order = 2)
	public String getIndicadorOperacao() {
		return indicadorOperacao;
	}

	@CampoSped(name = "IND_EMIT", order = 3)
	public String getIndicadorEmitente() {
		return indicadorEmitente;
	}

	@CampoSped(name = "COD_PART", order = 4)
	public String getCodigoParticipante() {
		return codigoParticipante;
	}

	@CampoSped(name = "COD_MOD", order = 5)
	public String getCodigoModelo() {
		return codigoModelo;
	}

	@CampoSped(name = "COD_SIT", order = 6)
	public String getCodigoSituacao() {
		return codigoSituacao;
	}

	@CampoSped(name = "SER", order = 7)
	public String getSerie() {
		return serie;
	}

	@CampoSped(name = "NUM_DOC", order = 8)
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}

	@CampoSped(name = "CHV_NFE", order = 9)
	public String getChaveNfe() {
		return chaveNfe;
	}

	@CampoSped(name = "DT_DOC", order = 10)
	@FormatDateSped(ArquivoSpedFiscal.FORMATO_DATA_SPED)
	public Date getDataDocumento() {
		return dataDocumento;
	}

	@CampoSped(name = "DT_E_S", order = 11)
	@FormatDateSped(ArquivoSpedFiscal.FORMATO_DATA_SPED)
	public Date getDataEntradaSaida() {
		return dataEntradaSaida;
	}

	@CampoSped(name = "VL_DOC", order = 12)
	@FormatNumberSped(2)
	public BigDecimal getValorDocumento() {
		return valorDocumento;
	}

	@CampoSped(name = "VL_DESC", order = 13)
	@FormatNumberSped(2)
	public BigDecimal getValorDesconto() {
		return valorDesconto;
	}

	@CampoSped(name = "IND_PGTO", order = 14)
	public Integer getIndicadorPagamento() {
		return indicadorPagamento;
	}

	@CampoSped(name = "VL_ABAT_NT", order = 15)
	@FormatNumberSped(2)
	public BigDecimal getValorAbatimento() {
		return valorAbatimento;
	}

	@CampoSped(name = "VL_MERC", order = 16)
	@FormatNumberSped(2)
	public BigDecimal getValorMercadorias() {
		return valorMercadorias;
	}

	@CampoSped(name = "IND_FRT", order = 17)
	public String getIndicadorFrete() {
		return indicadorFrete;
	}

	@CampoSped(name = "VL_FRT", order = 18)
	@FormatNumberSped(2)
	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	@CampoSped(name = "VL_SEG", order = 19)
	@FormatNumberSped(2)
	public BigDecimal getValorSeguro() {
		return valorSeguro;
	}

	@CampoSped(name = "VL_OUT_DA", order = 20)
	@FormatNumberSped(2)
	public BigDecimal getValorOutrasDespesas() {
		return valorOutrasDespesas;
	}

	@CampoSped(name = "VL_BC_ICMS", order = 21)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcms() {
		return valorBaseIcms;
	}

	@CampoSped(name = "VL_ICMS", order = 22)
	@FormatNumberSped(2)
	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	@CampoSped(name = "VL_BC_ICMS_ST", order = 23)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcmsSt() {
		return valorBaseIcmsSt;
	}

	@CampoSped(name = "VL_ICMS_ST", order = 24)
	@FormatNumberSped(2)
	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	@CampoSped(name = "VL_IPI", order = 25)
	@FormatNumberSped(2)
	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	@CampoSped(name = "VL_PIS", order = 26)
	@FormatNumberSped(2)
	public BigDecimal getValorPis() {
		return valorPis;
	}

	@CampoSped(name = "VL_COFINS", order = 27)
	@FormatNumberSped(2)
	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	@CampoSped(name = "VL_PIS_ST", order = 28)
	@FormatNumberSped(2)
	public BigDecimal getValorPisSt() {
		return valorPisSt;
	}

	@CampoSped(name = "VL_COFINS_ST", order = 29)
	@FormatNumberSped(2)
	public BigDecimal getValorCofinsSt() {
		return valorCofinsSt;
	}

	public void setIndicadorOperacao(String indicadorOperacao) {
		this.indicadorOperacao = indicadorOperacao;
	}

	public void setIndicadorEmitente(String indicadorEmitente) {
		this.indicadorEmitente = indicadorEmitente;
	}

	public void setCodigoParticipante(String codigoParticipante) {
		this.codigoParticipante = codigoParticipante;
	}

	public void setCodigoModelo(String codigoModelo) {
		this.codigoModelo = codigoModelo;
	}

	public void setCodigoSituacao(String codigoSituacao) {
		this.codigoSituacao = codigoSituacao;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public void setChaveNfe(String chaveNfe) {
		this.chaveNfe = chaveNfe;
	}

	public void setDataEntradaSaida(Date dataEntradaSaida) {
		this.dataEntradaSaida = dataEntradaSaida;
	}

	public void setValorDocumento(BigDecimal valorDocumento) {
		this.valorDocumento = valorDocumento;
	}

	public void setIndicadorPagamento(Integer indicadorPagamento) {
		this.indicadorPagamento = indicadorPagamento;
	}

	public void setValorDesconto(BigDecimal valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public void setValorAbatimento(BigDecimal valorAbatimento) {
		this.valorAbatimento = valorAbatimento;
	}

	public void setValorMercadorias(BigDecimal valorMercadorias) {
		this.valorMercadorias = valorMercadorias;
	}

	public void setIndicadorFrete(String indicadorFrete) {
		this.indicadorFrete = indicadorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public void setValorSeguro(BigDecimal valorSeguro) {
		this.valorSeguro = valorSeguro;
	}

	public void setValorOutrasDespesas(BigDecimal valorOutrasDespesas) {
		this.valorOutrasDespesas = valorOutrasDespesas;
	}

	public void setValorBaseIcms(BigDecimal valorBaseIcms) {
		this.valorBaseIcms = valorBaseIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public void setValorBaseIcmsSt(BigDecimal valorBaseIcmsSt) {
		this.valorBaseIcmsSt = valorBaseIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public void setValorPisSt(BigDecimal valorPisSt) {
		this.valorPisSt = valorPisSt;
	}

	public void setValorCofinsSt(BigDecimal valorCofinsSt) {
		this.valorCofinsSt = valorCofinsSt;
	}

	public void setDataDocumento(Date dataDocumento) {
		this.dataDocumento = dataDocumento;
	}

	public List<RegistroC170> getItens() {
		return itens;
	}

	public void setItens(List<RegistroC170> itens) {
		this.itens = itens;
	}

}

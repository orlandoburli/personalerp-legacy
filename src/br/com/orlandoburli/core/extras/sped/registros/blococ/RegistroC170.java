package br.com.orlandoburli.core.extras.sped.registros.blococ;

import java.math.BigDecimal;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC170 extends RegistroSped implements BlocoC {

	private Integer numeroItem;
	private String codigoItem;
	private String descricaoComplementar;
	private BigDecimal Quantidade;
	private String unidade;
	private BigDecimal valorTotalItem;
	private BigDecimal valorDescontoItem;
	private String indicadorMovimentacaoItem;
	private String cstIcms;
	private String cfop;
	private String codigoNaturezaOperacao;

	private BigDecimal valorBaseIcms;
	private BigDecimal aliquotaIcms;
	private BigDecimal valorIcms;

	private BigDecimal valorBaseIcmsSt;
	private BigDecimal aliquotaIcmsSt;
	private BigDecimal valorIcmsSt;

	private String indicadorApuracaoIpi;
	private String cstIpi;
	private String codigoEnquadramentoIpi;

	private BigDecimal valorBaseIpi;
	private BigDecimal aliquotaIpi;
	private BigDecimal valorIpi;

	private String cstPis;
	private BigDecimal valorBasePis;
	private BigDecimal aliquotaPercentualPis;
	private BigDecimal quantidadeBasePis;
	private BigDecimal aliquotaValorPis;
	private BigDecimal valorPis;

	private String cstCofins;
	private BigDecimal valorBaseCofins;
	private BigDecimal aliquotaPercentualCofins;
	private BigDecimal quantidadeBaseCofins;
	private BigDecimal aliquotaValorCofins;
	private BigDecimal valorCofins;

	private String contaAnalitica;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C170";
	}

	@CampoSped(name = "NUM_ITEM", order = 2)
	public Integer getNumeroItem() {
		return numeroItem;
	}

	@CampoSped(name = "COD_ITEM", order = 3)
	public String getCodigoItem() {
		return codigoItem;
	}

	@CampoSped(name = "DESCR_COMPL", order = 4)
	public String getDescricaoComplementar() {
		return descricaoComplementar;
	}

	@CampoSped(name = "QTD", order = 5)
	@FormatNumberSped(5)
	public BigDecimal getQuantidade() {
		return Quantidade;
	}

	@CampoSped(name = "UNID", order = 6)
	public String getUnidade() {
		return unidade;
	}

	@CampoSped(name = "VL_ITEM", order = 7)
	@FormatNumberSped(2)
	public BigDecimal getValorTotalItem() {
		return valorTotalItem;
	}

	@CampoSped(name = "VL_DESC", order = 8)
	@FormatNumberSped(2)
	public BigDecimal getValorDescontoItem() {
		return valorDescontoItem;
	}

	@CampoSped(name = "IND_MOV", order = 9)
	public String getIndicadorMovimentacaoItem() {
		return indicadorMovimentacaoItem;
	}

	@CampoSped(name = "CST_ICMS", order = 10)
	public String getCstIcms() {
		return cstIcms;
	}

	@CampoSped(name = "CFOP", order = 11)
	public String getCfop() {
		return cfop;
	}

	@CampoSped(name = "COD_NAT", order = 12)
	public String getCodigoNaturezaOperacao() {
		return codigoNaturezaOperacao;
	}

	@CampoSped(name = "VL_BC_ICMS", order = 13)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcms() {
		return valorBaseIcms;
	}

	@CampoSped(name = "ALIQ_ICMS", order = 14)
	@FormatNumberSped(2)
	public BigDecimal getAliquotaIcms() {
		return aliquotaIcms;
	}

	@CampoSped(name = "VL_ICMS", order = 15)
	@FormatNumberSped(2)
	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	@CampoSped(name = "VL_BC_ICMS_ST", order = 16)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcmsSt() {
		return valorBaseIcmsSt;
	}

	@CampoSped(name = "ALIQ_ST", order = 17)
	@FormatNumberSped(2)
	public BigDecimal getAliquotaIcmsSt() {
		return aliquotaIcmsSt;
	}

	@CampoSped(name = "VL_ICMS_ST", order = 18)
	@FormatNumberSped(2)
	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	@CampoSped(name = "IND_APUR", order = 19)
	public String getIndicadorApuracaoIpi() {
		return indicadorApuracaoIpi;
	}

	@CampoSped(name = "CST_IPI", order = 20)
	public String getCstIpi() {
		return cstIpi;
	}

	@CampoSped(name = "COD_ENQ", order = 21)
	public String getCodigoEnquadramentoIpi() {
		return codigoEnquadramentoIpi;
	}

	@CampoSped(name = "VL_BC_IPI", order = 22)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIpi() {
		return valorBaseIpi;
	}

	@CampoSped(name = "ALIQ_IPI", order = 23)
	@FormatNumberSped(2)
	public BigDecimal getAliquotaIpi() {
		return aliquotaIpi;
	}

	@CampoSped(name = "VL_IPI", order = 24)
	@FormatNumberSped(2)
	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	@CampoSped(name = "CST_PIS", order = 25)
	public String getCstPis() {
		return cstPis;
	}

	@CampoSped(name = "VL_BC_PIS", order = 26)
	@FormatNumberSped(2)
	public BigDecimal getValorBasePis() {
		return valorBasePis;
	}

	@CampoSped(name = "ALIQ_PIS", order = 27)
	@FormatNumberSped(4)
	public BigDecimal getAliquotaPercentualPis() {
		return aliquotaPercentualPis;
	}

	@CampoSped(name = "QUANT_BC_PIS", order = 28)
	@FormatNumberSped(3)
	public BigDecimal getQuantidadeBasePis() {
		return quantidadeBasePis;
	}

	@CampoSped(name = "ALIQ_PIS", order = 29)
	@FormatNumberSped(4)
	public BigDecimal getAliquotaValorPis() {
		return aliquotaValorPis;
	}

	@CampoSped(name = "VL_PIS", order = 30)
	@FormatNumberSped(2)
	public BigDecimal getValorPis() {
		return valorPis;
	}

	@CampoSped(name = "CST_COFINS", order = 31)
	public String getCstCofins() {
		return cstCofins;
	}

	@CampoSped(name = "VL_BC_COFINS", order = 32)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseCofins() {
		return valorBaseCofins;
	}

	@CampoSped(name = "ALIQ_COFINS", order = 33)
	@FormatNumberSped(4)
	public BigDecimal getAliquotaPercentualCofins() {
		return aliquotaPercentualCofins;
	}

	@CampoSped(name = "QUANT_BC_PIS", order = 34)
	@FormatNumberSped(3)
	public BigDecimal getQuantidadeBaseCofins() {
		return quantidadeBaseCofins;
	}

	@CampoSped(name = "ALIQ_COFINS", order = 35)
	@FormatNumberSped(4)
	public BigDecimal getAliquotaValorCofins() {
		return aliquotaValorCofins;
	}

	@CampoSped(name = "VL_COFINS", order = 36)
	@FormatNumberSped(2)
	public BigDecimal getValorCofins() {
		return valorCofins;
	}

	@CampoSped(name = "COD_CTA", order = 37)
	public String getContaAnalitica() {
		return contaAnalitica;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}

	public void setCodigoItem(String codigoItem) {
		this.codigoItem = codigoItem;
	}

	public void setDescricaoComplementar(String descricaoComplementar) {
		this.descricaoComplementar = descricaoComplementar;
	}

	public void setQuantidade(BigDecimal quantidade) {
		Quantidade = quantidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public void setValorTotalItem(BigDecimal valorTotalItem) {
		this.valorTotalItem = valorTotalItem;
	}

	public void setValorDescontoItem(BigDecimal valorDescontoItem) {
		this.valorDescontoItem = valorDescontoItem;
	}

	public void setIndicadorMovimentacaoItem(String indicadorMovimentacaoItem) {
		this.indicadorMovimentacaoItem = indicadorMovimentacaoItem;
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}

	public void setCodigoNaturezaOperacao(String codigoNaturezaOperacao) {
		this.codigoNaturezaOperacao = codigoNaturezaOperacao;
	}

	public void setValorBaseIcms(BigDecimal valorBaseIcms) {
		this.valorBaseIcms = valorBaseIcms;
	}

	public void setAliquotaIcms(BigDecimal aliquotaIcms) {
		this.aliquotaIcms = aliquotaIcms;
	}

	public void setValorIcms(BigDecimal valorIcms) {
		this.valorIcms = valorIcms;
	}

	public void setValorBaseIcmsSt(BigDecimal valorBaseIcmsSt) {
		this.valorBaseIcmsSt = valorBaseIcmsSt;
	}

	public void setAliquotaIcmsSt(BigDecimal aliquotaIcmsSt) {
		this.aliquotaIcmsSt = aliquotaIcmsSt;
	}

	public void setValorIcmsSt(BigDecimal valorIcmsSt) {
		this.valorIcmsSt = valorIcmsSt;
	}

	public void setIndicadorApuracaoIpi(String indicadorApuracaoIpi) {
		this.indicadorApuracaoIpi = indicadorApuracaoIpi;
	}

	public void setCstIpi(String cstIpi) {
		this.cstIpi = cstIpi;
	}

	public void setCodigoEnquadramentoIpi(String codigoEnquadramentoIpi) {
		this.codigoEnquadramentoIpi = codigoEnquadramentoIpi;
	}

	public void setValorBaseIpi(BigDecimal valorBaseIpi) {
		this.valorBaseIpi = valorBaseIpi;
	}

	public void setAliquotaIpi(BigDecimal aliquotaIpi) {
		this.aliquotaIpi = aliquotaIpi;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public void setCstPis(String cstPis) {
		this.cstPis = cstPis;
	}

	public void setValorBasePis(BigDecimal valorBasePis) {
		this.valorBasePis = valorBasePis;
	}

	public void setAliquotaPercentualPis(BigDecimal aliquotaPercentualPis) {
		this.aliquotaPercentualPis = aliquotaPercentualPis;
	}

	public void setQuantidadeBasePis(BigDecimal quantidadeBasePis) {
		this.quantidadeBasePis = quantidadeBasePis;
	}

	public void setAliquotaValorPis(BigDecimal aliquotaValorPis) {
		this.aliquotaValorPis = aliquotaValorPis;
	}

	public void setCstCofins(String cstCofins) {
		this.cstCofins = cstCofins;
	}

	public void setValorBaseCofins(BigDecimal valorBaseCofins) {
		this.valorBaseCofins = valorBaseCofins;
	}

	public void setAliquotaPercentualCofins(BigDecimal aliquotaPercentualCofins) {
		this.aliquotaPercentualCofins = aliquotaPercentualCofins;
	}

	public void setQuantidadeBaseCofins(BigDecimal quantidadeBaseCofins) {
		this.quantidadeBaseCofins = quantidadeBaseCofins;
	}

	public void setAliquotaValorCofins(BigDecimal aliquotaValorCofins) {
		this.aliquotaValorCofins = aliquotaValorCofins;
	}

	public void setValorCofins(BigDecimal valorCofins) {
		this.valorCofins = valorCofins;
	}

	public void setContaAnalitica(String contaAnalitica) {
		this.contaAnalitica = contaAnalitica;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public void setValorPis(BigDecimal valorPis) {
		this.valorPis = valorPis;
	}

}

package br.com.orlandoburli.core.extras.sped.registros.blococ;

import java.math.BigDecimal;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.interfaces.FormatNumberSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC190 extends RegistroSped implements BlocoC {

	private String cstIcms;
	private String cfop;
	private BigDecimal aliqIcms;
	private BigDecimal valorOperacao;
	private BigDecimal valorBaseIcms;
	private BigDecimal valorIcms;
	private BigDecimal valorBaseIcmsSt;
	private BigDecimal valorIcmsSt;
	private BigDecimal valorReducaoBase;
	private BigDecimal valorIpi;
	private String codigoObservacao;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C190";
	}

	@CampoSped(name = "CST_ICMS", order = 2)
	public String getCstIcms() {
		return cstIcms;
	}

	@CampoSped(name = "CFOP", order = 3)
	public String getCfop() {
		return cfop;
	}

	@CampoSped(name = "ALIQ_ICMS", order = 4)
	@FormatNumberSped(2)
	public BigDecimal getAliqIcms() {
		return aliqIcms;
	}

	@CampoSped(name = "VL_OPR", order = 5)
	@FormatNumberSped(2)
	public BigDecimal getValorOperacao() {
		return valorOperacao;
	}

	@CampoSped(name = "VL_BC_ICMS", order = 6)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcms() {
		return valorBaseIcms;
	}

	@CampoSped(name = "VL_ICMS", order = 7)
	@FormatNumberSped(2)
	public BigDecimal getValorIcms() {
		return valorIcms;
	}

	@CampoSped(name = "VL_BC_ICMS_ST", order = 8)
	@FormatNumberSped(2)
	public BigDecimal getValorBaseIcmsSt() {
		return valorBaseIcmsSt;
	}

	@CampoSped(name = "VL_ICMS_ST", order = 9)
	@FormatNumberSped(2)
	public BigDecimal getValorIcmsSt() {
		return valorIcmsSt;
	}

	@CampoSped(name = "VL_RED_BC", order = 10)
	@FormatNumberSped(2)
	public BigDecimal getValorReducaoBase() {
		return valorReducaoBase;
	}

	@CampoSped(name = "VL_IPI", order = 11)
	@FormatNumberSped(2)
	public BigDecimal getValorIpi() {
		return valorIpi;
	}

	@CampoSped(name = "COD_OBS", order = 12)
	public String getCodigoObservacao() {
		return codigoObservacao;
	}

	public void setCstIcms(String cstIcms) {
		this.cstIcms = cstIcms;
	}

	public void setCfop(String cfop) {
		this.cfop = cfop;
	}

	public void setAliqIcms(BigDecimal aliqIcms) {
		this.aliqIcms = aliqIcms;
	}

	public void setValorOperacao(BigDecimal valorOperacao) {
		this.valorOperacao = valorOperacao;
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

	public void setValorReducaoBase(BigDecimal valorReducaoBase) {
		this.valorReducaoBase = valorReducaoBase;
	}

	public void setValorIpi(BigDecimal valorIpi) {
		this.valorIpi = valorIpi;
	}

	public void setCodigoObservacao(String codigoObservacao) {
		this.codigoObservacao = codigoObservacao;
	}

}
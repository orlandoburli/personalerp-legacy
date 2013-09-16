package br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi;

import java.io.Serializable;
import java.math.BigDecimal;

public class IpiTrib implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código da situação tributária do IPI
	 */
	private String CST;
	/**
	 * Valor da Base de calculo do IPI
	 */
	private BigDecimal vBc;
	/**
	 * Alíquota do IPI
	 */
	private BigDecimal pIPI;
	/**
	 * Quantidade total na unidade padrão para tributação (somente para os produtos tributados por unidade)
	 */
	private BigDecimal qUnid;
	/**
	 * Valor por Unidade Tributável
	 */
	private BigDecimal vUnid;
	/**
	 * Valor por Unidade Tributável
	 */
	private BigDecimal vIPI;
	
	public String getCST() {
		return CST;
	}
	public void setCST(String cST) {
		CST = cST;
	}
	public BigDecimal getvBc() {
		return vBc;
	}
	public void setvBc(BigDecimal vBc) {
		this.vBc = vBc;
	}
	public BigDecimal getpIPI() {
		return pIPI;
	}
	public void setpIPI(BigDecimal pIPI) {
		this.pIPI = pIPI;
	}
	public BigDecimal getqUnid() {
		return qUnid;
	}
	public void setqUnid(BigDecimal qUnid) {
		this.qUnid = qUnid;
	}
	public BigDecimal getvUnid() {
		return vUnid;
	}
	public void setvUnid(BigDecimal vUnid) {
		this.vUnid = vUnid;
	}
	public BigDecimal getvIPI() {
		return vIPI;
	}
	public void setvIPI(BigDecimal vIPI) {
		this.vIPI = vIPI;
	}
}

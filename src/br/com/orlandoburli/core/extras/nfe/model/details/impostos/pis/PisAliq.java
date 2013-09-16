package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de PIS tributado pela alíquota 
 */
public class PisAliq implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 
	 * Código da Situação Tributária
	 * <ul>
	 * <li>01 – Operação Tributável (base de cálculo = valor da operação<br/> 
	 * alíquota normal (cumulativo/não cumulativo));</li>
	 * <li>02 - Operação Tributável (base de cálculo = valor da operação<br/> 
	 * (alíquota diferenciada));</li>
	 * </ul>
	 */
	private String CST;
	/**
	 * Valor da Base de Cálculo do PIS (15,2)
	 */
	private BigDecimal vBC;
	/**
	 * Alíquota do PIS (em percentual) (5,2)
	 */
	private BigDecimal pPIS;
	/**
	 * Valor do PIS (15,2)
	 */
	private BigDecimal vPIS;
	
	/////////////////////////
	

	/** 
	 * Código da Situação Tributária
	 * <ul>
	 * <li>01 – Operação Tributável (base de cálculo = valor da operação<br/> 
	 * alíquota normal (cumulativo/não cumulativo));</li>
	 * <li>02 - Operação Tributável (base de cálculo = valor da operação<br/> 
	 * (alíquota diferenciada));</li>
	 * </ul>
	 */
	public void setCST(String cST) {
		CST = cST;
	}
	public String getCST() {
		return CST;
	}
	/**
	 * Valor da Base de Cálculo do PIS (15,2)
	 */
	public void setvBC(BigDecimal vBC) {
		this.vBC = vBC;
		if (this.vBC != null) {
			this.vBC = this.vBC.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvBC() {
		return vBC;
	}
	/**
	 * Alíquota do PIS (em percentual) (5,2)
	 */
	public void setpPIS(BigDecimal pPIS) {
		this.pPIS = pPIS;
		if (this.pPIS != null) {
			this.pPIS = this.pPIS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpPIS() {
		return pPIS;
	}
	/**
	 * Valor do PIS (15,2)
	 */
	public void setvPIS(BigDecimal vPIS) {
		this.vPIS = vPIS;
		if (this.vPIS != null) {
			this.vPIS = this.vPIS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvPIS() {
		return vPIS;
	}
}

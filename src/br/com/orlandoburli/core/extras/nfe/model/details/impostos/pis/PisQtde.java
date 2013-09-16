package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de PIS tributado por Qtde
 */
public class PisQtde implements Serializable {

	private static final long serialVersionUID = 1L;

	/** Código da Situação Tributária
	 * <ul>
	 * <li>03 - Operação Tributável (base de cálculo = quantidade vendida x alíquota por unidade de produto);</li>
	 * </ul>
	 */
	private String CST = "03";
	/**
	 * Quantidade Vendida (16, 4)
	 */
	private BigDecimal qBCProd;
	/**
	 * Alíquota do PIS (em reais) (15, 4)
	 */
	private BigDecimal vAliqProd;
	/**
	 * Valor do PIS (15,2)
	 */
	private BigDecimal vPIS;
	
	///////////////////////

	/** Código da Situação Tributária
	 * <ul>
	 * <li>03 - Operação Tributável (base de cálculo = quantidade vendida x alíquota por unidade de produto);</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	/**
	 * Quantidade Vendida (16, 4)
	 */
	public void setqBCProd(BigDecimal qBCProd) {
		this.qBCProd = qBCProd;
		if (this.qBCProd != null) {
			this.qBCProd = this.qBCProd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getqBCProd() {
		return qBCProd;
	}
	/**
	 * Alíquota do PIS (em reais) (15, 4)
	 */
	public void setvAliqProd(BigDecimal vAliqProd) {
		this.vAliqProd = vAliqProd;
		if (this.vAliqProd != null) {
			this.vAliqProd = this.vAliqProd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvAliqProd() {
		return vAliqProd;
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
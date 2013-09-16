package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de PIS tributado por Qtde
 */
public class PisQtde implements Serializable {

	private static final long serialVersionUID = 1L;

	/** C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>03 - Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto);</li>
	 * </ul>
	 */
	private String CST = "03";
	/**
	 * Quantidade Vendida (16, 4)
	 */
	private BigDecimal qBCProd;
	/**
	 * Al�quota do PIS (em reais) (15, 4)
	 */
	private BigDecimal vAliqProd;
	/**
	 * Valor do PIS (15,2)
	 */
	private BigDecimal vPIS;
	
	///////////////////////

	/** C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>03 - Opera��o Tribut�vel (base de c�lculo = quantidade vendida x al�quota por unidade de produto);</li>
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
	 * Al�quota do PIS (em reais) (15, 4)
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
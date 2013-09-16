package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de PIS Outras Operações <br/>
 * Informar campos para cálculo do PIS em percentual (P07 e P08)<br/> 
 * ou campos para PIS em valor (P10 e P11)
 */
public class PisOutr implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código da Situação Tributária
	 * <ul>
	 * <li>99 - Outras Operações;</li>
	 * </ul>
	 */
	private String CST = "99";
	/**
	 * Valor da Base de Cálculo do PIS (15, 2)
	 */
	private BigDecimal vBC;
	/**
	 * Alíquota do PIS (em percentual) (5, 2)
	 */
	private BigDecimal pPIS;
	/**
	 * Quantidade Vendida (16, 2)
	 */
	private BigDecimal qBCProd;
	/**
	 * Alíquota do PIS (em reais) (15, 4)
	 */
	private BigDecimal vAliqProd;
	/**
	 * Valor do PIS (15, 2)
	 */
	private BigDecimal vPIS;

	/**
	 * Código da Situação Tributária
	 * <ul>
	 * <li>99 - Outras Operações;</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	/**
	 * Valor da Base de Cálculo do PIS (15, 2)
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
	 * Alíquota do PIS (em percentual) (5, 2)
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
	 * Quantidade Vendida (16, 2)
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
	 * Valor do PIS (15, 2)
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
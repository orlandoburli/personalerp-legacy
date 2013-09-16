package br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de COFINS Outras Operações
 */
public class CofinsOutr implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>99 - Outras Operações;</li>
	 * </ul>
	 */
	private String CST = "99";
	/**
	 * Valor da Base de Cálculo da COFINS<br/>
	 * Informar campos para cálculo do COFINS em percentual (S07 e S08)<br/>
	 * ou campos para COFINS em valor (S09 e S10).
	 */
	private BigDecimal vBC;
	/**
	 * Alíquota da COFINS (em percentual) (5, 2)
	 */
	private BigDecimal pCOFINS;
	/**
	 * Quantidade Vendida (16, 4)
	 */
	private BigDecimal qBCProd;
	/**
	 * Alíquota do COFINS (em reais) (15, 4)
	 */
	private BigDecimal vAliqProd;
	/**
	 * Valor do COFINS (15, 2)
	 */
	private BigDecimal vCOFINS;
	
	/////////////////
	
	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>99 - Outras Operações;</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	public BigDecimal getvBC() {
		return vBC;
	}
	/**
	 * Valor da Base de Cálculo da COFINS<br/>
	 * Informar campos para cálculo do COFINS em percentual (S07 e S08)<br/>
	 * ou campos para COFINS em valor (S09 e S10).
	 */
	public void setvBC(BigDecimal vBC) {
		this.vBC = vBC;
		if (this.vBC != null) {
			this.vBC = this.vBC.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpCOFINS() {
		return pCOFINS;
	}
	/**
	 * Alíquota da COFINS (em percentual) (5, 2)
	 */
	public void setpCOFINS(BigDecimal pCOFINS) {
		this.pCOFINS = pCOFINS;
		if (this.pCOFINS != null) {
			this.pCOFINS = this.pCOFINS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getqBCProd() {
		return qBCProd;
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
	public BigDecimal getvAliqProd() {
		return vAliqProd;
	}
	/**
	 * Alíquota do COFINS (em reais) (15, 4)
	 */
	public void setvAliqProd(BigDecimal vAliqProd) {
		this.vAliqProd = vAliqProd;
		if (this.vAliqProd != null) {
			this.vAliqProd = this.vAliqProd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvCOFINS() {
		return vCOFINS;
	}
	/**
	 * Valor do COFINS (15, 2)
	 */
	public void setvCOFINS(BigDecimal vCOFINS) {
		this.vCOFINS = vCOFINS;
		if (this.vCOFINS != null) {
			this.vCOFINS = this.vCOFINS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
}
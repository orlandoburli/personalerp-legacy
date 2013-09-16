package br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Grupo de COFINS tributado por Qtde
 */
public class CofinsQtde implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>03 - Operação Tributável (base de cálculo = 
	 * quantidade vendida x alíquota por unidade de produto);</li>
	 * </ul>
	 */
	private String CST = "03";
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

	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>03 - Operação Tributável (base de cálculo = 
	 * quantidade vendida x alíquota por unidade de produto);</li>
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
	 * Alíquota do COFINS (em reais) (15, 4)
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
	 * Valor do COFINS (15, 2)
	 */
	public void setvCOFINS(BigDecimal vCOFINS) {
		this.vCOFINS = vCOFINS;
		if (this.vCOFINS != null) {
			this.vCOFINS = this.vCOFINS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvCOFINS() {
		return vCOFINS;
	}
}
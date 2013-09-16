package br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de COFINS tributado pela alíquota
 */
public class CofinsAliq implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>01 – Operação Tributável (base de cálculo = valor da operação alíquota normal<br/> 
	 * (cumulativo/não cumulativo));</li>
	 * <li>02 - Operação Tributável (base de cálculo = valor da operação<br/>
	 *  (alíquota diferenciada));</li>
	 * </ul>
	 */
	private String CST;
	/**
	 * Valor da Base de Cálculo da COFINS (15, 2)
	 */
	private BigDecimal vBC;
	/**
	 * Alíquota da COFINS (em percentual) (5, 2)
	 */
	private BigDecimal pCOFINS;
	/**
	 * Valor do COFINS (15, 2)
	 */
	private BigDecimal vCOFINS;
	
	/**
	 * Código de Situação Tributária do COFINS
	 * <ul>
	 * <li>01 – Operação Tributável (base de cálculo = valor da operação alíquota normal<br/> 
	 * (cumulativo/não cumulativo));</li>
	 * <li>02 - Operação Tributável (base de cálculo = valor da operação<br/>
	 *  (alíquota diferenciada));</li>
	 * </ul>
	 */
	public void setCST(String cST) {
		CST = cST;
	}
	public String getCST() {
		return CST;
	}
	/**
	 * Valor da Base de Cálculo da COFINS (15, 2)
	 */
	public void setvBC(BigDecimal vBC) {
		this.vBC = vBC;
		if (this.vBC != null) {
			this.vBC = vBC.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvBC() {
		return vBC;
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
	public BigDecimal getpCOFINS() {
		return pCOFINS;
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
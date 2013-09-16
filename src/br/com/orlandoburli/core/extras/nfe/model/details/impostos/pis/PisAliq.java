package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do Grupo de PIS tributado pela al�quota 
 */
public class PisAliq implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 
	 * C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>01 � Opera��o Tribut�vel (base de c�lculo = valor da opera��o<br/> 
	 * al�quota normal (cumulativo/n�o cumulativo));</li>
	 * <li>02 - Opera��o Tribut�vel (base de c�lculo = valor da opera��o<br/> 
	 * (al�quota diferenciada));</li>
	 * </ul>
	 */
	private String CST;
	/**
	 * Valor da Base de C�lculo do PIS (15,2)
	 */
	private BigDecimal vBC;
	/**
	 * Al�quota do PIS (em percentual) (5,2)
	 */
	private BigDecimal pPIS;
	/**
	 * Valor do PIS (15,2)
	 */
	private BigDecimal vPIS;
	
	/////////////////////////
	

	/** 
	 * C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>01 � Opera��o Tribut�vel (base de c�lculo = valor da opera��o<br/> 
	 * al�quota normal (cumulativo/n�o cumulativo));</li>
	 * <li>02 - Opera��o Tribut�vel (base de c�lculo = valor da opera��o<br/> 
	 * (al�quota diferenciada));</li>
	 * </ul>
	 */
	public void setCST(String cST) {
		CST = cST;
	}
	public String getCST() {
		return CST;
	}
	/**
	 * Valor da Base de C�lculo do PIS (15,2)
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
	 * Al�quota do PIS (em percentual) (5,2)
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

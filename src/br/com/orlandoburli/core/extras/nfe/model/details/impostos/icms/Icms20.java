package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do tipo Tributação do ICMS 20 - <br/>
 * Com redução de base de cálculo<br/>
 */
public class Icms20 implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Origem da mercadoria: 
	 * <ul>
	 * <li>0 - Nacional</li> 
	 * <li>1 - Estrangeira - Importação direta</li> 
	 * <li>2 - Estrangeira - Adquirida no mercado interno</li>
	 * </ul> 
	 */
	private String orig;
	/**
	 * Tributação pelo ICMS
	 * <ul>
	 * <li>20 - Com redução de base de cálculo</li>
	 * </ul>
	 */
	private String CST = "20";
	/**
	 * Modalidade de determinação da Base de Calculo do ICMS:
	 * <ul>
	 * <li>0 - Margem Valor Agregado (%);</li>
	 * <li>1 - Pauta (valor);</li>
	 * <li>2 - Preço Tabelado Máximo (valor);</li>
	 * <li>3 - Valor da Operação.</li>
	 * </ul>
	 */
	private String modBC;
	/**
	 * Valor da Base de Calculo do ICMS<br/>
	 * Formato 13,2
	 */
	private BigDecimal vBC;
	/**
	 * Alíquota do ICMS<br/>
	 * Formato 3,2
	 */
	private BigDecimal pICMS;
	/**
	 * Valor do ICMS<br/>
	 * Formato 13,2
	 */
	private BigDecimal vICMS;
	/**
	 * Percentual da Redução de BC
	 */
	private BigDecimal pRedBC;
	
	///////////////
	
	/**
	 * Origem da mercadoria: 
	 * <ul>
	 * <li>0 - Nacional</li> 
	 * <li>1 - Estrangeira - Importação direta</li> 
	 * <li>2 - Estrangeira - Adquirida no mercado interno</li>
	 * </ul> 
	 */
	public void setOrig(String orig) {
		this.orig = orig;
	}
	public String getOrig() {
		return orig;
	}
	/**
	 * Tributação pelo ICMS
	 * <ul>
	 * <li>20 - Com redução de base de cálculo</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	/**
	 * Modalidade de determinação da BC do ICMS:
	 * <ul>
	 * <li>0 - Margem Valor Agregado (%);</li>
	 * <li>1 - Pauta (valor);</li>
	 * <li>2 - Preço Tabelado Máximo (valor);</li>
	 * <li>3 - Valor da Operação.</li>
	 * </ul>
	 */
	public void setModBC(String modBC) {
		this.modBC = modBC;
	}
	public String getModBC() {
		return modBC;
	}
	/**
	 * Valor da Base de Calculo do ICMS<br/>
	 * Formato 13,2
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
	 * Alíquota do ICMS<br/>
	 * Formato 3,2
	 */
	public void setpICMS(BigDecimal pICMS) {
		this.pICMS = pICMS;
		if (this.pICMS != null) {
			this.pICMS = this.pICMS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpICMS() {
		return pICMS;
	}
	/**
	 * Valor do ICMS<br/>
	 * Formato 13,2
	 */
	public void setvICMS(BigDecimal vICMS) {
		this.vICMS = vICMS;
		if (this.vICMS != null) {
			this.vICMS = this.vICMS.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvICMS() {
		return vICMS;
	}
	/**
	 * Percentual da Redução de BC
	 */
	public void setpRedBC(BigDecimal pRedBC) {
		this.pRedBC = pRedBC;
		if (this.pRedBC != null) {
			this.pRedBC = this.pRedBC.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpRedBC() {
		return pRedBC;
	}
}

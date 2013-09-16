package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do tipo de tributa��o 00 - <br/> 
 * Tributada totalmente 
 */
public class Icms00 implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Origem da mercadoria: 
	 * <ul>
	 * <li>0 - Nacional</li> 
	 * <li>1 - Estrangeira - Importa��o direta</li> 
	 * <li>2 - Estrangeira - Adquirida no mercado interno</li>
	 * </ul> 
	 */
	private String orig;
	/**
	 * Tributa��o pelo ICMS
	 * <ul>
	 * <li>00 - Tributada integralmente</li>
	 * </ul>
	 */
	private String CST = "00";
	/**
	 * Modalidade de determina��o da Base de Calculo do ICMS:
	 * <ul>
	 * <li>0 - Margem Valor Agregado (%);</li>
	 * <li>1 - Pauta (valor);</li>
	 * <li>2 - Pre�o Tabelado M�ximo (valor);</li>
	 * <li>3 - Valor da Opera��o.</li>
	 * </ul>
	 */
	private String modBC;
	/**
	 * Valor da Base de Calculo do ICMS<br/>
	 * Formato 13,2
	 */
	private BigDecimal vBC;
	/**
	 * Al�quota do ICMS<br/>
	 * Formato 3,2
	 */
	private BigDecimal pICMS;
	/**
	 * Valor do ICMS<br/>
	 * Formato 13,2
	 */
	private BigDecimal vICMS;
	
	
	/**
	 * Origem da mercadoria: 
	 * <ul>
	 * <li>0 - Nacional</li> 
	 * <li>1 - Estrangeira - Importa��o direta</li> 
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
	 * Tributa��o pelo ICMS
	 * <ul>
	 * <li>00 - Tributada integralmente</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	/**
	 * Modalidade de determina��o da BC do ICMS:
	 * <ul>
	 * <li>0 - Margem Valor Agregado (%);</li>
	 * <li>1 - Pauta (valor);</li>
	 * <li>2 - Pre�o Tabelado M�ximo (valor);</li>
	 * <li>3 - Valor da Opera��o.</li>
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
	 * Al�quota do ICMS<br/>
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
}
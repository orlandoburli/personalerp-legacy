package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do tipo Tributacao do ICMS 30 - <br/> 
 * Isenta ou não tributada e com cobrança do ICMS por substituição tributária
 */
public class Icms30 implements Serializable {
	
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
	 * <li>30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária</li>
	 * </ul>
	 */
	private String CST = "30";
	/**
	 * Modalidade de determinação da Base de Calculo do ICMS ST (Substuicao Tributaria):
	 * <ul>
	 * <li>0 – Preço tabelado ou máximo sugerido;</li>
	 * <li>1 - Lista Negativa (valor);</li>
	 * <li>2 - Lista Positiva (valor);</li>
	 * <li>3 - Lista Neutra (valor);</li>
	 * <li>4 - Margem Valor Agregado (%);</li>
	 * <li>5 - Pauta (valor);</li>
	 * </ul>
	 */
	private String modBCST;
	/**
	 * Percentual da margem de valor Adicionado do ICMS ST
	 */
	private BigDecimal pMVAST;
	/**
	 * Percentual da Redução de Base de Calculo do ICMS Substituicao Tributaria
	 */	
	private BigDecimal pRedBCST;
	/**
	 * Valor da Base de Calculo do ICMS ST<br/>
	 * Formato 13,2
	 */
	private BigDecimal vBCST;
	/**
	 * Alíquota do ICMS ST<br/>
	 * Formato 3,2
	 */
	private BigDecimal pICMSST;
	/**
	 * Valor do ICMS ST<br/>
	 * Formato 13,2
	 */
	private BigDecimal vICMSST;
	
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
	 * <li>30 - Isenta ou não tributada e com cobrança do ICMS por substituição tributária</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
	/**
	 * Modalidade de determinação da Base de Calculo do ICMS ST (Substuicao Tributaria):
	 * <ul>
	 * <li>0 – Preço tabelado ou máximo sugerido;</li>
	 * <li>1 - Lista Negativa (valor);</li>
	 * <li>2 - Lista Positiva (valor);</li>
	 * <li>3 - Lista Neutra (valor);</li>
	 * <li>4 - Margem Valor Agregado (%);</li>
	 * <li>5 - Pauta (valor);</li>
	 * </ul>
	 */
	public void setModBC(String modBCST) {
		this.modBCST = modBCST;
	}
	public String getModBCST() {
		return modBCST;
	}
	/**
	 * Valor da Base de Calculo do ICMS ST<br/>
	 * Formato 13,2
	 */
	public void setvBCST(BigDecimal vBCST) {
		this.vBCST = vBCST;
		if (this.vBCST != null) {
			this.vBCST = this.vBCST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvBCST() {
		return vBCST;
	}
	/**
	 * Alíquota do ICMS ST<br/>
	 * Formato 3,2
	 */
	public void setpICMSST(BigDecimal pICMSST) {
		this.pICMSST = pICMSST;
		if (this.pICMSST != null) {
			this.pICMSST = this.pICMSST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpICMSST() {
		return pICMSST;
	}
	/**
	 * Valor do ICMS ST<br/>
	 * Formato 13,2
	 */
	public void setvICMSST(BigDecimal vICMSST) {
		this.vICMSST = vICMSST;
		if (this.vICMSST != null) {
			this.vICMSST = this.vICMSST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getvICMSST() {
		return vICMSST;
	}
	/**
	 * Percentual da margem de valor Adicionado do ICMS ST
	 */
	public void setpMVAST(BigDecimal pMVAST) {
		this.pMVAST = pMVAST;
		if (this.pMVAST != null) {
			this.pMVAST = this.pMVAST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpMVAST() {
		return pMVAST;
	}
	/**
	 * Percentual da Redução de Base de Calculo do ICMS Substituicao Tributaria
	 */
	public void setpRedBCST(BigDecimal pRedBCST) {
		this.pRedBCST = pRedBCST;
		if (this.pRedBCST != null) {
			this.pRedBCST = this.pRedBCST.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		}
	}
	public BigDecimal getpRedBCST() {
		return pRedBCST;
	}
}
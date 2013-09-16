package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.orlandoburli.core.extras.nfe.interfaces.INumberFormat;

/**
 * Classe do tipo Tributa��o do ICMS 10 - <br/>
 * Tributada e com cobran�a do ICMS por Substitui��o Tribut�ria<br/>
 * <b>ST</b> - <i>Substituicao Tributaria</i> 
 */
public class Icms10 implements Serializable {
	
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
	 * <li>10 - Tributada e com cobran�a do ICMS por substitui��o tribut�ria</li>
	 * </ul>
	 */
	private String CST = "10";
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
	 * Modalidade de determina��o da BC do ICMS ST
	 * <ul>
	 * <li>0 � Pre�o tabelado ou m�ximo sugerido;</li>
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
	@INumberFormat(maskNumber="#0.00")
	private BigDecimal pMVAST;
	/**
	 * Percentual da Redu��o de BC do ICMS ST
	 */
	@INumberFormat(maskNumber="#0.00")
	private BigDecimal pRedBCST;
	/**
	 * Valor da BC do ICMS ST
	 */
	private BigDecimal vBCST;
	/**
	 *  Al�quota do imposto do ICMS Substituicao Tributaria
	 */
	private BigDecimal pICMSST;
	/**
	 * Valor do ICMS ST
	 */
	private BigDecimal vICMSST;
	
	////////////////////
	
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
	 * <li>10 - Tributada e com cobran�a do ICMS por substitui��o tribut�ria</li>
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
	 * Percentual da Redu��o de BC do ICMS ST
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
	/**
	 * Valor da BC do ICMS ST
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
	 *  Al�quota do imposto do ICMS Substituicao Tributaria
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
	 * Valor do ICMS ST
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
	 * Modalidade de determina��o da BC do ICMS ST
	 * <ul>
	 * <li>0 � Pre�o tabelado ou m�ximo sugerido;</li>
	 * <li>1 - Lista Negativa (valor);</li>
	 * <li>2 - Lista Positiva (valor);</li>
	 * <li>3 - Lista Neutra (valor);</li>
	 * <li>4 - Margem Valor Agregado (%);</li>
	 * <li>5 - Pauta (valor);</li>
	 * </ul>
	 */
	public void setModBCST(String modBCST) {
		this.modBCST = modBCST;
	}
	public String getModBCST() {
		return modBCST;
	}
}
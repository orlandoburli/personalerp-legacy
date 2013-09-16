package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do tipo de tributação 40, 41, 51<br/> 
 * Tributação do ICMS :
 * <ul>
 * <li>40 - Isenta</li>
 * <li>41 - Não tributada</li>
 * <li>50 - Suspensão</li>
 * </ul>
 */
public class IcmsXX implements Serializable {

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
	 * <li>40 - Isenta</li>
	 * <li>41 - Não tributada</li>
	 * <li>50 - Suspensão</li>
	 * </ul>
	 */
	private String CST;
	
	/**
	 * Valor do ICMS<br/>
	 * O valor do ICMS será informado apenas nas operações com veículos<br/>
	 * beneficiados com a desoneração condicional do ICMS. (v2.0)<br/>
	 */
	private BigDecimal vICMS;
	/**
	 * Motivo da desoneração do ICMS<br/>
	 * Este campo será preenchido quando o campo anterior estiver preenchido.<br>
	 * Informar o motivo da desoneração:
	 * <ul>
	 * <li>1 – Táxi;</li>
	 * <li>2 – Deficiente Físico;</li>
	 * <li>3 – Produtor Agropecuário;</li>
	 * <li>4 – Frotista/Locadora;</li>
	 * <li>5 – Diplomático/Consular;</li>
	 * <li>6 – Utilitários e Motocicletas da Amazônia Ocidental e Áreas de<br/>
	 * Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações);</li>
	 * <li>7 – SUFRAMA;</li>
	 * <li>9 – outros. (v2.0)</li>
	 * </ul>
	 */
	private String motDesICMS;
	
	//////////////////////
	
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
	 * Valor do ICMS<br/>
	 * O valor do ICMS será informado apenas nas operações com veículos<br/>
	 * beneficiados com a desoneração condicional do ICMS. (v2.0)<br/>
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
	 * Motivo da desoneração do ICMS<br/>
	 * Este campo será preenchido quando o campo anterior estiver preenchido.<br>
	 * Informar o motivo da desoneração:
	 * <ul>
	 * <li>1 – Táxi;</li>
	 * <li>2 – Deficiente Físico;</li>
	 * <li>3 – Produtor Agropecuário;</li>
	 * <li>4 – Frotista/Locadora;</li>
	 * <li>5 – Diplomático/Consular;</li>
	 * <li>6 – Utilitários e Motocicletas da Amazônia Ocidental e Áreas de<br/>
	 * Livre Comércio (Resolução 714/88 e 790/94 – CONTRAN e suas alterações);</li>
	 * <li>7 – SUFRAMA;</li>
	 * <li>9 – outros. (v2.0)</li>
	 * </ul>
	 */
	public void setMotDesICMS(String motDesICMS) {
		this.motDesICMS = motDesICMS;
	}
	public String getMotDesICMS() {
		return motDesICMS;
	}
	/**
	 * Tributação pelo ICMS
	 * <ul>
	 * <li>40 - Isenta</li>
	 * <li>41 - Não tributada</li>
	 * <li>50 - Suspensão</li>
	 * </ul>
	 */
	protected void setCST(String cST) {
		CST = cST;
	}
	/**
	 * Tributação pelo ICMS
	 * <ul>
	 * <li>40 - Isenta</li>
	 * <li>41 - Não tributada</li>
	 * <li>50 - Suspensão</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
}
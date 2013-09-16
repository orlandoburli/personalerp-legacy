package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Classe do tipo de tributa��o 40, 41, 51<br/> 
 * Tributa��o do ICMS :
 * <ul>
 * <li>40 - Isenta</li>
 * <li>41 - N�o tributada</li>
 * <li>50 - Suspens�o</li>
 * </ul>
 */
public class IcmsXX implements Serializable {

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
	 * <li>40 - Isenta</li>
	 * <li>41 - N�o tributada</li>
	 * <li>50 - Suspens�o</li>
	 * </ul>
	 */
	private String CST;
	
	/**
	 * Valor do ICMS<br/>
	 * O valor do ICMS ser� informado apenas nas opera��es com ve�culos<br/>
	 * beneficiados com a desonera��o condicional do ICMS. (v2.0)<br/>
	 */
	private BigDecimal vICMS;
	/**
	 * Motivo da desonera��o do ICMS<br/>
	 * Este campo ser� preenchido quando o campo anterior estiver preenchido.<br>
	 * Informar o motivo da desonera��o:
	 * <ul>
	 * <li>1 � T�xi;</li>
	 * <li>2 � Deficiente F�sico;</li>
	 * <li>3 � Produtor Agropecu�rio;</li>
	 * <li>4 � Frotista/Locadora;</li>
	 * <li>5 � Diplom�tico/Consular;</li>
	 * <li>6 � Utilit�rios e Motocicletas da Amaz�nia Ocidental e �reas de<br/>
	 * Livre Com�rcio (Resolu��o 714/88 e 790/94 � CONTRAN e suas altera��es);</li>
	 * <li>7 � SUFRAMA;</li>
	 * <li>9 � outros. (v2.0)</li>
	 * </ul>
	 */
	private String motDesICMS;
	
	//////////////////////
	
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
	 * Valor do ICMS<br/>
	 * O valor do ICMS ser� informado apenas nas opera��es com ve�culos<br/>
	 * beneficiados com a desonera��o condicional do ICMS. (v2.0)<br/>
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
	 * Motivo da desonera��o do ICMS<br/>
	 * Este campo ser� preenchido quando o campo anterior estiver preenchido.<br>
	 * Informar o motivo da desonera��o:
	 * <ul>
	 * <li>1 � T�xi;</li>
	 * <li>2 � Deficiente F�sico;</li>
	 * <li>3 � Produtor Agropecu�rio;</li>
	 * <li>4 � Frotista/Locadora;</li>
	 * <li>5 � Diplom�tico/Consular;</li>
	 * <li>6 � Utilit�rios e Motocicletas da Amaz�nia Ocidental e �reas de<br/>
	 * Livre Com�rcio (Resolu��o 714/88 e 790/94 � CONTRAN e suas altera��es);</li>
	 * <li>7 � SUFRAMA;</li>
	 * <li>9 � outros. (v2.0)</li>
	 * </ul>
	 */
	public void setMotDesICMS(String motDesICMS) {
		this.motDesICMS = motDesICMS;
	}
	public String getMotDesICMS() {
		return motDesICMS;
	}
	/**
	 * Tributa��o pelo ICMS
	 * <ul>
	 * <li>40 - Isenta</li>
	 * <li>41 - N�o tributada</li>
	 * <li>50 - Suspens�o</li>
	 * </ul>
	 */
	protected void setCST(String cST) {
		CST = cST;
	}
	/**
	 * Tributa��o pelo ICMS
	 * <ul>
	 * <li>40 - Isenta</li>
	 * <li>41 - N�o tributada</li>
	 * <li>50 - Suspens�o</li>
	 * </ul>
	 */
	public String getCST() {
		return CST;
	}
}
package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

/**
 * Classe de tributacao ICMS pelo Simples Nacional.<br/>
 * Tributa��o do ICMS :
 * <ul>
 * <li>102- Tributada pelo Simples Nacional sem permiss�o de cr�dito.</li>
 * <li>103 � Isen��o do ICMS no Simples Nacional para faixa de receita bruta.</li>
 * <li>300 � Imune.</li>
 * <li>400 � N�o tributda pelo Simples Nacional.</li>
 * </ul> 
 */
public class IcmsSn102 {

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
	 * C�digo de Situa��o da Opera��o � Simples Nacional<br/>
	 * <ul>
	 * <li>102- Tributada pelo Simples Nacional sem permiss�o de cr�dito.</li>
	 * <li>103 � Isen��o do ICMS no Simples Nacional para faixa de receita bruta.</li>
	 * <li>300 � Imune.</li>
	 * <li>400 � N�o tributda pelo Simples Nacional.</li>
	 * </ul> 
	 */
	private String CSOSN;
	
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
	 * C�digo de Situa��o da Opera��o � Simples Nacional<br/>
	 * <ul>
	 * <li>102- Tributada pelo Simples Nacional sem permiss�o de cr�dito.</li>
	 * <li>103 � Isen��o do ICMS no Simples Nacional para faixa de receita bruta.</li>
	 * <li>300 � Imune.</li>
	 * <li>400 � N�o tributda pelo Simples Nacional.</li>
	 * </ul> 
	 */
	public void setCSOSN(String cSOSN) {
		CSOSN = cSOSN;
	}
	public String getCSOSN() {
		return CSOSN;
	}
}

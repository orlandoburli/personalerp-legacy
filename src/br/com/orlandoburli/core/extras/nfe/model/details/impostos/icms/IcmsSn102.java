package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

/**
 * Classe de tributacao ICMS pelo Simples Nacional.<br/>
 * Tributação do ICMS :
 * <ul>
 * <li>102- Tributada pelo Simples Nacional sem permissão de crédito.</li>
 * <li>103 – Isenção do ICMS no Simples Nacional para faixa de receita bruta.</li>
 * <li>300 – Imune.</li>
 * <li>400 – Não tributda pelo Simples Nacional.</li>
 * </ul> 
 */
public class IcmsSn102 {

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
	 * Código de Situação da Operação – Simples Nacional<br/>
	 * <ul>
	 * <li>102- Tributada pelo Simples Nacional sem permissão de crédito.</li>
	 * <li>103 – Isenção do ICMS no Simples Nacional para faixa de receita bruta.</li>
	 * <li>300 – Imune.</li>
	 * <li>400 – Não tributda pelo Simples Nacional.</li>
	 * </ul> 
	 */
	private String CSOSN;
	
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
	 * Código de Situação da Operação – Simples Nacional<br/>
	 * <ul>
	 * <li>102- Tributada pelo Simples Nacional sem permissão de crédito.</li>
	 * <li>103 – Isenção do ICMS no Simples Nacional para faixa de receita bruta.</li>
	 * <li>300 – Imune.</li>
	 * <li>400 – Não tributda pelo Simples Nacional.</li>
	 * </ul> 
	 */
	public void setCSOSN(String cSOSN) {
		CSOSN = cSOSN;
	}
	public String getCSOSN() {
		return CSOSN;
	}
}

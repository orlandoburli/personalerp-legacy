package br.com.orlandoburli.core.extras.nfe.model.transporte;

import java.io.Serializable;

/**
 * Classe do Grupo Transportador
 */
public class Transportadora implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * CNPJ da Transportadora
	 */
	private String CNPJ;
	/**
	 * CPF da Transportadora
	 */
	private String CPF;
	/**
	 * Raz�o Social ou nome
	 */
	private String xNome;
	/**
	 * Informar a IE quando o transportador for contribuinte do ICMS.<br/>
	 * Informar ISENTO quando o transportador for contribuinte do ICMS,<br/>
	 * mas n�o estiver obrigado � inscri��o no cadastro de contribuintes do ICMS.<br/>
	 * N�o informar o conte�do da TAG se o transportador n�o for<br/>
	 * contribuinte do ICMS.
	 * Esta tag aceita apenas:
	 * <ul>
	 * <li>aus�ncia de conte�do (<IE></IE> ou <IE/>) para transportador n�o contribuinte do ICMS;</li>
	 * <li>algarismos para transportador contribuinte do ICMS, sem caracteres de formata��o (ponto, barra, h�fen, etc.);</li>
	 * <li>literal �ISENTO� para transportador contribuintes do ICMS que s�o isentos de inscri��o no cadastro de contribuintes do ICMS;</li>
	 * </ul>
	 * A UF deve ser informada se informado uma IE. (v2.0)
	 */
	private String IE;
	/**
	 * Endere�o Completo
	 */
	private String xEnder;
	/**
	 * Nome do munic�pio
	 */
	private String xMun;
	/**
	 * Sigla da UF
	 */
	private String UF;
	
	
	/**
	 * CNPJ da Transportadora
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	/**
	 * CPF da Transportadora
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCPF() {
		return CPF;
	}
	/**
	 * Raz�o Social ou nome
	 * @param xNome
	 */
	public void setxNome(String xNome) {
		this.xNome = xNome;
	}
	public String getxNome() {
		return xNome;
	}
	public String getIE() {
		return IE;
	}
	/**
	 * Informar a IE quando o transportador for contribuinte do ICMS.<br/>
	 * Informar ISENTO quando o transportador for contribuinte do ICMS,<br/>
	 * mas n�o estiver obrigado � inscri��o no cadastro de contribuintes do ICMS.<br/>
	 * N�o informar o conte�do da TAG se o transportador n�o for<br/>
	 * contribuinte do ICMS.
	 * Esta tag aceita apenas:
	 * <ul>
	 * <li>aus�ncia de conte�do (<IE></IE> ou <IE/>) para transportador n�o contribuinte do ICMS;</li>
	 * <li>algarismos para transportador contribuinte do ICMS, sem caracteres de formata��o (ponto, barra, h�fen, etc.);</li>
	 * <li>literal �ISENTO� para transportador contribuintes do ICMS que s�o isentos de inscri��o no cadastro de contribuintes do ICMS;</li>
	 * </ul>
	 * A UF deve ser informada se informado uma IE. (v2.0)
	 */
	public void setIE(String iE) {
		IE = iE;
	}
	public String getxEnder() {
		return xEnder;
	}
	/**
	 * Endere�o Completo
	 */
	public void setxEnder(String xEnder) {
		this.xEnder = xEnder;
	}
	public String getxMun() {
		return xMun;
	}
	/**
	 * Nome do munic�pio
	 */
	public void setxMun(String xMun) {
		this.xMun = xMun;
	}
	public String getUF() {
		return UF;
	}
	/**
	 * Sigla da UF
	 */
	public void setUF(String uF) {
		UF = uF;
	}
}
package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Emit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Número do CPF do emitente
	 */
	private String CPF;
	/**
	 * Número do CNPJ do emitente
	 */
	private String CNPJ;
	/**
	 * Razão Social ou Nome do emitente
	 * De 2 a 60 Caracteres.
	 */
	private String xNome;
	/**
	 * Nome fantasia
	 * De 1 a 60 Caracteres
	 */
	private String xFant;
	/**
	 * Endereço do Emitente. 
	 */
	private EnderEmit enderEmit;
	/**
	 * Inscrição Estadual
	 */
	private String IE;
	/**
	 * Inscricao Estadual do Substituto Tributário
	 */
	private String IEST;
	/**
	 * Inscrição Municipal (NAO OBRIGATORIO)
	 */
	private String IM;
	/**
	 * CNAE Fiscal (NAO OBRIGATORIO)
	 */
	private String CNAE;
	/**
	 * Código de Regime Tributário. (NAO OBRIGATORIO)???? 
	 * Este campo será obrigatoriamente preenchido com:
	 * 1 – Simples Nacional;
	 * 2 – Simples Nacional – excesso de sublimite de receita bruta;
	 * 3 – Regime Normal. (v2.0).
	 */
	private String CRT;
	
	public Emit() {
		super();
		this.enderEmit = new EnderEmit();
	}
	
	public String getCPF() {
		return CPF;
	}
	/**
	 * Número do CPF do emitente
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	/**
	 * Número do CNPJ do emitente
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getxNome() {
		return xNome;
	}
	/**
	 * Razão Social ou Nome do emitente
	 * De 2 a 60 Caracteres.
	 */
	public void setxNome(String xNome) {
		this.xNome = xNome;
	}
	public String getxFant() {
		return xFant;
	}
	/**
	 * Nome fantasia
	 * De 1 a 60 Caracteres
	 */
	public void setxFant(String xFant) {
		this.xFant = xFant;
	}
	/**
	 * Endereço do Emitente. 
	 */
	public EnderEmit getEnderEmit() {
		return enderEmit;
	}
	public String getIE() {
		return IE;
	}
	/**
	 * Inscrição Estadual
	 */
	public void setIE(String iE) {
		IE = iE;
	}
	public String getIEST() {
		return IEST;
	}
	/**
	 * Inscricao Estadual do Substituto Tributário
	 */
	public void setIEST(String iEST) {
		IEST = iEST;
	}
	public String getIM() {
		return IM;
	}
	/**
	 * Inscrição Municipal (NAO OBRIGATORIO)
	 */
	public void setIM(String iM) {
		IM = iM;
	}
	public String getCNAE() {
		return CNAE;
	}
	/**
	 * Seta o CNAE Fiscal (NAO OBRIGATORIO)<br/>
	 * (Classificação Nacional de Atividades Econômicas)
	 * @param cNAE
	 */
	public void setCNAE(String cNAE) {
		CNAE = cNAE;
	}
	public String getCRT() {
		return CRT;
	}
	/**
	 * Código de Regime Tributário. (NAO OBRIGATORIO)????<br/> 
	 * Este campo será obrigatoriamente preenchido com:<br/>
	 * <ul>
	 * <li>1 – Simples Nacional;</li>
	 * <li>2 – Simples Nacional – excesso de sublimite de receita bruta;</li>
	 * <li>3 – Regime Normal. (v2.0).</li>
	 * </ul>
	 * @param cRT Valor do CRT
	 */
	public void setCRT(String cRT) {
		CRT = cRT;
	}
}
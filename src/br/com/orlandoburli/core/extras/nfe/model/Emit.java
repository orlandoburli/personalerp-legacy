package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Emit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * N�mero do CPF do emitente
	 */
	private String CPF;
	/**
	 * N�mero do CNPJ do emitente
	 */
	private String CNPJ;
	/**
	 * Raz�o Social ou Nome do emitente
	 * De 2 a 60 Caracteres.
	 */
	private String xNome;
	/**
	 * Nome fantasia
	 * De 1 a 60 Caracteres
	 */
	private String xFant;
	/**
	 * Endere�o do Emitente. 
	 */
	private EnderEmit enderEmit;
	/**
	 * Inscri��o Estadual
	 */
	private String IE;
	/**
	 * Inscricao Estadual do Substituto Tribut�rio
	 */
	private String IEST;
	/**
	 * Inscri��o Municipal (NAO OBRIGATORIO)
	 */
	private String IM;
	/**
	 * CNAE Fiscal (NAO OBRIGATORIO)
	 */
	private String CNAE;
	/**
	 * C�digo de Regime Tribut�rio. (NAO OBRIGATORIO)???? 
	 * Este campo ser� obrigatoriamente preenchido com:
	 * 1 � Simples Nacional;
	 * 2 � Simples Nacional � excesso de sublimite de receita bruta;
	 * 3 � Regime Normal. (v2.0).
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
	 * N�mero do CPF do emitente
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	/**
	 * N�mero do CNPJ do emitente
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getxNome() {
		return xNome;
	}
	/**
	 * Raz�o Social ou Nome do emitente
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
	 * Endere�o do Emitente. 
	 */
	public EnderEmit getEnderEmit() {
		return enderEmit;
	}
	public String getIE() {
		return IE;
	}
	/**
	 * Inscri��o Estadual
	 */
	public void setIE(String iE) {
		IE = iE;
	}
	public String getIEST() {
		return IEST;
	}
	/**
	 * Inscricao Estadual do Substituto Tribut�rio
	 */
	public void setIEST(String iEST) {
		IEST = iEST;
	}
	public String getIM() {
		return IM;
	}
	/**
	 * Inscri��o Municipal (NAO OBRIGATORIO)
	 */
	public void setIM(String iM) {
		IM = iM;
	}
	public String getCNAE() {
		return CNAE;
	}
	/**
	 * Seta o CNAE Fiscal (NAO OBRIGATORIO)<br/>
	 * (Classifica��o Nacional de Atividades Econ�micas)
	 * @param cNAE
	 */
	public void setCNAE(String cNAE) {
		CNAE = cNAE;
	}
	public String getCRT() {
		return CRT;
	}
	/**
	 * C�digo de Regime Tribut�rio. (NAO OBRIGATORIO)????<br/> 
	 * Este campo ser� obrigatoriamente preenchido com:<br/>
	 * <ul>
	 * <li>1 � Simples Nacional;</li>
	 * <li>2 � Simples Nacional � excesso de sublimite de receita bruta;</li>
	 * <li>3 � Regime Normal. (v2.0).</li>
	 * </ul>
	 * @param cRT Valor do CRT
	 */
	public void setCRT(String cRT) {
		CRT = cRT;
	}
}
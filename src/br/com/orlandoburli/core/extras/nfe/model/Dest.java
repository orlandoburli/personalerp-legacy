package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Dest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Número do CPF do destinatário
	 */
	private String CPF;
	/**
	 * Número do CNPJ do destinatário
	 */
	private String CNPJ;
	/**
	 * Razão Social ou Nome do destinatário
	 * De 2 a 60 Caracteres.
	 */
	private String xNome;
	/**
	 * Nome fantasia
	 * De 1 a 60 Caracteres
	 */
	private String xFant;
	/**
	 * Endereço do Destinatário. 
	 */
	private EnderDest enderDest;
	/**
	 * Inscrição Estadual
	 */
	private String IE;
	
	public Dest() {
		super();
		this.enderDest = new EnderDest();
	}
	
	public String getCPF() {
		return CPF;
	}
	/**
	 * Número do CPF do destinatário
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	/**
	 * Número do CNPJ do destinatário
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getxNome() {
		return xNome;
	}
	/**
	 * Razão Social ou Nome do destinatário
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
	 * Endereço do Destinatário. 
	 */
	public EnderDest getEnderDest() {
		return enderDest;
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
}
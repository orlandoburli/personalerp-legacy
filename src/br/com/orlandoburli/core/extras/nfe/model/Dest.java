package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Dest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * N�mero do CPF do destinat�rio
	 */
	private String CPF;
	/**
	 * N�mero do CNPJ do destinat�rio
	 */
	private String CNPJ;
	/**
	 * Raz�o Social ou Nome do destinat�rio
	 * De 2 a 60 Caracteres.
	 */
	private String xNome;
	/**
	 * Nome fantasia
	 * De 1 a 60 Caracteres
	 */
	private String xFant;
	/**
	 * Endere�o do Destinat�rio. 
	 */
	private EnderDest enderDest;
	/**
	 * Inscri��o Estadual
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
	 * N�mero do CPF do destinat�rio
	 */
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getCNPJ() {
		return CNPJ;
	}
	/**
	 * N�mero do CNPJ do destinat�rio
	 */
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getxNome() {
		return xNome;
	}
	/**
	 * Raz�o Social ou Nome do destinat�rio
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
	 * Endere�o do Destinat�rio. 
	 */
	public EnderDest getEnderDest() {
		return enderDest;
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
}
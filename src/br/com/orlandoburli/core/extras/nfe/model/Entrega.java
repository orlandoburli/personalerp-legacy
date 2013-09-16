package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class Entrega implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * N�mero do CNPJ da entrega
	 */
	private String CNPJ;
	/**
	 * N�mero do CPF da entrega
	 */
	private String CPF;
	/**
	 * Logradouro da entrega
	 */
	private String xLgr;
	/**
	 * Numero da entrega
	 */
	private String nro;
	/**
	 * Complemento da entrega
	 */
	private String xCpl;
	/**
	 * Bairro da entrega
	 */
	private String xBairro;
	/**
	 * Codigo da cidade de entrega
	 */
	private String cMun;
	/**
	 * Nome da cidade de entrega
	 */
	private String xMun;
	/**
	 * Sigla da UF de entrega
	 */
	private String UF;
	
	
	public String getCNPJ() {
		return CNPJ;
	}
	public void setCNPJ(String cNPJ) {
		CNPJ = cNPJ;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}
	public String getxLgr() {
		return xLgr;
	}
	public void setxLgr(String xLgr) {
		this.xLgr = xLgr;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	public String getxCpl() {
		return xCpl;
	}
	public void setxCpl(String xCpl) {
		this.xCpl = xCpl;
	}
	public String getxBairro() {
		return xBairro;
	}
	public void setxBairro(String xBairro) {
		this.xBairro = xBairro;
	}
	public String getcMun() {
		return cMun;
	}
	public void setcMun(String cMun) {
		this.cMun = cMun;
	}
	public String getxMun() {
		return xMun;
	}
	public void setxMun(String xMun) {
		this.xMun = xMun;
	}
	public String getUF() {
		return UF;
	}
	public void setUF(String uF) {
		UF = uF;
	}
}
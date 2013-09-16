package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class EnderDest implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Logradouro
	 */
	private String xLgr;
	/**
	 * Número
	 */
	private String nro;
	/**
	 * Complemento
	 */
	private String xCpl;
	/**
	 * Bairro
	 */
	private String xBairro;
	/**
	 * Código do município (utilizar a tabela do IBGE), 
	 * informar 9999999 para operações com o exterior.
	 */
	private String cMun;
	/**
	 * Nome do município, informar EXTERIOR para operações com o exterior.
	 */
	private String xMun;
	/**
	 * Sigla da UF
	 */
	private String UF;
	/**
	 * CEP
	 */
	private String CEP;
	/**
	 * Código do país
	 * Único valor aceito: 1058;
	 */
	private String cPais;
	/**
	 * Nome do país
	 * Valores aceitos: Brasil e BRASIL
	 */
	private String xPais;
	/**
	 * Preencher com Código DDD + número do telefone (v.2.0)
	 */
	private String fone;
	
	public EnderDest() {
		super();
		this.xPais = "BRASIL";
		this.cPais = "1058";
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
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
	public String getcPais() {
		if (cPais == null) {
			cPais = "1058";
		}
		return cPais;
	}
	public String getxPais() {
		if (xPais == null) {
			xPais = "BRASIL";
		}
		return xPais;
	}
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
}
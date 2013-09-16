package br.com.orlandoburli.core.extras.nfe.model.cobranca;

import java.io.Serializable;
import java.math.BigDecimal;

public class Dup implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Numero da duplicata
	 */
	private String nDup;
	/**
	 * Data do vencimento
	 */
	private String dVenc;
	/**
	 * Valor da duplicata
	 */
	private BigDecimal vDup;
	
	public String getnDup() {
		return nDup;
	}
	public void setnDup(String nDup) {
		this.nDup = nDup;
	}
	public String getdVenc() {
		return dVenc;
	}
	public void setdVenc(String dVenc) {
		this.dVenc = dVenc;
	}
	public BigDecimal getvDup() {
		return vDup;
	}
	public void setvDup(BigDecimal vDup) {
		if (vDup != null) {
			vDup = vDup.setScale(2, BigDecimal.ROUND_CEILING);
		}
		this.vDup = vDup;
	}
}

package br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms;

import java.io.Serializable;

import br.com.orlandoburli.core.extras.nfe.interfaces.XmlAtributte;

/**
 * Classe que trata dos impostos de ICMS 
 * TODO Ainda falta varios tipos de ICMS...
 * Passei pra frente so pra nao desmotivar!!!
 */
public class Icms implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Tipo de tributacao 00 - Tributada totalmente
	 */
	private Icms00 ICMS00;
	/**
	 * Tipo de Tributação do ICMS 10 - <br/>
	 * Tributada e com cobrança do ICMS por Substituição Tributária<br/>
	 */
	private Icms10 ICMS10;
	/**
	 * Classe do tipo Tributação do ICMS 20 - <br/>
	 * Com redução de base de cálculo<br/>
	 */
	private Icms20 ICMS20;
	/**
	 * Classe do tipo Tributação do ICMS 30 - <br/>
	 * Isenta ou não tributada e com cobrança do ICMS por substituição tributária
	 */
	private Icms30 ICMS30;
	
	/**
	 * Classe do tipo Tributação do ICMS 40 - <br/>
	 * Isenta
	 */
	@XmlAtributte("ICMS40")
	private IcmsXX ICMS40;
	/**
	 * Classe do tipo Tributação do ICMS 41 - <br/>
	 * Não tributada
	 */
	@XmlAtributte("ICMS40")
	private IcmsXX ICMS41;
	/**
	 * Classe do tipo Tributação do ICMS 50 - <br/>
	 * Suspensão
	 */
	@XmlAtributte("ICMS40")
	private IcmsXX ICMS50;
	
	/**
	 * Classe do tipo Tributação do ICMS SN 102 - <br/>
	 * Simples Nacional
	 */
	private IcmsSn102 ICMSSN102;
	
	/////////////
	
	/**
	 * Instancia o devido tipo de ICMS de acordo com o CST - ICMS
	 */
	public void setCSTICMS(String CstIcms) {
		if (CstIcms != null) {
			if (CstIcms.equals("00")) {
				this.ICMS00 = new Icms00();
			} else if (CstIcms.equals("10")) {
				this.ICMS10 = new Icms10();
			} else if (CstIcms.equals("20")) {
				this.ICMS20 = new Icms20();
			} else if (CstIcms.equals("30")) {
				this.ICMS30 = new Icms30();
			} else if (CstIcms.equals("40")) {
				this.ICMS40 = new IcmsXX();
				this.ICMS40.setCST("40");
			} else if (CstIcms.equals("41")) {
				this.ICMS41 = new IcmsXX();
				this.ICMS41.setCST("41");
			} else if (CstIcms.equals("50")) {
				this.ICMS50 = new IcmsXX();
				this.ICMS50.setCST("50");
			} else if (CstIcms.equals("102")) {
				this.ICMSSN102 = new IcmsSn102();
				this.ICMSSN102.setCSOSN("102");
			} else if (CstIcms.equals("103")) {
				this.ICMSSN102 = new IcmsSn102();
				this.ICMSSN102.setCSOSN("103");
			} else if (CstIcms.equals("300")) {
				this.ICMSSN102 = new IcmsSn102();
				this.ICMSSN102.setCSOSN("300");
			} else if (CstIcms.equals("400")) {
				this.ICMSSN102 = new IcmsSn102();
				this.ICMSSN102.setCSOSN("400");
			}
		}
	}

	/**
	 * Retorna o tipo de tributacao 00 - Tributada totalmente;<br/>
	 */
	public Icms00 getICMS00() {
		return ICMS00;
	}
	/**
	 * Retorna o tipo de Tributação do ICMS 10 - <br/>
	 * Tributada e com cobrança do ICMS por Substituição Tributária<br/>
	 */
	public Icms10 getICMS10() {
		return ICMS10;
	}
	/**
	 * Retorna o tipo Tributação do ICMS 20 - <br/>
	 * Com redução de base de cálculo<br/>
	 */
	public Icms20 getICMS20() {
		return ICMS20;
	}
	/**
	 * Retorna o tipo Tributação do ICMS 30 - <br/>
	 * Isenta ou não tributada e com cobrança do ICMS por substituição tributária<br/>
	 */
	public Icms30 getICMS30() {
		return ICMS30;
	}
	/**
	 * Retorna o tipo Tributação do ICMS 40 - <br/>
	 * Isenta<br/>
	 */
	public IcmsXX getICMS40() {
		if (this.ICMS40 != null) {
			this.ICMS40.setCST("40");
		}
		return ICMS40;
	}
	/**
	 * Retorna o tipo Tributação do ICMS 41 - <br/>
	 * Não tributada<br/>
	 */
	public IcmsXX getICMS41() {
		if (this.ICMS41 != null) {
			this.ICMS41.setCST("41");
		}
		return ICMS41;
	}
	/**
	 * Classe do tipo Tributação do ICMS 50 - <br/>
	 * Suspensão<br/>
	 */
	public IcmsXX getICMS50() {
		if (this.ICMS50 != null) {
			this.ICMS50.setCST("50");
		}
		return ICMS50;
	}
	
	/**
	 * Classe do tipo Tributacao do ICMS SN 102 - <br/>
	 * Simples Nacional
	 * @return
	 */
	public IcmsSn102 getICMSSN102() {
		return this.ICMSSN102;
	}
}
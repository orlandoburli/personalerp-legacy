package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;

/**
 * Classe que trata dos impostos de PIS
 */
public class Pis implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Grupo de PIS tributado pela alíquota
	 */
	private PisAliq PISAliq;
	/**
	 * Grupo de PIS tributado pela quantidade
	 */
	private PisQtde PISQtde;
	/**
	 * Grupo de PIS Não tributado
	 */
	private PisNT PISNT;
	/**
	 * Grupo de PIS Outros
	 */
	private PisOutr PISOutr;
	
	public void setCSTPIS(String CstPis) {
		if (CstPis != null) {
			if (CstPis.equals("01")) {
				this.PISAliq = new PisAliq();
				this.PISAliq.setCST("01");
			} else if (CstPis.equals("02")) {
				this.PISAliq = new PisAliq();
				this.PISAliq.setCST("01");
			} else if (CstPis.equals("03")) {
				this.PISQtde = new PisQtde();
			} else if (CstPis.equals("04")) {
				this.PISNT = new PisNT();
				this.PISNT.setCST("04");
			} else if (CstPis.equals("06")) {
				this.PISNT = new PisNT();
				this.PISNT.setCST("06");
			} else if (CstPis.equals("07")) {
				this.PISNT = new PisNT();
				this.PISNT.setCST("07");
			} else if (CstPis.equals("08")) {
				this.PISNT = new PisNT();
				this.PISNT.setCST("08");
			} else if (CstPis.equals("09")) {
				this.PISNT = new PisNT();
				this.PISNT.setCST("09");
			} else if (CstPis.equals("99")) {
				this.PISOutr = new PisOutr();
			} else if (CstPis.equals("49")) {
				this.PISOutr = new PisOutr();
			}
		}
	}

	/**
	 * Grupo de PIS tributado pela alíquota<br/>
	 */
	public PisAliq getPISAliq() {
		return PISAliq;
	}
	/**
	 * Grupo de PIS tributado pela quantidade<br/>
	 */
	public PisQtde getPISQtde() {
		return PISQtde;
	}
	/**
	 * Grupo de PIS Não tributado<br/>
	 */
	public PisNT getPISNT() {
		return PISNT;
	}
	/**
	 * Grupo de PIS Outros<br/>
	 */
	public PisOutr getPISOutr() {
		return PISOutr;
	}
}
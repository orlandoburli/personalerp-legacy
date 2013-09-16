package br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins;

import java.io.Serializable;

public class Cofins implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Grupo de COFINS tributado pela alíquota
	 */
	private CofinsAliq COFINSAliq;
	/**
	 * Grupo de COFINS tributado por Qtde
	 */
	private CofinsQtde COFINSQtde;
	/**
	 * Grupo de COFINS não tributado
	 */
	private CofinsNT COFINSNT;
	/**
	 * Grupo de COFINS Outras Operações
	 */
	private CofinsOutr COFINSOutr;
	
	public void setCSTCOFINS(String CstCofins) {
		if (CstCofins != null) {
			if (CstCofins.equals("01")) {
				this.COFINSAliq = new CofinsAliq();
				this.COFINSAliq.setCST("01");
			} else if (CstCofins.equals("02")) {
				this.COFINSAliq = new CofinsAliq();
				this.COFINSAliq.setCST("02");
			} else if (CstCofins.equals("03")) {
				this.COFINSQtde = new CofinsQtde();
			} else if (CstCofins.equals("04")) {
				this.COFINSNT = new CofinsNT();
				this.COFINSNT.setCST("04");
			} else if (CstCofins.equals("06")) {
				this.COFINSNT = new CofinsNT();
				this.COFINSNT.setCST("06");
			} else if (CstCofins.equals("07")) {
				this.COFINSNT = new CofinsNT();
				this.COFINSNT.setCST("07");
			} else if (CstCofins.equals("08")) {
				this.COFINSNT = new CofinsNT();
				this.COFINSNT.setCST("08");
			} else if (CstCofins.equals("09")) {
				this.COFINSNT = new CofinsNT();
				this.COFINSNT.setCST("09");
			} else if (CstCofins.equals("49")) {
				this.COFINSOutr = new CofinsOutr();
			} else if (CstCofins.equals("99")) {
				this.COFINSOutr = new CofinsOutr();
			}
		}
	}
	
	/**
	 * Grupo de COFINS tributado pela alíquota<br/>
	 */
	public CofinsAliq getCOFINSAliq() {
		return COFINSAliq;
	}
	/**
	 * Grupo de COFINS tributado por Qtde<br/>
	 */
	public CofinsQtde getCOFINSQtde() {
		return COFINSQtde;
	}
	/**
	 * Grupo de COFINS não tributado<br/>
	 */
	public CofinsNT getCOFINSNT() {
		return COFINSNT;
	}
	/**
	 * Grupo de COFINS Outras Operações<br/>
	 */
	public CofinsOutr getCOFINSOutr() {
		return COFINSOutr;
	}
}

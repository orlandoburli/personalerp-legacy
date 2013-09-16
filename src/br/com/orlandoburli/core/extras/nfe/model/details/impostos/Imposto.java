package br.com.orlandoburli.core.extras.nfe.model.details.impostos;

import java.io.Serializable;

import br.com.orlandoburli.core.extras.nfe.model.details.impostos.cofins.Cofins;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.icms.Icms;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi.Ipi;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis.Pis;

/**
 * Classe que trata das tributações do Produto. 
 */
public class Imposto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Dados do ICMS Normal e ST
	 */
	private Icms ICMS;
	/**
	 * Dados do IPI
	 */
	private Ipi IPI;
	/**
	 * Dados do PIS
	 */
	private Pis PIS;
	/**
	 * Dados do COFINS
	 */
	private Cofins COFINS;
	
	public static Imposto getInstance() {
		return new Imposto();
	}
	
	private Imposto() {
		this.ICMS = new Icms();
		this.PIS = new Pis();
		this.COFINS = new Cofins();
	}
	
	/**
	 * Dados do ICMS Normal e ST
	 */
	public Icms getICMS() {
		return ICMS;
	}
	/**
	 * Dados do PIS
	 */
	public Pis getPIS() {
		return PIS;
	}
	/**
	 * Dados do COFINS
	 */
	public Cofins getCOFINS() {
		return COFINS;
	}

	public Ipi getIPI() {
		return IPI;
	}

	public void setIPI(Ipi iPI) {
		IPI = iPI;
	}
}
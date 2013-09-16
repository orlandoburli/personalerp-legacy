package br.com.orlandoburli.core.extras.nfe.model.totais;

import java.io.Serializable;

/**
 * Classe do Grupo de Valores Totais da NF-e
 */
public class Total implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Grupo de Valores Totais referentes ao ICMS
	 */
	private IcmsTot ICMSTot;
	
	/**
	 * Grupo de Valores Totais referentes ao ICMS
	 */
	public IcmsTot getICMSTot() {
		if (this.ICMSTot == null) {
			this.ICMSTot = new IcmsTot();
		}
		return ICMSTot;
	}
}
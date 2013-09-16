package br.com.orlandoburli.core.extras.nfe.model.details.impostos.ipi;

import java.io.Serializable;

public class IpiNT implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * C�digo da situa��o tribut�ria do IPI
	 */
	private String CST;

	public String getCST() {
		return CST;
	}

	public void setCST(String cST) {
		CST = cST;
	}
}

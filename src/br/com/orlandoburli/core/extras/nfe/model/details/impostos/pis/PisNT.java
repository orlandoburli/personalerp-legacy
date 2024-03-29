package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;

/**
 * Classe do Grupo de PIS n�o tributado
 */
public class PisNT implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>04 - Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero));</li>
	 * <li>06 - Opera��o Tribut�vel (al�quota zero);</li>
	 * <li>07 - Opera��o Isenta da Contribui��o;</li>
	 * <li>08 - Opera��o Sem Incid�ncia da Contribui��o;</li>
	 * <li>09 - Opera��o com Suspens�o da Contribui��o;</li>
	 * </ul>
	 */
	private String CST;

	/**
	 * C�digo da Situa��o Tribut�ria
	 * <ul>
	 * <li>04 - Opera��o Tribut�vel (tributa��o monof�sica (al�quota zero));</li>
	 * <li>06 - Opera��o Tribut�vel (al�quota zero);</li>
	 * <li>07 - Opera��o Isenta da Contribui��o;</li>
	 * <li>08 - Opera��o Sem Incid�ncia da Contribui��o;</li>
	 * <li>09 - Opera��o com Suspens�o da Contribui��o;</li>
	 * </ul>
	 */
	public void setCST(String cST) {
		CST = cST;
	}

	public String getCST() {
		return CST;
	}
}

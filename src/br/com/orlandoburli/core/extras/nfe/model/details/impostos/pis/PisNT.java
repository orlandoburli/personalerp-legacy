package br.com.orlandoburli.core.extras.nfe.model.details.impostos.pis;

import java.io.Serializable;

/**
 * Classe do Grupo de PIS não tributado
 */
public class PisNT implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Código da Situação Tributária
	 * <ul>
	 * <li>04 - Operação Tributável (tributação monofásica (alíquota zero));</li>
	 * <li>06 - Operação Tributável (alíquota zero);</li>
	 * <li>07 - Operação Isenta da Contribuição;</li>
	 * <li>08 - Operação Sem Incidência da Contribuição;</li>
	 * <li>09 - Operação com Suspensão da Contribuição;</li>
	 * </ul>
	 */
	private String CST;

	/**
	 * Código da Situação Tributária
	 * <ul>
	 * <li>04 - Operação Tributável (tributação monofásica (alíquota zero));</li>
	 * <li>06 - Operação Tributável (alíquota zero);</li>
	 * <li>07 - Operação Isenta da Contribuição;</li>
	 * <li>08 - Operação Sem Incidência da Contribuição;</li>
	 * <li>09 - Operação com Suspensão da Contribuição;</li>
	 * </ul>
	 */
	public void setCST(String cST) {
		CST = cST;
	}

	public String getCST() {
		return CST;
	}
}

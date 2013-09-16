package br.com.orlandoburli.core.extras.nfe.model.details;

import java.io.Serializable;

import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlIgnore;
import br.com.orlandoburli.core.extras.nfe.interfaces.IXmlNamespace;
import br.com.orlandoburli.core.extras.nfe.model.NFe;
import br.com.orlandoburli.core.extras.nfe.model.details.impostos.Imposto;

public class Det implements Serializable, IXmlNamespace {

	private static final long serialVersionUID = 1L;
	/**
	 * Dados dos produtos e servi�os da NF-e
	 */
	private Prod prod;
	/**
	 * Tributos incidentes nos produtos ou servi�os da NF-e
	 */
	private Imposto imposto;
	
	/**
	 * N�mero do item do NF
	 */
	@IXmlIgnore
	private String nItem;
	
	public static Det getInstance(NFe nfe) {
		Det det = new Det(nfe);
		return det;
	}
	
	private Det(NFe nfe) {
		super();
		this.prod = new Prod();
		this.imposto = Imposto.getInstance();
		nfe.getInfNfe().getDet().add(this);
		this.nItem = Integer.toString(nfe.getInfNfe().getDet().indexOf(this) + 1);
	}

	@Override
	public String getNamespaceDeclaration() {
		return " nItem=\"" + getnItem() + "\"";
	}
	/**
	 * Retorna Dados dos produtos e servi�os da NF-e
	 * @return Dados dos produtos
	 */
	public Prod getProd() {
		return prod;
	}
	/**
	 * N�mero do item do NF
	 */
	public void setnItem(String nItem) {
		this.nItem = nItem;
	}
	public String getnItem() {
		return nItem;
	}
	/**
	 * Tributos incidentes nos produtos ou servi�os da NF-e
	 */
	public Imposto getImposto() {
		return imposto;
	}	
}
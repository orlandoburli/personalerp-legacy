package br.com.orlandoburli.core.extras.nfe.model.transporte;

import java.io.Serializable;

/**
 * Classe do Grupo de Informa��es do Transporte da NF-e
 */
public class Transporte implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Modalidade de Frete
	 * <ul>
	 * <li>0- Por conta do emitente;</li>
	 * <li>1- Por conta do destinat�rio/remetente;</li>
	 * <li>2- Por conta de terceiros;</li>
	 * <li>9- Sem frete. (V2.0)</li>
	 * </ul>
	 */
	private String modFrete;
	/**
	 * Dados da transportadora
	 */
	private Transportadora transporta;
	
	/**
	 * Modalidade de Frete
	 * <ul>
	 * <li>0- Por conta do emitente;</li>
	 * <li>1- Por conta do destinat�rio/remetente;</li>
	 * <li>2- Por conta de terceiros;</li>
	 * <li>9- Sem frete. (V2.0)</li>
	 * </ul>
	 */
	public void setModFrete(String modFrete) {
		this.modFrete = modFrete;
	}
	public String getModFrete() {
		return modFrete;
	}
	/**
	 * Dados da transportadora<br/>
	 * <b>ATEN��O.</b> Se n�o usar a transportadora, n�o chame o GET.<br/> 
	 * Ele ir� instanciar a classe e criar o n� xml indevidamente.
	 */
	public Transportadora getTransporta() {
		if (this.transporta == null) {
			this.transporta = new Transportadora();
		}
		return transporta;
	}
}
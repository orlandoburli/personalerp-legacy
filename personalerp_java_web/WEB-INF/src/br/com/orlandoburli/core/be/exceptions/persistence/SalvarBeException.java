package br.com.orlandoburli.core.be.exceptions.persistence;

import br.com.orlandoburli.core.be.exceptions.BaseBeException;

public class SalvarBeException extends BaseBeException {

	private static final long serialVersionUID = 1L;
	
	private String campo;
	
	public SalvarBeException(String message) {
		super(message);
	}
	
	public SalvarBeException(String message, String campo) {
		super(message);
		this.setCampo(campo);
	}

	public String getCampo() {
		return campo;
	}

	protected void setCampo(String campo) {
		this.campo = campo;
	}
}
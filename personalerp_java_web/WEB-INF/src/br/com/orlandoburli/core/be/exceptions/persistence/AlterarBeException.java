package br.com.orlandoburli.core.be.exceptions.persistence;

public class AlterarBeException extends SalvarBeException {

	private static final long serialVersionUID = 1L;
	
	public AlterarBeException(String message) {
		super(message);
	}
	
	public AlterarBeException(String message, String campo) {
		super(message);
		this.setCampo(campo);
	}
}
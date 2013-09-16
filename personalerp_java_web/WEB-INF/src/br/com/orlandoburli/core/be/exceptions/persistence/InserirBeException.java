package br.com.orlandoburli.core.be.exceptions.persistence;

public class InserirBeException extends SalvarBeException {
	
	private static final long serialVersionUID = 1L;
	
	public InserirBeException(String message) {
		super(message);
	}
	
	public InserirBeException(String message, String campo) {
		super(message);
		this.setCampo(campo);
	}
}
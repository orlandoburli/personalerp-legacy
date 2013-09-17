package br.com.orlandoburli.core.be.exceptions.persistence;

import br.com.orlandoburli.core.be.exceptions.BaseBeException;

public class DesfazerBeException extends BaseBeException {

	private static final long serialVersionUID = 1L;

	public DesfazerBeException(String message) {
		super(message);
	}
	
	public DesfazerBeException() {
		super("Erro ao desfazer alterações. Entre em contato com o administrador do sistema.");
	}
}

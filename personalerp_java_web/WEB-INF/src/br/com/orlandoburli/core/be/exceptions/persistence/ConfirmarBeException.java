package br.com.orlandoburli.core.be.exceptions.persistence;

import br.com.orlandoburli.core.be.exceptions.BaseBeException;

public class ConfirmarBeException extends BaseBeException {

	private static final long serialVersionUID = 1L;

	public ConfirmarBeException(String message) {
		super(message);
	}
	
	public ConfirmarBeException() {
		super("Erro ao confirmar dados. Entre em contato com o administrador do sistema.");
	}
}
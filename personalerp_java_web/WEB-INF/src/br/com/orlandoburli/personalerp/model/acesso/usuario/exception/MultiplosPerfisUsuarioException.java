package br.com.orlandoburli.personalerp.model.acesso.usuario.exception;

public class MultiplosPerfisUsuarioException extends Exception {

	private static final long serialVersionUID = 1L;

	public MultiplosPerfisUsuarioException() {
		super("Usuário com múltiplos perfis");
	}
}

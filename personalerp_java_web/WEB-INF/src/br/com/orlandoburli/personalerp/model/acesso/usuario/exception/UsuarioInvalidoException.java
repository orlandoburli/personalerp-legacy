package br.com.orlandoburli.personalerp.model.acesso.usuario.exception;

public class UsuarioInvalidoException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioInvalidoException() {
		super("Usuário / senha inválido!");
	}

	public UsuarioInvalidoException(String mensagem) {
		super(mensagem);
	}
}

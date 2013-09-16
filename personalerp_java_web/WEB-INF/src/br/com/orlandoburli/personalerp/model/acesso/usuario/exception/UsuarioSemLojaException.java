package br.com.orlandoburli.personalerp.model.acesso.usuario.exception;

public class UsuarioSemLojaException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioSemLojaException() {
		super("Usuário não tem permissão em nenhuma loja!");
	}
}

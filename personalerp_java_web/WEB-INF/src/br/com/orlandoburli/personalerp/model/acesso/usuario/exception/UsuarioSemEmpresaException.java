package br.com.orlandoburli.personalerp.model.acesso.usuario.exception;

public class UsuarioSemEmpresaException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioSemEmpresaException() {
		super("Usuário não tem permissão em nenhuma empresa!");
	}
}

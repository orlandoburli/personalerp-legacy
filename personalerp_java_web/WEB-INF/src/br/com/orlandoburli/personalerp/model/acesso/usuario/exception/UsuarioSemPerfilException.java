package br.com.orlandoburli.personalerp.model.acesso.usuario.exception;

public class UsuarioSemPerfilException extends Exception {

	private static final long serialVersionUID = 1L;

	public UsuarioSemPerfilException() {
		super("Usuário sem perfil!");
	}	
}
package br.com.orlandoburli.core.utils.txt.exceptions;

public class CampoNaoEncontradoException extends Exception{

	private static final long serialVersionUID = 1L;

	public CampoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}

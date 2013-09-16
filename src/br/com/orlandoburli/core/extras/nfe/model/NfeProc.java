package br.com.orlandoburli.core.extras.nfe.model;

import java.io.Serializable;

public class NfeProc implements Serializable{

	private static final long serialVersionUID = 1L;

	private NFe nfe;
	private String versao;

	public NfeProc() {
		this.nfe = NFe.getInstance();
	}
	
	public NFe getNFe() {
		return nfe;
	}

	public void setNFe(NFe nFe) {
		nfe = nFe;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}
}

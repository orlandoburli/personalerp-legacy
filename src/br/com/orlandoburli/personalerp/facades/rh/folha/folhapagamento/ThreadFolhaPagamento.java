package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento;

import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento.processamento.ProcessamentoFolhaAction;

public class ThreadFolhaPagamento extends Thread {

	private FolhaPagamentoVO folha;
	
	public void run() {
		ProcessamentoFolhaAction processamento = new ProcessamentoFolhaAction();
		processamento.setFolha(folha);
		processamento.execute();
	}

	public void setFolha(FolhaPagamentoVO folha) {
		this.folha = folha;
	}

	public FolhaPagamentoVO getFolha() {
		return folha;
	}
}
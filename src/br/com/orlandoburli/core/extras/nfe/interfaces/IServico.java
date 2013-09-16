package br.com.orlandoburli.core.extras.nfe.interfaces;

import br.com.orlandoburli.core.vo.sistema.LojaVO;

public interface IServico {
	enum Ambiente {
		Producao,
		Homologacao
	}
	
	public String getServiceAddress(Ambiente ambiente);
	
	public boolean execute(String dados, Ambiente ambiente, LojaVO loja);
}
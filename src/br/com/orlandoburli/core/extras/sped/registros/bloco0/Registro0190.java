package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class Registro0190 extends RegistroSped implements Bloco0 {

	private String unidade;
	private String descricao;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0190";
	}

	@CampoSped(name = "UNID", order = 2)
	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@CampoSped(name = "DESCR", order = 3)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}

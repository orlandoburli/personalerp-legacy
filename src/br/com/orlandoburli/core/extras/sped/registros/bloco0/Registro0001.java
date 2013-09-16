package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;

public class Registro0001 extends RegistroSped implements Bloco0 {

	private String indicadorMovimento;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0001";
	}

	@CampoSped(name = "IND_MOV", order = 2)
	public String getIndicadorMovimento() {
		return indicadorMovimento;
	}

	public void setIndicadorMovimento(String indicadorMovimento) {
		this.indicadorMovimento = indicadorMovimento;
	}
}
package br.com.orlandoburli.core.extras.sped.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class Registro0990 extends RegistroSped implements Bloco0 {

	private Integer linhas;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "0990";
	}

	@CampoSped(name = "QTD_LIN_0", order = 2)
	public Integer getLinhas() {
		return linhas;
	}

	public void setLinhas(Integer linhas) {
		this.linhas = linhas;
	}
}

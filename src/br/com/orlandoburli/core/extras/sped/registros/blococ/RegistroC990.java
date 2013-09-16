package br.com.orlandoburli.core.extras.sped.registros.blococ;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC990 extends RegistroSped implements BlocoC {

	private Integer registros;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C990";
	}

	@CampoSped(name = "QTD_LIN_C", order = 2)
	public Integer getRegistros() {
		return registros;
	}

	public void setRegistros(Integer registros) {
		this.registros = registros;
	}
}

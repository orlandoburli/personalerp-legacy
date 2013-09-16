package br.com.orlandoburli.core.extras.sped.registros.blococ;

import br.com.orlandoburli.core.extras.sped.interfaces.CampoSped;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;

public class RegistroC0001 extends RegistroSped implements BlocoC{

	private Integer indicadorMovimento;

	@Override
	@CampoSped(name = "REG", order = 1)
	public String getRegistro() {
		return "C001";
	}

	@CampoSped(name = "IND_MOV", order = 2)
	public Integer getIndicadorMovimento() {
		return indicadorMovimento;
	}

	public void setIndicadorMovimento(Integer indicadorMovimento) {
		this.indicadorMovimento = indicadorMovimento;
	}
}

package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Bloco0;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0990;

public class Registro0990Factory {

	private static Registro0990Factory factory;

	private Registro0990Factory() {

	}

	public static Registro0990Factory getFactory() {
		if (factory == null) {
			factory = new Registro0990Factory();
		}
		return factory;
	}

	public void buildRegistro0990(ArquivoSpedFiscal arquivo) {
		Registro0990 reg0990 = new Registro0990();

		arquivo.addRegistro(reg0990);
		
		// Conta as linhas do bloco 0
		int contador = 0;
		for (RegistroSped item : arquivo.getRegistros()) {
			if (item instanceof Bloco0) {
				contador++;
			}
		}

		reg0990.setLinhas(contador);
	}
}

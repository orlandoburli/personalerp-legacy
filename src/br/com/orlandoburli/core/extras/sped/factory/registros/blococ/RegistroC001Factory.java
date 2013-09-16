package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC0001;

public class RegistroC001Factory {

	private static RegistroC001Factory factory;

	private RegistroC001Factory() {

	}

	public static RegistroC001Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC001Factory();
		}
		return factory;
	}

	public void buildRegistroC001(ArquivoSpedFiscal arquivo) {
		RegistroC0001 regc001 = new RegistroC0001();

		regc001.setIndicadorMovimento(1); // TODO verificar depois da geracao dos outros blocos, se tem realmente os registros

		arquivo.addRegistro(regc001);
	}
}

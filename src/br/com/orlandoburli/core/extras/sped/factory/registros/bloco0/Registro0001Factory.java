package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0001;

public class Registro0001Factory {

	private static Registro0001Factory factory;

	private Registro0001Factory() {

	}

	public static Registro0001Factory getFactory() {
		if (factory == null) {
			factory = new Registro0001Factory();
		}
		return factory;
	}
	
	public void buildRegistro0001(ArquivoSpedFiscal arquivo, GenericDAO dao) {
		Registro0001 reg0001 = new Registro0001();
		reg0001.setIndicadorMovimento("0"); // Depois posso ter de ver isso...

		arquivo.addRegistro(reg0001);
	}
}

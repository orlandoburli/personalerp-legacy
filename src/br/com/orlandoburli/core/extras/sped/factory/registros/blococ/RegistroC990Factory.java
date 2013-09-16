package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.extras.sped.registros.blococ.BlocoC;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC100;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC990;

public class RegistroC990Factory {

	private static RegistroC990Factory factory;
	
	private RegistroC990Factory() { }
	
	public static RegistroC990Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC990Factory();
		}
		return factory;
	}
	
	public void buildRegistroC990(ArquivoSpedFiscal arquivo) {
		RegistroC990 regc990 = new RegistroC990();
		
		arquivo.addRegistro(regc990);
		
		int contador = 0;
		for (RegistroSped registro : arquivo.getRegistros()) {
			if (registro instanceof BlocoC) {
				contador ++;
				if (registro instanceof RegistroC100) {
					contador += ((RegistroC100) registro).getItens().size();
				}
			}
		}
		
		regc990.setRegistros(contador);
	}
}

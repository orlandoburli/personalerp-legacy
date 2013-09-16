package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.RegistroSped;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC100;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC170;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC190;

public class RegistroC190Factory {

	private static RegistroC190Factory factory;

	private RegistroC190Factory() {

	}

	public static RegistroC190Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC190Factory();
		}
		return factory;
	}

	public void buildRegistroC190(ArquivoSpedFiscal arquivo, GenericDAO dao) {
		List<RegistroC190> registrosC190 = new ArrayList<RegistroC190>();

		for (RegistroSped registro : arquivo.getRegistros()) {
			// Somente interessa o registro C170
			if (registro instanceof RegistroC100) {

				for (RegistroC170 regc170 : ((RegistroC100) registro).getItens()) {

					RegistroC190 regc190 = new RegistroC190();
					regc190.setCstIcms(regc170.getCstIcms());
					regc190.setCfop(regc170.getCfop());
					regc190.setAliqIcms(regc170.getAliquotaIcms());

					regc190.setValorOperacao(BigDecimal.ZERO);
					regc190.setValorBaseIcms(BigDecimal.ZERO);
					regc190.setValorIcms(BigDecimal.ZERO);
					regc190.setValorBaseIcmsSt(BigDecimal.ZERO);
					regc190.setValorIcmsSt(BigDecimal.ZERO);
					regc190.setValorReducaoBase(BigDecimal.ZERO);
					regc190.setValorIpi(BigDecimal.ZERO);
					regc190.setCodigoObservacao("");

					boolean found = false;

					// Busca na lista se ja existe a combinacao de CST_ICMS, CFOP e ALIQ_ICMS
					for (RegistroC190 i : registrosC190) {
						if (i.getCstIcms().equals(regc190.getCstIcms()) && i.getCfop().equals(regc190.getCfop()) && i.getAliqIcms().equals(regc190.getAliqIcms())) {
							regc190 = i;
							found = true;
							break;
						}
					}

					regc190.setValorOperacao(regc190.getValorOperacao().add(regc170.getValorTotalItem()));
					regc190.setValorBaseIcms(regc190.getValorBaseIcms().add(regc170.getValorBaseIcms()));
					regc190.setValorIcms(regc190.getValorIcms().add(regc170.getValorIcms()));
					
					if (regc170.getValorBaseIcmsSt() != null) {
						regc190.setValorBaseIcmsSt(regc190.getValorBaseIcmsSt().add(regc170.getValorBaseIcmsSt()));
					}
					
					if (regc170.getValorIcmsSt() != null) {
						regc190.setValorIcmsSt(regc190.getValorIcmsSt().add(regc170.getValorIcmsSt()));
					}
					
					if (!found) {
						registrosC190.add(regc190);
					}
				}
			}
		}

		arquivo.getRegistros().addAll(registrosC190);
	}
}

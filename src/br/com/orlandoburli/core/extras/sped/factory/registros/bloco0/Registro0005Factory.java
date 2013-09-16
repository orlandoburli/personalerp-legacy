package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0005;
import br.com.orlandoburli.core.utils.Utils;

public class Registro0005Factory {

	private static Registro0005Factory factory;
	
	private Registro0005Factory() {
		
	}
	
	public static Registro0005Factory getFactory() {
		if (factory == null) {
			factory = new Registro0005Factory();
		}
		return factory;
	}
	
	public void buildRegistro0005(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SpedException {
		Registro0005 reg0005 = new Registro0005();

		reg0005.setFantasia(arquivo.getLoja().getFantasiaEmpresa());

		String cepLoja = Utils.filtro(arquivo.getLoja().getCepLoja(), "1234567890");
		reg0005.setCep(Integer.parseInt(cepLoja));

		reg0005.setEndereco(arquivo.getLoja().getEnderecoLoja());
		reg0005.setComplemento("");
		reg0005.setBairro(arquivo.getLoja().getBairroLoja());

		String foneLoja = Utils.filtro(arquivo.getLoja().getFoneLoja(), "1234567890");
		reg0005.setFone(foneLoja);

		String faxLoja = Utils.filtro(arquivo.getLoja().getFaxLoja(), "1234567890");
		reg0005.setFax(faxLoja);

		reg0005.setEmail(""); // Nao tem no cadastro.

		// Validacoes
		if (reg0005.getFantasia() == null || reg0005.getFantasia().trim().equals("")) {
			throw new SpedException("Fantasia da Loja " + arquivo.getLoja().getNomeLoja() + " não está preenchido!");
		}

		if (reg0005.getCep() == null || reg0005.getCep().compareTo(0) <= 0) {
			throw new SpedException("Cep da Loja " + arquivo.getLoja().getNomeLoja() + " não está preenchido!");
		}

		if (reg0005.getCep().toString().length() < 7) {
			throw new SpedException("Cep da Loja " + arquivo.getLoja().getNomeLoja() + " não está preenchido corretamente!");
		}

		if (reg0005.getBairro() == null || reg0005.getBairro().trim().equals("")) {
			throw new SpedException("Bairro da Loja " + arquivo.getLoja().getNomeLoja() + " não está preenchido!");
		}

		arquivo.addRegistro(reg0005);
	}

}

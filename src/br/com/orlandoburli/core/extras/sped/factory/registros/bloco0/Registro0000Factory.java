package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import java.sql.SQLException;
import java.util.Calendar;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.base.CidadeDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0000;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.base.CidadeVO;
import br.com.orlandoburli.core.web.framework.validators.CpfCnpjValidator;

public class Registro0000Factory {

	private static Registro0000Factory factory;
	
	private Registro0000Factory() {
		
	}
	
	public static Registro0000Factory getFactory() {
		if (factory == null) {
			factory = new Registro0000Factory();
		}
		return factory;
	}
	
	public void buildRegistro0000(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException, SpedException {
		Registro0000 reg0000 = new Registro0000();
		reg0000.setCnpjEntidade(arquivo.getLoja().getCNPJLoja());

		// Busca Municipio
		CidadeDAO cidadeDao = new CidadeDAO();
		cidadeDao.mergeDAO(dao);

		CidadeVO cidade = new CidadeVO();
		cidade.setCodigoCidade(arquivo.getLoja().getCodigoCidadeLoja());
		cidade = cidadeDao.get(cidade);

		if (cidade == null) {
			throw new SpedException("Código da cidade da loja " + arquivo.getLoja().getNomeLoja() + " não preenchido!");
		} else if (cidade.getCodigoIbgeCidade() == null || cidade.getCodigoIbgeCidade().trim().equals("")) {
			throw new SpedException("Código IBGE da cidade " + cidade.getNomeCidade() + " não preenchido!");
		}

		// Tenta validar como numero
		try {
			reg0000.setCodigoMunicipio(Integer.parseInt(cidade.getCodigoIbgeEstado() + cidade.getCodigoIbgeCidade()));
		} catch (NumberFormatException e) {
			throw new SpedException("Código IBGE da cidade " + cidade.getNomeCidade() + " não preenchido corretamente, verifique!");
		}

		// O código do IBGE completo deve ter 7 dígitos
		if (reg0000.getCodigoMunicipio() == null || reg0000.getCodigoMunicipio().toString().length() != 7) {
			throw new SpedException("Código IBGE da cidade " + cidade.getNomeCidade() + " não preenchido corretamente, verifique!");
		}

		// Valida as datas informadas
		reg0000.setDataInicial(arquivo.getDataInicial());
		reg0000.setDataFinal(arquivo.getDataFinal());

		if (reg0000.getDataInicial() == null) {
			throw new SpedException("A data inicial do arquivo não foi informada!");
		}

		if (reg0000.getDataFinal() == null) {
			throw new SpedException("A data final do arquivo não foi informada!");
		}

		Calendar calDataInicial = Utils.dateToCalendar(reg0000.getDataInicial());
		Calendar calDataFinal = Utils.dateToCalendar(reg0000.getDataFinal());

		if (calDataInicial.compareTo(calDataFinal) >= 0) {
			throw new SpedException("A data inicial deve ser menor que a data final!");
		}

		// Validar CNPJ da loja
		CpfCnpjValidator cnpjValidator = new CpfCnpjValidator("CNPJLoja");
		cnpjValidator.setVo(arquivo.getLoja());

		if (!cnpjValidator.validate()) {
			throw new SpedException("O CNPJ " + arquivo.getLoja().getCNPJLoja() + " da loja " + arquivo.getLoja().getNomeLoja() + "não é valido!");
		}

		reg0000.setNomeEntidade(arquivo.getLoja().getRazaoSocialEmpresa());
		reg0000.setCnpjEntidade(arquivo.getLoja().getCNPJLoja());
		reg0000.setUf(cidade.getSiglaUFCidade());
		reg0000.setIe(arquivo.getLoja().getInscricaoEstadualLoja());

		reg0000.setSuframa(null); // Não tenho esse campo...
		reg0000.setIndicadorAtividade(1); // Por hora vai ficar fixo.
		reg0000.setPerfil("A"); // Por hora vai ficar fixo, depois parametrizo.

		reg0000.setVersao("007"); // Vai ficar fixo também, por hora, não sei
									// como versionar ainda. Provavelemente pelo
									// método.
		reg0000.setFinalidade("0"); // Por hora fixo.

		arquivo.addRegistro(reg0000);
	}
	
}

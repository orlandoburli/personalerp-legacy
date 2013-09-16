package br.com.orlandoburli.core.extras.sped.factory.registros.bloco0;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.registros.bloco0.Registro0190;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;

public class Registro0190Factory {

	private static Registro0190Factory factory;

	private Registro0190Factory() { }

	public static Registro0190Factory getFactory() {
		if (factory == null) {
			factory = new Registro0190Factory();
		}
		return factory;
	}

	public void buildRegistro0190(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException, SpedException {
		for (IValueObject i : arquivo.getListVo()) {
			if (i instanceof UnidadeVO) {

				UnidadeVO unidade = (UnidadeVO) i;

				if (unidade.getNomeUnidadeSingular() == null || unidade.getNomeUnidadeSingular().trim().equals("")) {
					throw new SpedException("A sigla " + unidade.getSiglaUnidade() + " não está com a descrição configurada!");
				}

				if (unidade.getSiglaUnidade().equalsIgnoreCase(unidade.getNomeUnidadeSingular())) {
					throw new SpedException("O nome da unidade " + unidade.getSiglaUnidade() + " não pode ser igual a sigla!");
				}

				Registro0190 reg0190 = new Registro0190();

				reg0190.setUnidade(unidade.getSiglaUnidade());
				reg0190.setDescricao(unidade.getNomeUnidadeSingular());

				arquivo.addRegistro(reg0190);
			}
		}
	}
}

package br.com.orlandoburli.core.extras.sped.factory.arquivo;

import java.sql.Date;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.exception.SpedException;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0000Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0001Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0005Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0150Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0190Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0200Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.bloco0.Registro0990Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC001Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC100Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC190Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC400Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC405Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC410Factory;
import br.com.orlandoburli.core.extras.sped.factory.registros.blococ.RegistroC990Factory;
import br.com.orlandoburli.core.vo.sistema.LojaVO;

public class ArquivoSpedFactory {

	private static ArquivoSpedFactory factory;

	private ArquivoSpedFactory() {
		super();
	}

	/**
	 * Retorna instancia da factory
	 * 
	 * @return Factory de ArquivoSped
	 */
	public static ArquivoSpedFactory getFactory() {
		if (factory == null) {
			factory = new ArquivoSpedFactory();
		}
		return factory;
	}

	public ArquivoSpedFiscal buildArquivoSped(LojaVO loja, Date dataInicial, Date dataFinal) throws SpedException {
		ArquivoSpedFiscal arquivo = new ArquivoSpedFiscal(loja, dataInicial, dataFinal);

		GenericDAO dao = new GenericDAO();
		
		try {

			// Bloco C
			RegistroC001Factory.getFactory().buildRegistroC001(arquivo); // Abertura do bloco C
			RegistroC100Factory.getFactory().buildRegistroC100(arquivo, dao); // Documentos Fiscais I - Mercadorias
			RegistroC190Factory.getFactory().buildRegistroC190(arquivo, dao); // Registro analítico do documento
			RegistroC400Factory.getFactory().buildRegistroC400(arquivo, dao); // Equipamentos ECF
			RegistroC405Factory.getFactory().buildRegistroC405(arquivo, dao); // Reducao Z
			RegistroC410Factory.getFactory().buildRegistroC410(arquivo, dao); // PIS e COFINS totalizados no dia
			RegistroC990Factory.getFactory().buildRegistroC990(arquivo);// Fechamento do bloco C
			
			// Bloco 0
			Registro0000Factory.getFactory().buildRegistro0000(arquivo, dao); // Abertura do arquivo digital e identificacao da entidade
			Registro0001Factory.getFactory().buildRegistro0001(arquivo, dao); // Abertura do bloco 0
			Registro0005Factory.getFactory().buildRegistro0005(arquivo, dao); // Dados Complementares Entidade
			Registro0150Factory.getFactory().buildRegistro0150(arquivo, dao); // Participantes
			Registro0200Factory.getFactory().buildRegistro0200(arquivo, dao); // Produtos
			Registro0190Factory.getFactory().buildRegistro0190(arquivo, dao); // Unidades
			Registro0990Factory.getFactory().buildRegistro0990(arquivo); // Fechamento do bloco 0
			
			dao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			dao.rollback();
			throw new SpedException("Erro ao gerar arquivo. Mensagem: " + e.getMessage());
		}

		return arquivo;
	}

}
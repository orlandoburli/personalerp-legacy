package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.vendas.pedido.EcfDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC400;
import br.com.orlandoburli.core.vo.vendas.pedido.EcfVO;

public class RegistroC400Factory {

	private static RegistroC400Factory factory;

	private RegistroC400Factory() {}

	public static RegistroC400Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC400Factory();
		}
		return factory;
	}

	public void buildRegistroC400(ArquivoSpedFiscal arquivo, GenericDAO dao) {
		EcfDAO ecfDao = new EcfDAO();
		try {
			ecfDao.mergeDAO(dao);
			EcfVO ecfFilter = new EcfVO();
			ecfFilter.setCodigoEmpresa(arquivo.getLoja().getCodigoEmpresa());
			ecfFilter.setCodigoLoja(arquivo.getLoja().getCodigoLoja());

			for (EcfVO ecf : ecfDao.getList(ecfFilter)) {
				RegistroC400 regc400 = new RegistroC400();
				
				regc400.setCodigoModelo("2D"); //???
				regc400.setCodigoCaixa(ecf.getCodigoPdv());
				regc400.setModeloEquipamento(ecf.getModeloEcf());
				regc400.setNumeroSerieEquipamento(ecf.getNumeroSerieEcf());
				
				arquivo.addRegistro(regc400);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
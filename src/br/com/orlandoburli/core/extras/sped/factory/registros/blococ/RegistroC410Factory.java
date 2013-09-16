package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.cupomfiscal.CupomFiscalDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC410;
import br.com.orlandoburli.core.vo.fiscal.cupomfiscal.CupomFiscalVO;

public class RegistroC410Factory {

	private static RegistroC410Factory factory;

	private RegistroC410Factory() {

	}

	public static RegistroC410Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC410Factory();
		}
		return factory;
	}

	public void buildRegistroC410(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		CupomFiscalDAO cupomDao = new CupomFiscalDAO();
		cupomDao.mergeDAO(dao);

		String sql = " CAST(DataHoraCupomFiscal AS DATE) BETWEEN '" + sdf.format(arquivo.getDataInicial()) + "' AND '" + sdf.format(arquivo.getDataFinal()) + "' ";
		cupomDao.setSpecialCondition(sql);
		
		CupomFiscalVO cupomFilter = new CupomFiscalVO();
		cupomFilter.setStatusCupomFiscal("P"); // Somente cupons processados
		
		List<CupomFiscalVO> listCupons = cupomDao.getList(cupomFilter, "DataHoraCupomFiscal");
		
		for (CupomFiscalVO cupom : listCupons) {
			cupom.toString();
			RegistroC410 regc410 = new RegistroC410();
			
			arquivo.addRegistro(regc410);
		}
	}
}

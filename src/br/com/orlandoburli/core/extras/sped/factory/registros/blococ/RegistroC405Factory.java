package br.com.orlandoburli.core.extras.sped.factory.registros.blococ;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.fiscal.ReducaoZDAO;
import br.com.orlandoburli.core.extras.sped.arquivo.ArquivoSpedFiscal;
import br.com.orlandoburli.core.extras.sped.registros.blococ.RegistroC405;
import br.com.orlandoburli.core.vo.fiscal.ReducaoZVO;

public class RegistroC405Factory {

	private static RegistroC405Factory factory;

	private RegistroC405Factory() {

	}

	public static RegistroC405Factory getFactory() {
		if (factory == null) {
			factory = new RegistroC405Factory();
		}
		return factory;
	}

	public void buildRegistroC405(ArquivoSpedFiscal arquivo, GenericDAO dao) throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ReducaoZDAO redzDao = new ReducaoZDAO();
		redzDao.mergeDAO(dao);
		String sql = " DataReducaoZ BETWEEN '" + sdf.format(arquivo.getDataInicial()) + "' AND '" + sdf.format(arquivo.getDataFinal()) + "' ";
		redzDao.setSpecialCondition(sql);
		List<ReducaoZVO> listReducao = redzDao.getList(null, "DataReducaoZ");
		
		for (ReducaoZVO reducao : listReducao) {
			RegistroC405 regc405 = new RegistroC405();
			regc405.setCro(reducao.getCroReducaoZ());
			regc405.setCrz(reducao.getCrzReducaoZ());
			regc405.setDataDocumento(reducao.getDataReducaoZ());
			regc405.setNumeroCooFinal(reducao.getNumeroReducaoZ());
			regc405.setValorGrandeTotal(reducao.getValorGrandeTotalReducaoZ());
			regc405.setValorVendaBruta(reducao.getValorVendaBrutaReducaoZ());
			
			arquivo.addRegistro(regc405);
		}
	}
}

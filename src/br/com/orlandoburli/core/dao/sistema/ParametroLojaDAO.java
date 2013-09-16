package br.com.orlandoburli.core.dao.sistema;

import java.math.BigDecimal;
import java.sql.SQLException;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.sistema.ParametroLojaVO;

public class ParametroLojaDAO extends BaseCadastroDAO<ParametroLojaVO> {

	public String getStringParametro(String ChaveParametro, Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
		ParametroLojaVO parametro = this.get(new Object[] {ChaveParametro, CodigoEmpresa, CodigoLoja});
		if (parametro != null) {
			return parametro.getValorParametro();
		}
		return null;
	}
	
	public Integer getIntegerParametro(String ChaveParametro, Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
		String parametro = getStringParametro(ChaveParametro, CodigoEmpresa, CodigoLoja);
		if (parametro != null) {
			return Integer.parseInt(parametro);
		}
		return null;
	}
	
	public BigDecimal getBigDecimalParametro(String ChaveParametro, Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
		String parametro = getStringParametro(ChaveParametro, CodigoEmpresa, CodigoLoja);
		if (parametro != null) {
			return new BigDecimal(parametro);
		}
		return null;
	}
}
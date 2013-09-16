package br.com.orlandoburli.core.dao.fiscal.nfsaida;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;

public class NFSaidaDAO extends BaseCadastroDAO<NFSaidaVO>{

//	public Integer getNextNFSaida(EmpresaVO empresa, LojaVO loja, String Serie) throws SQLException {
//		String sql = "SELECT COALESCE(MAX(CodigoNFSaida), 0) + 1 as CodigoNFSaida FROM [schema].NFSaida " +
//				"      WHERE CodigoEmpresa = " + empresa.getCodigoEmpresa() +
//				"        AND CodigoLoja = " + loja.getCodigoLoja() +
//				"        AND SerieNFSaida = '" + Serie + "'";
//		try {
//			sql = sql.replace("[schema]", SystemManager.getProperty("db.schema"));
//			RowSet row = getRowSet(sql);
//			if (row.next()) {
//				return row.getInt(1);
//			} else {
//				return 1;
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}

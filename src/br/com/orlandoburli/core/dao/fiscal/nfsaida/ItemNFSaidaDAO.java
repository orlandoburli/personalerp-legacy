package br.com.orlandoburli.core.dao.fiscal.nfsaida;

import java.sql.SQLException;
import javax.sql.RowSet;
import br.com.orlandoburli.SystemManager;
import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;

public class ItemNFSaidaDAO extends BaseCadastroDAO<ItemNFSaidaVO>{

	public Integer getNextSequencialItemNFSaida(NFSaidaVO nfsaida) throws SQLException {
		String sql = "SELECT COALESCE(MAX(SequencialItemNFSaida), 0) + 1 as CodigoNFSaida FROM [schema].ItemNFSaida " +
				"      WHERE CodigoEmpresa = " + nfsaida.getCodigoEmpresa() +
				"        AND CodigoLoja = " + nfsaida.getCodigoLoja() +
				"        AND SerieNFSaida = '" + nfsaida.getSerieNFSaida() + "'" +
				"		 AND CodigoNFSaida = " + nfsaida.getCodigoNFSaida();
		try {
			sql = sql.replace("[schema]", SystemManager.getProperty("db.schema"));
			RowSet row = getRowSet(sql);
			if (row.next()) {
				return row.getInt(1);
			} else {
				return 1;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
package br.com.orlandoburli.core.dao.fiscal.nfe;

import java.sql.SQLException;

import javax.sql.RowSet;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;

public class NFEletronicaDAO extends BaseCadastroDAO<NFEletronicaVO>{

	public Integer getNextNFEletronica(Integer CodigoEmpresa, Integer CodigoLoja) throws SQLException {
		try {
			String sql = "SELECT COALESCE(MAX(CodigoNFe), 0) + 1 FROM " + getSchemaName() + ".NFEletronica a " +
					"  	   WHERE a.CodigoEmpresa = " + CodigoEmpresa + " " +
					"	     AND a.CodigoLoja = " +  CodigoLoja;
			
			RowSet row = this.getRowSet(sql);
			row.next();
			return row.getInt(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
package br.com.orlandoburli.core.dao.base;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.base.*;

public class CidadeDAO extends BaseCadastroDAO<CidadeVO>{

	public CidadeVO getByNomeAndUF(String NomeCidade, String SiglaUFCidade) throws SQLException {
		CidadeVO filter = new CidadeVO();
		filter.setNomeCidade(NomeCidade);
		filter.setSiglaUFCidade(SiglaUFCidade);
		List<CidadeVO> list = this.getList(filter);
		for (CidadeVO cidade : list) {
			return cidade;
		}
		return null;
	}
}

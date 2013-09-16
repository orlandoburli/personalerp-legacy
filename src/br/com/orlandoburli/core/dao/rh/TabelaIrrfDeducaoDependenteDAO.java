package br.com.orlandoburli.core.dao.rh;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfDeducaoDependenteVO;

public class TabelaIrrfDeducaoDependenteDAO extends BaseCadastroDAO<TabelaIrrfDeducaoDependenteVO> {

	public TabelaIrrfDeducaoDependenteVO get(Date DataBaseFolha ) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" ('" + DataBaseFolha.toString() + "' BETWEEN DataInicialVigenciaTabelaIrrf AND COALESCE(DataFinalVigenciaTabelaIrrf, NOW()))");
		
		setSpecialCondition(sb.toString());
		
		List<TabelaIrrfDeducaoDependenteVO> list = getList(new TabelaIrrfDeducaoDependenteVO());
		
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
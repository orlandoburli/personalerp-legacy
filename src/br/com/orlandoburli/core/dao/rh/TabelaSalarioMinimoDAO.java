package br.com.orlandoburli.core.dao.rh;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioMinimoVO;

public class TabelaSalarioMinimoDAO extends BaseCadastroDAO<TabelaSalarioMinimoVO> {

	public TabelaSalarioMinimoVO get(Date DataBaseFolha ) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" ('" + DataBaseFolha.toString() + "' BETWEEN DataInicialVigenciaSalarioMinimo AND COALESCE(DataFinalVigenciaSalarioMinimo, NOW()))");
		
		setSpecialCondition(sb.toString());
		
		List<TabelaSalarioMinimoVO> list = getList(new TabelaSalarioMinimoVO());
		
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
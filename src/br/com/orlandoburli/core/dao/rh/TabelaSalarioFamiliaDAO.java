package br.com.orlandoburli.core.dao.rh;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioFamiliaVO;

public class TabelaSalarioFamiliaDAO extends BaseCadastroDAO<TabelaSalarioFamiliaVO> {
	
	public TabelaSalarioFamiliaVO get(BigDecimal ValorSalarioBase, Date DataBaseFolha) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" (" + ValorSalarioBase.toString() + " BETWEEN a.ValorInicialSalarioFamilia AND a.ValorFinalSalarioFamilia) ");
		sb.append(" AND ('" + DataBaseFolha.toString() + "' BETWEEN a.DataInicialVigenciaSalarioFamilia AND COALESCE(a.DataFinalVigenciaSalarioFamilia, NOW()))");
		
		setSpecialCondition(sb.toString());
		List<TabelaSalarioFamiliaVO> list = getList(new TabelaSalarioFamiliaVO());
		
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
}
package br.com.orlandoburli.core.dao.rh;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfVO;

public class TabelaIrrfDAO extends BaseCadastroDAO<TabelaIrrfVO> {

	public TabelaIrrfVO get(BigDecimal ValorSalarioBase, Date DataBaseFolha) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" (" + ValorSalarioBase.toString() + " BETWEEN a.ValorInicialTabelaIrrf AND a.ValorFinalTabelaIrrf) ");
		sb.append(" AND ('" + DataBaseFolha.toString() + "' BETWEEN a.DataInicialVigenciaTabelaIrrf AND COALESCE(a.DataFinalVigenciaTabelaIrrf, NOW()))");
		
		setSpecialCondition(sb.toString());
		
		List<TabelaIrrfVO> list = getList(new TabelaIrrfVO());
		
		if (list.size() > 0) {
			return list.get(0);
		} else {
			// Se nao achou na faixa de valor especificada, 
			// busca a ultima faixa de valores.
			sb = new StringBuilder();
			sb.append(" ('" + DataBaseFolha.toString() + "' BETWEEN a.DataInicialVigenciaTabelaIrrf AND COALESCE(a.DataFinalVigenciaTabelaIrrf, NOW()))");
			
			setSpecialCondition(sb.toString());
			
			list = getList(new TabelaIrrfVO(), "a.ValorFinalTabelaIrrf DESC");
			
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
}

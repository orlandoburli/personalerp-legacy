package br.com.orlandoburli.core.dao.rh;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.BaseCadastroDAO;
import br.com.orlandoburli.core.vo.rh.TabelaInssVO;

public class TabelaInssDAO extends BaseCadastroDAO<TabelaInssVO> {

	public TabelaInssVO get(BigDecimal ValorSalarioBase, Date DataBaseFolha) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append(" (" + ValorSalarioBase.toString() + " BETWEEN a.ValorInicialTabelaInss AND a.ValorFinalTabelaInss) ");
		sb.append(" AND ('" + DataBaseFolha.toString() + "' BETWEEN a.DataInicialVigenciaTabelaInss AND COALESCE(a.DataFinalVigenciaTabelaInss, NOW()))");
		
		setSpecialCondition(sb.toString());
		
		List<TabelaInssVO> list = getList(new TabelaInssVO());
		
		if (list.size() > 0) {
			return list.get(0);
		} else {
			// Se nao achou na faixa de valor especificada, 
			// busca a ultima faixa de valores.
			sb = new StringBuilder();
			sb.append(" ('" + DataBaseFolha.toString() + "' BETWEEN a.DataInicialVigenciaTabelaInss AND COALESCE(a.DataFinalVigenciaTabelaInss, NOW()))");
			
			setSpecialCondition(sb.toString());
			
			list = getList(new TabelaInssVO(), "a.ValorFinalTabelaInss DESC");
			
			if (list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}
}

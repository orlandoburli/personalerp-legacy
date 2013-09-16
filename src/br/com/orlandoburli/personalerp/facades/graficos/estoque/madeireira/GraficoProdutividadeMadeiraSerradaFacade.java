package br.com.orlandoburli.personalerp.facades.graficos.estoque.madeireira;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.view.estoque.madeireira.GraficoProdutividadeViewDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.view.estoque.madeireira.GraficoProdutividadeView;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;

public class GraficoProdutividadeMadeiraSerradaFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	public GraficoProdutividadeMadeiraSerradaFacade() {
		
	}
	
	public void execute() {
		GraficoProdutividadeViewDAO dao = new GraficoProdutividadeViewDAO();
		
		try {
	 		List<GraficoProdutividadeView> list = dao.getList(new GraficoProdutividadeView());
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}

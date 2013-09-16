package br.com.orlandoburli.personalerp.web.action.estoque.grupo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.personalerp.model.estoque.grupo.be.GrupoBe;
import br.com.orlandoburli.personalerp.model.estoque.grupo.dao.GrupoDAO;
import br.com.orlandoburli.personalerp.model.estoque.grupo.vo.GrupoVO;

public class GrupoConsultaAction extends BaseConsultaAction<GrupoVO, GrupoDAO, GrupoBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspConsulta() {
		return "web/pages/estoque/grupo/grupoconsulta.jsp";
	}

	@Override
	public String getJspGridConsulta() {
		return "web/pages/estoque/grupo/grupoconsulta_grid.jsp";
	}

	@Override
	public String getOrderFields() {
		return "NomeGrupo";
	}

	@Override
	public void doBeforeFilter(GrupoVO filter, GrupoBe be, HttpServletRequest request, HttpServletResponse response) {
		if (getParametroPesquisa() != null) {
			if (getParametroPesquisa().equalsIgnoreCase("Codigo")) {
				Integer codigo = null;
				try {
					codigo = Integer.parseInt(getPesquisarPor());
				} catch (NumberFormatException e) {}
				filter.setCodigoGrupo(codigo);
			} else if (getParametroPesquisa().equalsIgnoreCase("Nome")) {
				filter.setNomeGrupo("%" + getPesquisarPor() + "%");
			}
		} else {
			filter.setNomeGrupo("%" + getPesquisarPor() + "%");
		}
	}
}

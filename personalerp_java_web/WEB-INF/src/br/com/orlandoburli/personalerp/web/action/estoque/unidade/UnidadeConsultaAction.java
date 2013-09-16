package br.com.orlandoburli.personalerp.web.action.estoque.unidade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.personalerp.model.estoque.unidade.be.UnidadeBe;
import br.com.orlandoburli.personalerp.model.estoque.unidade.dao.UnidadeDAO;
import br.com.orlandoburli.personalerp.model.estoque.unidade.vo.UnidadeVO;

public class UnidadeConsultaAction extends BaseConsultaAction<UnidadeVO, UnidadeDAO, UnidadeBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspConsulta() {
		return "web/pages/estoque/unidade/unidadeconsulta.jsp";
	}

	@Override
	public String getJspGridConsulta() {
		return "web/pages/estoque/unidade/unidadeconsulta_grid.jsp";
	}

	@Override
	public String getOrderFields() {
		return "SiglaUnidade";
	}

	@Override
	public void doBeforeFilter(UnidadeVO filter, UnidadeBe be, HttpServletRequest request, HttpServletResponse response) {
		if (getParametroPesquisa() != null) {
			if (getParametroPesquisa().equalsIgnoreCase("Sigla")) {
				filter.setSiglaUnidade("%" + getPesquisarPor() + "%");
			} else if (getParametroPesquisa().equalsIgnoreCase("Nome")) {
				filter.setNomeUnidadeSingular("%" + getPesquisarPor() + "%");
			}
		} else {
			filter.setNomeUnidadeSingular("%" + getPesquisarPor() + "%");
		}
	}
}

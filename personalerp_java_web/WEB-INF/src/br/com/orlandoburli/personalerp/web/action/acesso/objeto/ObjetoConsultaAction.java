package br.com.orlandoburli.personalerp.web.action.acesso.objeto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.personalerp.model.acesso.objeto.be.ObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.objeto.dao.ObjetoDAO;
import br.com.orlandoburli.personalerp.model.acesso.objeto.vo.ObjetoVO;

public class ObjetoConsultaAction extends BaseConsultaAction<ObjetoVO, ObjetoDAO, ObjetoBe>{

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspConsulta() {
		return "web/pages/acesso/objeto/objetoconsulta.jsp";
	}

	@Override
	public String getJspGridConsulta() {
		return "web/pages/acesso/objeto/objetoconsulta_grid.jsp";
	}

	@Override
	public String getOrderFields() {
		return "NomeObjeto ASC";
	}

	@Override
	public void doBeforeFilter(ObjetoVO filter, ObjetoBe be, HttpServletRequest request, HttpServletResponse response) {
		// Limpa o Objeto selecionado da sessao
		request.getSession().removeAttribute("objeto");
		filter.setDescricaoObjeto("%" + getPesquisarPor() + "%");
	}
}

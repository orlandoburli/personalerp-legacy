package br.com.orlandoburli.personalerp.web.action.acesso.menu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.personalerp.model.acesso.menu.be.MenuBe;
import br.com.orlandoburli.personalerp.model.acesso.menu.dao.MenuDAO;
import br.com.orlandoburli.personalerp.model.acesso.menu.vo.MenuVO;

public class MenuConsultaAction extends BaseConsultaAction<MenuVO, MenuDAO, MenuBe>{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getJspConsulta() {
		return "web/pages/acesso/menu/menuconsulta.jsp";
	}
	
	@Override
	public String getJspGridConsulta() {
		return "web/pages/acesso/menu/menuconsulta_grid.jsp";
	}
	
	@Override
	public String getOrderFields() {
		return "NomeMenu";
	}
	
	@Override
	public void doBeforeFilter(MenuVO filter, MenuBe be, HttpServletRequest request, HttpServletResponse response) {
		filter.setNomeMenu("%" + getPesquisarPor() + "%");
	}

}

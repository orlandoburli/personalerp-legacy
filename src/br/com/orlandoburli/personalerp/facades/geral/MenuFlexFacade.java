package br.com.orlandoburli.personalerp.facades.geral;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.base.MenuDAO;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

@IgnoreFacadeAuthentication
public class MenuFlexFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	@OutjectSession
	private PerfilVO perfilsessao;

	public void execute() {
		response.setCharacterEncoding("UTF-8");
		String retorno = "";
		
		try {
			List<MenuVO> list;
			list = new MenuDAO().getListMenusPerfil(perfilsessao);
			retorno = "<?xml version='1.0' encoding='utf-8'?><list>";

			for (MenuVO item : list) {
				if (item.getCodigoMenu().equals(perfilsessao.getCodigoMenu())) {
					retorno += getChild(list, item);
				}
			}
			retorno += "</list>";
		} catch (SQLException e1) {
			writeErrorMessage(e1.getMessage());
			return;
		}

		try {
			response.getWriter().write(retorno);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dispatch();
	}

	private String getChild(List<MenuVO> list, MenuVO menu) {
		String retorno = "";
		for (MenuVO child : list) {
			if (child.getCodigoMenuPai() != null && child.getCodigoMenuPai().equals(menu.getCodigoMenu())) {
				retorno += "<menuitem label=\"" + child.getNomeMenu() + "\" link=\"" + child.getNomeObjeto() + "\" type=\"" + child.getTipoMenu() + "\" >";
				if (child.getCountChild() > 0) {
					retorno += getChild(list, child);
				}
				retorno += "</menuitem>";
			}
		}
		return retorno;
	}

	public PerfilVO getPerfilsession() {
		return perfilsessao;
	}

	public void setPerfilsessao(PerfilVO perfilsession) {
		this.perfilsessao = perfilsession;
	}
}
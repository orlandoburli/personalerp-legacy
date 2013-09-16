package br.com.orlandoburli.personalerp.model.acesso.menu.be;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.be.BaseBe;
import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.personalerp.model.acesso.menu.dao.MenuDAO;
import br.com.orlandoburli.personalerp.model.acesso.menu.vo.MenuVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;

public class MenuBe extends BaseBe<MenuVO, MenuDAO> {

	public MenuVO getMenuPerfil(PerfilVO perfil) throws ListException {
		try {
			MenuVO menuPrincipal = new MenuVO();
			menuPrincipal.setCodigoMenu(perfil.getCodigoMenu());
			
			menuPrincipal = getDao().get(menuPrincipal);
			
			List<MenuVO> listSubMenus = getListSubMenus(menuPrincipal);
			
			menuPrincipal.getSubMenus().addAll(listSubMenus);
			
			return menuPrincipal;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ListException();
		}
	}
	
	private List<MenuVO> getListSubMenus(MenuVO menu) throws SQLException {
		MenuVO filter = new MenuVO();
		filter.setCodigoMenuPai(menu.getCodigoMenu());
		
		List<MenuVO> listSub = getDao().getList(filter, "OrdemMenu");
		
		for (MenuVO m : listSub) {
			if (m.getNomeObjeto() != null) {
				m.setNomeObjeto(m.getNomeObjeto().replace(".action", ".erp"));
			}
			if (m.getCountChild() > 0) {
				m.getSubMenus().addAll(getListSubMenus(m));
			}
		}
		
		return listSub;
	}
}

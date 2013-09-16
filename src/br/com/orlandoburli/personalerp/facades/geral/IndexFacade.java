package br.com.orlandoburli.personalerp.facades.geral;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.vo.acesso.*;

public class IndexFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	private MenuVO menu;
	private List<MenuVO> listMenu;
	private List<MenuVO> menuUsuarioSession;
	private UsuarioVO usuarioSession;
	private PerfilVO perfilSession;
	
	private String mensagem;
	
	public IndexFacade() {
		this.menu = new MenuVO();
		this.listMenu = new ArrayList<MenuVO>();
	}

	public void execute() {
		
		if (this.menu.getCodigoMenu() == null || this.menu.getCodigoMenu() <= 0) {
			this.menu.setCodigoMenu(perfilSession.getCodigoMenu()); // Menu padrão do usuário
		}
		
		for (MenuVO item : menuUsuarioSession) {
			if (menu.getCodigoMenu().equals(item.getCodigoMenu())) {
				this.menu = item;
			}
		}
		
		if (menu.getNomeObjeto() != null && !menu.getNomeObjeto().trim().equals("")) {
			redir(menu.getNomeObjeto());
			return;
		}

		for (MenuVO item : menuUsuarioSession) {
			if (item.getCodigoMenuPai() != null && item.getCodigoMenuPai().equals(menu.getCodigoMenu())) {
				listMenu.add(item);
			}
		}
		
		if (menu.getCodigoMenuPai() != null && menu.getCodigoMenuPai() > 0 && menu.getCodigoMenu() != perfilSession.getCodigoMenu() ) {
			MenuVO menuVoltar = new MenuVO();
			menuVoltar.setCodigoMenu(menu.getCodigoMenuPai());
			menuVoltar.setNomeMenu("Voltar");
			this.getListMenu().add(menuVoltar);
		}
		forward("index.jsp");
	}

	public void setListMenus(List<MenuVO> listMenu) {
		this.listMenu = listMenu;
	}

	public List<MenuVO> getListMenu() {
		return listMenu;
	}

	public void setMenu(MenuVO menu) {
		this.menu = menu;
	}

	public MenuVO getMenu() {
		return menu;
	}

	public void setUsuarioSession(UsuarioVO usuarioSession) {
		this.usuarioSession = usuarioSession;
	}

	public UsuarioVO getUsuarioSession() {
		return usuarioSession;
	}

	public void setPerfilSession(PerfilVO perfilSession) {
		this.perfilSession = perfilSession;
	}

	public PerfilVO getPerfilSession() {
		return perfilSession;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}
}
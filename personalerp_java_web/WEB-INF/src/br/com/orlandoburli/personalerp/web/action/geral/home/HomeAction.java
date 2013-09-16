package br.com.orlandoburli.personalerp.web.action.geral.home;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.action.BaseAction;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.personalerp.Versao;
import br.com.orlandoburli.personalerp.model.acesso.menu.vo.MenuVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.vo.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.vo.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;
import br.com.orlandoburli.personalerp.model.sistema.loja.vo.LojaVO;

public class HomeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private UsuarioVO usuariosessao;
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private MenuVO menusessao;

	private List<PermissaoPerfilObjetoVO> permissoesobjetos;
	private List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos;

	private String term;

	@IgnoreMethodAuthentication
	public void execute() {
		getRequest().setAttribute("versao", Versao.getInstance());

		// Monta menu html
		String menuString = "";

		for (MenuVO menu : menusessao.getSubMenus()) {
			menuString += buildMenu(menu, true);
		}

		getRequest().setAttribute("menuString", menuString);

		forward("web/home.jsp");
	}

	@IgnoreMethodAuthentication
	public void buscamenu() {

		List<MenuVO> listRetorno = new ArrayList<MenuVO>();

		String termPesquisa = Utils.removerAcentos(getTerm().toUpperCase());

		buscaSubMenus(listRetorno, termPesquisa, menusessao.getSubMenus());

		write(Utils.voToJson(listRetorno));
	}

	private void buscaSubMenus(List<MenuVO> listRetorno, String termPesquisa, List<MenuVO> source) {
		for (MenuVO i : source) {

			if (i.getSubMenus().size() > 0) {
				buscaSubMenus(listRetorno, termPesquisa, i.getSubMenus());
			} else {
				String descricaoObjeto = Utils.removerAcentos(i.getNomeMenu().toUpperCase());
				
				if (descricaoObjeto.indexOf(termPesquisa) >= 0) {
					if (i.getNomeObjeto() != null) {
						i.setNomeObjeto(i.getNomeObjeto().replace(".action", ".erp"));
						listRetorno.add(i);
					}
				}
			}
		}
	}

	private String buildMenu(MenuVO menu, boolean topLevel) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<li data-top-level=\"" + (topLevel ? "true" : "false") + "\">");
		
		if (menu.getTipoMenu() != null && menu.getTipoMenu().equals("separator")) {
			// Separador
			sb.append("-");
		} else {
			sb.append("<a href=\"#\" data-nav=\"" + menu.getCodigoMenu() + "\" data-link=\"" + menu.getNomeObjeto() + "\" >" + menu.getNomeMenu() + "</a>");

			if (menu.getSubMenus() != null && menu.getSubMenus().size() > 0) {
				sb.append("<ul>");
				for (MenuVO sub : menu.getSubMenus()) {
					sb.append(buildMenu(sub, false));
				}
				sb.append("</ul>");
			}
		}
		sb.append("</li>");
		
		return sb.toString();
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public MenuVO getMenusessao() {
		return menusessao;
	}

	public void setMenusessao(MenuVO menusessao) {
		this.menusessao = menusessao;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public List<PermissaoPerfilObjetoVO> getPermissoesobjetos() {
		return permissoesobjetos;
	}

	public void setPermissoesobjetos(List<PermissaoPerfilObjetoVO> permissoesobjetos) {
		this.permissoesobjetos = permissoesobjetos;
	}

	public List<PermissaoPerfilAcaoObjetoVO> getPermissoesacoesobjetos() {
		return permissoesacoesobjetos;
	}

	public void setPermissoesacoesobjetos(List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos) {
		this.permissoesacoesobjetos = permissoesacoesobjetos;
	}
}
package br.com.orlandoburli.personalerp.web.action.acesso.login;

import java.util.List;

import br.com.orlandoburli.core.be.exceptions.list.ListException;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.web.framework.action.BaseAction;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.retorno.RetornoAction;
import br.com.orlandoburli.personalerp.model.acesso.menu.be.MenuBe;
import br.com.orlandoburli.personalerp.model.acesso.menu.vo.MenuVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.be.PerfilBe;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.be.PermissaoPerfilObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.be.PermissaoPerfilAcaoObjetoBe;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.permissaoperfilacaoobjeto.vo.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.permissaoperfilobjeto.vo.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.personalerp.model.acesso.perfil.vo.PerfilVO;
import br.com.orlandoburli.personalerp.model.acesso.usuario.perfilusuario.be.PerfilUsuarioBe;
import br.com.orlandoburli.personalerp.model.acesso.usuario.be.UsuarioBe;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioInvalidoException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioSemEmpresaException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioSemLojaException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.exception.UsuarioSemPerfilException;
import br.com.orlandoburli.personalerp.model.acesso.usuario.vo.UsuarioVO;
import br.com.orlandoburli.personalerp.model.sistema.empresa.be.EmpresaBe;
import br.com.orlandoburli.personalerp.model.sistema.empresa.vo.EmpresaVO;
import br.com.orlandoburli.personalerp.model.sistema.loja.be.LojaBe;
import br.com.orlandoburli.personalerp.model.sistema.loja.vo.LojaVO;

public class LoginAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;
	
	@OutjectSession
	private UsuarioVO usuariosessao;
	
	@OutjectSession
	private PerfilVO perfilsessao;
	
	@OutjectSession
	private EmpresaVO empresasessao;
	
	@OutjectSession
	private LojaVO lojasessao;
	
	@OutjectSession
	private MenuVO menusessao;
	
	@OutjectSession
	private List<PermissaoPerfilObjetoVO> permissoesobjetos;
	
	@OutjectSession
	private List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos;
	
	@InstantiateObject
	private PerfilVO perfil;
	
	@InstantiateObject
	private EmpresaVO empresa;
	
	@InstantiateObject
	private LojaVO loja;
	
	public void execute() {
		forward("web/pages/acesso/login/login.jsp");
	}
	
	public void login() {
		try {
			UsuarioVO usuario = new UsuarioBe().login(login, senha);
			setUsuariosessao(usuario);
			
			List<PerfilVO> perfis = new PerfilUsuarioBe().getPerfilUsuario(usuario);
			
			if (perfis.size() > 1) {
				write(Utils.voToJson(new RetornoAction(true, "SELECIONAR_PERFIL", "login")));
				return;
			} else {
				setPerfilsessao(perfis.get(0));
			}
			
			write(Utils.voToJson(new RetornoAction(true, "LOGIN_OK", "")));
			
		} catch (ListException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "login")));
		} catch (UsuarioInvalidoException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "login")));
		} catch (UsuarioSemPerfilException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "login")));
		}
	}
	
	public void perfis() {
		try {
			if (getUsuariosessao() == null) {
				return;
			}
			
			List<PerfilVO> perfis = new PerfilUsuarioBe().getPerfilUsuario(getUsuariosessao());
			
			write(Utils.voToJson(perfis));
		} catch (ListException e) {
			e.printStackTrace();
		} catch (UsuarioSemPerfilException e) {
			e.printStackTrace();
		}
	}
	
	public void perfil() {
		try {
			perfil = new PerfilBe().get(perfil);
			if (perfil == null) {
				write(Utils.voToJson(new RetornoAction(false, "Perfil obrigatório!", "Perfil")));
				return;
			}
			setPerfilsessao(perfil);
			
			write(Utils.voToJson(new RetornoAction(true, "PERFIL_OK", "")));
		} catch (ListException e) {
			e.printStackTrace();
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "Perfil")));
		}
	}
	
	public void empresas() {
		try {
			List<EmpresaVO> empresas = new EmpresaBe().getListEmpresasUsuario(getUsuariosessao());
			
			write(Utils.voToJson(empresas));
		} catch (ListException e) {
			e.printStackTrace();
		} catch (UsuarioSemEmpresaException e) {
			e.printStackTrace();
		}
	}
	
	public void empresa() {
		try {
			empresa = new EmpresaBe().get(empresa);
			if (empresa == null) {
				throw new UsuarioSemEmpresaException();
			}
			setEmpresasessao(empresa);
			
			write(Utils.voToJson(new RetornoAction(true, "EMPRESA_OK", "")));
		} catch (ListException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "Empresa")));
		} catch (UsuarioSemEmpresaException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "Empresa")));
		}
	}
	
	public void lojas() {
		try {
			List<LojaVO> lojas = new LojaBe().getListLojasUsuario(getUsuariosessao(), getEmpresasessao());
			
			write(Utils.voToJson(lojas));
		} catch (ListException e) {
			e.printStackTrace();
		}
	}
	
	public void loja() {
		try {
			loja = new LojaBe().get(loja);
			if (loja == null) {
				throw new UsuarioSemLojaException();
			}
			setLojasessao(loja);
			
			write(Utils.voToJson(new RetornoAction(true, "LOJA_OK", "")));
		} catch (ListException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "Loja")));
		} catch (UsuarioSemLojaException e) {
			write(Utils.voToJson(new RetornoAction(false, e.getMessage(), "Loja")));
		}
	}
	
	public void load() {
		try {
			// Carrega menus do usuario
			setMenusessao(new MenuBe().getMenuPerfil(getPerfilsessao()));
			
			// Carrega permissao de objetos
			setPermissoesobjetos(new PermissaoPerfilObjetoBe().getList(getPerfilsessao()));
			
			// Carrega permissao de acao de objetos
			setPermissoesacoesobjetos(new PermissaoPerfilAcaoObjetoBe().getList(getPerfilsessao()));
			
			write(Utils.voToJson(new RetornoAction(true, "LOAD_OK", "")));
			
		} catch (ListException e) {
			writeErrorMessage(e.getMessage(), "Login");
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public PerfilVO getPerfilsessao() {
		return perfilsessao;
	}

	public void setPerfilsessao(PerfilVO perfilsessao) {
		this.perfilsessao = perfilsessao;
	}

	public PerfilVO getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
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
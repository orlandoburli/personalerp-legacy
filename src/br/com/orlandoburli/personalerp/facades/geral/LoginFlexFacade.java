package br.com.orlandoburli.personalerp.facades.geral;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.acesso.PerfilDAO;
import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilAcaoObjetoDAO;
import br.com.orlandoburli.core.dao.acesso.PermissaoPerfilObjetoDAO;
import br.com.orlandoburli.core.dao.acesso.UsuarioDAO;
import br.com.orlandoburli.core.dao.sistema.EmpresaDAO;
import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaDAO;
import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaLojaDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.base.MenuVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaLojaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

@IgnoreFacadeAuthentication
public class LoginFlexFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	@OutjectSession
	private UsuarioVO usuariosessao;
	@OutjectSession
	private UsuarioVO usuariotmp;
	@OutjectSession
	private PerfilVO perfilsessao;
	@OutjectSession
	private EmpresaVO empresasessao;
	@OutjectSession
	private LojaVO lojasessao;
	@OutjectSession
	private GrupoEmpresaVO grupoempresasessao;

	@OutjectSession
	private List<MenuVO> menusessao;
	@OutjectSession
	private List<PermissaoPerfilObjetoVO> permissoesobjetos;
	@OutjectSession
	private List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos;

	@OutjectSession
	private List<PerfilVO> listPerfis;
	@OutjectSession
	private List<EmpresaVO> listEmpresas;
	@OutjectSession
	private List<LojaVO> listLojas;

	// Inputs da tela
	private String login; // input de login da tela
	private String senha; // input de password da tela
	private String perfil; // select de perfil da tela
	private String empresa; // select de empresa da tela
	private String loja; // select de loja da tela

	public void execute() throws IOException {
		if (login == null || login.trim().equals("")) {
			write("Login obrigatório!");
			return;
		}

		usuariotmp = new UsuarioVO();
		usuariotmp.setLoginUsuario(getLogin());
		List<UsuarioVO> listAuxUsu;
		try {
			listAuxUsu = new UsuarioDAO().getList(usuariotmp);
		} catch (SQLException e) {
			write(e.getMessage());
			return;
		}

		if (listAuxUsu.size() == 1) { // Tem que retornar somente UM usuario
			usuariotmp = listAuxUsu.get(0);
			if (!usuariotmp.getSenhaUsuario().equals(this.senha) || !usuariotmp.isAtivo()) {
				write("Usuário / senha inválidos!");
				return;
			}
		} else {
			write("Usuário / senha inválidos!");
			return;
		}
		// Verifica perfis
		try {
			listPerfis = new PerfilDAO().getListPerfisUsuario(usuariotmp);
		} catch (SQLException e1) {
			writeErrorMessage(e1.getMessage());
			dispatch();
			return;
		}
		if (listPerfis.size() == 0) {
			write("Nenhum perfil associado ao usuário!");
			dispatch();
			return;
		} else if (listPerfis.size() == 1) {
			perfilsessao = listPerfis.get(0);
		} else {
			write("perfil");
			dispatch();
			return;
		}
		listEmpresas = new EmpresaDAO().getListEmpresasUsuario(usuariotmp);
		if (listEmpresas.size() == 0) {
			write("Este usuário não tem permissão de acesso a nenhuma empresa!");
			dispatch();
			return;
		} else if (listEmpresas.size() == 1) {
			empresasessao = listEmpresas.get(0);
		} else {
			write("empresa");
			dispatch();
			return;
		}

		try {
			listLojas = new LojaDAO().getListLojasUsuario(usuariotmp, empresasessao);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			dispatch();
			return;
		}
		if (listLojas.size() == 0) {
			write("Este usuário não tem permissão de acesso a nenhuma loja!");
			dispatch();
			return;
		} else if (listLojas.size() == 1) {
			setLojasessao(listLojas.get(0));
		} else {
			write("loja");
			dispatch();
			return;
		}
		setUsuariosessao(usuariotmp);
		setPermissoes();
		write("ok");
		dispatch();
	}

	public void perfil() {
		for (PerfilVO oPerfil : listPerfis) {
			if (oPerfil.getCodigoPerfil().equals(Integer.parseInt(perfil))) {
				this.perfilsessao = oPerfil;
				break;
			}
		}
		listEmpresas = new EmpresaDAO().getListEmpresasUsuario(usuariotmp);
		if (listEmpresas.size() == 0) {
			write("Este usuário não tem permissão de acesso a nenhuma empresa!");
			dispatch();
			return;
		} else if (listEmpresas.size() == 1) {
			empresasessao = listEmpresas.get(0);
		} else {
			write("empresa");
			dispatch();
			return;
		}
		try {
			listLojas = new LojaDAO().getListLojasUsuario(usuariotmp, empresasessao);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			dispatch();
			return;
		}
		if (listLojas.size() == 0) {
			write("Este usuário não tem permissão de acesso a nenhuma loja!");
			dispatch();
			return;
		} else if (listLojas.size() == 1) {
			setLojasessao(listLojas.get(0));
		} else {
			write("loja");
			dispatch();
			return;
		}
		setUsuariosessao(usuariotmp);
		setPermissoes();
		write("ok");
		dispatch();
	}

	public void empresa() {
		for (EmpresaVO oEmpresa : listEmpresas) {
			if (oEmpresa.getCodigoEmpresa().equals(Integer.parseInt(empresa))) {
				empresasessao = oEmpresa;
				break;
			}
		}
		try {
			listLojas = new LojaDAO().getListLojasUsuario(usuariotmp, empresasessao);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			dispatch();
			return;
		}
		if (listLojas.size() == 0) {
			write("Este usuário não tem permissão de acesso a nenhuma loja!");
			dispatch();
			return;
		} else if (listLojas.size() == 1) {
			setLojasessao(listLojas.get(0));
		} else {
			write("loja");
			dispatch();
			return;
		}
		setUsuariosessao(usuariotmp);
		setPermissoes();
		write("ok");
		dispatch();
	}

	public void loja() {
		for (LojaVO oLoja : listLojas) {
			if (oLoja.getCodigoEmpresa().equals(Integer.parseInt(empresa)) && oLoja.getCodigoLoja().equals(Integer.parseInt(loja))) {
				setLojasessao(oLoja);
				break;
			}
		}
		setUsuariosessao(usuariotmp);
		setPermissoes();
		write("ok");
		dispatch();
	}

	public void listperfil() {
		write(Utils.voToXml(listPerfis));
	}

	public void listempresa() {
		write(Utils.voToXml(listEmpresas));
	}

	public void listloja() {
		write(Utils.voToXml(listLojas));
	}

	private void setPermissoes() {
		try {
			this.permissoesobjetos = new PermissaoPerfilObjetoDAO().getList(this.perfilsessao);
			this.permissoesacoesobjetos = new PermissaoPerfilAcaoObjetoDAO().getList(this.perfilsessao);
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return this.senha;
	}

	public String getPerfil() {
		return this.perfil;
	}

	public PerfilVO getPerfilSessao() {
		return this.perfilsessao;
	}

	public String getEmpresa() {
		return this.empresa;
	}

	public void setLoja(String loja) {
		this.loja = loja;
	}

	public String getLoja() {
		return loja;
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;

		// Seleciona o grupo da empresa
		try {

			GrupoEmpresaLojaVO filter = new GrupoEmpresaLojaVO();
			filter.setCodigoEmpresa(this.lojasessao.getCodigoEmpresa());
			filter.setCodigoLoja(this.lojasessao.getCodigoLoja());

			GrupoEmpresaLojaDAO filterDao = new GrupoEmpresaLojaDAO();
			List<GrupoEmpresaLojaVO> list = filterDao.getList(filter);
			
			if (list.size() > 0) {
				GrupoEmpresaVO grupo = new GrupoEmpresaVO();
				grupo.setCodigoGrupoEmpresa(list.get(0).getCodigoGrupoEmpresa());
				grupo = new GrupoEmpresaDAO().get(grupo);
				
				setGrupoempresasessao(grupo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setMenuSessao(List<MenuVO> menusessao) {
		this.menusessao = menusessao;
	}

	public List<MenuVO> getMenuSessao() {
		return menusessao;
	}

	public void setPermissoesObjetos(List<PermissaoPerfilObjetoVO> permissoesobjetos) {
		this.permissoesobjetos = permissoesobjetos;
	}

	public List<PermissaoPerfilObjetoVO> getPermissoesObjetos() {
		return permissoesobjetos;
	}

	public void setPermissoesAcoesObjetos(List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos) {
		this.permissoesacoesobjetos = permissoesacoesobjetos;
	}

	public List<PermissaoPerfilAcaoObjetoVO> getPermissoesAcoesObjetos() {
		return permissoesacoesobjetos;
	}

	public UsuarioVO getUsuariotmp() {
		return usuariotmp;
	}

	public void setUsuariotmp(UsuarioVO usuariotmp) {
		this.usuariotmp = usuariotmp;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public GrupoEmpresaVO getGrupoempresasessao() {
		return grupoempresasessao;
	}

	public void setGrupoempresasessao(GrupoEmpresaVO grupoempresasessao) {
		this.grupoempresasessao = grupoempresasessao;
	}
}
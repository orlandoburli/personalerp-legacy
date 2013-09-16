package br.com.orlandoburli.personalerp.facades.geral;

import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.PerfilVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilAcaoObjetoVO;
import br.com.orlandoburli.core.vo.acesso.PermissaoPerfilObjetoVO;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class SessionDataFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private UsuarioVO usuariosessao;
	private PerfilVO perfilsessao;
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private List<PermissaoPerfilObjetoVO> permissoesobjetos;
	private List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos;
	
	public void execute() {
		List<Object> list = new ArrayList<Object>();
		list.add(usuariosessao);
		list.add(perfilsessao);
		list.add(empresasessao);
		list.add(lojasessao);
		write(Utils.voToXml(list));
	}
	
	public void permissoes() {
		List<Object> list = new ArrayList<Object>();
		list.addAll(getPermissoesobjetos());
		list.addAll(getPermissoesacoesobjetos());
		write(Utils.voToXml(list));
	}

	public void setUsuarioSessao(UsuarioVO usuario) {
		this.usuariosessao = usuario;
	}

	public UsuarioVO getUsuarioSessao() {
		return usuariosessao;
	}

	public void setPerfilSessao(PerfilVO perfilsessao) {
		this.perfilsessao = perfilsessao;
	}

	public PerfilVO getPerfilSessao() {
		return perfilsessao;
	}

	public void setEmpresaSessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresaSessao() {
		return empresasessao;
	}

	public void setLojaSessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public LojaVO getLojaSessao() {
		return lojasessao;
	}

	public void setPermissoesobjetos(List<PermissaoPerfilObjetoVO> permissoesobjetos) {
		this.permissoesobjetos = permissoesobjetos;
	}

	public List<PermissaoPerfilObjetoVO> getPermissoesobjetos() {
		return permissoesobjetos;
	}

	public void setPermissoesacoesobjetos(List<PermissaoPerfilAcaoObjetoVO> permissoesacoesobjetos) {
		this.permissoesacoesobjetos = permissoesacoesobjetos;
	}

	public List<PermissaoPerfilAcaoObjetoVO> getPermissoesacoesobjetos() {
		return permissoesacoesobjetos;
	}
}
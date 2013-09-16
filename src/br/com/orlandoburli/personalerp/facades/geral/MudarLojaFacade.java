package br.com.orlandoburli.personalerp.facades.geral;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaDAO;
import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaLojaDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaLojaVO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

public class MudarLojaFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private UsuarioVO usuariosessao;

	private Integer CodigoEmpresa;
	private Integer CodigoLoja;

	@OutjectSession
	private EmpresaVO empresasessao;
	@OutjectSession
	private LojaVO lojasessao;
	@OutjectSession
	private GrupoEmpresaVO grupoempresasessao;

	@IgnoreMethodAuthentication
	public void execute() {
		System.out.println("mudando...");
		GenericDAO dao = new GenericDAO();
		dao.setAutoCommit(false);

		try {

			EmpresaVO empresa = new EmpresaVO();
			empresa.setCodigoEmpresa(getCodigoEmpresa());
			empresa = (EmpresaVO) dao.get(empresa);

			LojaVO loja = new LojaVO();
			loja.setCodigoEmpresa(getCodigoEmpresa());
			loja.setCodigoLoja(getCodigoLoja());
			loja = (LojaVO) dao.get(loja);

			if (loja != null && empresa != null) {
				setEmpresasessao(empresa);
				setLojasessao(loja);
			}

			dao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			dao.rollback();
		}
	}

	@IgnoreMethodAuthentication
	public void lojas() {
		try {

			List<LojaVO> listLojas = new LojaDAO().getListLojasUsuario(usuariosessao, null);
			write(Utils.voToXml(listLojas));

		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			dispatch();
			return;
		}

	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public Integer getCodigoEmpresa() {
		return CodigoEmpresa;
	}

	public void setCodigoEmpresa(Integer codigoEmpresa) {
		CodigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoLoja() {
		return CodigoLoja;
	}

	public void setCodigoLoja(Integer codigoLoja) {
		CodigoLoja = codigoLoja;
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

	public GrupoEmpresaVO getGrupoempresasessao() {
		return grupoempresasessao;
	}

	public void setGrupoempresasessao(GrupoEmpresaVO grupoempresasessao) {
		this.grupoempresasessao = grupoempresasessao;
	}
}
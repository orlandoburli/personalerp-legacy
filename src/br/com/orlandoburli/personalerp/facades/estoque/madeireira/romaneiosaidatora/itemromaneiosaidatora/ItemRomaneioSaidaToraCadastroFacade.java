package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiosaidatora.itemromaneiosaidatora;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiosaidatora.ItemRomaneioSaidaToraDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiosaidatora.ItemRomaneioSaidaToraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;

public class ItemRomaneioSaidaToraCadastroFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	@InstantiateObject
	private ItemRomaneioToraVO voTora;
	@InstantiateObject
	private ItemRomaneioToraDAO daoTora;
	@InstantiateObject
	private ItemRomaneioSaidaToraVO vo;
	@InstantiateObject
	private ItemRomaneioSaidaToraDAO dao;
	
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private UsuarioVO usuariosessao;
	
	public ItemRomaneioSaidaToraCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@IgnoreMethodAuthentication
	public void visualizar() {
		List<ItemRomaneioToraVO> list;
		try {
			list = daoTora.getList(voTora);
			if (list.size() == 1) {
				if (list.get(0).getFlagBaixaTora() != null && list.get(0).getFlagBaixaTora().equalsIgnoreCase("S")) {
					writeErrorMessage("Plaqueta já baixada na serragem!", "NumeroPlaquetaRomaneioTora");
				} else {
					write(Utils.voToXml(list.get(0)));
				}
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void inserir() {
		try {
			voTora = daoTora.get(voTora);
			if (voTora != null) {
				// Primeiro, baixa a plaqueta
				voTora = daoTora.get(voTora);
				voTora.setFlagBaixaTora("V"); // V - Flag de baixa por venda
				voTora.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				voTora.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				voTora.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				
				daoTora.persist(voTora);
				
				vo.setNewRecord(true);
				vo.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				vo.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				vo.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				
				dao.persist(vo);
				write("ok");
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void excluir() {
		try {
			voTora = daoTora.get(voTora);
			if (voTora != null) {
				// Primeiro, baixa a plaqueta
				voTora = daoTora.get(voTora);
				voTora.setFlagBaixaTora("N"); // N - Flag de nao baixa
				voTora.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				voTora.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				voTora.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
				
				daoTora.persist(voTora);
				
				vo = dao.get(vo);
				
				dao.remove(vo);
				
				write("ok");
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	public void setEmpresasessao(EmpresaVO empresasessao) {
		this.empresasessao = empresasessao;
	}

	public EmpresaVO getEmpresasessao() {
		return empresasessao;
	}

	public void setLojasessao(LojaVO lojasessao) {
		this.lojasessao = lojasessao;
	}

	public LojaVO getLojasessao() {
		return lojasessao;
	}

	public void setUsuariosessao(UsuarioVO usuariosessao) {
		this.usuariosessao = usuariosessao;
	}

	public UsuarioVO getUsuariosessao() {
		return usuariosessao;
	}

	public void setVo(ItemRomaneioSaidaToraVO vo) {
		this.vo = vo;
	}

	public ItemRomaneioSaidaToraVO getVo() {
		return vo;
	}

	public void setDao(ItemRomaneioSaidaToraDAO dao) {
		this.dao = dao;
	}

	public ItemRomaneioSaidaToraDAO getDao() {
		return dao;
	}

	public void setVoTora(ItemRomaneioToraVO voTora) {
		this.voTora = voTora;
	}

	public ItemRomaneioToraVO getVoTora() {
		return voTora;
	}

	public void setDaoTora(ItemRomaneioToraDAO daoTora) {
		this.daoTora = daoTora;
	}

	public ItemRomaneioToraDAO getDaoTora() {
		return daoTora;
	}
}
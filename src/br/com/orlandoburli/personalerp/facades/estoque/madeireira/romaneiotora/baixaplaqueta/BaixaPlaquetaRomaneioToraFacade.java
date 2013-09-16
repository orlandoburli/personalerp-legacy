package br.com.orlandoburli.personalerp.facades.estoque.madeireira.romaneiotora.baixaplaqueta;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.cadastro.madeireira.GerenteMadeireiraDAO;
import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.ItemRomaneioToraDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.vo.estoque.madeireira.romaneiotora.ItemRomaneioToraVO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;

public class BaixaPlaquetaRomaneioToraFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;
	
	private ItemRomaneioToraVO vo;
	private ItemRomaneioToraDAO dao;
	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private UsuarioVO usuariosessao;
	private Date DataBaixa;
	
	@InstantiateObject
	private GerenteMadeireiraVO gerente; 
	
	public BaixaPlaquetaRomaneioToraFacade() {
		this.vo = new ItemRomaneioToraVO();
		this.dao = new ItemRomaneioToraDAO();
	}
	
	@IgnoreMethodAuthentication
	public void visualizar() {
		List<ItemRomaneioToraVO> list;
		try {
			list = dao.getList(vo);
			if (list.size() == 1) {
				write(Utils.voToXml(list.get(0)));
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	@IgnoreMethodAuthentication
	public void gerentes() {
		try {
			List<GerenteMadeireiraVO> list = new GerenteMadeireiraDAO().getList(new GerenteMadeireiraVO());
			write(Utils.voToXml(list));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
	
	public void execute() {
		if (getDataBaixa() == null) {
			writeErrorMessage("Informe a data da baixa", "DataBaixa");
			return;
		}
		List<ItemRomaneioToraVO> list;
		try {
			list = dao.getList(vo);
			if (list.size() == 1) {
				vo = list.get(0);
				if (vo.getFlagBaixaTora() != null && vo.getFlagBaixaTora().equalsIgnoreCase("S")) {
					writeErrorMessage("Plaqueta já baixada!", "NumeroPlaquetaRomaneioTora");
				} else if (vo.getFlagBaixaTora() != null && vo.getFlagBaixaTora().equalsIgnoreCase("V")) {
					writeErrorMessage("Plaqueta baixada por venda!", "NumeroPlaquetaRomaneioTora");
				} else {
					vo.setFlagBaixaTora("S");
					vo.setDataHoraBaixaRomaneio(Utils.getNow());
					vo.setDataBaixaItemRomaneio(getDataBaixa());
					
					vo.setCodigoEmpresaUsuarioBaixaRomaneio(getUsuariosessao().getCodigoEmpresa());
					vo.setCodigoLojaUsuarioBaixaRomaneio(getUsuariosessao().getCodigoLoja());
					vo.setCodigoUsuarioBaixaRomaneio(getUsuariosessao().getCodigoUsuario());
					
					vo.setCodigoEmpresaGerente(gerente.getCodigoEmpresa());
					vo.setCodigoLojaGerente(gerente.getCodigoLoja());
					vo.setCodigoGerente(gerente.getCodigoGerente());
					
					dao.persist(vo);
					write("ok");
				}
			} else {
				writeErrorMessage("Plaqueta não encontrada!", "NumeroPlaquetaRomaneioTora");
			}
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}

	public void setVo(ItemRomaneioToraVO vo) {
		this.vo = vo;
	}

	public ItemRomaneioToraVO getVo() {
		return vo;
	}

	public void setDao(ItemRomaneioToraDAO dao) {
		this.dao = dao;
	}

	public ItemRomaneioToraDAO getDao() {
		return dao;
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

	public void setGerente(GerenteMadeireiraVO gerente) {
		this.gerente = gerente;
	}

	public GerenteMadeireiraVO getGerente() {
		return gerente;
	}

	public void setDataBaixa(Date dataBaixa) {
		DataBaixa = dataBaixa;
	}

	public Date getDataBaixa() {
		return DataBaixa;
	}
}
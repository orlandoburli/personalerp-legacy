package br.com.orlandoburli.personalerp.facades.vendas.metavendas.metavenda;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.ItemMetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.NivelMetaVendaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.ItemMetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.NivelMetaVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MetaVendaCadastroFacade extends BaseCadastroFlexFacade<MetaVendaVO, MetaVendaDAO>{

	private static final long serialVersionUID = 1L;
	
	@OutjectSession
	private List<ItemMetaVendaVO> metasValor;
	@OutjectSession
	private List<ItemMetaVendaVO> metasQuantidade;
	
	private Integer Posicao;
	@Precision(decimals=2)
	private BigDecimal NovoValor;
	private String Lista;
	private String Campo;

	public MetaVendaCadastroFacade() {
		addNewValidator(new NotEmptyValidator("DataInicialMetaVenda", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("DataFinalMetaVenda", "Data Final"));
	}
	
	@Override
	public void doAfterSave() throws SQLException {
		// Salva os itens de meta
		for (ItemMetaVendaVO item : metasQuantidade) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setCodigoMetaVenda(getVo().getCodigoMetaVenda());
			getGenericDao().persist(item);
		}
		for (ItemMetaVendaVO item : metasValor) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setCodigoMetaVenda(getVo().getCodigoMetaVenda());
			getGenericDao().persist(item);
		}
		
//		this.metasQuantidade = new ArrayList<ItemMetaVendaVO>();
//		this.metasValor = new ArrayList<ItemMetaVendaVO>();
		
		for (ItemMetaVendaVO item : metasQuantidade) {
			item.setCodigoEmpresa(null);
			item.setCodigoLoja(null);
			item.setCodigoMetaVenda(null);
			item.setNewRecord(true);
		}
		for (ItemMetaVendaVO item : metasValor) {
			item.setCodigoEmpresa(null);
			item.setCodigoLoja(null);
			item.setCodigoMetaVenda(null);
			item.setNewRecord(true);
		}
		
		super.doAfterSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		ItemMetaVendaVO filterValor = new ItemMetaVendaVO();
		
		filterValor.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filterValor.setCodigoLoja(getVo().getCodigoLoja());
		filterValor.setCodigoMetaVenda(getVo().getCodigoMetaVenda());
		
		List<IValueObject> list = getGenericDao().getList(filterValor);
		
		for (IValueObject item : list) {
			getGenericDao().remove(item);
		}
		
		return super.doBeforeDelete();
	}
	
	@IgnoreMethodAuthentication
	public void clearmetas() {
		metasValor = new ArrayList<ItemMetaVendaVO>();
		metasQuantidade = new ArrayList<ItemMetaVendaVO>();
		
		ItemMetaVendaDAO itemMetaDao = new ItemMetaVendaDAO();
		itemMetaDao.setAutoCommit(false);
		
		try {
			if (getVo().getCodigoMetaVenda() != null) {
				setVo((MetaVendaVO) getGenericDao().get(getVo()));
			}
			
			if (getVo() != null && getVo().getCodigoMetaVenda() != null) {
				// Se nao for uma nova meta, ler as listas
				ItemMetaVendaVO filterValor = new ItemMetaVendaVO();
				filterValor.setCodigoEmpresa(getVo().getCodigoEmpresa());
				filterValor.setCodigoLoja(getVo().getCodigoLoja());
				filterValor.setCodigoMetaVenda(getVo().getCodigoMetaVenda());
				filterValor.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_VALOR);
				
				metasValor = itemMetaDao.getList(filterValor);
				
				// Ordena o array pelo peso
				Collections.sort(metasValor, new Comparator<ItemMetaVendaVO>() {
					@Override
					public int compare(ItemMetaVendaVO o1, ItemMetaVendaVO o2) {
						return o1.getPesoNivelMetaVenda().compareTo(o2.getPesoNivelMetaVenda());
					}
				});
				
				ItemMetaVendaVO filterQuantidade = new ItemMetaVendaVO();
				filterQuantidade.setCodigoEmpresa(getVo().getCodigoEmpresa());
				filterQuantidade.setCodigoLoja(getVo().getCodigoLoja());
				filterQuantidade.setCodigoMetaVenda(getVo().getCodigoMetaVenda());
				filterQuantidade.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_QUANTIDADE);
				
				metasQuantidade = itemMetaDao.getList(filterQuantidade);
				
				// Ordena o array pelo nome do grupo
				Collections.sort(metasQuantidade, new Comparator<ItemMetaVendaVO>() {
					@Override
					public int compare(ItemMetaVendaVO o1, ItemMetaVendaVO o2) {
						return o1.getNomeGrupo().compareTo(o2.getNomeGrupo());
					}
				});
				
				itemMetaDao.commit();
			}
		} catch (SQLException e) {
			itemMetaDao.rollback();
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void metasvalor() {
		try {
			if (metasValor == null || metasValor.size() == 0) {
				metasValor = new ArrayList<ItemMetaVendaVO>();
				
				// Adiciona os itens de acordo com os niveis
				List<NivelMetaVendaVO> niveis = new NivelMetaVendaDAO().getList(null, "PesoNivelMetaVenda");
				
				for (NivelMetaVendaVO nivel : niveis) {
					ItemMetaVendaVO item = new ItemMetaVendaVO();
					item.setNewRecord(true);
					
					item.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_VALOR);
					item.setValorMetaVenda(BigDecimal.ZERO);
					item.setPremioMetaVenda(BigDecimal.ZERO);
					
					item.setCodigoNivelMetaVenda(nivel.getCodigoNivelMetaVenda());
					item.setNomeNivelMetaVenda(nivel.getNomeNivelMetaVenda());
					item.setPesoNivelMetaVenda(nivel.getPesoNivelMetaVenda());
					
					metasValor.add(item);
				}
			}
			write(Utils.voToXml(metasValor, metasValor.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void metasquantidade() {
		try {
			if (metasQuantidade == null || metasQuantidade.size() == 0) {
				metasQuantidade = new ArrayList<ItemMetaVendaVO>();
				
				List<GrupoVO> grupos = new GrupoDAO().getList(null, "NomeGrupo");
				
				for (GrupoVO grupo : grupos) {
					ItemMetaVendaVO item = new ItemMetaVendaVO();
					item.setNewRecord(true);
					
					item.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_QUANTIDADE);
					item.setValorMetaVenda(BigDecimal.ZERO);
					item.setPremioMetaVenda(BigDecimal.ZERO);
					
					item.setReferenciaMetaVenda(grupo.getCodigoGrupo());
					item.setNomeGrupo(grupo.getNomeGrupo());
					
					metasQuantidade.add(item);
				}
			}
			write(Utils.voToXml(metasQuantidade, metasQuantidade.size()));
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void alteraritem() {
		if (getPosicao() != null && getPosicao() >= 0) {
			if (getLista().equals("quantidade")) {
				if (getCampo().equals("valormeta")) {
					metasQuantidade.get(getPosicao()).setValorMetaVenda(getNovoValor());
				} else if (getCampo().equals("valorcomissao")) {
					metasQuantidade.get(getPosicao()).setPremioMetaVenda(getNovoValor());
				}
			} else if (getLista().equals("valor")) {
				if (getCampo().equals("valormeta")) {
					metasValor.get(getPosicao()).setValorMetaVenda(getNovoValor());
				} else if (getCampo().equals("valorcomissao")) {
					metasValor.get(getPosicao()).setPremioMetaVenda(getNovoValor());
				}
			}
			write("ok");
		} else {
			writeErrorMessage("Posição não encontrada!");
		}
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		return super.doBeforeSave();
	}
	
	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			VendedorVO filter = new VendedorVO();
			filter.setSituacaoVendedor("N"); // Ativo
			List<VendedorVO> list = new VendedorDAO().getList(filter);
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setMetasValor(List<ItemMetaVendaVO> metasValor) {
		this.metasValor = metasValor;
	}

	public List<ItemMetaVendaVO> getMetasValor() {
		return metasValor;
	}

	public void setMetasQuantidade(List<ItemMetaVendaVO> metasQuantidade) {
		this.metasQuantidade = metasQuantidade;
	}

	public List<ItemMetaVendaVO> getMetasQuantidade() {
		return metasQuantidade;
	}

	public void setPosicao(Integer posicao) {
		Posicao = posicao;
	}

	public Integer getPosicao() {
		return Posicao;
	}

	public void setNovoValor(BigDecimal novoValor) {
		NovoValor = novoValor;
	}

	public BigDecimal getNovoValor() {
		return NovoValor;
	}

	public void setLista(String lista) {
		Lista = lista;
	}

	public String getLista() {
		return Lista;
	}

	public void setCampo(String campo) {
		Campo = campo;
	}

	public String getCampo() {
		return Campo;
	}
}
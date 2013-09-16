package br.com.orlandoburli.personalerp.facades.reports.vendas.pedidos;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import net.sf.jasperreports.engine.JRDataSource;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.ItemMetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasGrupoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.view.vendas.VendasGrupoView;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.vendas.comissoes.auxiliares.ItemComissaoVendedorVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.ItemMetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

@IgnoreFacadeAuthentication
public class RelatorioMetaVendasFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;

	private Integer CodigoEmpresaVendedor;
	private Integer CodigoLojaVendedor;
	private Integer CodigoVendedor;

	private String TipoRelatorio;
	
	private String TodasLojas;
	
	public RelatorioMetaVendasFacade() {
		super();
	}

	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			write(Utils.voToXml(new VendedorDAO().getList()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JRDataSource getDataSource() {
		return null;
	//	return new JRBeanCollectionDataSource(getListDS());
	}

	public List<?> getListDS() {
		GenericDAO dao = new GenericDAO();

		try {
			BigDecimal cem = new BigDecimal(100);

			dao.setAutoCommit(false);
			dao.getConnection();

			// Monta lista de datas
			List<Calendar> datas = new ArrayList<Calendar>();
			Calendar calendarUtil = Utils.dateToCalendar(getDataInicial());
			Calendar calendarFinal = Utils.dateToCalendar(getDataFinal());

			while (calendarUtil.before(calendarFinal) || !calendarUtil.after(calendarFinal)) {
				datas.add((Calendar) calendarUtil.clone());
				calendarUtil.add(Calendar.DAY_OF_MONTH, 1);
			}

			List<ItemComissaoVendedorVO> listVendasFinal = new ArrayList<ItemComissaoVendedorVO>();

			VendasGrupoDAO vendasDao = new VendasGrupoDAO();
			vendasDao.mergeDAO(dao);

			GrupoDAO grupoDao = new GrupoDAO();
			grupoDao.mergeDAO(dao);

			VendasGrupoView filterViewVendas = new VendasGrupoView();

			filterViewVendas.setCodigoEmpresaVendedor(getCodigoEmpresaVendedor());
			filterViewVendas.setCodigoLojaVendedor(getCodigoLojaVendedor());
			filterViewVendas.setCodigoVendedor(getCodigoVendedor());
			
			if (getTodasLojas() == null || getTodasLojas().equals("N")) {
				filterViewVendas.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				filterViewVendas.setCodigoLoja(getLojasessao().getCodigoLoja());
			}

			vendasDao.setSpecialCondition(" DataVenda >= '" + getDataInicial() + "' AND DataVenda <= '" + getDataFinal() + "'");
			List<VendasGrupoView> vendasView = vendasDao.getList(filterViewVendas, "DataVenda, NomeVendedor, NomeGrupo");

			List<GrupoVO> grupos = grupoDao.getList(null, "NomeGrupo");

			for (final Calendar data : datas) {

				Predicate filtroData = new Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						if (!(arg0 instanceof VendasGrupoView)) {
							return false;
						}
						VendasGrupoView item = (VendasGrupoView) arg0;

						if (item.getDataVenda().compareTo(Utils.calendarToDate(data)) == 0) {
							return true;
						}
						return false;
					}
				};

				List<VendasGrupoView> listaFiltradaDia = new ArrayList<VendasGrupoView>();
				CollectionUtils.select(vendasView, filtroData, listaFiltradaDia);

				// Percorre todas as vendas
				for (final VendasGrupoView venda : listaFiltradaDia) {
					// Verifica se ja tem esse vendedor neste dia
					// Filtro para vendedor / dia
					Predicate filtroVendedorDia = new Predicate() {
						@Override
						public boolean evaluate(Object arg0) {
							if (!(arg0 instanceof ItemComissaoVendedorVO)) {
								return false;
							}
							ItemComissaoVendedorVO item2 = (ItemComissaoVendedorVO) arg0;
							if (item2.getCodigoEmpresaVendedor().equals(venda.getCodigoEmpresaVendedor()) &&
									item2.getCodigoLojaVendedor().equals(venda.getCodigoLojaVendedor()) &&
									item2.getCodigoVendedor().equals(venda.getCodigoVendedor()) &&
									item2.getDataVenda().compareTo(venda.getDataVenda()) == 0) {
								return true;
							}
							return false;
						}
					};

					ItemComissaoVendedorVO itemComissao = null;
					itemComissao = (ItemComissaoVendedorVO) CollectionUtils.find(listVendasFinal, filtroVendedorDia);

					if (itemComissao == null) {
						itemComissao = new ItemComissaoVendedorVO();
						itemComissao.setCodigoEmpresa(venda.getCodigoEmpresa());
						itemComissao.setCodigoLoja(venda.getCodigoLoja());
						itemComissao.setNomeLoja("???");
						
						itemComissao.setDataVenda(venda.getDataVenda());

						itemComissao.setCodigoEmpresaVendedor(venda.getCodigoEmpresaVendedor());
						itemComissao.setCodigoLojaVendedor(venda.getCodigoLojaVendedor());
						itemComissao.setCodigoVendedor(venda.getCodigoVendedor());

						itemComissao.setNomeVendedor(venda.getNomeVendedor());
						itemComissao.setAllZeros();
						itemComissao.setValorTotalVendas(BigDecimal.ZERO);
						listVendasFinal.add(itemComissao);
					}

					// Seta o grupo
					int posicao = 0;
					if (venda.getCodigoGrupo() != null && !venda.getCodigoGrupo().equals(0)) {
						for (GrupoVO grupo : grupos) {
							posicao++;
							itemComissao.setGrupoVenda(grupo.getCodigoGrupo(), grupo.getNomeGrupo(), posicao);

							if (grupo.getCodigoGrupo().equals(venda.getCodigoGrupo())) {
								itemComissao.setGrupoVenda(grupo.getCodigoGrupo(), grupo.getNomeGrupo(), venda.getTotalPecas(), posicao);
								itemComissao.setValorTotalVendas(itemComissao.getValorTotalVendas().add(venda.getValorTotal()));
							}
						}
					} else {
						itemComissao.setValorTotalVendas(itemComissao.getValorTotalVendas().add(venda.getValorTotal()));
					}
				}
			}

			// Total
			List<ItemComissaoVendedorVO> listTotal = new ArrayList<ItemComissaoVendedorVO>();
			for (final ItemComissaoVendedorVO itemLoop : listVendasFinal) {
				Predicate filtroVendedor = new Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						if (!(arg0 instanceof ItemComissaoVendedorVO)) {
							return false;
						}
						ItemComissaoVendedorVO compare = (ItemComissaoVendedorVO) arg0;
						if (compare.getNomeVendedor().equals(itemLoop.getNomeVendedor())) {
							return true;
						}
						return false;
					}
				};

				ItemComissaoVendedorVO itemComissao = null;

				itemComissao = (ItemComissaoVendedorVO) CollectionUtils.find(listTotal, filtroVendedor);

				if (itemComissao == null) {
					itemComissao = new ItemComissaoVendedorVO();
					itemComissao.setDataVenda(null);
					itemComissao.setNomeVendedor(itemLoop.getNomeVendedor());
					itemComissao.setCodigoEmpresaVendedor(itemLoop.getCodigoEmpresaVendedor());
					itemComissao.setCodigoLojaVendedor(itemLoop.getCodigoLojaVendedor());
					itemComissao.setCodigoVendedor(itemLoop.getCodigoVendedor());
					itemComissao.setAllZeros();
					itemComissao.setValorTotalVendas(BigDecimal.ZERO);
					listTotal.add(itemComissao);
				}

				// Grupos
				itemComissao.setNomeGrupo01(itemLoop.getNomeGrupo01());
				itemComissao.setNomeGrupo02(itemLoop.getNomeGrupo02());
				itemComissao.setNomeGrupo03(itemLoop.getNomeGrupo03());
				itemComissao.setNomeGrupo04(itemLoop.getNomeGrupo04());
				itemComissao.setNomeGrupo05(itemLoop.getNomeGrupo05());
				itemComissao.setNomeGrupo06(itemLoop.getNomeGrupo06());
				itemComissao.setNomeGrupo07(itemLoop.getNomeGrupo07());

				itemComissao.setCodigoGrupo01(itemLoop.getCodigoGrupo01());
				itemComissao.setCodigoGrupo02(itemLoop.getCodigoGrupo02());
				itemComissao.setCodigoGrupo03(itemLoop.getCodigoGrupo03());
				itemComissao.setCodigoGrupo04(itemLoop.getCodigoGrupo04());
				itemComissao.setCodigoGrupo05(itemLoop.getCodigoGrupo05());
				itemComissao.setCodigoGrupo06(itemLoop.getCodigoGrupo06());
				itemComissao.setCodigoGrupo07(itemLoop.getCodigoGrupo07());

				// Seta o grupo
				itemComissao.setQuantidadeGrupo01(itemComissao.getQuantidadeGrupo01().add(itemLoop.getQuantidadeGrupo01()));
				itemComissao.setQuantidadeGrupo02(itemComissao.getQuantidadeGrupo02().add(itemLoop.getQuantidadeGrupo02()));
				itemComissao.setQuantidadeGrupo03(itemComissao.getQuantidadeGrupo03().add(itemLoop.getQuantidadeGrupo03()));
				itemComissao.setQuantidadeGrupo04(itemComissao.getQuantidadeGrupo04().add(itemLoop.getQuantidadeGrupo04()));
				itemComissao.setQuantidadeGrupo05(itemComissao.getQuantidadeGrupo05().add(itemLoop.getQuantidadeGrupo05()));
				itemComissao.setQuantidadeGrupo06(itemComissao.getQuantidadeGrupo06().add(itemLoop.getQuantidadeGrupo06()));
				itemComissao.setQuantidadeGrupo07(itemComissao.getQuantidadeGrupo07().add(itemLoop.getQuantidadeGrupo07()));

				// Valor
				itemComissao.setValorTotalVendas(itemComissao.getValorTotalVendas().add(itemLoop.getValorTotalVendas()));
			}

			MetaVendaDAO metaDao = new MetaVendaDAO();
			metaDao.mergeDAO(dao);

			ItemMetaVendaDAO itemMetaDao = new ItemMetaVendaDAO();
			itemMetaDao.mergeDAO(dao);

			// Busca metas para cada vendedor
			for (ItemComissaoVendedorVO item : listTotal) {

				// Busca meta para o vendedor
				MetaVendaVO meta = new MetaVendaVO();
				meta.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				meta.setCodigoLoja(getLojasessao().getCodigoLoja());
				meta.setDataInicialMetaVenda(getDataInicial());
				meta.setDataFinalMetaVenda(getDataFinal());

				meta.setCodigoEmpresaVendedor(item.getCodigoEmpresaVendedor());
				meta.setCodigoLojaVendedor(item.getCodigoLojaVendedor());
				meta.setCodigoVendedor(item.getCodigoVendedor());

				List<MetaVendaVO> listTmp = metaDao.getList(meta);

				if (listTmp.size() > 0) {
					meta = listTmp.get(0);
				} else {
					meta = null;
				}

				if (meta == null) {
					// Se nao achou, tenta buscar a meta generica
					meta = new MetaVendaVO();
					meta.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
					meta.setCodigoLoja(getLojasessao().getCodigoLoja());
					meta.setDataInicialMetaVenda(getDataInicial());
					meta.setDataFinalMetaVenda(getDataFinal());
					meta.setTipoMetaVenda("V"); // Vendedor
					metaDao.setSpecialCondition("CodigoVendedor IS NULL");

					listTmp = metaDao.getList(meta);

					metaDao.setSpecialCondition(null);

					if (listTmp.size() > 0) {
						meta = listTmp.get(0);
					} else {
						meta = null;
					}
				}

				// Lancamento das metas
				if (meta != null) {
					// Busca itens da meta (Valor)
					ItemMetaVendaVO itemMetaFilter = new ItemMetaVendaVO();
					itemMetaFilter.setCodigoEmpresa(meta.getCodigoEmpresa());
					itemMetaFilter.setCodigoLoja(meta.getCodigoLoja());
					itemMetaFilter.setCodigoMetaVenda(meta.getCodigoMetaVenda());
					itemMetaFilter.setTipoItemMetaVenda("V");

					List<ItemMetaVendaVO> itensValor = itemMetaDao.getList(itemMetaFilter, "PesoNivelMetaVenda DESC");

					itemMetaFilter.setTipoItemMetaVenda("Q");

					// Busca itens da meta (Quantidade/Grupo)
					List<ItemMetaVendaVO> itensQuantidade = itemMetaDao.getList(itemMetaFilter);

					// Calcula meta em valor
					ItemMetaVendaVO itemMetaValor = null;
					for (ItemMetaVendaVO itemMeta : itensValor) {
						itemMetaValor = itemMeta;
						// if
						// (itemMeta.getValorMetaVenda().compareTo(item.getValorTotalVendas())
						// >= 0) {
						if (item.getValorTotalVendas().compareTo(itemMeta.getValorMetaVenda()) >= 0) {
							break;
						}
					}

					// Calcula comissao (valores)
					item.setValorPremioVendas(item.getValorTotalVendas().multiply(itemMetaValor.getPremioMetaVenda()).divide(cem, 2, BigDecimal.ROUND_CEILING));
					item.setPercentualComissaoVendas(itemMetaValor.getPremioMetaVenda().divide(cem, 4, BigDecimal.ROUND_CEILING));

					// Calcula premios (quantidades)
					for (ItemMetaVendaVO itemQuantidade : itensQuantidade) {
						for (int i = 1; i < grupos.size() + 1; i++) {
							if (itemQuantidade.getReferenciaMetaVenda().equals(item.getCodigoGrupo(i))) {
								item.setMetaGrupo(itemQuantidade.getValorMetaVenda(), i);

								if (item.getQuantidadeGrupo(i).compareTo(item.getMetaGrupo(i)) >= 0) {
									item.setPremioGrupo(itemQuantidade.getPremioMetaVenda(), i);
								} else {
									item.setPremioGrupo(BigDecimal.ZERO, i);
								}
							}
						}
					}
				}
			}

			// Busca meta para a loja
			MetaVendaVO meta = new MetaVendaVO();
			meta.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
			meta.setCodigoLoja(getLojasessao().getCodigoLoja());
			meta.setDataInicialMetaVenda(getDataInicial());
			meta.setDataFinalMetaVenda(getDataFinal());
			meta.setTipoMetaVenda("G"); // Gerente
			
			List<MetaVendaVO> listTmp = metaDao.getList(meta);
			
			if (listTmp.size() > 0) {
				meta = listTmp.get(0);
			} else {
				meta = null;
			}

			// Dos totalizadores, faz o total da loja no periodo.
			ItemComissaoVendedorVO itemTotalLoja = new ItemComissaoVendedorVO();
			itemTotalLoja.setAllZeros();
			itemTotalLoja.setValorTotalVendas(BigDecimal.ZERO);
			itemTotalLoja.setValorPremioVendas(BigDecimal.ZERO);

			for (ItemComissaoVendedorVO item : listTotal) {

				for (int i = 1; i < grupos.size() + 1; i++) {
					// itemTotalLoja.setGrupoVenda(item.getCodigoGrupo(i),
					// item.getNomeGrupo(i), item.getQuantidadeGrupo(i), i);
					if (item.getCodigoGrupo(i) != null && item.getNomeGrupo(i) != null) {
						itemTotalLoja.setGrupoVenda(item.getCodigoGrupo(i), item.getNomeGrupo(i), i);
					}
					itemTotalLoja.setQuantidadeGrupo(itemTotalLoja.getQuantidadeGrupo(i).add(item.getQuantidadeGrupo(i)), i);
				}

				itemTotalLoja.setValorTotalVendas(itemTotalLoja.getValorTotalVendas().add(item.getValorTotalVendas()));
			}

			if (meta != null) {
				ItemMetaVendaVO itemMetaFilter = new ItemMetaVendaVO();
				itemMetaFilter.setCodigoEmpresa(meta.getCodigoEmpresa());
				itemMetaFilter.setCodigoLoja(meta.getCodigoLoja());
				itemMetaFilter.setCodigoMetaVenda(meta.getCodigoMetaVenda());
				itemMetaFilter.setTipoItemMetaVenda("V");

				List<ItemMetaVendaVO> itensValor = itemMetaDao.getList(itemMetaFilter, "PesoNivelMetaVenda DESC");

				itemMetaFilter.setTipoItemMetaVenda("Q");

				// Busca itens da meta (Quantidade/Grupo)
				List<ItemMetaVendaVO> itensQuantidade = itemMetaDao.getList(itemMetaFilter);

				// Calcula meta em valor
				ItemMetaVendaVO itemMetaValor = null;
				for (ItemMetaVendaVO itemMeta : itensValor) {
					itemMetaValor = itemMeta;
					if (itemTotalLoja.getValorTotalVendas().compareTo(itemMeta.getValorMetaVenda()) >= 0) {
						break;
					}
				}

				// Calcula comissao (valores)
				itemTotalLoja.setValorPremioVendas(itemTotalLoja.getValorTotalVendas().multiply(itemMetaValor.getPremioMetaVenda()).divide(cem, 2, BigDecimal.ROUND_CEILING));
				itemTotalLoja.setPercentualComissaoVendas(itemMetaValor.getPremioMetaVenda().divide(cem, 4, BigDecimal.ROUND_CEILING));

				// Calcula premios (quantidades)
				for (ItemMetaVendaVO itemQuantidade : itensQuantidade) {
					for (int i = 1; i < grupos.size() + 1; i++) {
						if (itemQuantidade.getReferenciaMetaVenda().equals(itemTotalLoja.getCodigoGrupo(i))) {
							itemTotalLoja.setMetaGrupo(itemQuantidade.getValorMetaVenda(), i);

							if (itemTotalLoja.getQuantidadeGrupo(i).compareTo(itemTotalLoja.getMetaGrupo(i)) >= 0) {
								itemTotalLoja.setPremioGrupo(itemQuantidade.getPremioMetaVenda(), i);
							} else {
								itemTotalLoja.setPremioGrupo(BigDecimal.ZERO, i);
							}
						}
					}
				}
			}

			listTotal.add(itemTotalLoja);

			// Adiciona os totalizadores
			listVendasFinal.addAll(listTotal);

			vendasDao.commit();

			if (getTipoRelatorio().equals("S")) {
				//return new JRBeanCollectionDataSource(listTotal);
				return listTotal;
			} else {
				//return new JRBeanCollectionDataSource(listVendasFinal);
				return listVendasFinal;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			dao.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
			dao.rollback();
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		super.doParameter(parameters);
		parameters.put("NomeEmpresa", getEmpresasessao().getRazaoSocialEmpresa());
		parameters.put("NomeLoja", getLojasessao().getNomeLoja());
		
		parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
		parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		
		parameters.put("TipoRelatorio", getTipoRelatorio());
		
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioMetaVenda2.jasper";
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setCodigoEmpresaVendedor(Integer codigoEmpresaVendedor) {
		CodigoEmpresaVendedor = codigoEmpresaVendedor;
	}

	public Integer getCodigoEmpresaVendedor() {
		return CodigoEmpresaVendedor;
	}

	public void setCodigoLojaVendedor(Integer codigoLojaVendedor) {
		CodigoLojaVendedor = codigoLojaVendedor;
	}

	public Integer getCodigoLojaVendedor() {
		return CodigoLojaVendedor;
	}

	public void setCodigoVendedor(Integer codigoVendedor) {
		CodigoVendedor = codigoVendedor;
	}

	public Integer getCodigoVendedor() {
		return CodigoVendedor;
	}

	public void setTipoRelatorio(String tipoRelatorio) {
		TipoRelatorio = tipoRelatorio;
	}

	public String getTipoRelatorio() {
		return TipoRelatorio;
	}

	public void setTodasLojas(String todasLojas) {
		TodasLojas = todasLojas;
	}

	public String getTodasLojas() {
		return TodasLojas;
	}
}
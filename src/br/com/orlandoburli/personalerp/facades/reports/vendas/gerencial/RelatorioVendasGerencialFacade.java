package br.com.orlandoburli.personalerp.facades.reports.vendas.gerencial;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.estoque.GrupoDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.ItemMetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasGrupoDAO;
import br.com.orlandoburli.core.view.vendas.VendasGrupoView;
import br.com.orlandoburli.core.vo.estoque.GrupoVO;
import br.com.orlandoburli.core.vo.vendas.comissoes.auxiliares.ItemComissaoVendedorVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.ItemMetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioVendasGerencialFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioVendasGerencial.jasper";
	}

	public JRDataSource getDataSource() {
		GenericDAO dao = new GenericDAO();

		try {
			BigDecimal cem = new BigDecimal(100);

			dao.setAutoCommit(false);
			dao.getConnection();

			List<ItemComissaoVendedorVO> listVendasGeral = new ArrayList<ItemComissaoVendedorVO>();

			VendasGrupoDAO vendasDao = new VendasGrupoDAO();
			vendasDao.mergeDAO(dao);

			GrupoDAO grupoDao = new GrupoDAO();
			grupoDao.mergeDAO(dao);
			
			VendasGrupoView filterViewVendas = new VendasGrupoView();

			vendasDao.setSpecialCondition(" DataVenda >= '" + getDataInicial() + "' AND DataVenda <= '" + getDataFinal() + "'");
			List<VendasGrupoView> vendasView = vendasDao.getList(filterViewVendas, "CodigoEmpresa, CodigoLoja, NomeVendedor, NomeGrupo");

			List<GrupoVO> grupos = grupoDao.getList(null, "NomeGrupo");

			for (final VendasGrupoView venda : vendasView) {

				ItemComissaoVendedorVO itemComissao = null;
				
				Predicate filtroVendedorLoja = new Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						ItemComissaoVendedorVO comparador = (ItemComissaoVendedorVO) arg0;
						if (comparador.getCodigoEmpresa().equals(venda.getCodigoEmpresa()) &&
							comparador.getCodigoLoja().equals(venda.getCodigoLoja()) &&
							comparador.getCodigoEmpresaVendedor().equals(venda.getCodigoEmpresaVendedor()) &&
							comparador.getCodigoLojaVendedor().equals(venda.getCodigoLojaVendedor()) &&
							comparador.getCodigoVendedor().equals(venda.getCodigoVendedor())) {
							return true;
						}
						return false;
					}
				};
				itemComissao = (ItemComissaoVendedorVO) CollectionUtils.find(listVendasGeral, filtroVendedorLoja);

				if (itemComissao == null) {
					itemComissao = new ItemComissaoVendedorVO();
					itemComissao.setCodigoEmpresa(venda.getCodigoEmpresa());
					itemComissao.setCodigoLoja(venda.getCodigoLoja());
					itemComissao.setNomeLoja(venda.getNomeLoja());

					itemComissao.setCodigoEmpresaVendedor(venda.getCodigoEmpresaVendedor());
					itemComissao.setCodigoLojaVendedor(venda.getCodigoLojaVendedor());
					itemComissao.setCodigoVendedor(venda.getCodigoVendedor());

					itemComissao.setNomeVendedor(venda.getNomeVendedor());
					itemComissao.setAllZeros();
					itemComissao.setValorTotalVendas(BigDecimal.ZERO);
					listVendasGeral.add(itemComissao);
				}

				// Seta o grupo
				int posicao = 0;
				if (venda.getCodigoGrupo() != null) {
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

			// TOTALIZACAO POR LOJA
			List<ItemComissaoVendedorVO> listTotal = new ArrayList<ItemComissaoVendedorVO>();
			
			for (final ItemComissaoVendedorVO itemLoop : listVendasGeral) {
				Predicate filtroLoja = new Predicate() {
					@Override
					public boolean evaluate(Object arg0) {
						if (!(arg0 instanceof ItemComissaoVendedorVO)) {
							return false;
						}
						ItemComissaoVendedorVO compare = (ItemComissaoVendedorVO) arg0;
						if (compare.getCodigoEmpresa().equals(itemLoop.getCodigoEmpresa()) &&
							compare.getCodigoLoja().equals(itemLoop.getCodigoLoja())) {
							return true;
						}
						return false;
					}
				};

				ItemComissaoVendedorVO itemComissao = null;

				itemComissao = (ItemComissaoVendedorVO) CollectionUtils.find(listTotal, filtroLoja);

				if (itemComissao == null) {
					itemComissao = new ItemComissaoVendedorVO();
					itemComissao.setCodigoEmpresa(itemLoop.getCodigoEmpresa());
					itemComissao.setCodigoLoja(itemLoop.getCodigoLoja());
					itemComissao.setNomeLoja(itemLoop.getNomeLoja());
					
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
			} // for (final ItemComissaoVendedorVO itemLoop : listVendasGeral) {

			MetaVendaDAO metaDao = new MetaVendaDAO();
			metaDao.mergeDAO(dao);

			ItemMetaVendaDAO itemMetaDao = new ItemMetaVendaDAO();
			itemMetaDao.mergeDAO(dao);

			// Busca metas para cada loja
			for (ItemComissaoVendedorVO item : listTotal) {
				item.setCodigoEmpresa(null);
				item.setCodigoLoja(null);

				// Busca meta para a loja
				MetaVendaVO meta = new MetaVendaVO();
				meta.setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
				meta.setCodigoLoja(getLojasessao().getCodigoLoja());
				meta.setDataInicialMetaVenda(getDataInicial());
				meta.setDataFinalMetaVenda(getDataFinal());
				meta.setTipoMetaVenda("G");
				
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
					itemTotalLoja.setGrupoVenda(item.getCodigoGrupo(i), item.getNomeGrupo(i), i);
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
			listVendasGeral.addAll(listTotal);
			
			vendasDao.commit();
			
			return new JRBeanCollectionDataSource(listVendasGeral);
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
		parameters.put("DataInicial", getDataInicial());
		parameters.put("DataFinal", getDataFinal());
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
}

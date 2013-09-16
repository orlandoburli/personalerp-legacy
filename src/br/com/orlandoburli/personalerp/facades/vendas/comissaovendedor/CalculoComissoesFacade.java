package br.com.orlandoburli.personalerp.facades.vendas.comissaovendedor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import br.com.orlandoburli.core.dao.GenericDAO;
import br.com.orlandoburli.core.dao.vendas.comissoes.ComissaoVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.ItemMetaVendaDAO;
import br.com.orlandoburli.core.dao.vendas.metavendas.MetaVendaDAO;
import br.com.orlandoburli.core.dao.view.vendas.VendasVendedorGrupoDAO;
import br.com.orlandoburli.core.view.vendas.VendasVendedorGrupoView;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.vo.vendas.comissoes.ComissaoVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.ItemMetaVendaVO;
import br.com.orlandoburli.core.vo.vendas.metavendas.MetaVendaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;

@IgnoreFacadeAuthentication
public class CalculoComissoesFacade extends BaseFacade {

	private static final long serialVersionUID = 1L;

	private Date DataInicial;
	private Date DataFinal;
	private String TodasLojas;

	private EmpresaVO empresasessao;
	private LojaVO lojasessao;
	private List<String> mensagens;

	public void execute() {
		BigDecimal cem = new BigDecimal(100);

		GenericDAO dao = new GenericDAO();

		try {
			dao.setAutoCommit(false);
			dao.getConnection();

			mensagens = new ArrayList<String>();

			VendasVendedorGrupoDAO vendasDao = new VendasVendedorGrupoDAO();
			vendasDao.mergeDAO(dao);

			MetaVendaDAO metaDao = new MetaVendaDAO();
			metaDao.mergeDAO(dao);

			ItemMetaVendaDAO itemMetaDao = new ItemMetaVendaDAO();
			itemMetaDao.mergeDAO(dao);

			ComissaoVendaDAO comissaoDao = new ComissaoVendaDAO();
			comissaoDao.mergeDAO(dao);

			limparComissoes(comissaoDao, getDataInicial(), getDataFinal());

			// Busca as metas de venda no periodo
			StringBuilder sb = new StringBuilder();
			sb.append(" DataInicialMetaVenda >= '" + DataInicial.toString() + "' AND ");
			sb.append(" DataFinalMetaVenda <= '" + DataFinal.toString() + "' ");

			metaDao.setSpecialCondition(sb.toString());

			// Busca os vendedores
			VendedorVO filterVendedor = new VendedorVO();
			filterVendedor.setSituacaoVendedor("N"); // Somente Normal (Ativos)

			List<IValueObject> vendedores = dao.getList(filterVendedor);

			for (IValueObject v : vendedores) {
				VendedorVO vendedor = (VendedorVO) v;

				// Busca vendas do vendedor, no periodo definido pela meta
				VendasVendedorGrupoView filterVendas = new VendasVendedorGrupoView();
				filterVendas.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
				filterVendas.setCodigoLojaVendedor(vendedor.getCodigoLoja());
				filterVendas.setCodigoVendedor(vendedor.getCodigoVendedor());

				List<VendasVendedorGrupoView> vendasVendedor = vendasDao.getList(filterVendas);

				BigDecimal valorTotalVendas = BigDecimal.ZERO;

				for (VendasVendedorGrupoView venda : vendasVendedor) {
					valorTotalVendas = valorTotalVendas.add(venda.getValorTotalPedido());
				}

				// Busca a meta para este vendedor
				MetaVendaVO meta = new MetaVendaVO();
				meta.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
				meta.setCodigoLojaVendedor(vendedor.getCodigoLoja());
				meta.setCodigoVendedor(vendedor.getCodigoVendedor());

				List<MetaVendaVO> metas = metaDao.getList(meta);

				meta = null;

				if (metas.size() == 0) {
					// Busca sem o vendedor
					meta = new MetaVendaVO();
					metas = metaDao.getList(meta);
					if (metas.size() > 0) {
						meta = metas.get(0);
					}
				} else {
					meta = metas.get(0);
				}

				if (meta == null) {
					mensagens.add("Meta não encontrada para o vendedor \"" + vendedor.getNomeVendedor() + "\"");
				} else {
					// Busca itens das metas - VALOR
					ItemMetaVendaVO filterItemMeta = new ItemMetaVendaVO();
					filterItemMeta.setCodigoEmpresa(meta.getCodigoEmpresa());
					filterItemMeta.setCodigoLoja(meta.getCodigoLoja());
					filterItemMeta.setCodigoMetaVenda(meta.getCodigoMetaVenda());
					filterItemMeta.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_VALOR);

					List<ItemMetaVendaVO> listMeta = itemMetaDao.getList(filterItemMeta, "ValorMetaVenda DESC");

					ItemMetaVendaVO itemMetaValor = null;

					for (ItemMetaVendaVO item : listMeta) {
						// Verifica, a partir da maior meta, se ela foi batida.
						itemMetaValor = item;
						if (itemMetaValor.getValorMetaVenda().compareTo(valorTotalVendas) <= 0) {
							break;
						}
					}

					// Cria a comissão por valor
					ComissaoVendaVO comissaoValor = new ComissaoVendaVO();
					comissaoValor.setNewRecord(true);
					comissaoValor.setCodigoEmpresa(itemMetaValor.getCodigoEmpresa());
					comissaoValor.setCodigoLoja(itemMetaValor.getCodigoLoja());
					comissaoValor.setCodigoMetaVenda(itemMetaValor.getCodigoMetaVenda());
					comissaoValor.setCodigoItemMetaVenda(itemMetaValor.getCodigoItemMetaVenda());

					comissaoValor.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
					comissaoValor.setCodigoLojaVendedor(vendedor.getCodigoLoja());
					comissaoValor.setCodigoVendedor(vendedor.getCodigoVendedor());

					comissaoValor.setValorAtingidoMetaVenda(valorTotalVendas);
					comissaoValor.setValorComissaoVenda(valorTotalVendas.multiply(itemMetaValor.getPremioMetaVenda()).divide(cem, 2));

					dao.persist(comissaoValor);
					
					// Busca itens das metas - QUANTIDADE
					filterItemMeta = new ItemMetaVendaVO();
					filterItemMeta.setCodigoEmpresa(meta.getCodigoEmpresa());
					filterItemMeta.setCodigoLoja(meta.getCodigoLoja());
					filterItemMeta.setCodigoMetaVenda(meta.getCodigoMetaVenda());
					filterItemMeta.setTipoItemMetaVenda(ItemMetaVendaVO.TIPO_META_QUANTIDADE);

					List<ItemMetaVendaVO> listMetaQtd = itemMetaDao.getList(filterItemMeta, "ReferenciaMetaVenda");

					for (final ItemMetaVendaVO itemMetaQtd : listMetaQtd) {
						// Busca o grupo nas vendas do vendedor
						VendasVendedorGrupoView venda = (VendasVendedorGrupoView) CollectionUtils.find(vendasVendedor, new Predicate() {
							
							@Override
							public boolean evaluate(Object arg0) {
								VendasVendedorGrupoView v = (VendasVendedorGrupoView) arg0;
								if (v.getCodigoGrupo() != null && itemMetaQtd.getReferenciaMetaVenda() != null) {
									return v.getCodigoGrupo().equals(itemMetaQtd.getReferenciaMetaVenda());
								}
								return false;
							}
						});
						
						// Se nao tem venda nesse grupo, pula para o próximo
//						if (venda == null) {
//							continue;
//						}
						// Verifica se bateu a meta de cada grupo
						ComissaoVendaVO comissaoGrupo = new ComissaoVendaVO();
						comissaoGrupo.setNewRecord(true);
						comissaoGrupo.setCodigoEmpresa(itemMetaQtd.getCodigoEmpresa());
						comissaoGrupo.setCodigoLoja(itemMetaQtd.getCodigoLoja());
						comissaoGrupo.setCodigoMetaVenda(itemMetaQtd.getCodigoMetaVenda());
						comissaoGrupo.setCodigoItemMetaVenda(itemMetaQtd.getCodigoItemMetaVenda());

						comissaoGrupo.setCodigoEmpresaVendedor(vendedor.getCodigoEmpresa());
						comissaoGrupo.setCodigoLojaVendedor(vendedor.getCodigoLoja());
						comissaoGrupo.setCodigoVendedor(vendedor.getCodigoVendedor());

						if (venda == null) {
							comissaoGrupo.setValorAtingidoMetaVenda(BigDecimal.ZERO);
							comissaoGrupo.setValorComissaoVenda(BigDecimal.ZERO);
						} else if (venda.getValorTotalPedido().compareTo(itemMetaQtd.getValorMetaVenda()) < 0 ) {
							comissaoGrupo.setValorAtingidoMetaVenda(venda.getValorTotalPedido());
							comissaoGrupo.setValorComissaoVenda(BigDecimal.ZERO);
						} else {
							comissaoGrupo.setValorAtingidoMetaVenda(venda.getTotalQuantidadePedido());
							comissaoGrupo.setValorComissaoVenda(itemMetaQtd.getPremioMetaVenda());
						}
						
						dao.persist(comissaoGrupo);
					}
				}
			}

			dao.commit();

			// Escreve as mensagens na tela...
			for (String mensagem : mensagens) {
				System.out.println(mensagem);
			}

			write("ok");
		} catch (SQLException ex) {
			writeErrorMessage(ex.getMessage());
			ex.printStackTrace();
			dao.rollback();
		} catch (ClassNotFoundException e) {
			dao.rollback();
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	private void limparComissoes(ComissaoVendaDAO dao, Date dataInicial, Date dataFinal) throws SQLException {
		StringBuilder sb = new StringBuilder();
//		sb.append(" ");
//		sb.append(" DataInicialReferencia >= '" + DataInicial.toString() + "' AND ");
//		sb.append(" DataFinalReferencia <= '" + DataFinal.toString() + "' ");

		dao.setSpecialCondition(sb.toString());

		List<ComissaoVendaVO> comissoes = dao.getList();

		for (ComissaoVendaVO comissao : comissoes) {
			dao.remove(comissao);
		}
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

	public void setTodasLojas(String todasLojas) {
		TodasLojas = todasLojas;
	}

	public String getTodasLojas() {
		return TodasLojas;
	}
}
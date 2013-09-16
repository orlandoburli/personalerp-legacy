package br.com.orlandoburli.personalerp.facades.bi.vendadiaria;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import br.com.orlandoburli.core.dao.bi.vendas.ProdutoVendaDiariaDAO;
import br.com.orlandoburli.core.dao.bi.vendas.VendaDiariaDAO;
import br.com.orlandoburli.core.dao.bi.vendas.auxiliar.AuxVendasDiariaDAO;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.bi.vendas.ProdutoVendaDiariaVO;
import br.com.orlandoburli.core.vo.bi.vendas.VendaDiariaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreFacadeAuthentication;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

@IgnoreFacadeAuthentication
public class VendaDiariaCadastroFacade extends BaseCadastroFlexFacade<VendaDiariaVO, VendaDiariaDAO> {

	private static final long serialVersionUID = 1L;

	@OutjectSession
	private List<ProdutoVendaDiariaVO> itens;

	@OutjectSession
	// Array de itens que serão excluídos ao fechamento do pedido.
	private List<ProdutoVendaDiariaVO> itensExclusao;

	private Integer PosicaoItem;
	@InstantiateObject
	private ProdutoVO produto;
	@InstantiateObject
	private ProdutoVendaDiariaVO item;

	private Date DataInicial;
	private Date DataFinal;

	private String FlagTodasLojas;

	@IgnoreMethodAuthentication
	public void processar() {
		try {

			AuxVendasDiariaDAO auxDao = new AuxVendasDiariaDAO();

			Integer codigoEmpresa = null;
			Integer codigoLoja = null;

			if (getFlagTodasLojas() == null || getFlagTodasLojas().equals("N")) {
				codigoEmpresa = getEmpresasessao().getCodigoEmpresa();
				codigoLoja = getLojasessao().getCodigoLoja();
			}

			RowSet rowset = auxDao.getVendas(codigoEmpresa, codigoLoja, getDataInicial(), getDataFinal());
			rowset.beforeFirst();

			getGenericDao().setAutoCommit(false);

			Date dataUltimaVenda = null;

			while (rowset.next()) {
				VendaDiariaVO venda = new VendaDiariaVO();
				venda.setCodigoEmpresa(rowset.getInt(1));
				venda.setCodigoLoja(rowset.getInt(2));
				venda.setDataVenda(rowset.getDate(3));

				venda = (VendaDiariaVO) getGenericDao().get(venda);

				if (venda == null) {
					venda = new VendaDiariaVO();
					venda.setNewRecord(true);
					venda.setCodigoEmpresa(rowset.getInt(1));
					venda.setCodigoLoja(rowset.getInt(2));
					venda.setDataVenda(rowset.getDate(3));
				}

				venda.setValorVenda(rowset.getBigDecimal(4));

				getGenericDao().persist(venda);

				RowSet itens = auxDao.getItemVendasDia(venda.getCodigoEmpresa(), venda.getCodigoLoja(), venda.getDataVenda());

				itens.beforeFirst();

				while (itens.next()) {
					ProdutoVendaDiariaVO produto = new ProdutoVendaDiariaVO();

					produto.setCodigoEmpresa(itens.getInt(1));
					produto.setCodigoLoja(itens.getInt(2));
					produto.setDataVenda(itens.getDate(3));
					produto.setCodigoEmpresaProduto(itens.getInt(4));
					produto.setCodigoLojaProduto(itens.getInt(5));
					produto.setCodigoProduto(itens.getInt(6));

					produto = (ProdutoVendaDiariaVO) getGenericDao().get(produto);

					if (produto == null) {
						produto = new ProdutoVendaDiariaVO();
						produto.setNewRecord(true);

						produto.setCodigoEmpresa(itens.getInt(1));
						produto.setCodigoLoja(itens.getInt(2));
						produto.setDataVenda(itens.getDate(3));
						produto.setCodigoEmpresaProduto(itens.getInt(4));
						produto.setCodigoLojaProduto(itens.getInt(5));
						produto.setCodigoProduto(itens.getInt(6));
					}

					produto.setQuantidadeProduto(itens.getBigDecimal(11));
					produto.setValorProduto(itens.getBigDecimal(12));
					// produto.setQuantidadeDisplayProduto(itens.getBigDecimal(9));
					// produto.setQuantidadeGavetaProduto(itens.getBigDecimal(8));

					getGenericDao().persist(produto);
				}

				dataUltimaVenda = venda.getDataVenda();
			}

			// Pega a data da ultima venda e atualiza o estoque
			// lista de lojas
			List<LojaVO> lojas = null;
			if (getFlagTodasLojas() == null || getFlagTodasLojas().equals("N")) {
				lojas = new ArrayList<LojaVO>();
				lojas.add(getLojasessao());
			} else {
				lojas = new LojaDAO().getList();
			}

			EstoqueDAO estoqueDAO = new EstoqueDAO();
			estoqueDAO.mergeDAO(getGenericDao());

			for (LojaVO loja : lojas) {
				VendaDiariaVO venda = new VendaDiariaVO();
				venda.setCodigoEmpresa(loja.getCodigoEmpresa());
				venda.setCodigoLoja(loja.getCodigoLoja());
				venda.setDataVenda(dataUltimaVenda);
				venda = (VendaDiariaVO) getGenericDao().get(venda);

				if (venda != null) {
					// Busca todos os produtos / estoques
					EstoqueVO estoqueFilter = new EstoqueVO();
					estoqueFilter.setCodigoEmpresaEstoque(venda.getCodigoEmpresa());
					estoqueFilter.setCodigoLojaEstoque(venda.getCodigoLoja());

					List<EstoqueVO> estoques = estoqueDAO.getList(estoqueFilter);

					for (EstoqueVO estoque : estoques) {
						ProdutoVendaDiariaVO produto = new ProdutoVendaDiariaVO();
						produto.setCodigoEmpresa(venda.getCodigoEmpresa());
						produto.setCodigoLoja(venda.getCodigoLoja());
						produto.setDataVenda(venda.getDataVenda());
						produto.setCodigoEmpresaProduto(estoque.getCodigoEmpresa());
						produto.setCodigoLojaProduto(estoque.getCodigoLoja());
						produto.setCodigoProduto(estoque.getCodigoProduto());

						produto = (ProdutoVendaDiariaVO) getGenericDao().get(produto);

						if (produto == null) {
							produto = new ProdutoVendaDiariaVO();
							produto.setNewRecord(true);
							produto.setCodigoEmpresa(venda.getCodigoEmpresa());
							produto.setCodigoLoja(venda.getCodigoLoja());
							produto.setDataVenda(venda.getDataVenda());
							produto.setCodigoEmpresaProduto(estoque.getCodigoEmpresa());
							produto.setCodigoLojaProduto(estoque.getCodigoLoja());
							produto.setCodigoProduto(estoque.getCodigoProduto());
							produto.setQuantidadeProduto(BigDecimal.ZERO);
							produto.setValorProduto(BigDecimal.ZERO);
						}

						produto.setQuantidadeDisplayProduto(estoque.getQuantidadeDisplayEstoque());
						produto.setQuantidadeGavetaProduto(estoque.getQuantidadeGavetaEstoque());

						getGenericDao().persist(produto);
					}
				}
			}

			getGenericDao().commit();

			write("ok");
		} catch (SQLException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}

	}

	@Override
	public void doAfterSave() throws SQLException {
		for (ProdutoVendaDiariaVO item : itens) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setDataVenda(getVo().getDataVenda());
			getGenericDao().persist(item);
		}

		for (ProdutoVendaDiariaVO item : itensExclusao) {
			getGenericDao().remove(item);
		}
		super.doAfterSave();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		if (getVo().getCodigoEmpresa() == null || getVo().getCodigoLoja() == null || getVo().getDataVenda() == null) {
			return false;
		}
		
		loaditens();
		for (ProdutoVendaDiariaVO item : itens) {
			getGenericDao().remove(item);
		}
		return super.doBeforeDelete();
	}

	@IgnoreMethodAuthentication
	public void listaritens() {
		write(Utils.voToXml(itens));
	}

	@IgnoreMethodAuthentication
	public void loaditens() {
		ProdutoVendaDiariaVO itemFilter = new ProdutoVendaDiariaVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setDataVenda(getVo().getDataVenda());

		try {
			this.itens = new ProdutoVendaDiariaDAO().getList(itemFilter);
			listaritens();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void adicionaritem() {
		try {
			//
			if (item.getCodigoEmpresaProduto() == null || item.getCodigoLojaProduto() == null || item.getCodigoProduto() == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}

			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
			produto.setCodigoLoja(item.getCodigoLojaProduto());
			produto.setCodigoProduto(item.getCodigoProduto());

			produto = (ProdutoVO) getGenericDao().get(produto);

			if (produto == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}
			item.setNomeProduto(produto.getDescricaoProduto());
			item.setNewRecord(true);

			itens.add(item);
			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void alteraritem() {
		if (PosicaoItem == null || PosicaoItem < 0) {
			writeErrorMessage("Selecione um item para alterar!");
			return;
		}

		if (PosicaoItem >= itens.size()) {
			writeErrorMessage("Posição inválida!");
			return;
		}

		try {
			//
			if (item.getCodigoEmpresaProduto() == null || item.getCodigoLojaProduto() == null || item.getCodigoProduto() == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}

			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
			produto.setCodigoLoja(item.getCodigoLojaProduto());
			produto.setCodigoProduto(item.getCodigoProduto());

			produto = (ProdutoVO) getGenericDao().get(produto);

			if (produto == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}

			item.setNewRecord(false);

			itens.get(PosicaoItem).setNomeProduto(produto.getDescricaoProduto());
			itens.get(PosicaoItem).setCodigoEmpresaProduto(item.getCodigoEmpresaProduto());
			itens.get(PosicaoItem).setCodigoLojaProduto(item.getCodigoLojaProduto());
			itens.get(PosicaoItem).setCodigoProduto(item.getCodigoProduto());
			itens.get(PosicaoItem).setQuantidadeProduto(item.getQuantidadeProduto());
			itens.get(PosicaoItem).setValorProduto(item.getValorProduto());
			itens.get(PosicaoItem).setQuantidadeDisplayProduto(item.getQuantidadeDisplayProduto());
			itens.get(PosicaoItem).setQuantidadeGavetaProduto(item.getQuantidadeGavetaProduto());

			write("ok");
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void removeritem() {
		if (PosicaoItem == null || PosicaoItem < 0) {
			writeErrorMessage("Selecione um item para excluir!");
			return;
		}

		if (PosicaoItem >= itens.size()) {
			writeErrorMessage("Posição inválida!");
			return;
		}

		ProdutoVendaDiariaVO item = itens.get(PosicaoItem);

		if (item.IsNew()) {
			// Simplesmente remove
			itens.remove(item);
		} else {
			// Guarda na lista de itens a excluir
			itens.remove(item);
			itensExclusao.add(item);
		}

		write("ok");
	}

	@IgnoreMethodAuthentication
	public void limpar() {
		this.setItens(new ArrayList<ProdutoVendaDiariaVO>());
		this.setItensExclusao(new ArrayList<ProdutoVendaDiariaVO>());
	}

	@IgnoreMethodAuthentication
	public void dados() {
		try {
			// Limpa os dados anteriores...
			limpar();

			// List com todos os itens
			List<IValueObject> list = new ArrayList<IValueObject>();

			// Produtos
			ProdutoVO produto = new ProdutoVO();
			produto.setSituacaoProduto("N");

			list.addAll(getGenericDao().getList(produto));

			AuxVendasDiariaDAO auxDao = new AuxVendasDiariaDAO();
			auxDao.mergeDAO(getGenericDao());

			for (IValueObject i : list) {
				ProdutoVO prod = (ProdutoVO) i;
				// Estoque do dia anterior
				ProdutoVendaDiariaVO prodVd = auxDao.getProdutoByUltimaVenda(prod, getEmpresasessao().getCodigoEmpresa(), getLojasessao().getCodigoLoja());
				
				if (prodVd != null) {
					prod.setQuantidadeDisplayProduto(prodVd.getQuantidadeDisplayProduto());
					prod.setQuantidadeGavetaProduto(prodVd.getQuantidadeGavetaProduto());
				}

				// EstoqueVO estoque = new EstoqueVO();
				// estoque.setCodigoEmpresa(prod.getCodigoEmpresa());
				// estoque.setCodigoLoja(prod.getCodigoLoja());
				// estoque.setCodigoProduto(prod.getCodigoProduto());
				// estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
				// estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
				// estoque = (EstoqueVO) _dao.get(estoque);
				// if (estoque != null) {
				// prod.setQuantidadeDisplayProduto(estoque.getQuantidadeDisplayEstoque());
				// prod.setQuantidadeGavetaProduto(estoque.getQuantidadeGavetaEstoque());
				// }
			}

			// Vendedores
			VendedorVO vendedor = new VendedorVO();
			vendedor.setSituacaoVendedor("N"); // Somente ativos

			list.addAll(getGenericDao().getList(vendedor));

			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ProdutoVendaDiariaVO> getItens() {
		return itens;
	}

	public void setItens(List<ProdutoVendaDiariaVO> itens) {
		this.itens = itens;
	}

	public List<ProdutoVendaDiariaVO> getItensExclusao() {
		return itensExclusao;
	}

	public void setItensExclusao(List<ProdutoVendaDiariaVO> itensExclusao) {
		this.itensExclusao = itensExclusao;
	}

	public Integer getPosicaoItem() {
		return PosicaoItem;
	}

	public void setPosicaoItem(Integer posicaoItem) {
		PosicaoItem = posicaoItem;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public ProdutoVendaDiariaVO getItem() {
		return item;
	}

	public void setItem(ProdutoVendaDiariaVO item) {
		this.item = item;
	}

	public Date getDataInicial() {
		return DataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		DataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return DataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		DataFinal = dataFinal;
	}

	public String getFlagTodasLojas() {
		return FlagTodasLojas;
	}

	public void setFlagTodasLojas(String flagTodasLojas) {
		FlagTodasLojas = flagTodasLojas;
	}
}

package br.com.orlandoburli.personalerp.facades.estoque.troca.trocamercadoria;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.DaoUtils;
import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.MovimentacaoEstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.SubProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.troca.TrocaMercadoriaDAO;
import br.com.orlandoburli.core.dao.estoque.troca.TrocaMercadoriaEntradaDAO;
import br.com.orlandoburli.core.dao.estoque.troca.TrocaMercadoriaSaidaDAO;
import br.com.orlandoburli.core.dao.vendas.VendedorDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.Precision;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.SubProdutoVO;
import br.com.orlandoburli.core.vo.estoque.troca.TrocaMercadoriaEntradaVO;
import br.com.orlandoburli.core.vo.estoque.troca.TrocaMercadoriaSaidaVO;
import br.com.orlandoburli.core.vo.estoque.troca.TrocaMercadoriaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.vo.vendas.VendedorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;

public class TrocaMercadoriaCadastroFacade extends BaseCadastroFlexFacade<TrocaMercadoriaVO, TrocaMercadoriaDAO> {

	private static final long serialVersionUID = 1L;

	private String DataTroca;
	private String HoraTroca;

	private Integer CodigoEmpresaProduto;
	private Integer CodigoLojaProduto;
	private Integer CodigoProduto;

	private String TipoItem;

	private Integer PosicaoItem;

	private String FlagComissaoVendedorTrocaSaida;

	@Precision(decimals = 2)
	private BigDecimal QuantidadeItem;
	@Precision(decimals = 2)
	private BigDecimal ValorItem;

	@OutjectSession
	private List<TrocaMercadoriaEntradaVO> itensEntrada;
	@OutjectSession
	private List<TrocaMercadoriaEntradaVO> itensEntradaExclusao;

	@OutjectSession
	private List<TrocaMercadoriaSaidaVO> itensSaida;
	@OutjectSession
	private List<TrocaMercadoriaSaidaVO> itensSaidaExclusao;

	public TrocaMercadoriaCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);

		this.itensEntrada = new ArrayList<TrocaMercadoriaEntradaVO>();
		this.itensEntradaExclusao = new ArrayList<TrocaMercadoriaEntradaVO>();
		this.itensSaida = new ArrayList<TrocaMercadoriaSaidaVO>();
		this.itensSaidaExclusao = new ArrayList<TrocaMercadoriaSaidaVO>();
	}

	@Override
	public void doAfterSave() throws SQLException {
		// Salva os itens (entrada)
		for (TrocaMercadoriaEntradaVO item : itensEntrada) {
			if (item.IsNew()) {
				item.setCodigoEmpresa(getVo().getCodigoEmpresa());
				item.setCodigoLoja(getVo().getCodigoLoja());
				item.setCodigoTroca(getVo().getCodigoTroca());
			}
			getGenericDao().persist(item);
		}

		// Salva os itens (saida)
		for (TrocaMercadoriaSaidaVO item : itensSaida) {
			if (item.IsNew()) {
				item.setCodigoEmpresa(getVo().getCodigoEmpresa());
				item.setCodigoLoja(getVo().getCodigoLoja());
				item.setCodigoTroca(getVo().getCodigoTroca());
			}
			getGenericDao().persist(item);
		}

		// Exclui os itens (entrada)
		for (TrocaMercadoriaEntradaVO item : itensEntradaExclusao) {
			getGenericDao().remove(item);
		}

		// Exclui os itens (saida)
		for (TrocaMercadoriaSaidaVO item : itensSaidaExclusao) {
			getGenericDao().remove(item);
		}
		super.doAfterSave();
	}

	@Override
	public boolean doBeforeSave() throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			getVo().setDataHoraTroca(new Timestamp(sdf.parse(getDataTroca() + " " + getHoraTroca()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		if (this.itensEntrada.size() <= 0) {
			this.messages.add(new MessageVO("Nenhum item devolvido!"));
			return false;
		}

		if (this.itensSaida.size() <= 0) {
			this.messages.add(new MessageVO("Nenhum item entregue!"));
			return false;
		}
		
		BigDecimal totalEntrada = BigDecimal.ZERO;
		BigDecimal totalSaida = BigDecimal.ZERO;
		BigDecimal totalLiquido = BigDecimal.ZERO;
		
		for (TrocaMercadoriaEntradaVO entrada : itensEntrada) {
			totalEntrada = totalEntrada.add(entrada.getValorTrocaEntrada());
		}
		for (TrocaMercadoriaSaidaVO saida : itensSaida) {
			totalSaida = totalSaida.add(saida.getValorTrocaSaida());
		}
		
		totalLiquido = totalSaida.subtract(totalEntrada);
		
		if (totalLiquido.compareTo(BigDecimal.ZERO) < 0) {
			this.messages.add(new MessageVO("O total dos itens entregues ao cliente (saída)\nnão pode ser menor que o total de itens de entrada!"));
			return false;
		}

		return super.doBeforeSave();
	}

	@Override
	public boolean doBeforeUpdate() throws SQLException {
		TrocaMercadoriaVO clone = new TrocaMercadoriaVO();
		clone = (TrocaMercadoriaVO) getGenericDao().get(getVo());

		if (clone.getStatusTroca().equals("P")) {
			this.messages.add(new MessageVO("Esta troca já foi processada e não pode ser alterada!"));
			return false;
		}
		return super.doBeforeUpdate();
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		this.getVo().setStatusTroca("A");
		return super.doBeforeInsert();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		TrocaMercadoriaEntradaDAO entradaDao = new TrocaMercadoriaEntradaDAO();
		
		TrocaMercadoriaEntradaVO filterEntrada = new TrocaMercadoriaEntradaVO();
		filterEntrada.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filterEntrada.setCodigoLoja(getVo().getCodigoLoja());
		filterEntrada.setCodigoTroca(getVo().getCodigoTroca());
		
		entradaDao.mergeDAO(getGenericDao());
		entradaDao.removeByFilter(filterEntrada);
		
		TrocaMercadoriaSaidaVO filterSaida = new TrocaMercadoriaSaidaVO();
		filterSaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
		filterSaida.setCodigoLoja(getVo().getCodigoLoja());
		filterSaida.setCodigoTroca(getVo().getCodigoTroca());
		
		TrocaMercadoriaSaidaDAO saidaDao = new TrocaMercadoriaSaidaDAO();
		saidaDao.mergeDAO(getGenericDao());
		saidaDao.removeByFilter(filterSaida);
		
		return super.doBeforeDelete();
	}

	public void processar() {
		try {
			// Inicia a conexao
			getGenericDao().getConnection();
			getGenericDao().setAutoCommit(false);

			TrocaMercadoriaEntradaDAO entradaDao = new TrocaMercadoriaEntradaDAO();
			TrocaMercadoriaSaidaDAO saidaDao = new TrocaMercadoriaSaidaDAO();
			ProdutoDAO produtoDao = new ProdutoDAO();
			EstoqueDAO estoqueDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO movDao = new MovimentacaoEstoqueDAO();
			SubProdutoDAO subProdutoDao = new SubProdutoDAO();

			dao.mergeDAO(getGenericDao());
			entradaDao.mergeDAO(getGenericDao());
			saidaDao.mergeDAO(getGenericDao());
			produtoDao.mergeDAO(getGenericDao());
			estoqueDao.mergeDAO(getGenericDao());
			movDao.mergeDAO(getGenericDao());
			subProdutoDao.mergeDAO(getGenericDao());

			setVo(dao.get(getVo()));

			if (getVo().getStatusTroca().equals("P")) {
				writeErrorMessage("Este item já foi processado!");
				return;
			}

			// Processa as entradas
			// somente se o tipo for A (Adaptação)
			if (getVo().getMotivoTroca().equals("A")) {
				List<TrocaMercadoriaEntradaVO> entradas = entradaDao.getListByPK(getVo());
				for (TrocaMercadoriaEntradaVO entrada : entradas) {
					ProdutoVO produto = new ProdutoVO();
					produto.setCodigoEmpresa(entrada.getCodigoEmpresaProduto());
					produto.setCodigoLoja(entrada.getCodigoLojaProduto());
					produto.setCodigoProduto(entrada.getCodigoProduto());
					produto = produtoDao.get(produto);

					if (produto.getFlagTemSubProduto().equals("N")) {
						geraMovimentacao(getVo(), produto, MovimentacaoEstoqueVO.OPERACAO_ADICIONAR,
								entrada.getQuantidadeTrocaEntrada(), estoqueDao, movDao,
								"Movimentação gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					} else {
						List<SubProdutoVO> subprodutos = getSubProdutos(produto, subProdutoDao);

						for (SubProdutoVO subProduto : subprodutos) {

							ProdutoVO sub = new ProdutoVO();
							sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
							sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
							sub.setCodigoProduto(subProduto.getCodigoSubProduto());
							sub = produtoDao.get(sub);

							geraMovimentacao(getVo(), sub, MovimentacaoEstoqueVO.OPERACAO_ADICIONAR,
									entrada.getQuantidadeTrocaEntrada().multiply(subProduto.getQuantidadeSubProduto()),
									estoqueDao, movDao,
									"Movimentação gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
						}
					}
				}
			} // if (vo.getMotivoTroca().equals("A")) {
			// Fim processamento entradas

			// Processamento das saidas
			List<TrocaMercadoriaSaidaVO> saidas = saidaDao.getListByPK(getVo());
			for (TrocaMercadoriaSaidaVO saida : saidas) {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(saida.getCodigoEmpresaProduto());
				produto.setCodigoLoja(saida.getCodigoLojaProduto());
				produto.setCodigoProduto(saida.getCodigoProduto());
				produto = produtoDao.get(produto);

				if (produto.getFlagTemSubProduto().equals("N")) {
					geraMovimentacao(getVo(), produto, MovimentacaoEstoqueVO.OPERACAO_DIMINUIR, saida.getQuantidadeTrocaSaida(),
							estoqueDao, movDao,
							"Movimentação gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
				} else {
					List<SubProdutoVO> subprodutos = getSubProdutos(produto, subProdutoDao);

					for (SubProdutoVO subProduto : subprodutos) {

						ProdutoVO sub = new ProdutoVO();
						sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
						sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
						sub.setCodigoProduto(subProduto.getCodigoSubProduto());
						sub = produtoDao.get(sub);

						geraMovimentacao(getVo(), sub, MovimentacaoEstoqueVO.OPERACAO_DIMINUIR, saida.getQuantidadeTrocaSaida().multiply(subProduto.getQuantidadeSubProduto()),
								estoqueDao, movDao,
								"Movimentação gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					}
				}
			} // for (TrocaMercadoriaSaidaVO saida : saidas) {
			// Fim processamento saidas

			getVo().setStatusTroca("P");
			dao.persist(getVo());

			getGenericDao().commit();

			write("ok");
		} catch (ClassNotFoundException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			getGenericDao().rollback();
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			getGenericDao().rollback();
		}
	}

	public void estornar() {
		try {
			// Inicia a conexao
			getGenericDao().getConnection();
			getGenericDao().setAutoCommit(false);

			TrocaMercadoriaEntradaDAO entradaDao = new TrocaMercadoriaEntradaDAO();
			TrocaMercadoriaSaidaDAO saidaDao = new TrocaMercadoriaSaidaDAO();
			ProdutoDAO produtoDao = new ProdutoDAO();
			EstoqueDAO estoqueDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO movDao = new MovimentacaoEstoqueDAO();
			SubProdutoDAO subProdutoDao = new SubProdutoDAO();

			dao.mergeDAO(getGenericDao());
			entradaDao.mergeDAO(getGenericDao());
			saidaDao.mergeDAO(getGenericDao());
			produtoDao.mergeDAO(getGenericDao());
			estoqueDao.mergeDAO(getGenericDao());
			movDao.mergeDAO(getGenericDao());
			subProdutoDao.mergeDAO(getGenericDao());

			setVo(dao.get(getVo()));

			if (getVo().getStatusTroca().equals("A")) {
				writeErrorMessage("Este item não foi processado!");
				return;
			}

			// Processa as entradas
			// somente se o tipo for A (Adaptação)
			if (getVo().getMotivoTroca().equals("A")) {
				List<TrocaMercadoriaEntradaVO> entradas = entradaDao.getListByPK(getVo());
				for (TrocaMercadoriaEntradaVO entrada : entradas) {
					ProdutoVO produto = new ProdutoVO();
					produto.setCodigoEmpresa(entrada.getCodigoEmpresaProduto());
					produto.setCodigoLoja(entrada.getCodigoLojaProduto());
					produto.setCodigoProduto(entrada.getCodigoProduto());
					produto = produtoDao.get(produto);

					if (produto.getFlagTemSubProduto().equals("N")) {
						geraMovimentacao(getVo(), produto, MovimentacaoEstoqueVO.OPERACAO_DIMINUIR,
								entrada.getQuantidadeTrocaEntrada(), estoqueDao, movDao,
								"Estorno gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					} else {
						List<SubProdutoVO> subprodutos = getSubProdutos(produto, subProdutoDao);

						for (SubProdutoVO subProduto : subprodutos) {

							ProdutoVO sub = new ProdutoVO();
							sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
							sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
							sub.setCodigoProduto(subProduto.getCodigoSubProduto());
							sub = produtoDao.get(sub);

							geraMovimentacao(getVo(), sub, MovimentacaoEstoqueVO.OPERACAO_DIMINUIR,
									entrada.getQuantidadeTrocaEntrada().multiply(subProduto.getQuantidadeSubProduto()),
									estoqueDao, movDao,
									"Estorno gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
						}
					}
				}
			} // if (vo.getMotivoTroca().equals("A")) {
			// Fim processamento entradas

			// Processamento das saidas
			List<TrocaMercadoriaSaidaVO> saidas = saidaDao.getListByPK(getVo());
			for (TrocaMercadoriaSaidaVO saida : saidas) {
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(saida.getCodigoEmpresaProduto());
				produto.setCodigoLoja(saida.getCodigoLojaProduto());
				produto.setCodigoProduto(saida.getCodigoProduto());
				produto = produtoDao.get(produto);

				if (produto.getFlagTemSubProduto().equals("N")) {
					geraMovimentacao(getVo(), produto, MovimentacaoEstoqueVO.OPERACAO_ADICIONAR, saida.getQuantidadeTrocaSaida(),
							estoqueDao, movDao,
							"Estorno gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
				} else {
					List<SubProdutoVO> subprodutos = getSubProdutos(produto, subProdutoDao);

					for (SubProdutoVO subProduto : subprodutos) {

						ProdutoVO sub = new ProdutoVO();
						sub.setCodigoEmpresa(subProduto.getCodigoEmpresaSubProduto());
						sub.setCodigoLoja(subProduto.getCodigoLojaSubProduto());
						sub.setCodigoProduto(subProduto.getCodigoSubProduto());
						sub = produtoDao.get(sub);

						geraMovimentacao(getVo(), sub, MovimentacaoEstoqueVO.OPERACAO_ADICIONAR, saida.getQuantidadeTrocaSaida().multiply(subProduto.getQuantidadeSubProduto()),
								estoqueDao, movDao,
								"Estorno gerada pela troca de número " + getVo().getCodigoTroca() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					}
				}
			} // for (TrocaMercadoriaSaidaVO saida : saidas) {
			// Fim processamento saidas

			getVo().setStatusTroca("A");
			dao.persist(getVo());

			getGenericDao().commit();

			write("ok");
		} catch (ClassNotFoundException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			getGenericDao().rollback();
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			getGenericDao().rollback();
		}
	}

	private List<SubProdutoVO> getSubProdutos(ProdutoVO produto, SubProdutoDAO subProdutoDao) throws SQLException {
		if (produto.getFlagTemSubProduto().equals("S")) {
			SubProdutoVO filter = new SubProdutoVO();
			filter.setCodigoEmpresa(produto.getCodigoEmpresa());
			filter.setCodigoLoja(produto.getCodigoLoja());
			filter.setCodigoProduto(produto.getCodigoProduto());

			return subProdutoDao.getList(filter);
		}

		return null;
	}

	private void geraMovimentacao(TrocaMercadoriaVO troca, ProdutoVO produto, String operacao, BigDecimal quantidade, EstoqueDAO estoqueDao, MovimentacaoEstoqueDAO movDao, String mensagem) throws SQLException {
		EstoqueVO estoque = new EstoqueVO();
		estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
		estoque.setCodigoLoja(produto.getCodigoLoja());
		estoque.setCodigoProduto(produto.getCodigoProduto());
		estoque.setCodigoEmpresaEstoque(troca.getCodigoEmpresa());
		estoque.setCodigoLojaEstoque(troca.getCodigoLoja());

		estoque = estoqueDao.get(estoque);

		if (estoque == null) {
			estoque = new EstoqueVO();
			estoque.setNewRecord(true);

			estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
			estoque.setCodigoLoja(produto.getCodigoLoja());
			estoque.setCodigoProduto(produto.getCodigoProduto());
			estoque.setCodigoEmpresaEstoque(troca.getCodigoEmpresa());
			estoque.setCodigoLojaEstoque(troca.getCodigoLoja());

			estoque.setEstoqueFiscal(BigDecimal.ZERO);
			estoque.setEstoqueFisico(BigDecimal.ZERO);
		}
		
		if (estoque.getQuantidadeDisplayEstoque() == null) {
			estoque.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
		}
		if (estoque.getQuantidadeGavetaEstoque() == null) {
			estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
		}

		MovimentacaoEstoqueVO movimentacao = new MovimentacaoEstoqueVO();
		movimentacao.setNewRecord(true);
		movimentacao.setCodigoEmpresa(produto.getCodigoEmpresa());
		movimentacao.setCodigoLoja(produto.getCodigoLoja());
		movimentacao.setCodigoProduto(produto.getCodigoProduto());
		movimentacao.setCodigoEmpresaEstoque(troca.getCodigoEmpresa());
		movimentacao.setCodigoLojaEstoque(troca.getCodigoLoja());
		movimentacao.setDataHoraMovimentacaoEstoque(Utils.getNow());
		movimentacao.setOperacaoMovimentacaoEstoque(operacao);

		movimentacao.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
		movimentacao.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
		movimentacao.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

		movimentacao.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
		movimentacao.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());

		if (operacao.equals(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR)) {
			// Volta o estoque...
			estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(quantidade));
			movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());

			// Volta estoque fiscal
			estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(quantidade));
			movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
			
			// Adiciona ao estoque GAVETA
			estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().add(quantidade));
		}
		if (operacao.equals(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR)) {
			estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(quantidade));
			movimentacao.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());

			estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(quantidade));
			movimentacao.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
			
			/// ESTOQUE GAVETA / DISPLAY
			
			// Verifica se o estoque gaveta tem os itens...
			BigDecimal quantidadeRestante = quantidade;
			
			// Se o estoque gaveta tiver o valor maior ou igual do pedido, diminui da gaveta
			if (estoque.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) >= 0) {
				estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().subtract(quantidadeRestante));
				quantidadeRestante = BigDecimal.ZERO;
			} else if (estoque.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) < 0 && estoque.getQuantidadeGavetaEstoque().compareTo(BigDecimal.ZERO) > 0) {
				// Se o estoque gaveta tiver o valor menor, mas for maior que zero, diminui do estoque gaveta e deixa o resto para o display.
				quantidadeRestante = quantidadeRestante.subtract(estoque.getQuantidadeGavetaEstoque());
				estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
			}
			
			// Se ainda tem quantidade a subtrair, subtrai-se do estoque display
			if (quantidadeRestante.compareTo(BigDecimal.ZERO) > 0) {
				estoque.setQuantidadeDisplayEstoque(estoque.getQuantidadeDisplayEstoque().subtract(quantidadeRestante));
			}
		}

		movimentacao.setObservacaoMovimentacaoEstoque(mensagem);
		movimentacao.setDocumentoMovimentacaoEstoque(troca.getCodigoTroca() + "." + troca.getCodigoEmpresa() + "." + troca.getCodigoLoja());

		estoqueDao.persist(estoque);
		movDao.persist(movimentacao);
	}

	@IgnoreMethodAuthentication
	public void vendedores() {
		try {
			// Zera as listas
			limpar();
			
			VendedorVO filter = new VendedorVO();
			filter.setSituacaoVendedor("N"); // Normal / Ativo

			List<VendedorVO> vendedores = new VendedorDAO().getList(filter);
			write(Utils.voToXml(vendedores, vendedores.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoVO produto = new ProdutoVO();
			produto.setSituacaoProduto("N");
			List<IValueObject> list = getGenericDao().getList(produto);
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void totais() {
		BigDecimal totalEntrada = BigDecimal.ZERO;
		BigDecimal totalSaida = BigDecimal.ZERO;
		BigDecimal totalLiquido = BigDecimal.ZERO;
		
		for (TrocaMercadoriaEntradaVO entrada : itensEntrada) {
			totalEntrada = totalEntrada.add(entrada.getValorTrocaEntrada());
		}
		for (TrocaMercadoriaSaidaVO saida : itensSaida) {
			totalSaida = totalSaida.add(saida.getValorTrocaSaida());
		}
		
		totalLiquido = totalSaida.subtract(totalEntrada);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");
		sb.append("<totais>");

		sb.append("<totalentrada>");
		sb.append("<![CDATA[" + totalEntrada + "]]>");
		sb.append("</totalentrada>");

		sb.append("<totalsaida>");
		sb.append("<![CDATA[" + totalSaida + "]]>");
		sb.append("</totalsaida>");

		sb.append("<totalliquido>");
		sb.append("<![CDATA[" + totalLiquido + "]]>");
		sb.append("</totalliquido>");

		sb.append("</totais>");
		
		write(sb.toString());
	}

	@IgnoreMethodAuthentication
	public void loaditens() {
		this.itensEntrada = null;
		this.itensEntradaExclusao = new ArrayList<TrocaMercadoriaEntradaVO>();

		this.itensSaida = null;
		this.itensSaidaExclusao = new ArrayList<TrocaMercadoriaSaidaVO>();

		try {
			TrocaMercadoriaEntradaDAO entradaDao = new TrocaMercadoriaEntradaDAO();
			entradaDao.mergeDAO(getGenericDao());

			TrocaMercadoriaSaidaDAO saidaDao = new TrocaMercadoriaSaidaDAO();
			saidaDao.mergeDAO(getGenericDao());

			TrocaMercadoriaEntradaVO filterEntrada = new TrocaMercadoriaEntradaVO();
			DaoUtils.clonePk(getVo(), filterEntrada);

			TrocaMercadoriaSaidaVO filterSaida = new TrocaMercadoriaSaidaVO();
			DaoUtils.clonePk(getVo(), filterSaida);

			this.itensEntrada = entradaDao.getList(filterEntrada);
			this.itensSaida = saidaDao.getList(filterSaida);

			itens();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void itens() {
		List<IValueObject> list = new ArrayList<IValueObject>();
		list.addAll(itensEntrada);
		list.addAll(itensSaida);
		write(Utils.voToXml(list));
	}

	public void limpar() {
		this.itensEntrada = new ArrayList<TrocaMercadoriaEntradaVO>();
		this.itensEntradaExclusao = new ArrayList<TrocaMercadoriaEntradaVO>();
		this.itensSaida = new ArrayList<TrocaMercadoriaSaidaVO>();
		this.itensSaidaExclusao = new ArrayList<TrocaMercadoriaSaidaVO>();
	}

	@IgnoreMethodAuthentication
	public void adicionaritem() {
		if (getCodigoEmpresaProduto() == null || getCodigoLojaProduto() == null || getCodigoProduto() == null) {
			writeErrorMessage("Informe o produto!");
			return;
		}
		if (getTipoItem() == null || getTipoItem().trim().equals("")) {
			writeErrorMessage("Informe o tipo do item a adicionar!");
			return;
		}
		if (getQuantidadeItem() == null || getQuantidadeItem().compareTo(BigDecimal.ZERO) <= 0) {
			writeErrorMessage("Informe uma quantidade maior que zero!");
			return;
		}
		if (getValorItem() == null) {
			writeErrorMessage("Informe um valor maior que zero!");
			return;
		}

		ProdutoVO produto = new ProdutoVO();
		produto.setCodigoEmpresa(getCodigoEmpresaProduto());
		produto.setCodigoLoja(getCodigoLojaProduto());
		produto.setCodigoProduto(getCodigoProduto());
		try {
			produto = (ProdutoVO) getGenericDao().get(produto);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (getTipoItem().equals("entrada")) {
			TrocaMercadoriaEntradaVO entrada = new TrocaMercadoriaEntradaVO();
			entrada.setNewRecord(true);
			entrada.setCodigoEmpresaProduto(getCodigoEmpresaProduto());
			entrada.setCodigoLojaProduto(getCodigoLojaProduto());
			entrada.setCodigoProduto(getCodigoProduto());

			entrada.setQuantidadeTrocaEntrada(getQuantidadeItem());
			entrada.setValorTrocaEntrada(getValorItem());

			entrada.setNomeProduto(produto.getDescricaoProduto());

			itensEntrada.add(entrada);
		} else if (getTipoItem().equals("saida")) {
			TrocaMercadoriaSaidaVO saida = new TrocaMercadoriaSaidaVO();
			saida.setNewRecord(true);
			saida.setCodigoEmpresaProduto(getCodigoEmpresaProduto());
			saida.setCodigoLojaProduto(getCodigoLojaProduto());
			saida.setCodigoProduto(getCodigoProduto());

			saida.setQuantidadeTrocaSaida(getQuantidadeItem());
			saida.setValorTrocaSaida(getValorItem());

			saida.setNomeProduto(produto.getDescricaoProduto());
			saida.setFlagComissaoVendedorTrocaSaida(getFlagComissaoVendedorTrocaSaida());

			itensSaida.add(saida);
		} else {
			writeErrorMessage("Tipo de item desconhecido [" + getTipoItem() + "]!");
			return;
		}

		write("ok");
	}

	@IgnoreMethodAuthentication
	public void removeritem() {
		if (getTipoItem() == null || getTipoItem().trim().equals("")) {
			writeErrorMessage("Informe o tipo do item a remover!");
			return;
		}

		if (getPosicaoItem() == null || getPosicaoItem() < 0) {
			writeErrorMessage("Informe a posição do item a remover!");
			return;
		}

		if (getTipoItem().equals("entrada")) {
			itensEntradaExclusao.add(itensEntrada.get(PosicaoItem));
			itensEntrada.remove(PosicaoItem.intValue());
		} else if (getTipoItem().equals("saida")) {
			itensSaidaExclusao.add(itensSaida.get(PosicaoItem));
			itensSaida.remove(PosicaoItem.intValue());
		} else {
			writeErrorMessage("Tipo de item desconhecido [" + getTipoItem() + "]!");
			return;
		}

		write("ok");
	}

	public void setDataTroca(String dataTroca) {
		DataTroca = dataTroca;
	}

	public String getDataTroca() {
		return DataTroca;
	}

	public void setHoraTroca(String horaTroca) {
		HoraTroca = horaTroca;
	}

	public String getHoraTroca() {
		return HoraTroca;
	}

	public void setItensEntrada(List<TrocaMercadoriaEntradaVO> itensEntrada) {
		this.itensEntrada = itensEntrada;
	}

	public List<TrocaMercadoriaEntradaVO> getItensEntrada() {
		return itensEntrada;
	}

	public void setItensSaida(List<TrocaMercadoriaSaidaVO> itensSaida) {
		this.itensSaida = itensSaida;
	}

	public List<TrocaMercadoriaSaidaVO> getItensSaida() {
		return itensSaida;
	}

	public void setItensEntradaExclusao(List<TrocaMercadoriaEntradaVO> itensEntradaExclusao) {
		this.itensEntradaExclusao = itensEntradaExclusao;
	}

	public List<TrocaMercadoriaEntradaVO> getItensEntradaExclusao() {
		return itensEntradaExclusao;
	}

	public void setItensSaidaExclusao(List<TrocaMercadoriaSaidaVO> itensSaidaExclusao) {
		this.itensSaidaExclusao = itensSaidaExclusao;
	}

	public List<TrocaMercadoriaSaidaVO> getItensSaidaExclusao() {
		return itensSaidaExclusao;
	}

	public Integer getCodigoEmpresaProduto() {
		return CodigoEmpresaProduto;
	}

	public void setCodigoEmpresaProduto(Integer codigoEmpresaProduto) {
		CodigoEmpresaProduto = codigoEmpresaProduto;
	}

	public Integer getCodigoLojaProduto() {
		return CodigoLojaProduto;
	}

	public void setCodigoLojaProduto(Integer codigoLojaProduto) {
		CodigoLojaProduto = codigoLojaProduto;
	}

	public Integer getCodigoProduto() {
		return CodigoProduto;
	}

	public void setCodigoProduto(Integer codigoProduto) {
		CodigoProduto = codigoProduto;
	}

	public BigDecimal getQuantidadeItem() {
		return QuantidadeItem;
	}

	public void setQuantidadeItem(BigDecimal quantidadeItem) {
		QuantidadeItem = quantidadeItem;
	}

	public BigDecimal getValorItem() {
		return ValorItem;
	}

	public void setValorItem(BigDecimal valorItem) {
		ValorItem = valorItem;
	}

	public void setTipoItem(String tipoItem) {
		TipoItem = tipoItem;
	}

	public String getTipoItem() {
		return TipoItem;
	}

	public void setPosicaoItem(Integer posicaoItem) {
		PosicaoItem = posicaoItem;
	}

	public Integer getPosicaoItem() {
		return PosicaoItem;
	}

	public void setFlagComissaoVendedorTrocaSaida(String flagComissaoVendedorTrocaSaida) {
		FlagComissaoVendedorTrocaSaida = flagComissaoVendedorTrocaSaida;
	}

	public String getFlagComissaoVendedorTrocaSaida() {
		return FlagComissaoVendedorTrocaSaida;
	}
}
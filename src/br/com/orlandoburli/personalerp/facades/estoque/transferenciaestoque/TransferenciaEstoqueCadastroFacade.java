package br.com.orlandoburli.personalerp.facades.estoque.transferenciaestoque;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.MovimentacaoEstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.ProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.transferencia.ItemTransferenciaEstoqueDAO;
import br.com.orlandoburli.core.dao.estoque.transferencia.TransferenciaEstoqueDAO;
import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.transferencia.ItemTransferenciaEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.transferencia.TransferenciaEstoqueVO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.filters.InstantiateObject;
import br.com.orlandoburli.core.web.framework.filters.OutjectSession;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TransferenciaEstoqueCadastroFacade extends BaseCadastroFlexFacade<TransferenciaEstoqueVO, TransferenciaEstoqueDAO> {

	private static final long serialVersionUID = 1L;

	private String DataEntrada;
	private String HoraEntrada;
	private String DataSaida;
	private String HoraSaida;

	@OutjectSession
	private List<ItemTransferenciaEstoqueVO> itens;

	@OutjectSession
	// Array de itens que serão excluídos ao fechamento do lancamento.
	private List<ItemTransferenciaEstoqueVO> itensExclusao;

	private Integer PosicaoItem;

	@InstantiateObject
	private ProdutoVO produto;

	@InstantiateObject
	private ItemTransferenciaEstoqueVO item;
	
	private String CodigoReferenciaPesquisa;

	public TransferenciaEstoqueCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);

		this.setItens(new ArrayList<ItemTransferenciaEstoqueVO>());
		this.setItensExclusao(new ArrayList<ItemTransferenciaEstoqueVO>());

		addNewValidator(new NotEmptyValidator("CodigoLojaDestino", "Loja de Destino", "Loja"));
		addNewValidator(new NotEmptyValidator("DataHoraSaidaTransferenciaEstoque", "Data / Hora de Saída do Estoque", "DataSaidaTransferenciaEstoque"));
		addNewValidator(new NotEmptyValidator("DataHoraEntradaTransferenciaEstoque", "Data / Hora de Entrada do Estoque", "DataEntradaTransferenciaEstoque"));
	}

	@Override
	public boolean doBeforeSave() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			if (getHoraEntrada() == null || getHoraEntrada().trim().equals("") || getHoraEntrada().trim().equals("__:__")) {
				setHoraEntrada("00:00");
			}
			getVo().setDataHoraEntradaTransferenciaEstoque(new Timestamp(sdf.parse(getDataEntrada() + " " + getHoraEntrada()).getTime()));
		} catch (ParseException e) {
		}

		try {
			if (getHoraSaida() == null || getHoraSaida().trim().equals("") || getHoraSaida().trim().equals("__:__")) {
				setHoraSaida("00:00");
			}
			getVo().setDataHoraSaidaTransferenciaEstoque(new Timestamp(sdf.parse(getDataSaida() + " " + getHoraSaida()).getTime()));
		} catch (ParseException e) {
		}

//		if (this.itens.size() <= 0) {
//			this.messages.add(new MessageVO("Nenhum item digitado!"));
//			return false;
//		}

		return super.doBeforeSave();
	}

	@Override
	public boolean doBeforeUpdate() throws SQLException {
		try {
			TransferenciaEstoqueVO copia = dao.get(getVo());

			if (!copia.getStatusTransferenciaEstoque().equals("A")) {
				this.messages.add(new MessageVO("Transferência já foi processada e não pode ser alterada!"));
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeUpdate();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		TransferenciaEstoqueVO copia = dao.get(getVo());

		if (!copia.getStatusTransferenciaEstoque().equals("A")) {
			this.messages.add(new MessageVO("Transferência já foi processada e não pode ser excluída!"));
			return false;
		}
		
		ItemTransferenciaEstoqueVO itemFilter = new ItemTransferenciaEstoqueVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoTransferenciaEstoque(getVo().getCodigoTransferenciaEstoque());
		

		List<IValueObject> itens = getGenericDao().getList(itemFilter);

		for (IValueObject item : itens) {
			getGenericDao().remove(item);
		}

		return super.doBeforeDelete();
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setStatusTransferenciaEstoque("A");
		return super.doBeforeInsert();
	}

	@Override
	public void doAfterSave() throws SQLException {
		// Salva os itens...
		for (ItemTransferenciaEstoqueVO item : itens) {
			item.setCodigoEmpresa(getVo().getCodigoEmpresa());
			item.setCodigoLoja(getVo().getCodigoLoja());
			item.setCodigoTransferenciaEstoque(getVo().getCodigoTransferenciaEstoque());
			getGenericDao().persist(item);
		}

		// Remove quem está na lista de exclusao
		for (ItemTransferenciaEstoqueVO item : itensExclusao) {
			getGenericDao().remove(item);
		}

		itens = null;
		itensExclusao = null;

		super.doAfterSave();
	}

	@IgnoreMethodAuthentication
	public void limpar() {
		this.itens = new ArrayList<ItemTransferenciaEstoqueVO>();
		this.itensExclusao = new ArrayList<ItemTransferenciaEstoqueVO>();
	}

	@IgnoreMethodAuthentication
	public void loaditens() {
		try {
			this.itens = new ItemTransferenciaEstoqueDAO().getListByPK(getVo());
			listaritens();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@IgnoreMethodAuthentication
	public void listaritens() {
		write(Utils.voToXml(itens));
	}

	@IgnoreMethodAuthentication
	public void adicionaritem() {

		try {
			
			if (item.getCodigoEmpresaProduto() == null || item.getCodigoLojaProduto() == null || item.getCodigoProduto() == null) {
				writeErrorMessage("Informe o produto!");
				return;
			}
			
			if (item.getQuantidadeItemTransferenciaEstoque() == null || item.getQuantidadeItemTransferenciaEstoque().compareTo(BigDecimal.ZERO) <= 0) {
				writeErrorMessage("Informe a quantidade do item!");
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

			if (produto.getFlagTemSubProduto().equals("S")) {
				writeErrorMessage("Não pode transferir Kits!");
				return;
			}

			item.setNewRecord(true);
			item.setNomeProduto(produto.getDescricaoProduto());

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
			if (item.getQuantidadeItemTransferenciaEstoque() == null || item.getQuantidadeItemTransferenciaEstoque().compareTo(BigDecimal.ZERO) <= 0) {
				writeErrorMessage("Informe a quantidade do item!");
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

			if (produto.getFlagTemSubProduto().equals("S")) {
				writeErrorMessage("Não pode transferir Kits!");
				return;
			}

			item.setNewRecord(false);
			item.setNomeProduto(produto.getDescricaoProduto());

			itens.get(PosicaoItem.intValue()).setCodigoEmpresaProduto(item.getCodigoEmpresaProduto());
			itens.get(PosicaoItem.intValue()).setCodigoLojaProduto(item.getCodigoLojaProduto());
			itens.get(PosicaoItem.intValue()).setCodigoProduto(item.getCodigoProduto());
			itens.get(PosicaoItem.intValue()).setQuantidadeItemTransferenciaEstoque(item.getQuantidadeItemTransferenciaEstoque());

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

		ItemTransferenciaEstoqueVO item = itens.get(PosicaoItem.intValue());

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
	public void total() {
		BigDecimal total = BigDecimal.ZERO;

		for (ItemTransferenciaEstoqueVO item : itens) {
			if (item.getQuantidadeItemTransferenciaEstoque() != null) {
				total = total.add(item.getQuantidadeItemTransferenciaEstoque());
			}
		}

		write(Utils.voToXml(getVo()));
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
			produto.setFlagTemSubProduto("N");

			// Lojas - Destino
			LojaDAO lojaDao = new LojaDAO();

			List<LojaVO> lojas = lojaDao.getList(new LojaVO(), "NomeLoja");

			// Remove a loja da sessão
			LojaVO lojaRemove = null;
			for (LojaVO loja : lojas) {
				if (loja.getCodigoLoja().equals(getLojasessao().getCodigoLoja()) && loja.getCodigoEmpresa().equals(getLojasessao().getCodigoEmpresa())) {
					lojaRemove = loja;
					break;
				}
			}
			
			if (lojaRemove != null) {
				lojas.remove(lojaRemove);
			}

			list.addAll(getGenericDao().getList(produto));
			list.addAll(lojas);

			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void processar() {
		try {
			getGenericDao().setAutoCommit(false);
			getGenericDao().getConnection();

			ItemTransferenciaEstoqueDAO itemDao = new ItemTransferenciaEstoqueDAO();
			EstoqueDAO estoqueDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO movDao = new MovimentacaoEstoqueDAO();
			ProdutoDAO prodDao = new ProdutoDAO();

			dao.mergeDAO(getGenericDao());
			itemDao.mergeDAO(getGenericDao());
			estoqueDao.mergeDAO(getGenericDao());
			movDao.mergeDAO(getGenericDao());
			prodDao.mergeDAO(getGenericDao());

			setVo(dao.get(getVo()));

			if (getVo().getStatusTransferenciaEstoque().equals("P")) {
				writeErrorMessage("Transferência já foi processada!");
				return;
			}

			List<ItemTransferenciaEstoqueVO> itens = itemDao.getListByPK(getVo());


			if (itens.size() <= 0) {
				writeErrorMessage("Nenhum item digitado!");
			}
			
			for (ItemTransferenciaEstoqueVO item : itens) {

				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				produto = prodDao.get(produto);

				// INICIO ESTOQUE ORIGEM
				EstoqueVO estoqueOrigem = new EstoqueVO();
				estoqueOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
				estoqueOrigem.setCodigoLoja(produto.getCodigoLoja());
				estoqueOrigem.setCodigoProduto(produto.getCodigoProduto());
				estoqueOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				estoqueOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());

				estoqueOrigem = estoqueDao.get(estoqueOrigem);

				if (estoqueOrigem == null) {
					estoqueOrigem = new EstoqueVO();
					estoqueOrigem.setNewRecord(true);

					estoqueOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
					estoqueOrigem.setCodigoLoja(produto.getCodigoLoja());
					estoqueOrigem.setCodigoProduto(produto.getCodigoProduto());
					estoqueOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
					estoqueOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());

					estoqueOrigem.setEstoqueFiscal(BigDecimal.ZERO);
					estoqueOrigem.setEstoqueFisico(BigDecimal.ZERO);
				}

				if (estoqueOrigem.getQuantidadeDisplayEstoque() == null) {
					estoqueOrigem.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
				}
				if (estoqueOrigem.getQuantidadeGavetaEstoque() == null) {
					estoqueOrigem.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				BigDecimal quantidade = item.getQuantidadeItemTransferenciaEstoque();

				MovimentacaoEstoqueVO movimentacaoOrigem = new MovimentacaoEstoqueVO();
				movimentacaoOrigem.setNewRecord(true);
				movimentacaoOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLoja(produto.getCodigoLoja());
				movimentacaoOrigem.setCodigoProduto(produto.getCodigoProduto());
				movimentacaoOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());
				movimentacaoOrigem.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacaoOrigem.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR);

				movimentacaoOrigem.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				movimentacaoOrigem.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

				movimentacaoOrigem.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoqueOrigem.getEstoqueFiscal());
				movimentacaoOrigem.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoqueOrigem.getEstoqueFisico());

				// Baixa o estoque...
				estoqueOrigem.setEstoqueFisico(estoqueOrigem.getEstoqueFisico().subtract(quantidade));
				movimentacaoOrigem.setQuantidadeFisicoMovimentacaoEstoque(estoqueOrigem.getEstoqueFisico());

				estoqueOrigem.setEstoqueFiscal(estoqueOrigem.getEstoqueFiscal().subtract(quantidade));
				movimentacaoOrigem.setQuantidadeFiscalMovimentacaoEstoque(estoqueOrigem.getEstoqueFiscal());

				// / ESTOQUE GAVETA / DISPLAY

				// Verifica se o estoque gaveta tem os itens...
				BigDecimal quantidadeRestante = quantidade;

				// Se o estoque gaveta tiver o valor maior ou igual do pedido,
				// diminui da gaveta
				if (estoqueOrigem.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) >= 0) {
					estoqueOrigem.setQuantidadeGavetaEstoque(estoqueOrigem.getQuantidadeGavetaEstoque().subtract(quantidadeRestante));
					quantidadeRestante = BigDecimal.ZERO;
				} else if (estoqueOrigem.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) < 0 && estoqueOrigem.getQuantidadeGavetaEstoque().compareTo(BigDecimal.ZERO) > 0) {
					// Se o estoque gaveta tiver o valor menor, mas for maior
					// que zero, diminui do estoque gaveta e deixa o resto para
					// o display.
					quantidadeRestante = quantidadeRestante.subtract(estoqueOrigem.getQuantidadeGavetaEstoque());
					estoqueOrigem.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				// Se ainda tem quantidade a subtrair, subtrai-se do estoque
				// display
				if (quantidadeRestante.compareTo(BigDecimal.ZERO) > 0) {
					estoqueOrigem.setQuantidadeDisplayEstoque(estoqueOrigem.getQuantidadeDisplayEstoque().subtract(quantidadeRestante));
				}

				movimentacaoOrigem.setObservacaoMovimentacaoEstoque("Movimentação gerada pela transferência de n.° " + getVo().getCodigoTransferenciaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja() + " para a empresa " + getVo().getCodigoEmpresaDestino() + " e loja " + getVo().getCodigoLojaDestino());
				movimentacaoOrigem.setDocumentoMovimentacaoEstoque(getVo().getCodigoTransferenciaEstoque() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

				getGenericDao().persist(estoqueOrigem);
				getGenericDao().persist(movimentacaoOrigem);

				// FIM ESTOQUE ORIGEM

				// INICIO ESTOQUE DESTINO

				EstoqueVO estoqueDestino = new EstoqueVO();
				estoqueDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
				estoqueDestino.setCodigoLoja(produto.getCodigoLoja());
				estoqueDestino.setCodigoProduto(produto.getCodigoProduto());
				estoqueDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
				estoqueDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());

				estoqueDestino = estoqueDao.get(estoqueDestino);

				if (estoqueDestino == null) {
					estoqueDestino = new EstoqueVO();
					estoqueDestino.setNewRecord(true);

					estoqueDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
					estoqueDestino.setCodigoLoja(produto.getCodigoLoja());
					estoqueDestino.setCodigoProduto(produto.getCodigoProduto());
					estoqueDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
					estoqueDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());

					estoqueDestino.setEstoqueFiscal(BigDecimal.ZERO);
					estoqueDestino.setEstoqueFisico(BigDecimal.ZERO);
				}

				if (estoqueDestino.getQuantidadeDisplayEstoque() == null) {
					estoqueDestino.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
				}
				if (estoqueDestino.getQuantidadeGavetaEstoque() == null) {
					estoqueDestino.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				MovimentacaoEstoqueVO movimentacaoDestino = new MovimentacaoEstoqueVO();
				movimentacaoDestino.setNewRecord(true);
				movimentacaoDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
				movimentacaoDestino.setCodigoLoja(produto.getCodigoLoja());
				movimentacaoDestino.setCodigoProduto(produto.getCodigoProduto());
				movimentacaoDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
				movimentacaoDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());
				movimentacaoDestino.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacaoDestino.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR);

				movimentacaoDestino.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				movimentacaoDestino.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				movimentacaoDestino.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

				movimentacaoDestino.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoqueDestino.getEstoqueFiscal());
				movimentacaoDestino.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoqueDestino.getEstoqueFisico());

				// Volta o estoque...
				estoqueDestino.setEstoqueFisico(estoqueDestino.getEstoqueFisico().add(quantidade));
				movimentacaoDestino.setQuantidadeFisicoMovimentacaoEstoque(estoqueDestino.getEstoqueFisico());

				// Volta estoque fiscal
				estoqueDestino.setEstoqueFiscal(estoqueDestino.getEstoqueFiscal().add(quantidade));
				movimentacaoDestino.setQuantidadeFiscalMovimentacaoEstoque(estoqueDestino.getEstoqueFiscal());

				// Volta os itens para o estoque GAVETA
				estoqueDestino.setQuantidadeGavetaEstoque(estoqueDestino.getQuantidadeGavetaEstoque().add(quantidade));

				movimentacaoDestino.setObservacaoMovimentacaoEstoque("Movimentação gerada pela transferência de n.° " + getVo().getCodigoTransferenciaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja() + " para a empresa " + getVo().getCodigoEmpresaDestino() + " e loja " + getVo().getCodigoLojaDestino());
				movimentacaoDestino.setDocumentoMovimentacaoEstoque(getVo().getCodigoTransferenciaEstoque() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

				getGenericDao().persist(estoqueDestino);
				getGenericDao().persist(movimentacaoDestino);

			}

			getVo().setStatusTransferenciaEstoque("P");
			
			dao.persist(getVo());
			
			getGenericDao().commit();
			
			write("ok");
		} catch (SQLException e) {
			getGenericDao().rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}

	public void estornar() {
		try {
			getGenericDao().setAutoCommit(false);
			getGenericDao().getConnection();

			ItemTransferenciaEstoqueDAO itemDao = new ItemTransferenciaEstoqueDAO();
			EstoqueDAO estoqueDao = new EstoqueDAO();
			MovimentacaoEstoqueDAO movDao = new MovimentacaoEstoqueDAO();
			ProdutoDAO prodDao = new ProdutoDAO();

			dao.mergeDAO(getGenericDao());
			itemDao.mergeDAO(getGenericDao());
			estoqueDao.mergeDAO(getGenericDao());
			movDao.mergeDAO(getGenericDao());
			prodDao.mergeDAO(getGenericDao());
			
			setVo(dao.get(getVo()));
			
			if (getVo().getStatusTransferenciaEstoque().equals("A")) {
				writeErrorMessage("Transferência não foi processada!");
				return;
			}
			
			List<ItemTransferenciaEstoqueVO> itens = itemDao.getListByPK(getVo());
			
			for (ItemTransferenciaEstoqueVO item : itens) {
				
				ProdutoVO produto = new ProdutoVO();
				produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				produto.setCodigoLoja(item.getCodigoLojaProduto());
				produto.setCodigoProduto(item.getCodigoProduto());
				produto = prodDao.get(produto);

				// INICIO ESTOQUE ORIGEM
				EstoqueVO estoqueOrigem = new EstoqueVO();
				estoqueOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
				estoqueOrigem.setCodigoLoja(produto.getCodigoLoja());
				estoqueOrigem.setCodigoProduto(produto.getCodigoProduto());
				estoqueOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				estoqueOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());
				
				estoqueOrigem = estoqueDao.get(estoqueOrigem);
				
				if (estoqueOrigem == null) {
					estoqueOrigem = new EstoqueVO();
					estoqueOrigem.setNewRecord(true);
					
					estoqueOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
					estoqueOrigem.setCodigoLoja(produto.getCodigoLoja());
					estoqueOrigem.setCodigoProduto(produto.getCodigoProduto());
					estoqueOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
					estoqueOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());

					estoqueOrigem.setEstoqueFiscal(BigDecimal.ZERO);
					estoqueOrigem.setEstoqueFisico(BigDecimal.ZERO);
				}

				if (estoqueOrigem.getQuantidadeDisplayEstoque() == null) {
					estoqueOrigem.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
				}
				if (estoqueOrigem.getQuantidadeGavetaEstoque() == null) {
					estoqueOrigem.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				BigDecimal quantidade = item.getQuantidadeItemTransferenciaEstoque();

				MovimentacaoEstoqueVO movimentacaoOrigem = new MovimentacaoEstoqueVO();
				movimentacaoOrigem.setNewRecord(true);
				movimentacaoOrigem.setCodigoEmpresa(produto.getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLoja(produto.getCodigoLoja());
				movimentacaoOrigem.setCodigoProduto(produto.getCodigoProduto());
				movimentacaoOrigem.setCodigoEmpresaEstoque(getVo().getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLojaEstoque(getVo().getCodigoLoja());
				movimentacaoOrigem.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacaoOrigem.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR);

				movimentacaoOrigem.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				movimentacaoOrigem.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				movimentacaoOrigem.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

				movimentacaoOrigem.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoqueOrigem.getEstoqueFiscal());
				movimentacaoOrigem.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoqueOrigem.getEstoqueFisico());

				// Volta a quantidade para o estoque...
				estoqueOrigem.setEstoqueFisico(estoqueOrigem.getEstoqueFisico().add(quantidade));
				movimentacaoOrigem.setQuantidadeFisicoMovimentacaoEstoque(estoqueOrigem.getEstoqueFisico());

				estoqueOrigem.setEstoqueFiscal(estoqueOrigem.getEstoqueFiscal().add(quantidade));
				movimentacaoOrigem.setQuantidadeFiscalMovimentacaoEstoque(estoqueOrigem.getEstoqueFiscal());

				// TODO 1
				
				// Volta os itens para o estoque GAVETA
				estoqueOrigem.setQuantidadeGavetaEstoque(estoqueOrigem.getQuantidadeGavetaEstoque().add(quantidade));

				movimentacaoOrigem.setObservacaoMovimentacaoEstoque("Movimentação gerada pelo estorno da transferência de n.° " + getVo().getCodigoTransferenciaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja() + " para a empresa " + getVo().getCodigoEmpresaDestino() + " e loja " + getVo().getCodigoLojaDestino());
				movimentacaoOrigem.setDocumentoMovimentacaoEstoque(getVo().getCodigoTransferenciaEstoque() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

				getGenericDao().persist(estoqueOrigem);
				getGenericDao().persist(movimentacaoOrigem);

				// FIM ESTOQUE ORIGEM

				// INICIO ESTOQUE DESTINO

				EstoqueVO estoqueDestino = new EstoqueVO();
				estoqueDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
				estoqueDestino.setCodigoLoja(produto.getCodigoLoja());
				estoqueDestino.setCodigoProduto(produto.getCodigoProduto());
				estoqueDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
				estoqueDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());

				estoqueDestino = estoqueDao.get(estoqueDestino);

				if (estoqueDestino == null) {
					estoqueDestino = new EstoqueVO();
					estoqueDestino.setNewRecord(true);

					estoqueDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
					estoqueDestino.setCodigoLoja(produto.getCodigoLoja());
					estoqueDestino.setCodigoProduto(produto.getCodigoProduto());
					estoqueDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
					estoqueDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());

					estoqueDestino.setEstoqueFiscal(BigDecimal.ZERO);
					estoqueDestino.setEstoqueFisico(BigDecimal.ZERO);
				}

				if (estoqueDestino.getQuantidadeDisplayEstoque() == null) {
					estoqueDestino.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
				}
				if (estoqueDestino.getQuantidadeGavetaEstoque() == null) {
					estoqueDestino.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				MovimentacaoEstoqueVO movimentacaoDestino = new MovimentacaoEstoqueVO();
				movimentacaoDestino.setNewRecord(true);
				movimentacaoDestino.setCodigoEmpresa(produto.getCodigoEmpresa());
				movimentacaoDestino.setCodigoLoja(produto.getCodigoLoja());
				movimentacaoDestino.setCodigoProduto(produto.getCodigoProduto());
				movimentacaoDestino.setCodigoEmpresaEstoque(getVo().getCodigoEmpresaDestino());
				movimentacaoDestino.setCodigoLojaEstoque(getVo().getCodigoLojaDestino());
				movimentacaoDestino.setDataHoraMovimentacaoEstoque(Utils.getNow());
				movimentacaoDestino.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR);

				movimentacaoDestino.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
				movimentacaoDestino.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
				movimentacaoDestino.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

				movimentacaoDestino.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoqueDestino.getEstoqueFiscal());
				movimentacaoDestino.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoqueDestino.getEstoqueFisico());

				// Estorna o estoque...
				estoqueDestino.setEstoqueFisico(estoqueDestino.getEstoqueFisico().subtract(quantidade));
				movimentacaoDestino.setQuantidadeFisicoMovimentacaoEstoque(estoqueDestino.getEstoqueFisico());

				// Estorna estoque fiscal
				estoqueDestino.setEstoqueFiscal(estoqueDestino.getEstoqueFiscal().subtract(quantidade));
				movimentacaoDestino.setQuantidadeFiscalMovimentacaoEstoque(estoqueDestino.getEstoqueFiscal());

				// TODO 2
				
				// / ESTOQUE GAVETA / DISPLAY

				// Verifica se o estoque gaveta tem os itens...
				BigDecimal quantidadeRestante = quantidade;

				// Se o estoque gaveta tiver o valor maior ou igual do pedido,
				// diminui da gaveta
				if (estoqueDestino.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) >= 0) {
					estoqueDestino.setQuantidadeGavetaEstoque(estoqueDestino.getQuantidadeGavetaEstoque().subtract(quantidadeRestante));
					quantidadeRestante = BigDecimal.ZERO;
				} else if (estoqueDestino.getQuantidadeGavetaEstoque().compareTo(quantidadeRestante) < 0 && estoqueDestino.getQuantidadeGavetaEstoque().compareTo(BigDecimal.ZERO) > 0) {
					// Se o estoque gaveta tiver o valor menor, mas for maior
					// que zero, diminui do estoque gaveta e deixa o resto para
					// o display.
					quantidadeRestante = quantidadeRestante.subtract(estoqueDestino.getQuantidadeGavetaEstoque());
					estoqueDestino.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
				}

				// Se ainda tem quantidade a subtrair, subtrai-se do estoque
				// display
				if (quantidadeRestante.compareTo(BigDecimal.ZERO) > 0) {
					estoqueDestino.setQuantidadeDisplayEstoque(estoqueDestino.getQuantidadeDisplayEstoque().subtract(quantidadeRestante));
				}

				movimentacaoDestino.setObservacaoMovimentacaoEstoque("Movimentação gerada pelo estorno da transferência de n.° " + getVo().getCodigoTransferenciaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " e da loja " + getVo().getCodigoLoja() + " para a empresa " + getVo().getCodigoEmpresaDestino() + " e loja " + getVo().getCodigoLojaDestino());
				movimentacaoDestino.setDocumentoMovimentacaoEstoque(getVo().getCodigoTransferenciaEstoque() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());

				getGenericDao().persist(estoqueDestino);
				getGenericDao().persist(movimentacaoDestino);

			}

			getVo().setStatusTransferenciaEstoque("A");
			
			dao.persist(getVo());
			
			getGenericDao().commit();
			
			write("ok");
		} catch (SQLException e) {
			getGenericDao().rollback();
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			writeErrorMessage(e.getMessage());
		}
	}
	
	
	@IgnoreMethodAuthentication
	public void codigobarra() {
		
		System.out.println("Buscando produto...");
		
		// Primeiro tenta converter pra Integer
		String codigoReferencia = "";
		
		try {
			codigoReferencia = new Integer(Integer.parseInt(getCodigoReferenciaPesquisa())).toString();
		} catch (NullPointerException e) {
			codigoReferencia = "";
		} catch (NumberFormatException e){
			codigoReferencia = getCodigoReferenciaPesquisa();
		}
		
		if (codigoReferencia == null || codigoReferencia.trim().equals("")) {
			return;
		}
		
		ProdutoVO produto = new ProdutoVO();
		produto.setCodigoReferenciaProduto(codigoReferencia);
		
		try {
			List<ProdutoVO> listProd = new ProdutoDAO().getList(produto);
			if (listProd.size() > 0) {
				write(Utils.voToXml(listProd.get(0)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@IgnoreMethodAuthentication
	public void adicionarcodigobarra() {

		try {
			ItemTransferenciaEstoqueDAO dao = new ItemTransferenciaEstoqueDAO();
			
			ItemTransferenciaEstoqueVO itemFilter = new ItemTransferenciaEstoqueVO();
			
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoTransferenciaEstoque(getVo().getCodigoTransferenciaEstoque());
			
			itemFilter.setCodigoEmpresaProduto(getProduto().getCodigoEmpresa());
			itemFilter.setCodigoLojaProduto(getProduto().getCodigoLoja());
			itemFilter.setCodigoProduto(getProduto().getCodigoProduto());
			
			List<ItemTransferenciaEstoqueVO> listFind = dao.getList(itemFilter);
			
			if (listFind.size() > 0) {
				
				ItemTransferenciaEstoqueVO itemFind = listFind.get(0);
				
				itemFind.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFind.setCodigoLoja(getVo().getCodigoLoja());
				itemFind.setCodigoTransferenciaEstoque(getVo().getCodigoTransferenciaEstoque());
				
				itemFind.setCodigoEmpresaProduto(getProduto().getCodigoEmpresa());
				itemFind.setCodigoLojaProduto(getProduto().getCodigoLoja());
				itemFind.setCodigoProduto(getProduto().getCodigoProduto());
				
				itemFind.setQuantidadeItemTransferenciaEstoque(itemFind.getQuantidadeItemTransferenciaEstoque().add(item.getQuantidadeItemTransferenciaEstoque()));

				
				dao.persist(itemFind);
			} else {
				ItemTransferenciaEstoqueVO itemFind = new ItemTransferenciaEstoqueVO();
				
				itemFind.setNewRecord(true);
				
				itemFind.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFind.setCodigoLoja(getVo().getCodigoLoja());
				itemFind.setCodigoTransferenciaEstoque(getVo().getCodigoTransferenciaEstoque());
				
				itemFind.setCodigoEmpresaProduto(getProduto().getCodigoEmpresa());
				itemFind.setCodigoLojaProduto(getProduto().getCodigoLoja());
				itemFind.setCodigoProduto(getProduto().getCodigoProduto());
				
				itemFind.setQuantidadeItemTransferenciaEstoque(item.getQuantidadeItemTransferenciaEstoque());
				
				dao.persist(itemFind);
			}
			
			write("ok");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void setItens(List<ItemTransferenciaEstoqueVO> itens) {
		this.itens = itens;
	}

	public List<ItemTransferenciaEstoqueVO> getItens() {
		return itens;
	}

	public void setItensExclusao(List<ItemTransferenciaEstoqueVO> itensExclusao) {
		this.itensExclusao = itensExclusao;
	}

	public List<ItemTransferenciaEstoqueVO> getItensExclusao() {
		return itensExclusao;
	}

	public void setPosicaoItem(Integer posicaoItem) {
		PosicaoItem = posicaoItem;
	}

	public Integer getPosicaoItem() {
		return PosicaoItem;
	}

	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}

	public ProdutoVO getProduto() {
		return produto;
	}

	public void setItem(ItemTransferenciaEstoqueVO item) {
		this.item = item;
	}

	public ItemTransferenciaEstoqueVO getItem() {
		return item;
	}

	public void setDataEntrada(String dataEntrada) {
		DataEntrada = dataEntrada;
	}

	public String getDataEntrada() {
		return DataEntrada;
	}

	public void setHoraEntrada(String horaEntrada) {
		HoraEntrada = horaEntrada;
	}

	public String getHoraEntrada() {
		return HoraEntrada;
	}

	public void setDataSaida(String dataSaida) {
		DataSaida = dataSaida;
	}

	public String getDataSaida() {
		return DataSaida;
	}

	public void setHoraSaida(String horaSaida) {
		HoraSaida = horaSaida;
	}

	public String getHoraSaida() {
		return HoraSaida;
	}

	public String getCodigoReferenciaPesquisa() {
		return CodigoReferenciaPesquisa;
	}

	public void setCodigoReferenciaPesquisa(String codigoReferenciaPesquisa) {
		CodigoReferenciaPesquisa = codigoReferenciaPesquisa;
	}
}
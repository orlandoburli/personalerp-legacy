package br.com.orlandoburli.personalerp.facades.estoque.levantamento.levantamentoestoque;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.levantamento.LevantamentoEstoqueDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.acesso.UsuarioVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueItemVO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class LevantamentoEstoqueCadastroFacade extends
		BaseCadastroFlexFacade<LevantamentoEstoqueVO, LevantamentoEstoqueDAO> {

	private static final long serialVersionUID = 1L;

	private String DataInicioLevantamentoEstoque;
	private String HoraInicioLevantamentoEstoque;
	private String DataFimLevantamentoEstoque;
	private String HoraFimLevantamentoEstoque;

	public LevantamentoEstoqueCadastroFacade() {
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);

		addNewValidator(new NotEmptyValidator("DataHoraInicioLevantamentoEstoque", "Data / Hora de Início"));
		addNewValidator(new NotEmptyValidator("DataHoraFimLevantamentoEstoque", "Data / Hora de Término"));
		addNewValidator(new NotEmptyValidator("CodigoUsuarioLevantamentoEstoque", "Usuário levantamento"));
	}

	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setStatusLevantamentoEstoque("A"); // Aberto
		return super.doBeforeInsert();
	}

	@Override
	public boolean doBeforeSave() throws SQLException {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		try {
			getVo().setDataHoraInicioLevantamentoEstoque(new Timestamp(sdf.parse(getDataInicioLevantamentoEstoque() + " " + getHoraInicioLevantamentoEstoque()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			getVo().setDataHoraFimLevantamentoEstoque(new Timestamp(sdf.parse(getDataFimLevantamentoEstoque() + " " + getHoraFimLevantamentoEstoque()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return super.doBeforeSave();
	}

	@Override
	public boolean doBeforeUpdate() throws SQLException {
		try {
			LevantamentoEstoqueVO levantamento = dao.get(getVo());

			if (levantamento.getStatusLevantamentoEstoque() == null || !levantamento.getStatusLevantamentoEstoque().equals("A")) {
				this.messages.add(new MessageVO("Levantamento já foi processado e não pode ser alterado!"));
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeUpdate();
	}

	@Override
	public boolean doBeforeDelete() throws SQLException {
		LevantamentoEstoqueVO levantamento = dao.get(getVo());

		if (levantamento.getStatusLevantamentoEstoque() == null || !levantamento.getStatusLevantamentoEstoque().equals("A")) {
			this.messages.add(new MessageVO("Levantamento já foi processado e não pode ser excluído!"));
			return false;
		}

		// Se for excluir, excluir os itens primeiro
		LevantamentoEstoqueItemVO itemFilter = new LevantamentoEstoqueItemVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());

		List<IValueObject> itens = getGenericDao().getList(itemFilter);

		for (IValueObject item : itens) {
			getGenericDao().remove(item);
		}

		return super.doBeforeDelete();
	}

	public void processar() {
		synchronized (EstoqueVO.class) {
			try {
				getGenericDao().setAutoCommit(false);

				setVo((LevantamentoEstoqueVO) getGenericDao().get(getVo()));

				if (!getVo().getStatusLevantamentoEstoque().equals("A")) {
					writeErrorMessage("Levantamento já foi processado!");
					return;
				}

				LevantamentoEstoqueItemVO itemFilter = new LevantamentoEstoqueItemVO();
				itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFilter.setCodigoLoja(getVo().getCodigoLoja());
				itemFilter.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());

				List<IValueObject> itens = getGenericDao().getList(itemFilter);

				for (IValueObject i : itens) {
					LevantamentoEstoqueItemVO item = (LevantamentoEstoqueItemVO) i;

					// Busca estoque
					EstoqueVO estoque = new EstoqueVO();

					estoque.setCodigoEmpresaEstoque(item.getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(item.getCodigoLoja());

					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());

					estoque = (EstoqueVO) getGenericDao().get(estoque);

					if (estoque == null) {
						// Se nao encontrou, criar
						estoque = new EstoqueVO();

						estoque.setCodigoEmpresaEstoque(item.getCodigoEmpresa());
						estoque.setCodigoLojaEstoque(item.getCodigoLoja());

						estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
						estoque.setCodigoLoja(item.getCodigoLojaProduto());
						estoque.setCodigoProduto(item.getCodigoProduto());

						estoque.setEstoqueFiscal(BigDecimal.ZERO);
						estoque.setEstoqueFisico(BigDecimal.ZERO);
						

						estoque.setNewRecord(true);
					}
					
					if (estoque.getQuantidadeDisplayEstoque() == null) {
						estoque.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
					}
					if (estoque.getQuantidadeGavetaEstoque() == null) {
						estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
					}

					// Verifica se ha diferencas do digitado e do encontrado no
					// sistema
					if (estoque.getEstoqueFisico().compareTo(item.getQuantidadeDigitadaItemLevantamentoEstoque()) != 0) {

						BigDecimal valorAntigo = estoque.getEstoqueFisico();

						// Seta a quantidade encontrada no estoque
						item.setQuantidadeEncontradaItemLevantamentoEstoque(estoque.getEstoqueFisico());
						item.setAcaoItemLevantamentoEstoque("A");

						getGenericDao().persist(item);

						// Atualiza o estoque com o valor digitado
						estoque.setEstoqueFiscal(item.getQuantidadeDigitadaItemLevantamentoEstoque());
						estoque.setEstoqueFisico(item.getQuantidadeDigitadaItemLevantamentoEstoque());

						BigDecimal valorNovo = estoque.getEstoqueFisico();

						getGenericDao().persist(estoque);

						// Movimentacao do estoque

						MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
						mov.setNewRecord(true);

						mov.setCodigoEmpresa(estoque.getCodigoEmpresa());
						mov.setCodigoLoja(estoque.getCodigoLoja());
						mov.setCodigoProduto(estoque.getCodigoProduto());
						mov.setCodigoEmpresaEstoque(estoque.getCodigoEmpresaEstoque());
						mov.setCodigoLojaEstoque(estoque.getCodigoLojaEstoque());
						mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
						mov.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_INVENTARIO);

						mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(valorAntigo);
						mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(valorAntigo);

						mov.setQuantidadeFiscalMovimentacaoEstoque(valorNovo);
						mov.setQuantidadeFisicoMovimentacaoEstoque(valorNovo);

						mov.setObservacaoMovimentacaoEstoque("Levantamento de estoque numero " + getVo().getCodigoLevantamentoEstoque());
						
						mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
						mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
						mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());

						getGenericDao().persist(mov);

						// Liga a movimentacao no item
						item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
						item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
						item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());

					} else {
						// Seta a quantidade encontrada no estoque
						item.setQuantidadeEncontradaItemLevantamentoEstoque(item.getQuantidadeEncontradaItemLevantamentoEstoque());
						item.setAcaoItemLevantamentoEstoque("D");
					}
					
					// Verifica os estoques de display e gaveta, se sao diferentes
					// porem a diferenca entre os dois nao gera erro de inventario
					if (item.getQuantidadeDisplayItemLevantamentoEstoque().compareTo(estoque.getQuantidadeDisplayEstoque()) != 0) {
						estoque.setQuantidadeDisplayEstoque(item.getQuantidadeDisplayItemLevantamentoEstoque());
					}
					
					if (item.getQuantidadeGavetaItemLevantamentoEstoque().compareTo(estoque.getQuantidadeGavetaEstoque()) != 0) {
						estoque.setQuantidadeGavetaEstoque(item.getQuantidadeGavetaItemLevantamentoEstoque());
					}
					
					getGenericDao().persist(estoque);
					getGenericDao().persist(item);
				}

				getVo().setStatusLevantamentoEstoque("P");
				getVo().setDataHoraProcessamentoLevantamentoEstoque(Utils.getNow());

				getGenericDao().persist(getVo());

				getGenericDao().commit();
				write("ok");
			} catch (SQLException ex) {
				getGenericDao().rollback();
				writeErrorMessage(ex.getMessage());
			} catch (Exception ex) {
				getGenericDao().rollback();
				writeErrorMessage(ex.getMessage());
			}
		}
	}

	public void estornar() {
		synchronized (EstoqueVO.class) {
			try {
				getGenericDao().setAutoCommit(false);

				setVo((LevantamentoEstoqueVO) getGenericDao().get(getVo()));

				if (!getVo().getStatusLevantamentoEstoque().equals("P")) {
					writeErrorMessage("Levantamento não foi processado!");
					return;
				}

				LevantamentoEstoqueItemVO itemFilter = new LevantamentoEstoqueItemVO();
				itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFilter.setCodigoLoja(getVo().getCodigoLoja());
				itemFilter.setCodigoLevantamentoEstoque(getVo().getCodigoLevantamentoEstoque());

				List<IValueObject> itens = getGenericDao().getList(itemFilter);

				for (IValueObject i : itens) {
					LevantamentoEstoqueItemVO item = (LevantamentoEstoqueItemVO) i;

					// Estorna somente os itens que sofreram movimentacao
					if (item.getAcaoItemLevantamentoEstoque().equals("A")) {

						// Busca estoque
						EstoqueVO estoque = new EstoqueVO();

						estoque.setCodigoEmpresaEstoque(item.getCodigoEmpresa());
						estoque.setCodigoLojaEstoque(item.getCodigoLoja());

						estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
						estoque.setCodigoLoja(item.getCodigoLojaProduto());
						estoque.setCodigoProduto(item.getCodigoProduto());

						estoque = (EstoqueVO) getGenericDao().get(estoque);

						if (estoque == null) {
							throw new Exception("Produto sem estoque!");
						}

						// Busca movimentacao de estoque
						MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();

						mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
						mov.setCodigoLoja(item.getCodigoLojaProduto());
						mov.setCodigoProduto(item.getCodigoProduto());

						mov.setCodigoEmpresaEstoque(item.getCodigoEmpresaEstoque());
						mov.setCodigoLojaEstoque(item.getCodigoLojaEstoque());
						mov.setCodigoMovimentacaoEstoque(item.getCodigoMovimentacaoEstoque());

						mov = (MovimentacaoEstoqueVO) getGenericDao().get(mov);

						if (mov == null) {
							throw new Exception("Movimentação não encontrada!");
						}

						// Verifica se houve movimentacao de estoque posterior
						// deste produto
						MovimentacaoEstoqueVO movFilter = new MovimentacaoEstoqueVO();

						movFilter.setCodigoEmpresa(estoque.getCodigoEmpresa());
						movFilter.setCodigoLoja(estoque.getCodigoLoja());
						movFilter.setCodigoProduto(estoque.getCodigoProduto());
						movFilter.setCodigoEmpresaEstoque(estoque.getCodigoEmpresaEstoque());
						movFilter.setCodigoLojaEstoque(estoque.getCodigoLojaEstoque());

						getGenericDao().setSpecialCondition(" CodigoMovimentacaoEstoque > " + mov.getCodigoMovimentacaoEstoque());

						List<IValueObject> list = getGenericDao().getList(movFilter);

						getGenericDao().setSpecialCondition(null);

						if (list.size() > 0) {
							// Busca cadastro do produto (para exibicao)
							ProdutoVO produto = new ProdutoVO();
							produto.setCodigoEmpresa(item.getCodigoEmpresaProduto());
							produto.setCodigoLoja(item.getCodigoLojaProduto());
							produto.setCodigoProduto(item.getCodigoProduto());

							produto = (ProdutoVO) getGenericDao().get(produto);

							throw new Exception("O produto " + produto.getDescricaoProduto() + "\nsofreu movimentações após o levantamento,\nnão podendo mais ser estornado!");
						}

						// Se nao tiver movimentacao apos, excluir a
						// movimentacao do item
						// e voltar o estoque ao valor anterior.
						item.setAcaoItemLevantamentoEstoque(null);
						item.setQuantidadeEncontradaItemLevantamentoEstoque(null);
						item.setCodigoEmpresaEstoque(null);
						item.setCodigoLojaEstoque(null);
						item.setCodigoMovimentacaoEstoque(null);

						estoque.setEstoqueFiscal(mov.getQuantidadeFiscalAnteriorMovimentacaoEstoque());
						estoque.setEstoqueFisico(mov.getQuantidadeFisicoAnteriorMovimentacaoEstoque());

						getGenericDao().persist(item);
						getGenericDao().persist(estoque);

						getGenericDao().remove(mov);
					}
				}

				getVo().setStatusLevantamentoEstoque("A");
				getVo().setDataHoraProcessamentoLevantamentoEstoque(null);

				getGenericDao().persist(getVo());

				getGenericDao().commit();
				write("ok");
			} catch (SQLException ex) {
				getGenericDao().rollback();
				writeErrorMessage(ex.getMessage());
			} catch (Exception ex) {
				getGenericDao().rollback();
				writeErrorMessage(ex.getMessage());
			}
		}
	}

	@IgnoreMethodAuthentication
	public void usuarios() {
		try {
			List<IValueObject> usuarios = getGenericDao().getList(new UsuarioVO());
			write(Utils.voToXml(usuarios, usuarios.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDataInicioLevantamentoEstoque(
			String dataInicioLevantamentoEstoque) {
		DataInicioLevantamentoEstoque = dataInicioLevantamentoEstoque;
	}

	public String getDataInicioLevantamentoEstoque() {
		return DataInicioLevantamentoEstoque;
	}

	public void setHoraInicioLevantamentoEstoque(
			String horaInicioLevantamentoEstoque) {
		HoraInicioLevantamentoEstoque = horaInicioLevantamentoEstoque;
	}

	public String getHoraInicioLevantamentoEstoque() {
		return HoraInicioLevantamentoEstoque;
	}

	public void setDataFimLevantamentoEstoque(String dataFimLevantamentoEstoque) {
		DataFimLevantamentoEstoque = dataFimLevantamentoEstoque;
	}

	public String getDataFimLevantamentoEstoque() {
		return DataFimLevantamentoEstoque;
	}

	public void setHoraFimLevantamentoEstoque(String horaFimLevantamentoEstoque) {
		HoraFimLevantamentoEstoque = horaFimLevantamentoEstoque;
	}

	public String getHoraFimLevantamentoEstoque() {
		return HoraFimLevantamentoEstoque;
	}
}

package br.com.orlandoburli.personalerp.facades.estoque.entrada.entradaestoque;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.entradaestoque.EntradaEstoqueDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.EntradaEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.ItemEntradaEstoqueVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class EntradaEstoqueCadastroFacade extends BaseCadastroFlexFacade<EntradaEstoqueVO, EntradaEstoqueDAO>{

	private static final long serialVersionUID = 1L;

	private String DataEntradaEstoque;
	private String HoraEntradaEstoque;
	
	public EntradaEstoqueCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
		addNewValidator(new NotEmptyValidator("DataHoraEntradaEstoque", "Data / Hora da Entrada"));
	}
	
	public void processar() {
		synchronized (EstoqueVO.class) {
			try {
				getGenericDao().setAutoCommit(false);
				
				setVo((EntradaEstoqueVO) getGenericDao().get(getVo()));
				
				if (!getVo().getStatusEntradaEstoque().equals("A")) {
					writeErrorMessage("Entrada já foi processada!");
					return;
				}
				
				ItemEntradaEstoqueVO itemFilter = new ItemEntradaEstoqueVO();
				itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFilter.setCodigoLoja(getVo().getCodigoLoja());
				itemFilter.setCodigoEntradaEstoque(getVo().getCodigoEntradaEstoque());
				
				List<IValueObject> itens = getGenericDao().getList(itemFilter);
				
				for (IValueObject i : itens) {
					ItemEntradaEstoqueVO item = (ItemEntradaEstoqueVO) i;
					
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
					
					BigDecimal valorAntigoFisico = estoque.getEstoqueFisico();
					BigDecimal valorAntigoFiscal = estoque.getEstoqueFiscal();
					
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(item.getQuantidadeItemEntradaEstoque()));
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(item.getQuantidadeItemEntradaEstoque()));
					
					MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
					mov.setNewRecord(true);

					mov.setCodigoEmpresa(estoque.getCodigoEmpresa());
					mov.setCodigoLoja(estoque.getCodigoLoja());
					mov.setCodigoProduto(estoque.getCodigoProduto());
					mov.setCodigoEmpresaEstoque(estoque.getCodigoEmpresaEstoque());
					mov.setCodigoLojaEstoque(estoque.getCodigoLojaEstoque());
					mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
					mov.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_ADICIONAR);

					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(valorAntigoFiscal);
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(valorAntigoFisico);

					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());

					mov.setObservacaoMovimentacaoEstoque("Entrada de estoque numero " + getVo().getCodigoEntradaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					
					mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					// Entrada sempre vai para a GAVETA
					estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().add(item.getQuantidadeItemEntradaEstoque()));
					
					getGenericDao().persist(estoque);
					getGenericDao().persist(mov);
					
					// Liga a movimentacao no item
					item.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
					item.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
					item.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
					
					getGenericDao().persist(item);
				}
				
				getVo().setStatusEntradaEstoque("P");
				getVo().setDataHoraProcessamentoEntradaEstoque(Utils.getNow());

				getGenericDao().persist(getVo());
				
				write("ok");
				getGenericDao().commit();
			} catch (SQLException e) {
				getGenericDao().rollback();
				writeErrorMessage(e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				getGenericDao().rollback();
				writeErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void estornar() {
		synchronized (EstoqueVO.class) {
			try {
				getGenericDao().setAutoCommit(false);
				
				setVo((EntradaEstoqueVO) getGenericDao().get(getVo()));
				
				if (!getVo().getStatusEntradaEstoque().equals("P")) {
					writeErrorMessage("Esta entrada não foi processada!");
					return;
				}
				
				ItemEntradaEstoqueVO itemFilter = new ItemEntradaEstoqueVO();
				itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
				itemFilter.setCodigoLoja(getVo().getCodigoLoja());
				itemFilter.setCodigoEntradaEstoque(getVo().getCodigoEntradaEstoque());
				
				List<IValueObject> itens = getGenericDao().getList(itemFilter);
				
				for (IValueObject i : itens) {
					ItemEntradaEstoqueVO item = (ItemEntradaEstoqueVO) i;
					
					// Busca estoque
					EstoqueVO estoque = new EstoqueVO();

					estoque.setCodigoEmpresaEstoque(item.getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(item.getCodigoLoja());

					estoque.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					estoque.setCodigoLoja(item.getCodigoLojaProduto());
					estoque.setCodigoProduto(item.getCodigoProduto());
					
					estoque = (EstoqueVO) getGenericDao().get(estoque);
					
					if (estoque == null) {
						throw new Exception("Estoque não encontrado!");
					}
					
					if (estoque.getQuantidadeDisplayEstoque() == null) {
						estoque.setQuantidadeDisplayEstoque(BigDecimal.ZERO);
					}
					if (estoque.getQuantidadeGavetaEstoque() == null) {
						estoque.setQuantidadeGavetaEstoque(BigDecimal.ZERO);
					}
					
					MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();

					mov.setCodigoEmpresa(item.getCodigoEmpresaProduto());
					mov.setCodigoLoja(item.getCodigoLojaProduto());
					mov.setCodigoProduto(item.getCodigoProduto());
					mov.setCodigoEmpresaEstoque(item.getCodigoEmpresaEstoque());
					mov.setCodigoLojaEstoque(item.getCodigoLojaEstoque());
					mov.setCodigoMovimentacaoEstoque(item.getCodigoMovimentacaoEstoque());
					
					mov = (MovimentacaoEstoqueVO) getGenericDao().get(mov);
					
					if (mov == null) {
						throw new Exception("Movimentação não encontrada");
					}
					
					// Verifica se houve inventario de estoque posterior
					// deste produto
					MovimentacaoEstoqueVO movFilter = new MovimentacaoEstoqueVO();
					movFilter.setCodigoEmpresa(estoque.getCodigoEmpresa());
					movFilter.setCodigoLoja(estoque.getCodigoLoja());
					movFilter.setCodigoProduto(estoque.getCodigoProduto());
					movFilter.setCodigoEmpresaEstoque(estoque.getCodigoEmpresaEstoque());
					movFilter.setCodigoLojaEstoque(estoque.getCodigoLojaEstoque());
					movFilter.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_INVENTARIO);

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

						throw new Exception("O produto " + produto.getDescricaoProduto() + "\nteve inventário após a entrada,\nnão podendo mais ser estornado!");
					}
					
					BigDecimal valorAntigoFiscal = estoque.getEstoqueFiscal();
					BigDecimal valorAntigoFisico = estoque.getEstoqueFisico();
					
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(item.getQuantidadeItemEntradaEstoque()));
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(item.getQuantidadeItemEntradaEstoque()));
					
					// Gera nova movimentação de estorno
					mov = new MovimentacaoEstoqueVO();
					mov.setNewRecord(true);

					mov.setCodigoEmpresa(estoque.getCodigoEmpresa());
					mov.setCodigoLoja(estoque.getCodigoLoja());
					mov.setCodigoProduto(estoque.getCodigoProduto());
					mov.setCodigoEmpresaEstoque(estoque.getCodigoEmpresaEstoque());
					mov.setCodigoLojaEstoque(estoque.getCodigoLojaEstoque());
					mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
					mov.setOperacaoMovimentacaoEstoque(MovimentacaoEstoqueVO.OPERACAO_DIMINUIR);
					
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(valorAntigoFiscal);
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(valorAntigoFisico);
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Estorno de entrada de estoque numero " + getVo().getCodigoEntradaEstoque() + " da empresa " + getVo().getCodigoEmpresa() + " da loja " + getVo().getCodigoLoja());
					
					mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					// Retira do estoque tipo GAVETA
					estoque.setQuantidadeGavetaEstoque(estoque.getQuantidadeGavetaEstoque().subtract(item.getQuantidadeItemEntradaEstoque()));
					
					// Desfaz a ligação da movimentacao no item
					item.setCodigoEmpresaEstoque(null);
					item.setCodigoLojaEstoque(null);
					item.setCodigoMovimentacaoEstoque(null);
					
					getGenericDao().persist(item);
					
					getGenericDao().persist(estoque);
					
					getGenericDao().persist(mov);
				}
				
				getVo().setStatusEntradaEstoque("A");
				getVo().setDataHoraProcessamentoEntradaEstoque(null);

				getGenericDao().persist(getVo());
				
				write("ok");
				getGenericDao().commit();
			} catch (SQLException e) {
				getGenericDao().rollback();
				writeErrorMessage(e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				getGenericDao().rollback();
				writeErrorMessage(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setStatusEntradaEstoque("A"); // Aberto
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		try {
			getVo().setDataHoraEntradaEstoque(new Timestamp(sdf.parse(getDataEntradaEstoque() + " " + getHoraEntradaEstoque()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		setVo(dao.get(getVo()));
		
		if (getVo() == null) {
			return false;
		} else if (getVo().getStatusEntradaEstoque().equals("P")) {
			this.messages.add(new MessageVO("Entrada já foi processada e não pode ser excluída!"));
			return false;
		}
		
		if (getVo().getCodigoEmpresa() == null || getVo().getCodigoLoja() == null || getVo().getCodigoEntradaEstoque() == null) {
			return false;
		}
		
		ItemEntradaEstoqueVO itemFilter = new ItemEntradaEstoqueVO();
		itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
		itemFilter.setCodigoLoja(getVo().getCodigoLoja());
		itemFilter.setCodigoEntradaEstoque(getVo().getCodigoEntradaEstoque());
		
		for (IValueObject item : getGenericDao().getList(itemFilter)) {
			getGenericDao().remove(item);
		}
		
		return super.doBeforeDelete();
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoa() {
		try {
			EnderecoPessoaVO endereco = new EnderecoPessoaVO();
			List<IValueObject> list = getGenericDao().getList(endereco);
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setDataEntradaEstoque(String dataEntradaEstoque) {
		DataEntradaEstoque = dataEntradaEstoque;
	}

	public String getDataEntradaEstoque() {
		return DataEntradaEstoque;
	}

	public void setHoraEntradaEstoque(String horaEntradaEstoque) {
		HoraEntradaEstoque = horaEntradaEstoque;
	}

	public String getHoraEntradaEstoque() {
		return HoraEntradaEstoque;
	}
}
package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.romaneioabate;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.SubProdutoDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.ItemCurralDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.romaneioabate.ItemRomaneioAbateDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.romaneioabate.MovimentacaoItemRomaneioAbateDAO;
import br.com.orlandoburli.core.dao.estoque.frigorifico.romaneioabate.RomaneioAbateDAO;
import br.com.orlandoburli.core.dao.financeiro.contasapagar.ContaPagarDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.cadastro.pessoa.EnderecoPessoaVO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.MovimentacaoEstoqueVO;
import br.com.orlandoburli.core.vo.estoque.SubProdutoVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.curral.ItemCurralVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.ItemRomaneioAbateVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.MovimentacaoItemRomaneioAbateVO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.romaneioabate.RomaneioAbateVO;
import br.com.orlandoburli.core.vo.financeiro.contasapagar.ContaPagarVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class RomaneioAbateCadastroFacade extends BaseCadastroFlexFacade<RomaneioAbateVO, RomaneioAbateDAO>{

	private static final long serialVersionUID = 1L;

	public RomaneioAbateCadastroFacade() {
		super();
		setWriteVoOnInsert(true);
		setWriteVoOnUpdate(true);
		
		addNewValidator(new NotEmptyValidator("DataRomaneio", "Data do Romaneio"));
		addNewValidator(new NotEmptyValidator("CodigoEnderecoPessoaFornecedor", "Fornecedor", "Fornecedor"));
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		this.getVo().setDataHoraLancamentoRomaneio(Utils.getNow());
		this.getVo().setStatusRomaneio("N");
		this.getVo().setStatusNotaRomaneio("N");
		this.getVo().setStatusPagamentoRomaneio("A");
		return super.doBeforeSave();
	}
	
	public void processar() {
		try {
			getGenericDao().setAutoCommit(false);
			
			setVo(dao.get(getVo()));
			ItemRomaneioAbateDAO itemDao = new ItemRomaneioAbateDAO();
			ItemRomaneioAbateVO itemFilter = new ItemRomaneioAbateVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			List<ItemRomaneioAbateVO> list = itemDao.getList(itemFilter);
			
			for (ItemRomaneioAbateVO item : list) {
				// Se tiver sido indicado o curral, abater no estoque do curral...
				if (item.getCodigoCurral() != null) {
					
					ItemCurralVO itemCurralFilter = new ItemCurralVO();
					itemCurralFilter.setCodigoEmpresa(getVo().getCodigoEmpresaPessoaFornecedor());
					itemCurralFilter.setCodigoLojaPessoa(getVo().getCodigoLojaPessoaFornecedor());
					itemCurralFilter.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
					
					itemCurralFilter.setCodigoEmpresa(item.getCodigoEmpresaCurral());
					itemCurralFilter.setCodigoLoja(item.getCodigoLojaCurral());
					itemCurralFilter.setCodigoCurral(item.getCodigoCurral());
					
					ItemCurralDAO itemCurralDao = new ItemCurralDAO();
					List<ItemCurralVO> listCurral = itemCurralDao.getList(itemCurralFilter);
					
					ItemCurralVO itemCurral = null;
					
					// Se tinha ele no curral, trata, senao, nem mexe...
					if (listCurral.size() > 0) {
						itemCurral = listCurral.get(0);
						itemCurral.setQuantidadeItemCurral(itemCurral.getQuantidadeItemCurral() - 1);
						if (itemCurral.getQuantidadeItemCurral() <= 0) {
							getGenericDao().remove(itemCurral);
						} else {
							getGenericDao().persist(itemCurral);
						}
					}
				}
				// Gerar estoque
				SubProdutoVO subprodutoFilter = new SubProdutoVO();
				subprodutoFilter.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				subprodutoFilter.setCodigoLoja(item.getCodigoLojaProduto());
				subprodutoFilter.setCodigoProduto(item.getCodigoProduto());
				
				SubProdutoDAO subDao = new SubProdutoDAO();
				List<SubProdutoVO> listSubProdutos = subDao.getList(subprodutoFilter);
				
				// Para cada subproduto, gera uma entrada no estoque.
				for (SubProdutoVO subproduto : listSubProdutos) {
					// Busca o estoque do produto
					EstoqueVO estoque = new EstoqueVO();
					
					estoque.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
					estoque.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
					estoque.setCodigoProduto(subproduto.getCodigoSubProduto());
					
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					
					estoque = (EstoqueVO) getGenericDao().get(estoque);
					
					if (estoque == null) {
						estoque = new EstoqueVO();
						estoque.setNewRecord(true);
						estoque.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
						estoque.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
						estoque.setCodigoProduto(subproduto.getCodigoSubProduto());
						
						estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
						estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
						estoque.setEstoqueFiscal(BigDecimal.ZERO);
						estoque.setEstoqueFisico(BigDecimal.ZERO);
					}
					
					// Gera movimentacao de estoque
					MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
					mov.setNewRecord(true);
					
					mov.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
					mov.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
					mov.setCodigoProduto(subproduto.getCodigoSubProduto());
					
					mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					
					mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					estoque.setEstoqueFisico(estoque.getEstoqueFisico().add(subproduto.getQuantidadeSubProduto()));
					estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().add(subproduto.getQuantidadeSubProduto()));
					
					mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
					mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
					
					mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Romaneio de abate de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
					
					mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
					mov.setOperacaoMovimentacaoEstoque("+");
					
					estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					
					getGenericDao().persist(estoque);
					getGenericDao().persist(mov);
					
					// Liga o registro de movimentacao à movimentacao do romaneio
					MovimentacaoItemRomaneioAbateVO movRom = new MovimentacaoItemRomaneioAbateVO();
					movRom.setNewRecord(true);
					
					movRom.setCodigoEmpresa(item.getCodigoEmpresa());
					movRom.setCodigoLoja(item.getCodigoLoja());
					movRom.setCodigoRomaneio(item.getCodigoRomaneio());
					movRom.setCodigoItemRomaneio(item.getCodigoItemRomaneio());
					
					movRom.setCodigoEmpresaEstoque(mov.getCodigoEmpresaEstoque());
					movRom.setCodigoLojaEstoque(mov.getCodigoLojaEstoque());
					movRom.setCodigoMovimentacaoEstoque(mov.getCodigoMovimentacaoEstoque());
					
					movRom.setCodigoEmpresaSubProduto(mov.getCodigoEmpresa());
					movRom.setCodigoLojaSubProduto(mov.getCodigoLoja());
					movRom.setCodigoSubProduto(mov.getCodigoProduto());
					
					movRom.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
					movRom.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					movRom.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
					
					getGenericDao().persist(movRom);
				}
			}
			
			// Gerar conta a pagar
			ContaPagarVO conta = new ContaPagarVO();
			conta.setNewRecord(true);
			conta.setCodigoEmpresa(getVo().getCodigoEmpresa());
			conta.setCodigoLoja(getVo().getCodigoLoja());
			conta.setNumeroParcelasContaPagar(1);
			conta.setNumeroTituloContaPagar(getVo().getCodigoRomaneio() + "." + getVo().getCodigoEmpresa() + "." + getVo().getCodigoLoja());
			conta.setParcelaContaPagar(1);
			
			conta.setCodigoEmpresaPessoaContaPagar(getVo().getCodigoEmpresaPessoaFornecedor());
			conta.setCodigoLojaPessoaContaPagar(getVo().getCodigoLojaPessoaFornecedor());
			conta.setCodigoPessoaContaPagar(getVo().getCodigoPessoaFornecedor());
			
			conta.setCodigoCentroCusto(1); //??? vamos ver daonde pegar..
			conta.setCodigoTipoDocumento(1); ///??? vamos ver tb...
			conta.setCodigoPortador(1); // ??? vamos ver mais um...
			conta.setCodigoPlanoConta(1); // ????
			
			conta.setDataLancamentoContaPagar(Utils.getToday());
			conta.setDataVencimentoContaPagar(Utils.getToday());
			conta.setSituacaoContaPagar("A"); // Aberto
			conta.setDescricaoContaPagar("Pagamento do romaneio de abate número " + getVo().getCodigoRomaneio());
			conta.setValorContaPagar(getVo().getValorTotalRomaneio());
			
			conta.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
			conta.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
			conta.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
			
			getGenericDao().persist(conta);
			
			getVo().setCodigoEmpresaContaPagar(conta.getCodigoEmpresa());
			getVo().setCodigoLojaContaPagar(conta.getCodigoLoja());
			getVo().setCodigoContaPagar(conta.getCodigoContaPagar());
			
			getVo().setStatusRomaneio("P");
			
			getGenericDao().persist(getVo());
			
			getGenericDao().commit();
			
			write("ok");
		} catch (SQLException ex) {
			getGenericDao().rollback();
			writeErrorMessage(ex.getMessage());
		}
	}
	
	public void estornar() {
		try {
			getGenericDao().setAutoCommit(false);
			
			setVo(dao.get(getVo()));
			
			ItemRomaneioAbateDAO itemDao = new ItemRomaneioAbateDAO();
			ItemRomaneioAbateVO itemFilter = new ItemRomaneioAbateVO();
			itemFilter.setCodigoEmpresa(getVo().getCodigoEmpresa());
			itemFilter.setCodigoLoja(getVo().getCodigoLoja());
			itemFilter.setCodigoRomaneio(getVo().getCodigoRomaneio());
			
			List<ItemRomaneioAbateVO> list = itemDao.getList(itemFilter);
			
			for (ItemRomaneioAbateVO item : list) {
				// Se tiver sido indicado o curral, abater no estoque do curral...
				if (item.getCodigoCurral() != null) {
					
					ItemCurralVO itemCurralFilter = new ItemCurralVO();
					itemCurralFilter.setCodigoEmpresa(getVo().getCodigoEmpresaPessoaFornecedor());
					itemCurralFilter.setCodigoLojaPessoa(getVo().getCodigoLojaPessoaFornecedor());
					itemCurralFilter.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
					
					itemCurralFilter.setCodigoEmpresa(item.getCodigoEmpresaCurral());
					itemCurralFilter.setCodigoLoja(item.getCodigoLojaCurral());
					itemCurralFilter.setCodigoCurral(item.getCodigoCurral());
					
					ItemCurralDAO itemCurralDao = new ItemCurralDAO();
					List<ItemCurralVO> listCurral = itemCurralDao.getList(itemCurralFilter);
					
					ItemCurralVO itemCurral = null;
					
					// Se tinha ele no curral, trata, senao, nem mexe...
					if (listCurral.size() > 0) {
						// Se tem registro, atualiza
						itemCurral = listCurral.get(0);
					} else {
						// Se não tem, insere
						itemCurral = new ItemCurralVO();
						itemCurral.setNewRecord(true);
						itemCurral.setQuantidadeItemCurral(0);
						
						itemCurral.setCodigoEmpresa(getVo().getCodigoEmpresaPessoaFornecedor());
						itemCurral.setCodigoLojaPessoa(getVo().getCodigoLojaPessoaFornecedor());
						itemCurral.setCodigoPessoa(getVo().getCodigoPessoaFornecedor());
						
						itemCurral.setCodigoEmpresa(item.getCodigoEmpresaCurral());
						itemCurral.setCodigoLoja(item.getCodigoLojaCurral());
						itemCurral.setCodigoCurral(item.getCodigoCurral());
					}
					
					itemCurral.setQuantidadeItemCurral(itemCurral.getQuantidadeItemCurral() + 1);
					getGenericDao().persist(itemCurral);
				}
				// Estornar estoque
				SubProdutoVO subprodutoFilter = new SubProdutoVO();
				subprodutoFilter.setCodigoEmpresa(item.getCodigoEmpresaProduto());
				subprodutoFilter.setCodigoLoja(item.getCodigoLojaProduto());
				subprodutoFilter.setCodigoProduto(item.getCodigoProduto());
				
				SubProdutoDAO subDao = new SubProdutoDAO();
				List<SubProdutoVO> listSubProdutos = subDao.getList(subprodutoFilter);
				
				// Para cada subproduto, gera uma entrada no estoque.
				for (SubProdutoVO subproduto : listSubProdutos) {
					// Busca o estoque do produto
					EstoqueVO estoque = new EstoqueVO();
					
					estoque.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
					estoque.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
					estoque.setCodigoProduto(subproduto.getCodigoSubProduto());
					
					estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					
					estoque = (EstoqueVO) getGenericDao().get(estoque);
					
					if (estoque == null) {
						estoque = new EstoqueVO();
						estoque.setNewRecord(true);
						estoque.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
						estoque.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
						estoque.setCodigoProduto(subproduto.getCodigoSubProduto());
						
						estoque.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
						estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
						
						estoque.setEstoqueFiscal(BigDecimal.ZERO);
						estoque.setEstoqueFisico(BigDecimal.ZERO);
						
						estoque.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
						estoque.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
						estoque.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
					}
					
					// Remove o registro de movimentacao
					MovimentacaoItemRomaneioAbateVO movRomFilter = new MovimentacaoItemRomaneioAbateVO();
					movRomFilter.setCodigoEmpresa(item.getCodigoEmpresa());
					movRomFilter.setCodigoLoja(item.getCodigoLoja());
					movRomFilter.setCodigoRomaneio(item.getCodigoRomaneio());
					movRomFilter.setCodigoItemRomaneio(item.getCodigoItemRomaneio());
					
					movRomFilter.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
					movRomFilter.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					movRomFilter.setCodigoEmpresaSubProduto(subproduto.getCodigoEmpresaSubProduto());
					movRomFilter.setCodigoLojaSubProduto(subproduto.getCodigoLojaSubProduto());
					movRomFilter.setCodigoSubProduto(subproduto.getCodigoSubProduto());
					
					boolean found = false;
					for (MovimentacaoItemRomaneioAbateVO movRom : new MovimentacaoItemRomaneioAbateDAO().getList(movRomFilter)) {
						getGenericDao().remove(movRom);
						found = true;
					}
					
					// Gera movimentacao de estoque
					if (found) {
						MovimentacaoEstoqueVO mov = new MovimentacaoEstoqueVO();
						mov.setNewRecord(true);
						
						mov.setCodigoEmpresa(subproduto.getCodigoEmpresaSubProduto());
						mov.setCodigoLoja(subproduto.getCodigoLojaSubProduto());
						mov.setCodigoProduto(subproduto.getCodigoSubProduto());
						
						mov.setCodigoEmpresaEstoque(getEmpresasessao().getCodigoEmpresa());
						mov.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
						
						mov.setQuantidadeFiscalAnteriorMovimentacaoEstoque(estoque.getEstoqueFiscal());
						mov.setQuantidadeFisicoAnteriorMovimentacaoEstoque(estoque.getEstoqueFisico());
						
						estoque.setEstoqueFisico(estoque.getEstoqueFisico().subtract(subproduto.getQuantidadeSubProduto()));
						estoque.setEstoqueFiscal(estoque.getEstoqueFiscal().subtract(subproduto.getQuantidadeSubProduto()));
						
						mov.setQuantidadeFiscalMovimentacaoEstoque(estoque.getEstoqueFiscal());
						mov.setQuantidadeFisicoMovimentacaoEstoque(estoque.getEstoqueFisico());
						
						mov.setObservacaoMovimentacaoEstoque("Movimentacao gerada pelo sistema - Estorno do Romaneio de abate de numero " + item.getCodigoRomaneio() + " da empresa " + item.getCodigoEmpresa() + " e da loja " + item.getCodigoLoja());
						
						mov.setCodigoUsuarioLog(getUsuariosessao().getCodigoUsuario());
						mov.setCodigoEmpresaUsuarioLog(getUsuariosessao().getCodigoEmpresa());
						mov.setCodigoLojaUsuarioLog(getUsuariosessao().getCodigoLoja());
						mov.setDataHoraMovimentacaoEstoque(Utils.getNow());
						mov.setOperacaoMovimentacaoEstoque("-");
						
						getGenericDao().persist(estoque);
						getGenericDao().persist(mov);
					} else {
						getGenericDao().persist(estoque);
					}
				}
			}
			
			// Remover conta a pagar
			ContaPagarVO conta = new ContaPagarVO();
			conta.setCodigoEmpresa(getVo().getCodigoEmpresaContaPagar());
			conta.setCodigoLoja(getVo().getCodigoLojaContaPagar());
			conta.setCodigoContaPagar(getVo().getCodigoContaPagar());
			
			ContaPagarDAO contaDao = new ContaPagarDAO();
			conta = contaDao.get(conta);
			
			getVo().setCodigoEmpresaContaPagar(null);
			getVo().setCodigoLojaContaPagar(null);
			getVo().setCodigoContaPagar(null);
			getVo().setStatusRomaneio("N");
			
			getGenericDao().persist(getVo());
			
			if (conta != null) {
				getGenericDao().remove(conta);
			}
			
			getGenericDao().commit();
			
			write("ok");
		} catch (SQLException ex) {
			getGenericDao().rollback();
			if (ex.getMessage().indexOf("contapagar_baixacontapagar_fk") >= 0) {
				writeErrorMessage("A conta a pagar vinculada a este romaneio de abate já foi paga!");
			} else {
				writeErrorMessage(ex.getMessage());
			}
		}
	}
	
	@IgnoreMethodAuthentication
	public void enderecopessoas() {
		try {
			List<IValueObject> list = getGenericDao().getList(new EnderecoPessoaVO());
			int count = getGenericDao().getListCount(new EnderecoPessoaVO());
			write(Utils.voToXml(list, count));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}
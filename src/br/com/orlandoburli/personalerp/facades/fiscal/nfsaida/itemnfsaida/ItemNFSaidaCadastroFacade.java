package br.com.orlandoburli.personalerp.facades.fiscal.nfsaida.itemnfsaida;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.fiscal.nfsaida.ItemNFSaidaDAO;
import br.com.orlandoburli.core.extras.nfe.interfaces.NfeException;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.nfe.NFEletronicaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.ItemNFSaidaVO;
import br.com.orlandoburli.core.vo.fiscal.nfsaida.NFSaidaVO;
import br.com.orlandoburli.core.vo.utils.MessageVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.personalerp.facades.fiscal.nfsaida.NFSaidaCadastroFacade;

public class ItemNFSaidaCadastroFacade extends BaseCadastroFlexFacade<ItemNFSaidaVO, ItemNFSaidaDAO>{

	private static final long serialVersionUID = 1L;
	
	
	public ItemNFSaidaCadastroFacade() {
		super();
		this.addNewValidator(new NotEmptyValidator("NCMItemNFSaida", "NCM do item"));
		this.addNewValidator(new NotEmptyValidator("UnidadeItemNFSaida", "Unidade do Item"));
		this.addNewValidator(new NotEmptyValidator("QuantidadeItemNFSaida", "Quantidade"));
		this.addNewValidator(new NotEmptyValidator("ValorUnitarioItemNFSaida", "Valor Unitário"));
	}

	@IgnoreMethodAuthentication
	public void produtos() {
		ProdutoVO produtoFilter = new ProdutoVO();
		try {
			getGenericDao().setSpecialCondition(" TipoEstoqueProduto IN ('E', 'V')");
			
			List<IValueObject> list = getGenericDao().getList(produtoFilter);
			
			getGenericDao().setSpecialCondition("");
			getGenericDao().setAutoCommit(false);
			
			for (IValueObject item : list) {
				if (item instanceof ProdutoVO) {
					ProdutoVO produto = (ProdutoVO) item;
					
					EstoqueVO estoque = new EstoqueVO();
					estoque.setCodigoEmpresa(produto.getCodigoEmpresa());
					estoque.setCodigoLoja(produto.getCodigoLoja());
					estoque.setCodigoProduto(produto.getCodigoProduto());
					estoque.setCodigoEmpresaEstoque(getLojasessao().getCodigoEmpresa());
					estoque.setCodigoLojaEstoque(getLojasessao().getCodigoLoja());
					
					estoque = (EstoqueVO) getGenericDao().get(estoque);
					if (estoque != null) {
						produto.setValorVendaAtacadoProduto(estoque.getPrecoMedioEstoque());
						produto.setValorVendaVarejoProduto(estoque.getPrecoMedioEstoque());
					}
				}
			}
			getGenericDao().commit();
			
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
			e.printStackTrace();
			getGenericDao().rollback();
		}
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		try {
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
			nfsaida.setCodigoLoja(getVo().getCodigoLoja());
			nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
			nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
			nfsaida = (NFSaidaVO) getGenericDao().get(nfsaida);
			getVo().setSequencialItemNFSaida(dao.getNextSequencialItemNFSaida(nfsaida));
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeSave() throws SQLException {
		try {
			// Valida o NCM do item
			if (this.getVo().getNCMItemNFSaida() != null) {
				if (this.getVo().getNCMItemNFSaida().length() != 2 && this.getVo().getNCMItemNFSaida().length() != 8) {
					this.messages.add(new MessageVO("O NCM deve ter 2 ou 8 dígitos!", "NCMItemNFSaida"));
					return false;
				}
			}
			
			// Valida a quantidade
			if (this.getVo().getQuantidadeItemNFSaida() != null) {
				if (this.getVo().getQuantidadeItemNFSaida().compareTo(BigDecimal.ZERO) <= 0) {
					this.messages.add(new MessageVO("A quantidade deve ser maior que zero!", "QuantidadeItemNFSaida"));
					return false;
				}
			}
			
			// Valida o valor unitario
			if (this.getVo().getValorUnitarioItemNFSaida() != null) {
				if (this.getVo().getValorUnitarioItemNFSaida().compareTo(BigDecimal.ZERO) <= 0) {
					this.messages.add(new MessageVO("O valor unitário deve ser maior que zero!", "ValorUnitarioItemNFSaida"));
					return false;
				}
			}
			
			// Busca a NF de Saida
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
			nfsaida.setCodigoLoja(getVo().getCodigoLoja());
			nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
			nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
			
			try {
				if (!NFSaidaCadastroFacade.validaAlteraNF(nfsaida, messages)) {
					return false;
				}
				nfsaida.setStatusNFSaida(NFEletronicaVO.STATUS_DIGITACAO);
				getGenericDao().persist(getVo());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(getVo().getCodigoEmpresaProduto());
			produto.setCodigoLoja(getVo().getCodigoLojaProduto());
			produto.setCodigoProduto(getVo().getCodigoProduto());
			produto = (ProdutoVO) getGenericDao().get(produto);
			
			if (produto != null) {
				getVo().setDescricaoItemNFSaida(produto.getDescricaoProduto());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeSave();
	}
	
	@Override
	public boolean doBeforeDelete() throws SQLException {
		try {
			// Busca a NF de Saida
			NFSaidaVO nfsaida = new NFSaidaVO();
			nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
			nfsaida.setCodigoLoja(getVo().getCodigoLoja());
			nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
			nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
			
			try {
				if (!NFSaidaCadastroFacade.validaAlteraNF(nfsaida, messages)) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			ProdutoVO produto = new ProdutoVO();
			produto.setCodigoEmpresa(getVo().getCodigoEmpresaProduto());
			produto.setCodigoLoja(getVo().getCodigoLojaProduto());
			produto.setCodigoProduto(getVo().getCodigoProduto());
			produto = (ProdutoVO) getGenericDao().get(produto);
			
			if (produto != null) {
				getVo().setDescricaoItemNFSaida(produto.getDescricaoProduto());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return super.doBeforeDelete();
	}
	
	@Override
	public void doAfterCommit(String operacao) throws SQLException {
		NFSaidaVO nfsaida = new NFSaidaVO();
		nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
		nfsaida.setCodigoLoja(getVo().getCodigoLoja());
		nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
		nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
		
		nfsaida = (NFSaidaVO) getGenericDao().get(nfsaida);
		
		try {
			NFSaidaCadastroFacade.validarNF(getGenericDao(), nfsaida, getLojasessao());
		} catch (NfeException e) {
			e.printStackTrace();
		}
		
		super.doAfterCommit(operacao);
	}
	
	@Override
	public void doAfterDelete() throws SQLException {
		NFSaidaVO nfsaida = new NFSaidaVO();
		nfsaida.setCodigoEmpresa(getVo().getCodigoEmpresa());
		nfsaida.setCodigoLoja(getVo().getCodigoLoja());
		nfsaida.setCodigoNFSaida(getVo().getCodigoNFSaida());
		nfsaida.setSerieNFSaida(getVo().getSerieNFSaida());
		
		nfsaida = (NFSaidaVO) getGenericDao().get(nfsaida);
		
		super.doAfterDelete();
	}
}
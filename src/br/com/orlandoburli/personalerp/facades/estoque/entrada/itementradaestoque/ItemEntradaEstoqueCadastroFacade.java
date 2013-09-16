package br.com.orlandoburli.personalerp.facades.estoque.entrada.itementradaestoque;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.estoque.entradaestoque.ItemEntradaEstoqueDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.estoque.entradaestoque.ItemEntradaEstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ItemEntradaEstoqueCadastroFacade extends BaseCadastroFlexFacade<ItemEntradaEstoqueVO, ItemEntradaEstoqueDAO>{

	private static final long serialVersionUID = 1L;

	public ItemEntradaEstoqueCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoProduto", "Produto", "Produto"));
		addNewValidator(new NotEmptyValidator("QuantidadeItemEntradaEstoque", "Quantidade"));
	}
	
	@IgnoreMethodAuthentication
	public void produtos() {
		try {
			ProdutoVO produtoFilter = new ProdutoVO();
			produtoFilter.setSituacaoProduto("N");
			produtoFilter.setFlagTemSubProduto("N");
			List<IValueObject> produtos = getGenericDao().getList(produtoFilter);
			
			for (IValueObject item : produtos) {
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
			
			write(Utils.voToXml(produtos, produtos.size()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

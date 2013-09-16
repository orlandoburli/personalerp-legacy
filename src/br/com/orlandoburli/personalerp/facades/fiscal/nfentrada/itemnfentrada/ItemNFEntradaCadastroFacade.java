package br.com.orlandoburli.personalerp.facades.fiscal.nfentrada.itemnfentrada;

import java.sql.SQLException;
import java.util.List;

import br.com.orlandoburli.core.dao.fiscal.nfentrada.ItemNFEntradaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.IValueObject;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.vo.estoque.ProdutoVO;
import br.com.orlandoburli.core.vo.fiscal.nfentrada.ItemNFEntradaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class ItemNFEntradaCadastroFacade extends BaseCadastroFlexFacade<ItemNFEntradaVO, ItemNFEntradaDAO>{

	private static final long serialVersionUID = 1L;

	@IgnoreMethodAuthentication
	public void produtos() {
		ProdutoVO produtoFilter = new ProdutoVO();
		try {
			getGenericDao().setSpecialCondition(" TipoEstoqueProduto IN ('E', 'V')");
			List<IValueObject> list = getGenericDao().getList(produtoFilter);
			
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
			
			write(Utils.voToXml(list, list.size()));
		} catch (SQLException e) {
			writeErrorMessage(e.getMessage());
		}
	}
}
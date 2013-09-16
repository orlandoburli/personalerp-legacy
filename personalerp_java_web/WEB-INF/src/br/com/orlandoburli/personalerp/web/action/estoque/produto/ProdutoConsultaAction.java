package br.com.orlandoburli.personalerp.web.action.estoque.produto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.action.BaseConsultaAction;
import br.com.orlandoburli.personalerp.model.estoque.produto.be.ProdutoBe;
import br.com.orlandoburli.personalerp.model.estoque.produto.dao.ProdutoDAO;
import br.com.orlandoburli.personalerp.model.estoque.produto.vo.ProdutoVO;

public class ProdutoConsultaAction extends BaseConsultaAction<ProdutoVO, ProdutoDAO, ProdutoBe> {

	private static final long serialVersionUID = 1L;

	@Override
	public String getJspConsulta() {
		return "web/pages/estoque/produto/produtoconsulta.jsp";
	}

	@Override
	public String getJspGridConsulta() {
		return "web/pages/estoque/produto/produtoconsulta_grid.jsp";
	}

	@Override
	public String getOrderFields() {
		return "DescricaoProduto";
	}

	@Override
	public void doBeforeFilter(ProdutoVO filter, ProdutoBe be, HttpServletRequest request, HttpServletResponse response) {
		if (getParametroPesquisa() != null) {
			if (getParametroPesquisa().equalsIgnoreCase("Codigo")) {
				Integer codigo = null;
				try {
					codigo = Integer.parseInt(getPesquisarPor());
				} catch (NumberFormatException e) {}
				filter.setCodigoProduto(codigo);
			} else if (getParametroPesquisa().equalsIgnoreCase("Referencia")) {
				filter.setCodigoReferenciaProduto(getPesquisarPor() + "%");
			} else if (getParametroPesquisa().equalsIgnoreCase("Nome")) {
				filter.setDescricaoProduto("%" + getPesquisarPor() + "%");
			}
		} else {
			filter.setDescricaoProduto("%" + getPesquisarPor() + "%");
		}
	}
}
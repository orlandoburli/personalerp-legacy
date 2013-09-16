package br.com.orlandoburli.personalerp.facades.estoque.linhaproduto;

import br.com.orlandoburli.core.dao.estoque.LinhaProdutoDAO;
import br.com.orlandoburli.core.vo.estoque.LinhaProdutoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class LinhaProdutoConsultaFacade extends BaseConsultaFlexFacade<LinhaProdutoVO, LinhaProdutoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeLinhaProduto(getFiltro() + "%");
	}
}
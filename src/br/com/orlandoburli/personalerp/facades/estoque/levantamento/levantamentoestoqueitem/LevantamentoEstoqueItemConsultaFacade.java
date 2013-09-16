package br.com.orlandoburli.personalerp.facades.estoque.levantamento.levantamentoestoqueitem;

import br.com.orlandoburli.core.dao.estoque.levantamento.LevantamentoEstoqueItemDAO;
import br.com.orlandoburli.core.vo.estoque.levantamento.LevantamentoEstoqueItemVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class LevantamentoEstoqueItemConsultaFacade extends BaseConsultaFlexFacade<LevantamentoEstoqueItemVO, LevantamentoEstoqueItemDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("CodigoItemLevantamentoEstoque");
	}
}
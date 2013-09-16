package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.curral.itemcurral;

import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.ItemCurralDAO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.curral.ItemCurralVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ItemCurralConsultaFacade extends BaseConsultaFlexFacade<ItemCurralVO, ItemCurralDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		
	}
}
package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.curral;

import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.CurralDAO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.curral.CurralVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class CurralConsultaFacade extends BaseConsultaFlexFacade<CurralVO, CurralDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoCurral(getFiltro() + "%");
	}
}
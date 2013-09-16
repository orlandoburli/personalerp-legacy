package br.com.orlandoburli.personalerp.facades.financeiro.tipocontaresumo;

import br.com.orlandoburli.core.dao.financeiro.contaresumo.TipoContaResumoDAO;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.TipoContaResumoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoContaResumoConsultaFacade extends BaseConsultaFlexFacade<TipoContaResumoVO, TipoContaResumoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoTipoContaResumo(getFiltro() + "%");
		setOrderFields("DescricaoTipoContaResumo");
	}

}

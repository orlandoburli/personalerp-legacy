package br.com.orlandoburli.personalerp.facades.sistema.loja;

import br.com.orlandoburli.core.dao.sistema.LojaDAO;
import br.com.orlandoburli.core.vo.sistema.LojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class LojaConsultaFacade extends BaseConsultaFlexFacade<LojaVO, LojaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeLoja(getFiltro() + "%");
		setOrderFields("CodigoLoja");
	}
}
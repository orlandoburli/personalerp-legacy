package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.manejotora;

import br.com.orlandoburli.core.dao.cadastro.madeireira.ManejoToraDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.ManejoToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class ManejoToraConsultaFacade extends BaseConsultaFlexFacade<ManejoToraVO, ManejoToraDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoManejo(getFiltro() + "%");
	}
}
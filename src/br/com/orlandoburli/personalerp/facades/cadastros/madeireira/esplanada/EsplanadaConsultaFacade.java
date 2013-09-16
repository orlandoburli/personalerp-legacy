package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.esplanada;

import br.com.orlandoburli.core.dao.cadastro.madeireira.EsplanadaDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.EsplanadaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EsplanadaConsultaFacade extends BaseConsultaFlexFacade<EsplanadaVO, EsplanadaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeEsplanada(getFiltro() + "%");
	}
}
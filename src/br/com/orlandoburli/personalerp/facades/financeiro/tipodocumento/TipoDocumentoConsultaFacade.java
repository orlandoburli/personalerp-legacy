package br.com.orlandoburli.personalerp.facades.financeiro.tipodocumento;

import br.com.orlandoburli.core.dao.financeiro.TipoDocumentoDAO;
import br.com.orlandoburli.core.vo.financeiro.TipoDocumentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoDocumentoConsultaFacade extends BaseConsultaFlexFacade<TipoDocumentoVO, TipoDocumentoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeTipoDocumento(getFiltro() + "%");
		this.setOrderFields("NomeTipoDocumento");
	}

	@Override
	protected Class<?> getDAOClass() {
		return TipoDocumentoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TipoDocumentoVO.class;
	}
}
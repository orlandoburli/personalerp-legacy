package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.gerentemadeireira;

import br.com.orlandoburli.core.dao.cadastro.madeireira.GerenteMadeireiraDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.GerenteMadeireiraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class GerenteMadeireiraConsultaFacade extends BaseConsultaFlexFacade<GerenteMadeireiraVO, GerenteMadeireiraDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
//		getFilter().setNomeGerente(getFiltro() + "%");
		getFilter().setCodigoEmpresa(getEmpresasessao().getCodigoEmpresa());
		getFilter().setCodigoLoja(getLojasessao().getCodigoLoja());
		setOrderFields("NomeGerente");
	}

	@Override
	protected Class<?> getDAOClass() {
		return GerenteMadeireiraDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return GerenteMadeireiraVO.class;
	}
}
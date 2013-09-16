package br.com.orlandoburli.personalerp.facades.sistema.grupoempresaloja;

import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaLojaDAO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class GrupoEmpresaLojaConsultaFacade extends BaseConsultaFlexFacade<GrupoEmpresaLojaVO, GrupoEmpresaLojaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeLoja(getFiltro() + "%");
		setOrderFields("NomeLoja");
	}

}

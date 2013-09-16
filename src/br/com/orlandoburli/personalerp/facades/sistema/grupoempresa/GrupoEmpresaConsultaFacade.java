package br.com.orlandoburli.personalerp.facades.sistema.grupoempresa;

import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaDAO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class GrupoEmpresaConsultaFacade extends BaseConsultaFlexFacade<GrupoEmpresaVO, GrupoEmpresaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeGrupoEmpresa(getFiltro() + "%");
		setOrderFields("NomeGrupoEmpresa");
	}

}

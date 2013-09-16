package br.com.orlandoburli.personalerp.facades.sistema.empresa;

import br.com.orlandoburli.core.dao.sistema.EmpresaDAO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class EmpresaConsultaFacade extends BaseConsultaFlexFacade<EmpresaVO, EmpresaDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		setOrderFields("CodigoEmpresa");
		this.getFilter().setRazaoSocialEmpresa(getFiltro() + "%");
	}
}
package br.com.orlandoburli.personalerp.facades.financeiro.tipoplanoconta;

import br.com.orlandoburli.core.dao.financeiro.TipoPlanoContaDAO;
import br.com.orlandoburli.core.vo.financeiro.TipoPlanoContaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoPlanoContaConsultaFacade extends BaseConsultaFlexFacade<TipoPlanoContaVO, TipoPlanoContaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeTipoPlanoConta(getFiltro() + "%");
		this.setOrderFields("NomeTipoPlanoConta");
	}

	@Override
	protected Class<?> getDAOClass() {
		return TipoPlanoContaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TipoPlanoContaVO.class;
	}

}

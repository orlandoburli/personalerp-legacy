package br.com.orlandoburli.personalerp.facades.estoque.madeireira.tipobeneficiada;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.TipoMadeiraBeneficiadaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.TipoMadeiraBeneficiadaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoMadeiraBeneficiadaConsultaFacade extends BaseConsultaFlexFacade<TipoMadeiraBeneficiadaVO, TipoMadeiraBeneficiadaDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeTipoMadeiraBeneficiada(getFiltro() + "%");
		setOrderFields("NomeTipoMadeiraBeneficiada");
	}

	@Override
	protected Class<?> getDAOClass() {
		return TipoMadeiraBeneficiadaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TipoMadeiraBeneficiadaVO.class;
	}
}
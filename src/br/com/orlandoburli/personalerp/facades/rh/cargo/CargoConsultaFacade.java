package br.com.orlandoburli.personalerp.facades.rh.cargo;

import br.com.orlandoburli.core.dao.rh.CargoDAO;
import br.com.orlandoburli.core.vo.rh.CargoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class CargoConsultaFacade extends BaseConsultaFlexFacade<CargoVO, CargoDAO> {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doBeforeFilter() {
		this.getFilter().setNomeCargo(getFiltro() + "%");
		setOrderFields("NomeCargo");
	}

	@Override
	protected Class<?> getDAOClass() {
		return CargoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return CargoVO.class;
	}
}
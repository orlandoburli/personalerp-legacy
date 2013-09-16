package br.com.orlandoburli.personalerp.facades.rh.horariotrabalho;

import br.com.orlandoburli.core.dao.rh.HorarioTrabalhoDAO;
import br.com.orlandoburli.core.vo.rh.HorarioTrabalhoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class HorarioTrabalhoConsultaFacade extends BaseConsultaFlexFacade<HorarioTrabalhoVO, HorarioTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoHorarioTrabalho(getFiltro() + "%");
		setOrderFields("DescricaoHorarioTrabalho");
	}

	@Override
	protected Class<?> getDAOClass() {
		return HorarioTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return HorarioTrabalhoVO.class;
	}
}
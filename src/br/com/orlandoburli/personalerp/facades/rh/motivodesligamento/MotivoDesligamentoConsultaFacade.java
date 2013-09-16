package br.com.orlandoburli.personalerp.facades.rh.motivodesligamento;

import br.com.orlandoburli.core.dao.rh.MotivoDesligamentoDAO;
import br.com.orlandoburli.core.vo.rh.MotivoDesligamentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class MotivoDesligamentoConsultaFacade extends BaseConsultaFlexFacade<MotivoDesligamentoVO, MotivoDesligamentoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		this.getFilter().setDescricaoMotivoDesligamento(getFiltro() + "%");
		this.setOrderFields("DescricaoMotivoDesligamento");
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotivoDesligamentoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotivoDesligamentoVO.class;
	}
}
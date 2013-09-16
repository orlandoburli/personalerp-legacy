package br.com.orlandoburli.personalerp.facades.rh.folha.folhapagamento;

import br.com.orlandoburli.core.dao.rh.folha.FolhaPagamentoDAO;
import br.com.orlandoburli.core.vo.rh.folha.FolhaPagamentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class FolhaPagamentoConsultaFacade extends BaseConsultaFlexFacade<FolhaPagamentoVO, FolhaPagamentoDAO> {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeLojaLotacao("%" + getFiltro() + "%");
		setOrderFields("AnoCompetenciaFolhaPagamento DESC, MesCompetenciaFolhaPagamento DESC, NomeLojaLotacao");
	}

	@Override
	protected Class<?> getDAOClass() {
		return FolhaPagamentoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FolhaPagamentoVO.class;
	}
}
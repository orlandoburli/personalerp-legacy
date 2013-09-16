package br.com.orlandoburli.personalerp.facades.manutencao.trocamanutencao.historicotrocamanutencao;

import br.com.orlandoburli.core.dao.manutencao.HistoricoTrocaManutencaoDAO;
import br.com.orlandoburli.core.vo.manutencao.HistoricoTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class HistoricoTrocaManutencaoConsultaFacade extends BaseConsultaFlexFacade<HistoricoTrocaManutencaoVO, HistoricoTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoHistoricoTrocaManutencao("%" + getFiltro() + "%");
		setOrderFields("DataHoraHistoricoTrocaManutencao DESC");
	}
}
package br.com.orlandoburli.personalerp.facades.manutencao.situacaotrocamanutencao;

import br.com.orlandoburli.core.dao.manutencao.SituacaoTrocaManutencaoDAO;
import br.com.orlandoburli.core.vo.manutencao.SituacaoTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class SituacaoTrocaManutencaoConsultaFacade extends BaseConsultaFlexFacade<SituacaoTrocaManutencaoVO, SituacaoTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setDescricaoSituacaoTrocaManutencao(getFiltro() + "%");
		setOrderFields("DescricaoSituacaoTrocaManutencao");
	}
}
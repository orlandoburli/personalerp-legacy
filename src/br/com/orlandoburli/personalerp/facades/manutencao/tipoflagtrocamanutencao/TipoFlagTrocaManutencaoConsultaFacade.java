package br.com.orlandoburli.personalerp.facades.manutencao.tipoflagtrocamanutencao;

import br.com.orlandoburli.core.dao.manutencao.TipoFlagTrocaManutencaoDAO;
import br.com.orlandoburli.core.vo.manutencao.TipoFlagTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseConsultaFlexFacade;

public class TipoFlagTrocaManutencaoConsultaFacade extends BaseConsultaFlexFacade<TipoFlagTrocaManutencaoVO, TipoFlagTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doBeforeFilter() {
		getFilter().setNomeTipoFlagTrocaManutencao(getFiltro() + "%");
		setOrderFields("NomeTipoFlagTrocaManutencao");
	}
}
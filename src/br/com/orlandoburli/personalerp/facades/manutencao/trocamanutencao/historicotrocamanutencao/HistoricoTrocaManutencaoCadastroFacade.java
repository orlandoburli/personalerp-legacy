package br.com.orlandoburli.personalerp.facades.manutencao.trocamanutencao.historicotrocamanutencao;

import java.sql.SQLException;

import br.com.orlandoburli.core.dao.manutencao.HistoricoTrocaManutencaoDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.manutencao.HistoricoTrocaManutencaoVO;
import br.com.orlandoburli.core.vo.manutencao.SituacaoTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class HistoricoTrocaManutencaoCadastroFacade extends BaseCadastroFlexFacade<HistoricoTrocaManutencaoVO, HistoricoTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;
	
	public HistoricoTrocaManutencaoCadastroFacade() {
		super();
	}
	
	@Override
	public boolean doBeforeInsert() throws SQLException {
		getVo().setDataHoraHistoricoTrocaManutencao(Utils.getNow());
		getVo().setFlagEmailEnviadoTrocaManutencao("N"); // Valor default
		return super.doBeforeInsert();
	}
	
	@Override
	public boolean doBeforeUpdate() throws SQLException {
		HistoricoTrocaManutencaoVO vo = dao.get(getVo());
		
		getVo().setFlagEmailEnviadoTrocaManutencao(vo.getFlagEmailEnviadoTrocaManutencao());
		
		return super.doBeforeUpdate();
	}
	
	@IgnoreMethodAuthentication
	public void situacoes() {
		try {
			write(Utils.voToXml(getGenericDao().getList(new SituacaoTrocaManutencaoVO(), "DescricaoSituacaoTrocaManutencao")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
package br.com.orlandoburli.personalerp.facades.manutencao.situacaotrocamanutencao;

import br.com.orlandoburli.core.dao.manutencao.SituacaoTrocaManutencaoDAO;
import br.com.orlandoburli.core.vo.manutencao.SituacaoTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class SituacaoTrocaManutencaoCadastroFacade extends BaseCadastroFlexFacade<SituacaoTrocaManutencaoVO, SituacaoTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;

	public SituacaoTrocaManutencaoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoSituacaoTrocaManutencao", "Descrição"));
		addNewValidator(new NotEmptyValidator("FlagFinalSituacaoTrocaManutencao", "Final"));
		addNewValidator(new NotEmptyValidator("FlagEnviaEmailSituacaoTrocaManutencao", "Envia Email"));
	}
}
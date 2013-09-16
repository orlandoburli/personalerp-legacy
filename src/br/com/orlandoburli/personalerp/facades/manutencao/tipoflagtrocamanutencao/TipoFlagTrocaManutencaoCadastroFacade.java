package br.com.orlandoburli.personalerp.facades.manutencao.tipoflagtrocamanutencao;

import br.com.orlandoburli.core.dao.manutencao.TipoFlagTrocaManutencaoDAO;
import br.com.orlandoburli.core.vo.manutencao.TipoFlagTrocaManutencaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoFlagTrocaManutencaoCadastroFacade extends BaseCadastroFlexFacade<TipoFlagTrocaManutencaoVO, TipoFlagTrocaManutencaoDAO>{

	private static final long serialVersionUID = 1L;

	public TipoFlagTrocaManutencaoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("NomeTipoFlagTrocaManutencao", "Nome"));
	}
}
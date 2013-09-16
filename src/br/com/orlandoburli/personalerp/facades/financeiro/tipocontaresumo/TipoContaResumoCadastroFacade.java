package br.com.orlandoburli.personalerp.facades.financeiro.tipocontaresumo;

import br.com.orlandoburli.core.dao.financeiro.contaresumo.TipoContaResumoDAO;
import br.com.orlandoburli.core.vo.financeiro.contaresumo.TipoContaResumoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoContaResumoCadastroFacade extends BaseCadastroFlexFacade<TipoContaResumoVO, TipoContaResumoDAO>{

	private static final long serialVersionUID = 1L;

	public TipoContaResumoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoTipoContaResumo", "Descrição"));
	}
}

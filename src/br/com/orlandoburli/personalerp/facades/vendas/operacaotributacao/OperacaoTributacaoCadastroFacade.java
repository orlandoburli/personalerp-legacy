package br.com.orlandoburli.personalerp.facades.vendas.operacaotributacao;

import br.com.orlandoburli.core.dao.fiscal.OperacaoTributacaoDAO;
import br.com.orlandoburli.core.vo.fiscal.OperacaoTributacaoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class OperacaoTributacaoCadastroFacade extends BaseCadastroFlexFacade<OperacaoTributacaoVO, OperacaoTributacaoDAO>{

	private static final long serialVersionUID = 1L;

	public OperacaoTributacaoCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("OperacaoTributacao", "Código"));
		addNewValidator(new NotEmptyValidator("DescricaoOperacaoTributacao", "Descrição"));
	}
}

package br.com.orlandoburli.personalerp.facades.vendas.caixa.caixaloja;

import br.com.orlandoburli.core.dao.vendas.caixa.CaixaLojaDAO;
import br.com.orlandoburli.core.vo.vendas.caixa.CaixaLojaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class CaixaLojaCadastroFacade extends BaseCadastroFlexFacade<CaixaLojaVO, CaixaLojaDAO>{

	private static final long serialVersionUID = 1L;

	public CaixaLojaCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoCaixa", "Descrição"));
	}
}

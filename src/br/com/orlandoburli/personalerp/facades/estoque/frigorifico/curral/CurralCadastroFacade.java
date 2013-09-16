package br.com.orlandoburli.personalerp.facades.estoque.frigorifico.curral;

import br.com.orlandoburli.core.dao.estoque.frigorifico.curral.CurralDAO;
import br.com.orlandoburli.core.vo.estoque.frigorifico.curral.CurralVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class CurralCadastroFacade extends BaseCadastroFlexFacade<CurralVO, CurralDAO>{

	private static final long serialVersionUID = 1L;
	
	public CurralCadastroFacade() {
		addNewValidator(new NotEmptyValidator("DescricaoCurral", "Nome"));
	}
}
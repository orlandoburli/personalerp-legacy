package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.esplanada;

import br.com.orlandoburli.core.dao.cadastro.madeireira.EsplanadaDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.EsplanadaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class EsplanadaCadastroFacade extends BaseCadastroFlexFacade<EsplanadaVO, EsplanadaDAO> {
	
	private static final long serialVersionUID = 1L;
	
	public EsplanadaCadastroFacade() {
		addNewValidator(new NotEmptyValidator("NomeEsplanada", "Nome"));
	}
}
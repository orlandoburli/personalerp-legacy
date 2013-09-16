package br.com.orlandoburli.personalerp.facades.cadastros.madeireira.manejotora;

import br.com.orlandoburli.core.dao.cadastro.madeireira.ManejoToraDAO;
import br.com.orlandoburli.core.vo.cadastro.madeireira.ManejoToraVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ManejoToraCadastroFacade extends BaseCadastroFlexFacade<ManejoToraVO, ManejoToraDAO>{

	private static final long serialVersionUID = 1L;
	
	public ManejoToraCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("DescricaoManejo", "Descrição"));
	}
}
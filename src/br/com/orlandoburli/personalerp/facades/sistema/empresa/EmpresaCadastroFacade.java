package br.com.orlandoburli.personalerp.facades.sistema.empresa;

import br.com.orlandoburli.core.dao.sistema.EmpresaDAO;
import br.com.orlandoburli.core.vo.sistema.EmpresaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class EmpresaCadastroFacade extends BaseCadastroFlexFacade<EmpresaVO, EmpresaDAO>{

	private static final long serialVersionUID = 1L;

	public EmpresaCadastroFacade() {
		super();
		addNewValidator(new NotEmptyValidator("CodigoEmpresa"));
		addNewValidator(new NotEmptyValidator("RazaoSocialEmpresa"));
		addNewValidator(new NotEmptyValidator("FantasiaEmpresa"));
	}
}
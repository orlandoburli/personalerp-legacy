package br.com.orlandoburli.personalerp.facades.sistema.grupoempresa;

import br.com.orlandoburli.core.dao.sistema.GrupoEmpresaDAO;
import br.com.orlandoburli.core.vo.sistema.GrupoEmpresaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class GrupoEmpresaCadastroFacade extends BaseCadastroFlexFacade<GrupoEmpresaVO, GrupoEmpresaDAO> {

	private static final long serialVersionUID = 1L;

	public GrupoEmpresaCadastroFacade() {
		addNewValidator(new NotEmptyValidator("NomeGrupoEmpresa"));
	}
}
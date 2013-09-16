package br.com.orlandoburli.personalerp.facades.estoque.subgrupo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.SubGrupoDAO;
import br.com.orlandoburli.core.vo.estoque.SubGrupoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class SubGrupoCadastroFacade extends BaseCadastroFlexFacade<SubGrupoVO, SubGrupoDAO> {

	public SubGrupoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeSubGrupo", "Nome do Sub Grupo"));
	}

	private static final long	serialVersionUID	= 1L;

	@Override
	public Class<?> getDAOClass() {
		return SubGrupoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return SubGrupoVO.class;
	}

}

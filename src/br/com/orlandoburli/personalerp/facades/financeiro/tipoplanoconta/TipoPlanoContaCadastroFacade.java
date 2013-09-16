package br.com.orlandoburli.personalerp.facades.financeiro.tipoplanoconta;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.financeiro.TipoPlanoContaDAO;
import br.com.orlandoburli.core.vo.financeiro.TipoPlanoContaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoPlanoContaCadastroFacade extends BaseCadastroFlexFacade<TipoPlanoContaVO, TipoPlanoContaDAO> {

	private static final long serialVersionUID = 1L;

	public TipoPlanoContaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeTipoPlanoConta", "Nome"));
	}

	@Override
	public Class<?> getDAOClass() {
		return TipoPlanoContaDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return TipoPlanoContaVO.class;
	}

}

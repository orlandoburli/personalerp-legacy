package br.com.orlandoburli.personalerp.facades.sistema.parametro;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.sistema.ParametroDAO;
import br.com.orlandoburli.core.vo.sistema.ParametroVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class ParametroCadastroFacade extends BaseCadastroFlexFacade<ParametroVO, ParametroDAO> {

	private static final long serialVersionUID = 1L;
	
	public ParametroCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("ChaveParametro", "Chave"));
		addNewValidator(new NotEmptyValidator("DescricaoParametro", "Descrição"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return ParametroDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ParametroVO.class;
	}
}
package br.com.orlandoburli.personalerp.facades.rh.dependente;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.DependenteDAO;
import br.com.orlandoburli.core.vo.rh.DependenteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class DependenteCadastroFacade extends BaseCadastroFlexFacade<DependenteVO, DependenteDAO> {

	private static final long serialVersionUID = 1L;

	public DependenteCadastroFacade(HttpServletRequest request,	HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeDependente", "Nome"));
		addNewValidator(new NotEmptyValidator("DataInicialDependente", "Data de Início"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return DependenteDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return DependenteVO.class;
	}
}

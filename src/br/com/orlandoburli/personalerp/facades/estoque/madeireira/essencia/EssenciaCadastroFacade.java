package br.com.orlandoburli.personalerp.facades.estoque.madeireira.essencia;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.madeireira.EssenciaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.EssenciaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class EssenciaCadastroFacade extends BaseCadastroFlexFacade<EssenciaVO, EssenciaDAO> {

	private static final long serialVersionUID = 1L;

	public EssenciaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeEssencia", "Nome"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return EssenciaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return EssenciaVO.class;
	}
}
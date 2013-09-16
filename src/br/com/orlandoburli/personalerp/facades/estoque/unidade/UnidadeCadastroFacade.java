package br.com.orlandoburli.personalerp.facades.estoque.unidade;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.UnidadeDAO;
import br.com.orlandoburli.core.vo.estoque.UnidadeVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class UnidadeCadastroFacade extends BaseCadastroFlexFacade<UnidadeVO, UnidadeDAO> {

	private static final long	serialVersionUID	= 1L;
	
	public UnidadeCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("SiglaUnidade", "Sigla"));
	}

	@Override
	public Class<?> getDAOClass() {
		return UnidadeDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return UnidadeVO.class;
	}
}
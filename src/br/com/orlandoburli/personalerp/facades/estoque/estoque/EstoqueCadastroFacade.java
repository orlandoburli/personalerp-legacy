package br.com.orlandoburli.personalerp.facades.estoque.estoque;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.EstoqueDAO;
import br.com.orlandoburli.core.vo.estoque.EstoqueVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class EstoqueCadastroFacade extends BaseCadastroFlexFacade<EstoqueVO, EstoqueDAO> {

	public EstoqueCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	private static final long serialVersionUID = 1L;

	@Override
	public Class<?> getDAOClass() {
		return EstoqueDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return EstoqueVO.class;
	}
}
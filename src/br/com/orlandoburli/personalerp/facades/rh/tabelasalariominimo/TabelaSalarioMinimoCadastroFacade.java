package br.com.orlandoburli.personalerp.facades.rh.tabelasalariominimo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.TabelaSalarioMinimoDAO;
import br.com.orlandoburli.core.vo.rh.TabelaSalarioMinimoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class TabelaSalarioMinimoCadastroFacade extends BaseCadastroFlexFacade<TabelaSalarioMinimoVO, TabelaSalarioMinimoDAO> {

	private static final long serialVersionUID = 1L;

	public TabelaSalarioMinimoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaSalarioMinimoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaSalarioMinimoVO.class;
	}
}

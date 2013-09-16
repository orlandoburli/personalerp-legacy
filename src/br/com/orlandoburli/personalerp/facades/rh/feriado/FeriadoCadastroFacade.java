package br.com.orlandoburli.personalerp.facades.rh.feriado;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.FeriadoDAO;
import br.com.orlandoburli.core.vo.rh.FeriadoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class FeriadoCadastroFacade extends BaseCadastroFlexFacade<FeriadoVO, FeriadoDAO> {

	private static final long serialVersionUID = 1L;

	public FeriadoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected Class<?> getDAOClass() {
		return FeriadoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return FeriadoVO.class;
	}
}
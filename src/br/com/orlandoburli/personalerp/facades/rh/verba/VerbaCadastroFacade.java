package br.com.orlandoburli.personalerp.facades.rh.verba;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.VerbaDAO;
import br.com.orlandoburli.core.vo.rh.VerbaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;

public class VerbaCadastroFacade extends BaseCadastroFlexFacade<VerbaVO, br.com.orlandoburli.core.dao.rh.VerbaDAO> {

	private static final long serialVersionUID = 1L;

	public VerbaCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected Class<?> getDAOClass() {
		return VerbaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return VerbaVO.class;
	}
}
package br.com.orlandoburli.personalerp.facades.rh.cbo;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.CboDAO;
import br.com.orlandoburli.core.vo.rh.CboVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class CboCadastroFacade extends BaseCadastroFlexFacade<CboVO, CboDAO> {

	private static final long serialVersionUID = 1L;

	public CboCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("CodigoCbo", "Código"));
		addNewValidator(new NotEmptyValidator("DescricaoCbo", "Descrição"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return CboDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return CboVO.class;
	}
}
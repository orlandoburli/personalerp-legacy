package br.com.orlandoburli.personalerp.facades.acesso.objeto.acaoobjeto;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.base.AcaoObjetoDAO;
import br.com.orlandoburli.core.vo.base.AcaoObjetoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class AcaoObjetoCadastroFacade extends BaseCadastroFlexFacade<AcaoObjetoVO, AcaoObjetoDAO>{

	private static final long serialVersionUID = 1L;
	
	public AcaoObjetoCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeAcaoObjeto", "Nome da Ação"));
	}

	@Override
	public Class<?> getDAOClass() {
		return AcaoObjetoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return AcaoObjetoVO.class;
	}
}
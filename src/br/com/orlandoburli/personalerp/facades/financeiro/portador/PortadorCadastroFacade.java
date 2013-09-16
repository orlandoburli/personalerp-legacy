package br.com.orlandoburli.personalerp.facades.financeiro.portador;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.financeiro.PortadorDAO;
import br.com.orlandoburli.core.vo.financeiro.PortadorVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class PortadorCadastroFacade extends BaseCadastroFlexFacade<PortadorVO, PortadorDAO> {

	private static final long serialVersionUID = 1L;

	public PortadorCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomePortador", "Nome"));
	}
}
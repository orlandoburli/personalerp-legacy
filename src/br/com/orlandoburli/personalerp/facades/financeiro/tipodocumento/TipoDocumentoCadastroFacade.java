package br.com.orlandoburli.personalerp.facades.financeiro.tipodocumento;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.financeiro.TipoDocumentoDAO;
import br.com.orlandoburli.core.vo.financeiro.TipoDocumentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoDocumentoCadastroFacade extends BaseCadastroFlexFacade<TipoDocumentoVO, TipoDocumentoDAO> {

	private static final long serialVersionUID = 1L;

	public TipoDocumentoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeTipoDocumento", "Nome"));
	}

	@Override
	public Class<?> getDAOClass() {
		return TipoDocumentoDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return TipoDocumentoVO.class;
	}
}

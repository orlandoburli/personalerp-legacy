package br.com.orlandoburli.personalerp.facades.estoque.fabricante;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.FabricanteDAO;
import br.com.orlandoburli.core.vo.estoque.FabricanteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class FabricanteCadastroFacade extends BaseCadastroFlexFacade<FabricanteVO, FabricanteDAO> {

	public FabricanteCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		this.addNewValidator(new NotEmptyValidator("NomeFabricante", "Nome do Fabricante"));
	}

	private static final long	serialVersionUID	= 1L;

	@Override
	public Class<?> getDAOClass() {
		return FabricanteDAO.class;
	}

	@Override
	public Class<?> getVOClass() {
		return FabricanteVO.class;
	}
}
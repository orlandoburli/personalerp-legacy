package br.com.orlandoburli.personalerp.facades.estoque.madeireira.tipobeneficiada;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.estoque.madeireira.romaneiotora.TipoMadeiraBeneficiadaDAO;
import br.com.orlandoburli.core.vo.estoque.madeireira.TipoMadeiraBeneficiadaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TipoMadeiraBeneficiadaCadastroFacade extends BaseCadastroFlexFacade<TipoMadeiraBeneficiadaVO, TipoMadeiraBeneficiadaDAO> {

	private static final long serialVersionUID = 1L;

	public TipoMadeiraBeneficiadaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("NomeTipoMadeiraBeneficiada", "Nome"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return TipoMadeiraBeneficiadaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TipoMadeiraBeneficiadaVO.class;
	}
}
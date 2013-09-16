package br.com.orlandoburli.personalerp.facades.rh.motivodesligamento;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.MotivoDesligamentoDAO;
import br.com.orlandoburli.core.vo.rh.MotivoDesligamentoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MotivoDesligamentoCadastroFacade extends BaseCadastroFlexFacade<MotivoDesligamentoVO, MotivoDesligamentoDAO> {

	private static final long serialVersionUID = 1L;

	public MotivoDesligamentoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DescricaoMotivoDesligamento", "Descrição"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotivoDesligamentoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotivoDesligamentoVO.class;
	}
}
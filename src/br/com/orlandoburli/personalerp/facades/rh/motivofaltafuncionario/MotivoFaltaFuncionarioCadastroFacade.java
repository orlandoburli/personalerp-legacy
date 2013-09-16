package br.com.orlandoburli.personalerp.facades.rh.motivofaltafuncionario;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.MotivoFaltaFuncionarioDAO;
import br.com.orlandoburli.core.vo.rh.MotivoFaltaFuncionarioVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class MotivoFaltaFuncionarioCadastroFacade extends BaseCadastroFlexFacade<MotivoFaltaFuncionarioVO, MotivoFaltaFuncionarioDAO> {

	private static final long serialVersionUID = 1L;

	public MotivoFaltaFuncionarioCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DescricaoMotivoFaltaFuncionario", "Descrição"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return MotivoFaltaFuncionarioDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return MotivoFaltaFuncionarioVO.class;
	}
}
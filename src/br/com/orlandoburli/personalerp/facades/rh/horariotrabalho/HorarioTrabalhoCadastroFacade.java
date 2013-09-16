package br.com.orlandoburli.personalerp.facades.rh.horariotrabalho;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.HorarioTrabalhoDAO;
import br.com.orlandoburli.core.vo.rh.HorarioTrabalhoVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class HorarioTrabalhoCadastroFacade extends BaseCadastroFlexFacade<HorarioTrabalhoVO, HorarioTrabalhoDAO> {

	private static final long serialVersionUID = 1L;

	public HorarioTrabalhoCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DescricaoHorarioTrabalho", "Descrição"));
		addNewValidator(new NotEmptyValidator("HoraInicio1HorarioTrabalho", "Hora de Inicial (1)"));
		addNewValidator(new NotEmptyValidator("HoraFim1HorarioTrabalho", "Hora Final (1)"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return HorarioTrabalhoDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return HorarioTrabalhoVO.class;
	}
}
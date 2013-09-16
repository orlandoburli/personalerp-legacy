package br.com.orlandoburli.personalerp.facades.rh.tabelairrfdeducaodependente;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.TabelaIrrfDeducaoDependenteDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfDeducaoDependenteVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;
import br.com.orlandoburli.core.web.framework.validators.RangeValidator;

public class TabelaIrrfDeducaoDependenteCadastroFacade extends BaseCadastroFlexFacade<TabelaIrrfDeducaoDependenteVO, TabelaIrrfDeducaoDependenteDAO> {

	private static final long serialVersionUID = 1L;

	public TabelaIrrfDeducaoDependenteCadastroFacade(
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataInicialVigenciaTabelaIrrf", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("ValorDeducaoTabelaIrrf", "Valor"));
		addNewValidator(new RangeValidator("ValorDeducaoTabelaIrrf", "Valor", 0.01, null));
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaIrrfDeducaoDependenteDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaIrrfDeducaoDependenteVO.class;
	}
}
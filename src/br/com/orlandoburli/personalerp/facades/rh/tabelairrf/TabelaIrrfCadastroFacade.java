package br.com.orlandoburli.personalerp.facades.rh.tabelairrf;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.TabelaIrrfDAO;
import br.com.orlandoburli.core.vo.rh.TabelaIrrfVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TabelaIrrfCadastroFacade extends BaseCadastroFlexFacade<TabelaIrrfVO, TabelaIrrfDAO> {

	private static final long serialVersionUID = 1L;

	public TabelaIrrfCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataInicialVigenciaTabelaIrrf", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("ValorInicialTabelaIrrf", "Valor Inicial"));
		addNewValidator(new NotEmptyValidator("ValorFinalTabelaIrrf", "Valor Final"));
		addNewValidator(new NotEmptyValidator("AliquotaTabelaIrrf", "Aliquota"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaIrrfDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaIrrfVO.class;
	}
}
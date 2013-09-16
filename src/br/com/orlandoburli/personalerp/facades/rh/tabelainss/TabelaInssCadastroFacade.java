package br.com.orlandoburli.personalerp.facades.rh.tabelainss;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.rh.TabelaInssDAO;
import br.com.orlandoburli.core.vo.rh.TabelaInssVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.validators.NotEmptyValidator;

public class TabelaInssCadastroFacade extends BaseCadastroFlexFacade<TabelaInssVO, TabelaInssDAO> {

	private static final long serialVersionUID = 1L;

	public TabelaInssCadastroFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
		addNewValidator(new NotEmptyValidator("DataInicialVigenciaTabelaInss", "Data Inicial"));
		addNewValidator(new NotEmptyValidator("ValorInicialTabelaInss", "Valor Inicial"));
		addNewValidator(new NotEmptyValidator("ValorFinalTabelaInss", "Valor Final"));
		addNewValidator(new NotEmptyValidator("AliquotaTabelaInss", "Aliquota"));
	}

	@Override
	protected Class<?> getDAOClass() {
		return TabelaInssDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return TabelaInssVO.class;
	}
}
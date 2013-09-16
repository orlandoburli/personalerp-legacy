package br.com.orlandoburli.personalerp.facades.financeiro.contabancaria;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.dao.financeiro.BancoDAO;
import br.com.orlandoburli.core.dao.financeiro.ContaBancariaDAO;
import br.com.orlandoburli.core.utils.Utils;
import br.com.orlandoburli.core.vo.financeiro.ContaBancariaVO;
import br.com.orlandoburli.core.web.framework.facades.BaseCadastroFlexFacade;
import br.com.orlandoburli.core.web.framework.filters.IgnoreMethodAuthentication;

public class ContaBancariaCadastroFacade extends BaseCadastroFlexFacade<ContaBancariaVO, ContaBancariaDAO> {

	private static final long serialVersionUID = 1L;
	private String CodigoBancoPesquisa;

	public ContaBancariaCadastroFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected Class<?> getDAOClass() {
		return ContaBancariaDAO.class;
	}

	@Override
	protected Class<?> getVOClass() {
		return ContaBancariaVO.class;
	}
	
	@IgnoreMethodAuthentication
	public void banco() {
		BancoDAO _dao = new BancoDAO();
		try {
			write(Utils.voToXml(_dao.get(getCodigoBancoPesquisa())));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setCodigoBancoPesquisa(String codigoBancoPesquisa) {
		CodigoBancoPesquisa = codigoBancoPesquisa;
	}

	public String getCodigoBancoPesquisa() {
		return CodigoBancoPesquisa;
	}
}
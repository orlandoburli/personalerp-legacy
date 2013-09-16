package br.com.orlandoburli.personalerp.facades.reports.cadastros;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class ListagemProdutosFacade extends BaseRelatorioFacade {

	private static final long serialVersionUID = 1L;

	public ListagemProdutosFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/listagemProdutos.jasper";
	}

}
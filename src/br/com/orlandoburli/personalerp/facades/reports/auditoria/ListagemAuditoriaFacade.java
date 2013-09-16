package br.com.orlandoburli.personalerp.facades.reports.auditoria;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class ListagemAuditoriaFacade extends BaseRelatorioFacade {

	private static final long	serialVersionUID	= 1L;
	
	private String meuFiltro;

	public ListagemAuditoriaFacade(HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			String methodName) {
		super(request, response, context, methodName);
	}
	
	@Override
	public void execute() {
		System.out.println("Filtro: " + meuFiltro);
		super.execute();
	}

	@Override
	protected String getJasperFileName() {
		return "reports/listagemAuditoria.jasper";
	}

	public void setMeuFiltro(String meuFiltro) {
		this.meuFiltro = meuFiltro;
	}

	public String getMeuFiltro() {
		return meuFiltro;
	}
}
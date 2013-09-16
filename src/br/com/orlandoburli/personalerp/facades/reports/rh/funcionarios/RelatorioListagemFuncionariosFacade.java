package br.com.orlandoburli.personalerp.facades.reports.rh.funcionarios;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.orlandoburli.core.web.framework.facades.BaseRelatorioFacade;

public class RelatorioListagemFuncionariosFacade extends BaseRelatorioFacade{

	private static final long serialVersionUID = 1L;
	private String Status;

	public RelatorioListagemFuncionariosFacade(HttpServletRequest request, HttpServletResponse response, ServletContext context, String methodName) {
		super(request, response, context, methodName);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void doParameter(Map parameters) {
		parameters.put("CodigoEmpresa", getEmpresasessao().getCodigoEmpresa());
		parameters.put("CodigoLoja", getLojasessao().getCodigoLoja());
		parameters.put("Status", getStatus());
		super.doParameter(parameters);
	}

	@Override
	protected String getJasperFileName() {
		return "reports/relatorioFuncionarios.jasper";
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getStatus() {
		return Status;
	}

}

